import com.baizhi.Yx_Application;
import com.baizhi.entity.City;
import com.baizhi.entity.Month;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Yx_Application.class)
public class UserTest {
    @Autowired
    private UserService service;
    @Test
    public void t1(){
        for (int i = 0; i < 4; i++) {

        User user = new User();
        user.setNick_name("北鸾");
        user.setPic_img("1.jpg");
        user.setPhone("12345678911");
        user.setBrief("早死早超生!希望在来生");
        service.insert(user);
        }
    }
    @Test
    public void t2(){
        service.u_state("6076f2f1-22b3-4a34-aaeb-cfeb50cb7bdb");
    }
    @Test
    public void t3(){
        User user = new User();
        user.setId("e83628e1-d0c2-46fa-b328-ed624a6daf3f");
        user.setPic_img("1606179981859-324280.jpg");
        service.update(user);
    }
    @Test
    public void t4(){
        List<User> users = service.selectAll(1,5);
        users.forEach(a -> System.out.println(a));
    }
    @Test
    public void t5(){
        List<User> users = service.findAllSearch(1, 5, "phone", "cn", "123");
        users.forEach(a -> System.out.println(a));
    }
    @Test
    public void t6(){
//        Integer cou = service.findSearchTotalCounts("phone", "cn", "123");
//        System.out.println(cou);
        List<Month> sexnv = service.sexnv();
        List<Month> sexnan = service.sexnan();
        List<Integer> gol=new ArrayList<>();
        List<Integer> boy=new ArrayList<>();
        for (int i = 1; i <=12 ; i++) {
            Integer num=-1;
            for (Month a:sexnv) {
                if (a.getMonth().equals(i)) {
                    num=a.getCount();
                }
                if(num==-1){
                    num=0;
                }

            }
            gol.add(num);
        }
        for (int i = 1; i <=12 ; i++) {
            Integer num=-1;
            for (Month a:sexnan) {
                if (a.getMonth().equals(i)) {
                    num=a.getCount();
                }
                if(num==-1){
                    num=0;
                }
            }
            boy.add(num);
        }
        System.out.println("男"+boy);
        Integer[] count1 = boy.toArray(new Integer[boy.size()]);

        Integer[] count2 = gol.toArray(new Integer[gol.size()]);
//        System.out.println("男"+boy);
    }
    @Test
    public void yy(){
    System.out.println("我是你爸爸");
    }

}
