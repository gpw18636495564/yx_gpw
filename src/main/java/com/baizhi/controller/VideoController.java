package com.baizhi.controller;

import com.alibaba.druid.util.StringUtils;
import com.baizhi.entity.User;
import com.baizhi.entity.Video;
import com.baizhi.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/video")
public class VideoController {
    private static final Logger log = LoggerFactory.getLogger(VideoController.class);
    @Autowired
    private VideoService service;
    @RequestMapping("/pageAll")
    @ResponseBody
    public Map<String,Object> pageAll(Integer page, Integer rows){
        log.info("当前页: {},每页显示记录数: {}",page,rows);
        HashMap<String, Object> result = new HashMap<>();
        List<Video> videos;
        Integer totalCounts;
        //分页查询的结果
        videos = service.selectpage(page, rows);
        //总条数
        totalCounts=service.count();
        //总页数
        Integer totalPage = totalCounts%rows==0?totalCounts/rows:totalCounts/rows+1;
        result.put("page",page);
        result.put("total",totalPage);
        result.put("records",totalCounts);
        result.put("rows",videos);
        return result;
    }
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(Video video,String oper){
        String result =null;
        if(oper.equals("add")){
            result = service.insert(video);
        }
        if(oper.equals("edit")){
            result = service.update(video);
        }

        if(oper.equals("del")){
            service.delete(video.getId());
        }
        return result;
    }
    @ResponseBody
    @RequestMapping("uploadVdieo")
    public void uploadVdieo(MultipartFile video_path,String id,HttpServletRequest request){
        System.out.println("videoPath = " + video_path + ", id = " + id);
        service.uploadVdieosAliyun(video_path,id,request);
    }
}
