package com.lc.lcserve.service;

import com.lc.lcserve.vo.WelProjectScheduleVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @date 2023/4/22&22:44
 **/
public interface ProjectService {
    Object createProject(MultipartFile file , String name);

    List<WelProjectScheduleVo> getAllProject();

}
