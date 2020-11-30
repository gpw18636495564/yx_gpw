package com.baizhi.controller;

import com.alibaba.druid.util.StringUtils;
import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cate")
public class CategoryController {
    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private CategoryService service;

    @RequestMapping("/cateAll")
    @ResponseBody
    public Map<String, Object> all(Integer page, Integer rows, String id) {
        log.info("当前页:{} 当前条数:{}", page, rows);
        HashMap<String, Object> result = new HashMap<>();
        List<Category> users;
        Integer totalCounts;
        if (id == null) {
            //分页查询的结果
            users = service.selectOne(page, rows);
            //总条数
            totalCounts = service.count1();
        } else {
            //分页查询的结果
            users = service.selectTwo(id, page, rows);
            //总条数
            totalCounts = service.count2(id);
        }
        //总页数
        Integer totalPage = totalCounts % rows == 0 ? totalCounts / rows : totalCounts / rows + 1;
        result.put("page", page);
        result.put("total", totalPage);
        result.put("records", totalCounts);
        result.put("rows", users);
        return result;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Map<String, Object> edit(Category cate, String oper) {
        log.info("当前操作: {}", oper);
        Map<String, Object> map = new HashMap<>();
        //判断是什么操作
        if (StringUtils.equals("add", oper)) {
            if(cate.getParent_id()==null){service.insertOne(cate);map.put("message","添加一级类别成功");}
            if(cate.getParent_id()!=null){service.insertTwo(cate);map.put("message","添加二级类别成功");}
        }
        if (StringUtils.equals("edit", oper)) {
            service.update(cate);
            map.put("message","修改成功");
        }
        if (StringUtils.equals("del", oper)) {
            try {
                service.delete(cate.getId());
                map.put("message","删除成功");
            }catch (Exception e){
                String message = e.getMessage();
                map.put("message",message);
            }
        }
        System.out.println(map.get("message"));
        return map;
    }
}
