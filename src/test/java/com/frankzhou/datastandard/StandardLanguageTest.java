package com.frankzhou.datastandard;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.frankzhou.datastandard.common.StandardStatusEnum;
import com.frankzhou.datastandard.dto.StandardLanguageQueryDTO;
import com.frankzhou.datastandard.entity.StandardLanguageDO;
import com.frankzhou.datastandard.entity.StandardLanguageQueryDO;
import com.frankzhou.datastandard.mapper.StandardLanguageMapper;
import com.frankzhou.datastandard.service.StandardLanguageService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class StandardLanguageTest {

    @Resource
    private StandardLanguageMapper standardLanguageMapper;

    @Resource
    private StandardLanguageService standardLanguageService;

    @Test
    public void testSelectByPage() {
        StandardLanguageQueryDO queryDO = new StandardLanguageQueryDO();
        queryDO.setStartPage(1);
        queryDO.setLimit(2);
        queryDO.setStartRow((queryDO.getStartPage()-1)*queryDO.getLimit());
        List<StandardLanguageDO> result = standardLanguageMapper.selectByPage(queryDO);
        System.out.println(result);
        Integer totalCount = standardLanguageMapper.getPageCount(queryDO);
        System.out.println(totalCount);
    }

    @Test
    public void testSelectByCond() {
        StandardLanguageQueryDTO queryDTO = new StandardLanguageQueryDTO();
        queryDTO.setNameCn("收");
        StandardLanguageQueryDO queryDO = new StandardLanguageQueryDO();
        BeanUtil.copyProperties(queryDTO,queryDO);
        List<StandardLanguageDO> result = standardLanguageMapper.selectListByCond(queryDO);
        System.out.println(result);
    }

    @Test
    public void testFindAll() {
        LambdaQueryWrapper<StandardLanguageDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StandardLanguageDO::getStatus,"NORMAL");
        List<StandardLanguageDO> resList = standardLanguageMapper.selectList(wrapper);
        System.out.println(resList);
    }

    @Test
    public void testBatchInsert() {
        List<StandardLanguageDO> doList = new ArrayList<>();
        StandardLanguageDO targetDo = new StandardLanguageDO();
        targetDo.setSerialNo("W000003").setNameCn("率")
                .setNameEn("rate").setNameEnAbbr("rate")
                .setStandardStatus(StandardStatusEnum.PUBLISH.getStatus());

        doList.add(targetDo);
        standardLanguageMapper.batchInsert(doList);
    }

    @Test
    public void testBatchUpdate() {
        List<StandardLanguageDO> doList = new ArrayList<>();
        StandardLanguageDO do1 = new StandardLanguageDO();
        do1.setSerialNo("W000003").setNameEn("Rate").setStandardStatus(StandardStatusEnum.CONFIRM_BEFORE.getStatus());
        StandardLanguageDO do2 = new StandardLanguageDO();
        do2.setSerialNo("W000001").setNameEn("Revenue").setNameEnAbbr("Revenue");

        doList.add(do1);
        doList.add(do2);
        standardLanguageMapper.batchUpdate(doList);
    }

    @Test
    public void testBatchDelete() {
        String ids = "8,9";
        String[] idArray = ids.split(",");
        standardLanguageMapper.batchDelete(idArray);
    }

    @Test
    public void testBatchPublish() {
        String ids = "7,8,9";
        String[] idArray = ids.split(",");
        standardLanguageMapper.batchPublish(idArray);
    }

    @Test
    public void testGetMaxSerialNo() {
        Integer maxNo = standardLanguageMapper.getMaxSerialNo();
        System.out.println(maxNo);
    }

    @Test
    public void testConstructSerialNo() {
        Integer maxNo = standardLanguageMapper.getMaxSerialNo();
        System.out.println(maxNo);
        String serialNo = "W" + String.format("%06d",maxNo+1);
        System.out.println(serialNo);
    }

    @Test
    public void testSelectList() {
        LambdaQueryWrapper<StandardLanguageDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StandardLanguageDO::getStatus,"NORMAL");
        List<StandardLanguageDO> result = standardLanguageMapper.selectList(wrapper);
        result.stream().forEach(System.out::println);
    }
}
