package com.lc.lcserve.controller;

import com.lc.lcserve.service.ProjectService;
import com.lc.lcserve.vo.R;
import com.lc.lcserve.vo.WelActScheduleVo;
import com.lc.lcserve.vo.WelProjectScheduleVo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 * @date 2023/4/22&22:43
 **/
@RestController
@RequestMapping("/project")
@CrossOrigin
public class ProjectController {

    @Resource
    private ProjectService projectService;

    // 管理员创建项目接口
    @PostMapping("/create")
    public R createProject(@RequestParam("file") MultipartFile file , @RequestParam(value = "name") String name) {
        return R.success(projectService.createProject(file , name));
    }

    // 获取项目信息
    @PostMapping("/all")
    public R getAllProject(){
        List<WelProjectScheduleVo> list = projectService.getAllProject();
        return R.success(list);
    }



}
