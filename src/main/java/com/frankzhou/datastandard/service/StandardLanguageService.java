package com.frankzhou.datastandard.service;

import com.frankzhou.datastandard.common.PageResultDTO;
import com.frankzhou.datastandard.common.ResultDTO;
import com.frankzhou.datastandard.dto.StandardLanguageDTO;
import com.frankzhou.datastandard.dto.StandardLanguageQueryDTO;
import com.frankzhou.datastandard.vo.StandardLanguageVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: this.FrankZhou
 * @date: 2022/12/28
 * @description: 标准分词业务处理
 */
public interface StandardLanguageService {

    ResultDTO<Boolean> updateById(StandardLanguageDTO languageDTO);

    ResultDTO<Boolean> deleteById(Integer id);

    ResultDTO<Boolean> insertOne(StandardLanguageDTO languageDTO);

    ResultDTO<Boolean> batchDelete(String ids);

    ResultDTO<Boolean> batchPublish(String ids);

    ResultDTO<StandardLanguageVO> getById(Integer id);

    PageResultDTO<List<StandardLanguageVO>> findPageListByCond(StandardLanguageQueryDTO queryDTO);

    void standardLanguageDownload(StandardLanguageQueryDTO queryDTO,HttpServletResponse response);

    ResultDTO<Boolean> standardLanguageUpload(MultipartFile dataFile);

}
