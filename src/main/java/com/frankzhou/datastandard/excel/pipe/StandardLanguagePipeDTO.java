package com.frankzhou.datastandard.excel.pipe;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ExcelIgnoreUnannotated
public class StandardLanguagePipeDTO {

    @ExcelIgnore
    private int id;

    @ExcelProperty(index = 0)
    private String serialNo;

    @ExcelProperty(index = 1)
    private String nameCn;

    @ExcelProperty(index = 2)
    private String nameEn;

    @ExcelProperty(index = 3)
    private String nameEnAbbr;

    @ExcelProperty(index = 4)
    private String description;

    @ExcelIgnore
    private LocalDateTime createTime;

    @ExcelIgnore
    private LocalDateTime updateTime;

    @ExcelIgnore
    private LocalDateTime status;
}
