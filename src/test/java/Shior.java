import com.baizhi.Yx_Application;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Yx_Application.class)
public class Shior {
    //后台认证的方法
    public static void testlogin(String username, String password) {
        //初始化安全管理工厂
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //根据安全管理工厂初始化安全管理器
        SecurityManager instance = iniSecurityManagerFactory.createInstance();
        //将安全管理交给安全工具类
        SecurityUtils.setSecurityManager(instance);
        //根据安全工具类获取主体对象
        Subject subject = SecurityUtils.getSubject();
        //创建令牌 token=身份信息(username)+凭证信息(password)
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        //认证
        //认证
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            System.out.println("未知的账号异常   用户名不正确");
        } catch (IncorrectCredentialsException e) {
            System.out.println("不正确的凭证异常   密码错误");
        }
        boolean authenticated = subject.isAuthenticated();

        System.out.println("认证状态: " + authenticated);
    }

    @Test
    public void maina() {
        testlogin("qiqi", "430607");
    }
}
