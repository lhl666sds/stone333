package com.lc.lcserve.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lc.lcserve.entity.BannerImg;
import com.lc.lcserve.exception.ServiceException;
import com.lc.lcserve.service.BannerImgService;
import com.lc.lcserve.tool.ImgUtil;
import com.lc.lcserve.vo.R;
import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shade.okhttp3.internal.http2.ErrorCode;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/image")
public class ImgController {

    // obs原区域地址
    private static String endPoint = "https://obs.cn-north-1.myhuaweicloud.com";

    // access key
    private static String ak = "RVCB8XY3JPFNRHCTP85P";

    // secret key
    private static String sk = "TWzFsBq0KZkkp4yivbimvNaRg8681ZgBrQeq9uHF";

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");


    @Autowired
    BannerImgService bannerImgService;

    @RequestMapping("/list")
    public R getAllImg() {
        R r = bannerImgService.getAllImg();
        return r;
    }
    /**
     * 新建图片
     *
     * @param files
     * @return
     */
    @PostMapping("/addImg")
    public R newImageUrl (@RequestBody MultipartFile[] files, BannerImg bannerImg) {
        if(files == null) {
            throw new ServiceException("request error");
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            String data = format.format(new Date());
            String fullName = data + "-banner-" + System.currentTimeMillis() + ".png";
            // ObsClient是线程安全的，可在并发场景下使用
            ObsClient obsClient = null;
            try
            {
                // 创建ObsClient实例
                obsClient = new ObsClient(ak, sk, endPoint);
                // 调用接口进行操作，例如上传对象
                obsClient.putObject("huawei-obs-3651", "sbb_project/banner/" + fullName , new ByteArrayInputStream(files[i].getBytes()));  // localfile为待上传的本地文件路径，需要指定到具体的文件名
                //System.out.println(response);
                // 插入图片
                list.add("https://huawei-obs-3651.obs.cn-north-1.myhuaweicloud.com/sbb_project/banner/"+  fullName );
            }
            catch (ObsException | IOException e)
            {
                return R.error();
            }finally{
                // 关闭ObsClient实例，如果是全局ObsClient实例，可以不在每个方法调用完成后关闭
                // ObsClient在调用ObsClient.close方法关闭后不能再次使用
                if(obsClient != null){
                    try
                    {
                        obsClient.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        for (int i = 0; i < list.size(); i++){
            bannerImg.setImgUrl(list.get(i));
            bannerImg.setBid(i);
            bannerImg.setImgType(1);
            bannerImgService.save(bannerImg);
            return R.success(bannerImg);
        }
        return R.error();
    }

}
