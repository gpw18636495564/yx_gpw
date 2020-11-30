package com.baizhi.dao;

import com.baizhi.entity.Video;
import com.baizhi.po.VideoPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoDao {
    /**
     * 分页查询视屏的方法
     * @param begin
     * @param size
     * @return
     */
    List<Video> selectpage(@Param("begin") Integer begin, @Param("pagesize")Integer size);

    /**
     * 添加视屏的方法
     * @param video
     */
    void insert(Video video);

    /**
     * 修改数据库的图片视频
     * @param video
     */
    void updateImg(Video video);

    /**
     * 查询总数的方法
     * @return
     */
    Integer count();

    /**
     * 删除视频的方法
     * @param id
     */
    void delete(String id);

    /**
     * 根据id查询一个
     * @param id
     * @return
     */
    Video selectOne(String id);

    /**
     * 修改视频信息的方法
     * @param video
     */
    void update(Video video);

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
