package com.baizhi.service;

import com.baizhi.entity.Video;
import com.baizhi.po.VideoPO;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface VideoService {
    /**
     * 分页查询视屏的方法
     * @param page
     * @param size
     * @return
     */
    List<Video> selectpage(Integer page, Integer size);

    /**
     * 添加视屏的方法
     * @param video
     */
    String insert(Video video);

    /**
     * 查询总数的方法
     * @return
     */
    Integer count();

    /**
     * 上传到阿里云
     * @param
     * @param id
     */
    public void uploadVdieosAliyun(MultipartFile videoPath, String id, HttpServletRequest request);
    public void uploadVdieos(MultipartFile videoPath, String id);

    /**
     * 删除视频的方法
     * @param id
     */
    public void delete(String id);

    /**
     * 修改视频的信息
     * @param video
     * @return
     */
    String update(Video video);
    /**
     * 根据上传时间进行排序
     * @return
     */
    List<VideoPO> queryByReleaseTime();
    /**
     * 根据名字模糊查询
     * @param content
     * @return
     */
    List<VideoPO> queryByLikeVideoName(String content);
}
