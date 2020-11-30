package com.baizhi.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private String id;
    private String cateName;
    private Integer levels;
    private String parentId;
    private List<Category> categoryList;
}
