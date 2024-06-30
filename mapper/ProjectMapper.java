package com.lc.lcserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lc.lcserve.entity.Project;
import org.apache.ibatis.annotations.Param;

/**
 * @date 2023/4/22&22:56
 **/
public interface ProjectMapper extends BaseMapper<Project> {
    Integer insertRetunId(@Param("name") String name);
}
