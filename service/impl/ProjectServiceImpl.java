package com.lc.lcserve.service.impl;

import com.lc.lcserve.entity.Project;
import com.lc.lcserve.mapper.ProjectMapper;
import com.lc.lcserve.service.ProjectService;
import com.lc.lcserve.tool.ImgUtil;
import com.lc.lcserve.vo.R;
import com.lc.lcserve.vo.WelProjectScheduleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @date 2023/4/22&22:44
 **/


@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private ImgUtil imgUtil;

    /**
     * 新增项目
     * @return
     */
    @Override
    public Object createProject(MultipartFile file , String name) {
        // 转化baseImg 传入oss
        Project project = new Project();
        project.setName(name);
        Integer id = projectMapper.insertRetunId(name);
        project.setPimg(imgUtil.pushImgToOss(id.toString() , "project_path" , file));
        projectMapper.updateById(project);
        return R.success();
    }

    /**
     * 获取全部项目逻辑
     * @return
     */
    @Override
    public List<WelProjectScheduleVo> getAllProject() {
        List<Project> projects = projectMapper.selectList(null);
        List<WelProjectScheduleVo> souce = new ArrayList<>();
        projects.forEach(
                item -> {
                    WelProjectScheduleVo welProjectScheduleVo = new WelProjectScheduleVo();
                    BeanUtils.copyProperties(item,welProjectScheduleVo);
                    souce.add(welProjectScheduleVo);
                }
        );
        return souce;
    }


}
