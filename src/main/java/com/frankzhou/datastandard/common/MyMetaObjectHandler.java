package com.frankzhou.datastandard.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author: this.FrankZhou
 * @date: 2012/12/27
 * @description: Mybatis-Plus公共字段填充
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    // 在创建时填充
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"createTime",LocalDateTime.class,LocalDateTime.now());
        this.strictInsertFill(metaObject,"updateTime",LocalDateTime.class,LocalDateTime.now());
        this.strictInsertFill(metaObject,"status",String.class,"NORMAL");
    }

    // 在其更新时填充
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject,"updateTime",LocalDateTime.class,LocalDateTime.now());
    }
}
