package com.frankzhou.datastandard.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.frankzhou.datastandard.dto.StandardLanguageDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class StandardLanguageListener extends AnalysisEventListener<StandardLanguageDTO>  {
    private Boolean isComplete = Boolean.FALSE;

    private final Integer BATCH_SIZE = 100;
    private List<StandardLanguageDTO> cachedDataList = new ArrayList<>();

    public static String[] headerCn = new String[] {
            "分词编号",
            "分词中文名",
            "分词英文名",
            "分词简称",
            "分词业务描述"
    };

    private Map<Integer,String> checkHeadMap = new HashMap<>();

    @Override
    public void invoke(StandardLanguageDTO languageDTO, AnalysisContext analysisContext) {
        this.cachedDataList.add(languageDTO);
    }

    @Override
    public void invokeHeadMap(Map<Integer,String> headMap, AnalysisContext analysisContext) {
        for (int i=0;i<headerCn.length;i++) {
            if (!headMap.get(i).equalsIgnoreCase(headerCn[i])) {
                checkHeadMap.put(i,headMap.get(i)+"and"+headerCn[i]);
            }
        }
    }

    // 当业务不复杂的时候，可以在这里编写业务逻辑
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        isComplete = Boolean.TRUE;
        log.info("excel解析完成");
    }

    public boolean isComplete() {
        return isComplete == Boolean.TRUE;
    }

    public List<StandardLanguageDTO> getDataList() {
        return this.cachedDataList;
    }

    public Map<Integer,String> getHeadMap() {
        return this.checkHeadMap;
    }
}
