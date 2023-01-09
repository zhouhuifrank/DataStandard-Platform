package com.frankzhou.datastandard.common;

import com.alibaba.druid.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StandardStatusEnum {

    COMPLETE_BEFORE("COMPLETE_BEFORE","待完善"),
    CONFIRM_BEFORE("CONFIRM_BEFORE","待确认"),
    PUBLISH("PUBLISH","发布"),
    REVISE("REVISE","修订"),
    INVALIDATE("INVALIDATE","失效");

    private final String status;
    private final String desc;

    public static String getDescByStatus(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }

        StandardStatusEnum[] statusEnums = StandardStatusEnum.values();
        for (int i=0;i<statusEnums.length;i++) {
            if (value.equals(statusEnums[i].getStatus())) {
                return statusEnums[i].getDesc();
            }
        }

        return null;
    }

    public static String getStatusByDesc(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }

        StandardStatusEnum[] statusEnums = StandardStatusEnum.values();
        for (int i=0;i< statusEnums.length;i++) {
            if (value.equals(statusEnums[i].getDesc())) {
                return statusEnums[i].getStatus();
            }
        }

        return null;
    }
}
