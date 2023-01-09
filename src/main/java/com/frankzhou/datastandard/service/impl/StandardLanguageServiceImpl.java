package com.frankzhou.datastandard.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.frankzhou.datastandard.common.PageResultDTO;
import com.frankzhou.datastandard.common.ResultCodeConstant;
import com.frankzhou.datastandard.common.ResultCodeDTO;
import com.frankzhou.datastandard.common.ResultDTO;
import com.frankzhou.datastandard.dto.StandardLanguageDTO;
import com.frankzhou.datastandard.dto.StandardLanguageQueryDTO;
import com.frankzhou.datastandard.entity.StandardLanguageDO;
import com.frankzhou.datastandard.entity.StandardLanguageQueryDO;
import com.frankzhou.datastandard.excel.listener.StandardLanguageListener;
import com.frankzhou.datastandard.excel.pipe.StandardLanguagePipeDTO;
import com.frankzhou.datastandard.mapper.StandardLanguageMapper;
import com.frankzhou.datastandard.redis.RedisCache;
import com.frankzhou.datastandard.redis.SimpleRedisLock;
import com.frankzhou.datastandard.redis.StringRedisCache;
import com.frankzhou.datastandard.service.StandardLanguageService;
import com.frankzhou.datastandard.util.DateUtil;
import com.frankzhou.datastandard.util.ListUtil;
import com.frankzhou.datastandard.util.StringFormatUtil;
import com.frankzhou.datastandard.vo.StandardLanguageVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StandardLanguageServiceImpl implements StandardLanguageService {

    @Resource
    private StandardLanguageMapper standardLanguageMapper;

    // 引入redis
    @Resource
    private RedisCache redisCache;

    @Resource
    private StringRedisCache stringRedisCache;

    @Resource
    private SimpleRedisLock redisLock;

    @Override
    public ResultDTO<Boolean> updateById(StandardLanguageDTO languageDTO) {
        StandardLanguageDO targetDo = new StandardLanguageDO();
        BeanUtil.copyProperties(languageDTO,targetDo);
        int updateCount = standardLanguageMapper.updateById(targetDo);

        if (updateCount < 1) {
            return ResultDTO.getErrorResult(ResultCodeConstant.DB_UPDATE_COUNT_ERROR);
        }

        return ResultDTO.getSuccessResult(Boolean.TRUE);
    }

    @Override
    public ResultDTO<Boolean> deleteById(Integer id) {
        StandardLanguageDO oneById = standardLanguageMapper.selectById(id);
        if (Objects.isNull(oneById)) {
            return ResultDTO.getErrorResult(ResultCodeConstant.DB_QUERY_NO_DATA);
        }

        Integer deleteCount = standardLanguageMapper.deleteById(id);
        if (deleteCount < 1) {
            return ResultDTO.getErrorResult(ResultCodeConstant.DB_DELETE_COUNT_ERROR);
        }

        return ResultDTO.getSuccessResult(Boolean.TRUE);
    }

    @Override
    public ResultDTO<Boolean> insertOne(StandardLanguageDTO languageDTO) {
        String nameEn = languageDTO.getNameEn();
        String nameEnAbbr = languageDTO.getNameEnAbbr();
        if (StringUtils.isEmpty(nameEnAbbr) && !Pattern.matches("^[A-Za-z0-9_]+$",nameEnAbbr)) {
            return ResultDTO.getResult(new ResultCodeDTO(12345,"english name is invalid",
                    "英文简称不符合规范"));
        }

        // 强制首字母大写
        if (nameEn.charAt(0) >= 'a' && nameEn.charAt(0) <= 'z') {
            String formatString = StringFormatUtil.toUpperFirstChar(nameEn);
            languageDTO.setNameEn(formatString);
        }

        if (nameEnAbbr.charAt(0) >= 'a' && nameEnAbbr.charAt(0) <= 'z') {
            String formatString = StringFormatUtil.toLowerFirstChar(nameEnAbbr);
            languageDTO.setNameEnAbbr(formatString);
        }

        // 自动获取分词编号
        languageDTO.setSerialNo(getMaxSerialNo());

        // todo 从Redis中获取操作人员信息

        // 去重校验 标准分词中文名不能重复
        StandardLanguageQueryDO queryDO = new StandardLanguageQueryDO();
        queryDO.setNameCn(languageDTO.getNameCn());
        queryDO.setStatus("NORMAL");
        List<StandardLanguageDO> duplicatedData = standardLanguageMapper.selectListByCond(queryDO);
        if (duplicatedData.size() >= 1) {
            return ResultDTO.getErrorResult(ResultCodeConstant.STANDARD_LANGUAGE_DUPLICATED);
        }

        StandardLanguageDO targetDo = new StandardLanguageDO();
        BeanUtil.copyProperties(languageDTO,targetDo);
        int insertCount = standardLanguageMapper.insert(targetDo);

        if (insertCount < 1) {
            return ResultDTO.getErrorResult(ResultCodeConstant.DB_INSERT_COUNT_ERROR);
        }

        return ResultDTO.getSuccessResult(Boolean.TRUE);
    }

    @Override
    public ResultDTO<Boolean> batchDelete(String ids) {
        String[] idArray = ids.split(",");
        Integer deleteCount = standardLanguageMapper.batchDelete(idArray);

        if (deleteCount != idArray.length) {
            return ResultDTO.getErrorResult(ResultCodeConstant.DB_DELETE_COUNT_ERROR);
        }

        return ResultDTO.getSuccessResult(Boolean.TRUE);
    }

    @Override
    public ResultDTO<Boolean> batchPublish(String ids) {
        String[] idArray = ids.split(",");
        Integer updateCount = standardLanguageMapper.batchPublish(idArray);

        if (updateCount != idArray.length) {
            return ResultDTO.getErrorResult(ResultCodeConstant.DB_UPDATE_COUNT_ERROR);
        }

        return ResultDTO.getSuccessResult(Boolean.TRUE);
    }

    @Override
    public ResultDTO<StandardLanguageVO> getById(Integer id) {
        StandardLanguageDO oneById = standardLanguageMapper.selectById(id);
        if (Objects.isNull(oneById)) {
            return ResultDTO.getErrorResult(ResultCodeConstant.DB_QUERY_NO_DATA);
        }

        StandardLanguageVO resData = new StandardLanguageVO();
        BeanUtil.copyProperties(oneById,resData);

        return ResultDTO.getSuccessResult(resData);
    }

    @Override
    public PageResultDTO<List<StandardLanguageVO>> findPageListByCond(StandardLanguageQueryDTO queryDTO) {
        if (Objects.isNull(queryDTO.getStartPage())) {
            queryDTO.setStartPage(1);
        }

        if (Objects.isNull(queryDTO.getLimit())) {
            queryDTO.setLimit(10);
        }

        if (StringUtils.isEmpty(queryDTO.getOrderBy())) {
            queryDTO.setOrderBy("create_time");
            if (StringUtils.isEmpty(queryDTO.getSort())) {
                queryDTO.setSort("ASC");
            }
        }

        StandardLanguageQueryDO queryDO = new StandardLanguageQueryDO();
        BeanUtil.copyProperties(queryDTO,queryDO);
        queryDO.setStartRow((queryDO.getStartPage()-1)*queryDO.getLimit());

        Integer totalCount = standardLanguageMapper.getPageCount(queryDO);
        if (totalCount < 1) {
            return PageResultDTO.getErrorPageResult(ResultCodeConstant.DB_QUERY_NO_DATA);
        }

        List<StandardLanguageDO> doList = standardLanguageMapper.selectByPage(queryDO);
        List<StandardLanguageVO> resList = ListUtil.listConvert(doList,StandardLanguageVO.class);

        return PageResultDTO.getSuccessPageResult(totalCount,resList);
    }

    @Override
    public void standardLanguageDownload(StandardLanguageQueryDTO queryDTO,HttpServletResponse response) {
        StandardLanguageQueryDO queryDo = new StandardLanguageQueryDO();
        BeanUtil.copyProperties(queryDTO,queryDo);
        List<StandardLanguageDO> list = standardLanguageMapper.selectListByCond(queryDo);
        List<StandardLanguagePipeDTO> resData = Lists.newArrayList();
        for (StandardLanguageDO targetDo : list) {
            StandardLanguagePipeDTO pipeDTO = new StandardLanguagePipeDTO();
            BeanUtil.copyProperties(targetDo,pipeDTO);
            resData.add(pipeDTO);
        }

        try {
            // 获取表头
            List<List<String>> headList = Lists.newArrayList();
            for (String str : StandardLanguageListener.headerCn) {
                headList.add(ListUtil.singletonList(str));
            }

            try {
                String fileName = URLEncoder.encode("标准分词表_"+ DateUtil.getCurrentDate(), StandardCharsets.UTF_8.name());
                response.setHeader("Content-Type","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setHeader("Content-Disposition","attachment");
                response.setHeader("fileName",fileName+".xlsx");
                response.setHeader("Access-Control-Expose-Headers","FileName");
            } catch (UnsupportedEncodingException e) {
                log.error("method exportExcel occur UnsupportedEncodingException");
            }

            // 写Excel
            OutputStream out = response.getOutputStream();
            ExcelWriter excelWriter = EasyExcel.write(out).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("标准分词表").head(headList).build();
            excelWriter.write(resData,writeSheet);
            excelWriter.finish();
            out.flush();;
            out.close();
        } catch (IOException e) {
            log.error("method exportExcel occur IOException");
        }
    }

    @Override
    public ResultDTO<Boolean> standardLanguageUpload(MultipartFile dataFile) {
        try {
            // 读取Excel表格
            InputStream inputStream = dataFile.getInputStream();
            ExcelReader excelReader = EasyExcel.read(inputStream).build();
            StandardLanguageListener listener = new StandardLanguageListener();
            ReadSheet readSheet = EasyExcel.readSheet(0).
                    head(StandardLanguagePipeDTO.class).
                    registerReadListener(listener).build();
            excelReader.read(readSheet);
            // 校验表头
            Map<Integer,String> headCheckMap = new HashMap<>();
            headCheckMap = listener.getHeadMap();
            Set<Integer> keys = headCheckMap.keySet();
            if (keys.size() >= 1) {
                return ResultDTO.getResult(new ResultCodeDTO(17856,"import excel head error",
                        "excel表头不匹配"));
            }

            List<StandardLanguageDTO> dataList = listener.getDataList();
            // todo 进行业务处理
            // 判断用户输入的标准编号是否合法(正则)

            // 校验用户权限

            // todo 获取操作人员信息


            // 去重校验
            List<StandardLanguageDO> insertList = Lists.newArrayList();
            List<StandardLanguageDO> updateList = Lists.newArrayList();
            LambdaQueryWrapper<StandardLanguageDO> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(StandardLanguageDO::getStatus,"NORMAL");
            List<StandardLanguageDO> dbList = standardLanguageMapper.selectList(wrapper);
            Map<String,StandardLanguageDO> dbMap = dbList.stream()
                    .collect(Collectors.toMap(StandardLanguageDO::getNameCn, e -> e));

            for (StandardLanguageDTO targetDto : dataList) {
                String nameEn = targetDto.getNameEn();
                String nameEnAbbr = targetDto.getNameEnAbbr();
                // 强制首字母大写
                if (nameEn.charAt(0) >= 'a' && nameEn.charAt(0) <= 'z') {
                    String formatString = StringFormatUtil.toUpperFirstChar(nameEn);
                    targetDto.setNameEn(formatString);
                }

                if (nameEnAbbr.charAt(0) >= 'a' && nameEnAbbr.charAt(0) <= 'z') {
                    String formatString = StringFormatUtil.toLowerFirstChar(nameEnAbbr);
                    targetDto.setNameEnAbbr(formatString);
                }

                StandardLanguageDO data = new StandardLanguageDO();
                BeanUtil.copyProperties(targetDto,data);

                if (dbMap.containsKey(data.getNameCn())) {
                    updateList.add(data);
                } else {
                    insertList.add(data);
                }
            }

            Integer insertCount = 0;
            Integer updateCount = 0;
            if (insertList.size() > 0) {
                insertCount += batchInsert(insertList);
            }
            if (updateList.size() > 0) {
                updateCount += standardLanguageMapper.batchUpdate(updateList);
            }

            if (insertCount != insertList.size()) {
                return ResultDTO.getErrorResult(ResultCodeConstant.DB_INSERT_COUNT_ERROR);
            }
            if (updateCount != updateList.size()) {
                return ResultDTO.getErrorResult(ResultCodeConstant.DB_UPDATE_COUNT_ERROR);
            }
        } catch (IOException e) {
            log.error("method excelImport occur IOException");
        }

        return ResultDTO.getSuccessResult(Boolean.TRUE);
    }

    private Integer batchInsert(List<StandardLanguageDO> doList) {
        final Integer BATCH_SIZE = 100;
        Integer insertTime = doList.size()/BATCH_SIZE;
        Integer insertCount = 0;
        List<StandardLanguageDO> subList = new ArrayList<>();
        for (int i=0;i<insertTime;i++) {
            subList = doList.subList(i*BATCH_SIZE,(i+1)*BATCH_SIZE);
            insertCount += standardLanguageMapper.batchInsert(subList);
        }
        subList = doList.subList(insertTime*BATCH_SIZE,doList.size());
        insertCount += standardLanguageMapper.batchInsert(subList);

        return insertCount;
    }

    /**
     * 获取最新的编号：编号规则W+6位数字
     */
    private String getMaxSerialNo() {
        Integer maxSerialNo = standardLanguageMapper.getMaxSerialNo();
        String serialNo = "W" + String.format("%06d",maxSerialNo+1);
        return serialNo;
    }
}
