import com.baizhi.Yx_Application;
import com.baizhi.entity.Admain;
import com.baizhi.service.AdmianService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Yx_Application.class)
public class AdmianTest {
    @Autowired
    private AdmianService service;

}
