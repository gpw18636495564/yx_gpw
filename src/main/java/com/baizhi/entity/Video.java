package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video implements Serializable {
    private String id;
    private String name;
    private String brief;
    private String cover_path;
    private String video_path;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date upload_time;
    private Integer like_count;
    private Integer play_count;
    private String category_id;
    private String user_id;
    private String group_id;
    private String state;
}
