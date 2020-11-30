package com.baizhi.app;
import com.baizhi.CommonResult;

import com.baizhi.po.Category;
import com.baizhi.po.VideoPO;
import com.baizhi.service.CategoryService;
import com.baizhi.service.VideoService;
import com.baizhi.util.AliyunUtil;
import com.baizhi.util.ImageCodeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("app")
public class AppController {

    @Resource
    VideoService videoService;
    @Resource
    CategoryService categoryService;
    @RequestMapping("getPhoneCode")
    public Object getPhoneCode(String phone){

        //生成验证码随机数
        String randomCode = ImageCodeUtil.getSecurityCode();
        System.out.println("手机验证码: "+randomCode);

        String message = null;
        try {
            //发送手机验证码
            message = AliyunUtil.sendPhoneMsg(phone, randomCode);

            return new CommonResult().success(message,phone);
        } catch (Exception e) {
            return new CommonResult().failed(message);
        }
    }

    @RequestMapping("queryByReleaseTime")
    public CommonResult queryByReleaseTime(){
        try {
            List<VideoPO> videoPOS = videoService.queryByReleaseTime();
            return new CommonResult().success(videoPOS);
        } catch (Exception e) {
            return new CommonResult().failed();
        }
    }
    @RequestMapping("queryAllCategory")
    public CommonResult queryAllCategory(){
        try {
            List<Category> videoPOS = categoryService.pageAll();
            return new CommonResult().success(videoPOS);
        } catch (Exception e) {
            return new CommonResult().failed();
        }
    }
    @RequestMapping("queryByLikeVideoName")
    public CommonResult queryByLikeVideoName(String content){
        try {
            List<VideoPO> videoPOS = videoService.queryByLikeVideoName(content);
            return new CommonResult().success(videoPOS);
        } catch (Exception e) {
            return new CommonResult().failed();
        }
    }
}
