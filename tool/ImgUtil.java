package com.lc.lcserve.tool;

import com.lc.lcserve.vo.R;
import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class ImgUtil {


    // obs原区域地址
    private static String endPoint = "https://obs.cn-north-1.myhuaweicloud.com";

    // access key
    private static String ak = "RVCB8XY3JPFNRHCTP85P";

    // secret key
    private static String sk = "TWzFsBq0KZkkp4yivbimvNaRg8681ZgBrQeq9uHF";

    //存放图片的绝对路径
    private final static String SAVE_IMAGE_PATH = "D:/LC/Carbon trading platform/src/assets/images/banner/";

    /**
     * 返回文件后缀
     * @param file
     * @return
     */
    public static String getImagePath(MultipartFile file) {
        String fileName = file.getOriginalFilename();//获取原文件名
        int index = fileName.indexOf(".");
        return fileName.substring(index);
    }

    /**
     * 保存图片
     * @param mfile
     * @param file
     * @return
     */
    public static boolean saveImage(MultipartFile mfile , File file) {
        //查看文件夹是否存在，不存在则创建
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            //使用此方法保存必须要绝对路径且文件夹必须已存在,否则报错
            mfile.transferTo(file);
            return true;
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 新文件名
     * @param suffix
     * @return
     */
    public static String getNewFileName(String suffix) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        return date + UUID.randomUUID() + suffix;
    }

    /**
     * 返回图片保存地址
     * @param name
     * @return
     */
    public static String getNewImagePath(String name) {
        return SAVE_IMAGE_PATH+name;
    }

    /**
     * 上传图片到obs【华为云】
     */
    public String pushImgToOss(String imgPath , String imgName , MultipartFile file){
        String path = "";
        String fullName = imgName  + ".png";
        // ObsClient是线程安全的，可在并发场景下使用
        ObsClient obsClient = null;
        try
        {
            // 创建ObsClient实例
            obsClient = new ObsClient(ak, sk, endPoint);
            // 调用接口进行操作，例如上传对象
            obsClient.putObject("huawei-obs-3651", "sbb_project/"+imgPath+"/" + fullName , new ByteArrayInputStream(file.getBytes()));  // localfile为待上传的本地文件路径，需要指定到具体的文件名
//            System.out.println(response);
            // 插入图片
            path = "https://huawei-obs-3651.obs.cn-north-1.myhuaweicloud.com/sbb_project/userImg/"+  fullName ;
        }
        catch (ObsException | IOException e)
        {
            return path;
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
        return path;
    }
}
