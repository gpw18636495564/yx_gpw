package com.baizhi.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoPO {
    private String id;
    private String videoTitle;
    private String cover;
    private String path;
    private String uploadTime;
    private String description;
    private Integer likeCount; //点赞数
    private String cateName;  //类别名
    private String userPhoto;  //用户头像
    private String categoryId; //类别id
    private String userId;     //用户id
    private String userName;   //用户名
}
