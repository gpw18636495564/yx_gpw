import com.baizhi.Yx_Application;
import com.baizhi.dao.LogDao;
import com.baizhi.entity.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Yx_Application.class)
public class LogTest {
    @Autowired
    private LogDao dao;
    @Test
    public void t1(){
        Log log = new Log("876d57b-0f0a-41ab-852b-82264cea2340","gpw",new Date(),"删除类别","error");
        dao.insert(log);
    }
}
