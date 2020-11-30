import com.baizhi.Yx_Application;
import com.baizhi.dao.VideoDao;
import com.baizhi.entity.Video;
import com.baizhi.po.VideoPO;
import com.baizhi.service.VideoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Yx_Application.class)
public class VideoTest {
    @Autowired
    private VideoService service;
    @Autowired
    private VideoDao dao;
    @Test
    public void t1(){
        Video video = new Video();
        video.setName("山海");
        video.setBrief("春蚕致死蜡炬成灰");
        video.setCover_path("1");
        video.setVideo_path("1");
        video.setCategory_id("9e8622d8-a2cb-4da5-8630-4145b07b51aa");
        video.setUser_id("bba9e856-1376-4958-8ef6-0b6fd8a06b0f");
        video.setGroup_id("1");
        service.insert(video);
    }
    @Test
    public void t2(){
        List<Video> selectpage = service.selectpage(1, 2);
        selectpage.forEach(a -> System.out.println(a));
        Integer count = service.count();
        System.out.println(count);
    }
    @Test
    public void t3(){
        Video video = new Video();
        video.setId("bb93d022-63ea-4aff-88e9-784a4f6dfdfd");
        video.setName("天官赐福，百无禁忌");
        video.setUpload_time(new Date());
        video.setLike_count(0);
        video.setPlay_count(0);
        video.setBrief("嘻嘻");
        video.setState("正常");
        video.setUser_id("2");
        video.setUpload_time(new Date());
        dao.update(video);
    }
    @Test
    public void t4(){
        List<VideoPO> videos = dao.queryByReleaseTime();
        videos.forEach(a -> System.out.println(a));
    }
    @Test
    public void t5(){
        List<VideoPO> videos = dao.queryByLikeVideoName("平凡");
        videos.forEach(a -> System.out.println(a));
    }
}
