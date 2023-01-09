package com.frankzhou.datastandard.controller;

import com.alibaba.druid.util.StringUtils;
import com.frankzhou.datastandard.common.PageResultDTO;
import com.frankzhou.datastandard.common.ResultCodeConstant;
import com.frankzhou.datastandard.common.ResultDTO;
import com.frankzhou.datastandard.dto.StandardLanguageDTO;
import com.frankzhou.datastandard.dto.StandardLanguageQueryDTO;
import com.frankzhou.datastandard.service.StandardLanguageService;
import com.frankzhou.datastandard.vo.StandardLanguageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.List;

/**
 * @author: this.FrankZhou
 * @date: 2022/12/28
 * @description: 标准分词前端控制器
 * 标准分词只有三种状态: completed_before待完善 publish发布 invalidated失效状态
 */
@Api(tags = {"标准分词管理"})
@RestController
@RequestMapping("/standardLanguage")
public class StandardLanguageController {

    @Resource
    private StandardLanguageService standardLanguageService;

    @ApiOperation(value = "标准分词分页查询")
    @GetMapping("/queryStandardLanguageByPage")
    public PageResultDTO<List<StandardLanguageVO>> queryStandardLanguageByPage(@RequestBody StandardLanguageQueryDTO queryDTO) {
        if (Objects.isNull(queryDTO)) {
            return PageResultDTO.getErrorPageResult(ResultCodeConstant.REQUEST_PARAM_ERROR);
        }
        if (StringUtils.isEmpty(queryDTO.getStatus())) {
            queryDTO.setStatus("NORMAL");
        }

        return standardLanguageService.findPageListByCond(queryDTO);
    }

    @ApiOperation(value = "新增标准分词")
    @PostMapping("/addStandardLanguage")
    public ResultDTO<Boolean> addStandardLanguage(@RequestBody StandardLanguageDTO languageDTO) {
        if (Objects.isNull(languageDTO)) {
            return ResultDTO.getErrorResult(ResultCodeConstant.REQUEST_PARAM_ERROR);
        }

        return standardLanguageService.insertOne(languageDTO);
    }

    @ApiOperation(value = "根据Id删除标准分词")
    @PostMapping("/deleteStandardLanguageById/{id}")
    public ResultDTO<Boolean> deleteStandardLanguageById(@PathVariable Integer id) {
        if (Objects.isNull(id)) {
            return ResultDTO.getErrorResult(ResultCodeConstant.REQUEST_PARAM_ERROR);
        }

        return standardLanguageService.deleteById(id);
    }

    @ApiOperation(value = "批量删除标准分词" )
    @PostMapping("/batchDeleteStandardLanguage")
    public ResultDTO<Boolean> batchDeleteStandardLanguage(String ids) {
        if (StringUtils.isEmpty(ids)) {
            return ResultDTO.getErrorResult(ResultCodeConstant.REQUEST_PARAM_ERROR);
        }

        return standardLanguageService.batchDelete(ids);
    }

    @ApiOperation(value = "根据Id查询标准分词")
    @GetMapping("/getStandardLanguageById/{id}")
    public ResultDTO<StandardLanguageVO> getStandardLanguageById(@PathVariable Integer id) {
        if (Objects.isNull(id)) {
            return ResultDTO.getErrorResult(ResultCodeConstant.REQUEST_PARAM_ERROR);
        }

        return standardLanguageService.getById(id);
    }

    @ApiOperation(value = "更新标准分词")
    @PostMapping("/updateStandardLanguage")
    public ResultDTO<Boolean> updateStandardLanguage(@RequestBody StandardLanguageDTO languageDTO) {
        if (Objects.isNull(languageDTO)) {
            return ResultDTO.getErrorResult(ResultCodeConstant.REQUEST_PARAM_ERROR);
        }

        return standardLanguageService.updateById(languageDTO);
    }

    @ApiOperation(value = "通过excel批量下载标准分词数据")
    @GetMapping("/downloadStandardLanguage")
    public  void standardLanguageDownload(@RequestBody StandardLanguageQueryDTO standardLanguageQueryDTO,HttpServletResponse response) {
        if (StringUtils.isEmpty(standardLanguageQueryDTO.getStatus())) {
            standardLanguageQueryDTO.setStatus("NORMAL");
        }

        standardLanguageService.standardLanguageDownload(standardLanguageQueryDTO,response);
    }

    @ApiOperation(value = "通过excel批量上传标准分词数据")
    @PostMapping("/uploadStandardLanguage")
    public ResultDTO<Boolean> StandardLanguageUpload(MultipartFile dataFile) {
        if (Objects.isNull(dataFile) || dataFile.isEmpty()) {
            return ResultDTO.getErrorResult(ResultCodeConstant.REQUEST_PARAM_ERROR);
        }

        return standardLanguageService.standardLanguageUpload(dataFile);
    }

    @ApiOperation(value = "标准分词批量发布")
    @PostMapping("/batchPublishStandardLanguage")
    public ResultDTO<Boolean> batchPublishStandardLanguage(String ids) {
        if (StringUtils.isEmpty(ids)) {
            return ResultDTO.getErrorResult(ResultCodeConstant.REQUEST_PARAM_ERROR);
        }

        return standardLanguageService.batchPublish(ids);
    }
}
