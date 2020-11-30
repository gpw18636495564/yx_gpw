import com.baizhi.Yx_Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Yx_Application.class)
public class Redis {
    @Autowired
    private RedisTemplate redisTemplate;  //存储对象
    @Test
    public void t1(){
        redisTemplate.opsForValue().set("age",18);
    }
}
