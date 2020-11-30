package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Excel(name = "ID")
    private String id;
    @Excel(name = "昵称")
    private String nick_name;
    @Excel(name="头像",type = 2,width = 10,height = 10,imageType = 1)
    private String pic_img;
    @Excel(name = "电话")
    private String phone;
    @Excel(name = "用户简介")
    private String brief;
    @Excel(name = "用户分数")
    private Double score;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册时间",format = "yyyy年MM月dd日")
    private Date create_date;
    @Excel(name = "用户状态")
    private String state;
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "地址")
    private String address;
}
