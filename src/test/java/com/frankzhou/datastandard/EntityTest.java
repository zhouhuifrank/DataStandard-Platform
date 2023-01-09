package com.frankzhou.datastandard;

import cn.hutool.core.bean.BeanUtil;
import com.frankzhou.datastandard.dto.StandardLanguageDTO;
import com.frankzhou.datastandard.entity.StandardLanguageDO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EntityTest {


    @Test
    public void testStandardLanguage() {
        StandardLanguageDTO dto = new StandardLanguageDTO();
        dto.setSerialNo("1312321");
        dto.setNameCn("袋子");
        dto.setNameEn("bag");
        dto.setDescription("啥子转用");
        dto.setStandardStatus("DELETED");
        System.out.println(dto);

        StandardLanguageDO targetDo = new StandardLanguageDO();
        BeanUtil.copyProperties(dto,targetDo);
        System.out.println(targetDo);
    }
}
