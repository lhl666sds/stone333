package com.lc.lcserve.controller;

import cn.hutool.core.map.MapUtil;
import com.lc.lcserve.controller.request.LoginRequest;
import com.lc.lcserve.entity.Admin;
import com.lc.lcserve.entity.SysUser;
import com.lc.lcserve.model.InterfaceType;
import com.lc.lcserve.log.SystemLog;
import com.lc.lcserve.service.AdminService;
import com.lc.lcserve.service.MakCountService;
import com.lc.lcserve.service.SysUserService;
import com.lc.lcserve.service.WelCountService;
import com.lc.lcserve.tool.ImgUtil;
import com.lc.lcserve.tool.JwtUtils;
import com.lc.lcserve.vo.CommonResult;
import com.lc.lcserve.vo.R;
import com.lc.lcserve.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/server")
public class SysUserController {

    @Autowired
    SysUserService sysuserService;

    @Autowired
    AdminService adminService;

    @Autowired
    MakCountService makCountService;

    @Autowired
    WelCountService welCountService;

    @Resource
    private ImgUtil imgUtil;


    //用户登录
    @PostMapping("/login")
    @SystemLog(description = "login" , type = InterfaceType.LOGIN)
//    public R loginUser(@RequestBody LoginRequest request) {
//        LoginDTO loginUser = sysuserService.loginUser(request);
//        return R.success(loginUser);
//    }
    public ResponseEntity<Result<Object>> login(@RequestBody LoginRequest loginRequest) {
        Object data = null;
        String token = null;
        // 将token放在响应体的header中返回给客户端
        HttpHeaders headers = new HttpHeaders();
        headers.set("Access-Control-Expose-Headers", "Authorization");
        //管理员登录
        Admin findAdmin = adminService.findByAdminname(loginRequest.getUname());
        if (findAdmin != null){
            Admin admin = adminService.login(loginRequest.getUname(), loginRequest.getPassword());
            data = admin;
            token = JwtUtils.genToken(admin.getAdmin(), admin.getPassword());
            headers.set("Authorization", "Bearer " + token);
            return new ResponseEntity<>(Result.success(data , "管理员登录成功"),headers , HttpStatus.OK);
        } else {
            //用户登录
            try {
                SysUser user = sysuserService.login(loginRequest.getUname(), loginRequest.getPassword());
                data = user;
                token = JwtUtils.genToken(user.getUname(), user.getPassword());
                headers.set("Authorization", "Bearer " + token);
            } catch (Exception e){
                return new ResponseEntity<>(Result.fail(-1,"用户登录失败" ),headers , HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(Result.success(data,"用户登录成功" ),headers , HttpStatus.OK);
    }

    //用户注册
    @PostMapping("/register")
    public CommonResult register(@RequestBody SysUser sysUser) {
        sysuserService.register(sysUser);
        return CommonResult.success();
    }

    // 删除单个用户
    @DeleteMapping("/{uid}")
    public R deleteUser(@PathVariable(value = "uid") Integer uid) {
        return R.success(sysuserService.removeById(uid));
    }

    // 删除多个用户
    @DeleteMapping("")
    public R deleteUserMore(@RequestBody List<Integer> ids) {
        return R.success(sysuserService.removeByIds(ids));
    }

    // 编辑用户
    @PutMapping("change")
    public R modifyUser(@RequestBody SysUser sysuser) {
        return R.success(sysuserService.updateById(sysuser));
    }

    // 根据用户编号查询个人信息
    @GetMapping("/info/{uid}")
    public R getUserInfo(@PathVariable(value = "uid") Integer uid) {
        if(uid == null || uid < 0){
            return R.error().message("用户编号错误");
        }
        return R.success(sysuserService.getUserInfoById(uid));
    }

    @PostMapping("/setimg/{uid}")
    public R setImg(@PathVariable("uid") Integer uid, @RequestBody MultipartFile file){
        //存入数据库，这里可以加if判断
        SysUser user = new SysUser();
        user.setUid(uid);
        user.setAvatar(imgUtil.pushImgToOss(uid.toString() , "userImg" , file));
        sysuserService.updateById(user);
        SysUser ret_user = sysuserService.getById(user.getUid());
        ret_user.setPassword("");
        return R.success(MapUtil.builder()
                .put("backUser",ret_user)
                .map());
    }

    @GetMapping("/project/{uid}")
    public R getProjectSchedule(@PathVariable("uid") Integer uid){
        return sysuserService.getProjectSchedule(uid);
    }

}
