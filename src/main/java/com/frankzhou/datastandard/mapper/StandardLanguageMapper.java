package com.frankzhou.datastandard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.frankzhou.datastandard.entity.StandardLanguageDO;
import com.frankzhou.datastandard.entity.StandardLanguageQueryDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: this.FrankZhou
 * @date: 2022/12/28
 * @description: 标准分词数据库操作
 */
@Mapper
public interface StandardLanguageMapper extends BaseMapper<StandardLanguageDO> {

    List<StandardLanguageDO> selectByPage(StandardLanguageQueryDO queryDO);

    Integer getPageCount(StandardLanguageQueryDO queryDO);

    List<StandardLanguageDO> selectListByCond(StandardLanguageQueryDO queryDO);

    Integer batchInsert(@Param("list") List<StandardLanguageDO> doList);

    Integer batchUpdate(@Param("list") List<StandardLanguageDO> doList);

    Integer batchDelete(@Param("ids") String[] idArray);

    Integer batchPublish(@Param("ids") String[] idArray);

    Integer getMaxSerialNo();
}
