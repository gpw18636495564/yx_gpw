package com.baizhi.controller;

import com.baizhi.entity.Feedback;
import com.baizhi.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/feed")
public class FeedbackController {
    @Autowired
    private FeedbackService service;
    @RequestMapping("all")
    @ResponseBody
    public Map<String,Object> all(Integer page, Integer rows){
        Map<String, Object> map = new HashMap<>();
        List<Feedback> list = service.selectAll(page, rows);
        Long count = service.count();
        Integer toalpage= Math.toIntExact(count % rows == 0 ? count / rows : count / rows + 1);
        map.put("page",page);
        map.put("total",toalpage);
        map.put("records",count);
        map.put("rows",list);
        return map;
    }
}
