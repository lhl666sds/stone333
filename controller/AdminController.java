package com.lc.lcserve.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.lc.lcserve.entity.*;
import com.lc.lcserve.mapper.TransOrderMapper;
import com.lc.lcserve.service.AdminService;
import com.lc.lcserve.vo.*;
import com.lc.lcserve.vo.params.LogParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    AdminService adminService;


    @Resource
    private TransOrderMapper orderMapper;


    /**
     * 用户添加
     * @param sysUser
     * @return
     */
    @PostMapping("/addUser")
    public CommonResult register(@RequestBody SysUser sysUser) {
        adminService.register(sysUser);
        return CommonResult.success();
    }

    /**
     * 用户查询
     */
    @PostMapping("/searchUser")
    public CommonResult searchUser() {
        return CommonResult.success(adminService.searchUser());
    }

    /**
     * 用户信息编辑
     */
    @PostMapping("/user/change")
    public CommonResult changeUserInfo(@RequestBody SysUser sysUser) {
        return CommonResult.success(adminService.changeUserInfo(sysUser));
    }

    /**
     * 环保图鉴模块查询
     */
    @GetMapping("/search/atlas")
    public Result searchAtlasInfos() {
        return adminService.searchAtlasInfos();
    }

    /**
     * 环保图鉴更新基本信息
     */
    @PostMapping("/change/atlas")
    public Result changeAtlasInfo(@RequestBody Atlas atlas) {
        return adminService.changeAtlasInfo(atlas);
    }

    /**
     * 查询商品模块信息
     */
    @GetMapping("/search/shop")
    public Result searchGoodInfo() {
        return adminService.searchGoodInfo();
    }

    /**
     * 商品模块基本信息修改
     */
    @PostMapping("/change/shop")
    public Result changeGoodInfos(@RequestBody TransGoods transGoods) {
        return adminService.changeGoodInfos(transGoods);
    }

    /**
     * 商品模块基本信息修改
     */
    @GetMapping("/delete/shop")
    public Result deleteGoodsById(@RequestParam("gid") String gid) {
        return adminService.deleteGoodsById(gid);
    }


    /**
     * 交易图标展示
     */
    @PostMapping("/search/order")
    public Result seachOrderInfo() {
        return adminService.seachOrderInfo();
    }

    /**
     * 日志查询
     */
    @PostMapping("/loginLog")
    public Result<Page<UserLogVo>> searchUserLoginLog(@RequestBody LogParams logParams ) throws ParseException {
        return adminService.getUserLoginLogByLimit(logParams);
    }

    /**
     * 商品图表数据
     */
    @PostMapping("/getChartsInfo")
    public Result getChartsInfo() {
        return adminService.getChartsInfo();
    }

    /**
     * excel导出
     */
    @GetMapping("/export")
    public void exportToExecl( HttpServletResponse response) throws IOException {
        List<ChartVo> transOrders = orderMapper.selectChars();
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码
        String fileName = URLEncoder.encode("商品售卖情况-" + DateUtil.format(LocalDateTime.now(), "MM月dd日HH时mm分"), "UTF-8").replaceAll("\\+", "%20");
        String sheetName = "售卖情况";
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel
                .write(response.getOutputStream(), ChartVo.class)
                .head(systemReportExcelHead())
                .sheet(sheetName)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .doWrite(transOrders);
    }

    private List<List<String>> systemReportExcelHead() {
        List<List<String>> headList = new ArrayList<>();
        headList.add(new ArrayList(){{
            add("商品名称");
        }});
        headList.add(new ArrayList(){{
            add("售卖数量");
        }});
        headList.add(new ArrayList(){{
            add("售卖价格");
        }});
        headList.add(new ArrayList(){{
            add("交易人数");
        }});
        headList.add(new ArrayList(){{
            add("成交总价");
        }});
        headList.add(new ArrayList(){{
            add("最近下单时间");
        }});
        return headList;
    }




}
