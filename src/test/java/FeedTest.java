import com.baizhi.Yx_Application;
import com.baizhi.entity.Feedback;
import com.baizhi.service.FeedbackService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Yx_Application.class)
public class FeedTest {
    @Autowired
    private FeedbackService service;
    @Test
    public void t1() {
        Feedback feedback = new Feedback();
        feedback.setTitle("上传视频报错");
        feedback.setContent("垃圾玩意");
        feedback.setUser_id("a092ae9f-2321-44f6-bf81-0da363762926");
        service.insert(feedback);
    }
    @Test
    public void t2() {
        List<Feedback> feedbacks = service.selectAll(1, 2);
        feedbacks.forEach(a -> System.out.println(a));
    }
    @Test
    public void t3() {
//        service.delete("a7d80fc6-6ca0-47e1-bbeb-2d8976ede196");
        System.out.println(service.count());
    }
}
