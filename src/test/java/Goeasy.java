import com.alibaba.fastjson.JSON;
import com.baizhi.Yx_Application;
import com.baizhi.entity.Month;
import com.baizhi.service.UserService;
import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Yx_Application.class)
public class Goeasy {
    @Autowired
    private UserService service;
    @Test
    public void a(){
        //创建初始化对象
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-449e58270cd84abb855e9ccce2ac322e");
        //发送消息
        goEasy.publish("qi_xue", "好久不见!");
    }
    @Test
    public void b() {
        int aaa = 1;
        for (int iz = 0; iz < 10; iz++) {
            if (aaa == 1) {
                List<Month> sexnv = service.sexnv();
                List<Month> sexnan = service.sexnan();
                List<Integer> gol = new ArrayList<>();
                List<Integer> boy = new ArrayList<>();
                for (int i = 1; i <= 12; i++) {
                    Integer num = -1;
                    for (Month a : sexnv) {
                        if (a.getMonth().equals(i)) {
                            num = a.getCount();
                        }
                        if (num == -1) {
                            num = 0;
                        }

                    }
                    gol.add(num);
                }
                for (int i = 1; i <= 12; i++) {
                    Integer num = -1;
                    for (Month a : sexnan) {
                        if (a.getMonth().equals(i)) {
                            num = a.getCount();
                        }
                        if (num == -1) {
                            num = 0;
                        }

                    }
                    boy.add(num);
                }
                Integer[] count1 = boy.toArray(new Integer[boy.size()]);

                Integer[] count2 = gol.toArray(new Integer[gol.size()]);
                Map<String, Object> map = new HashMap<>();
                map.put("months", Arrays.asList("一月份", "二月份", "三月份", "四月份", "五月份", "六月份", "七月份", "八月份", "九月份", "十月份", "十一月份", "十二月份"));
                map.put("nv", Arrays.asList(count1));
                map.put("nan", Arrays.asList(count2));
                String string = JSON.toJSONStringWithDateFormat(map, "yyyy年MM月dd日");
                //创建初始化对象
                GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-449e58270cd84abb855e9ccce2ac322e");
                //发送消息
                goEasy.publish("qi_xue", string);
                aaa -= 1;
            } else {
                List<Month> sexnv = service.sexnv();
                List<Month> sexnan = service.sexnan();
                List<Integer> gol = new ArrayList<>();
                List<Integer> boy = new ArrayList<>();
                for (int i = 1; i <= 12; i++) {
                    Integer num = -1;
                    for (Month a : sexnv) {
                        if (a.getMonth().equals(i)) {
                            num = a.getCount();
                        }
                        if (num == -1) {
                            num = 0;
                        }

                    }
                    gol.add(num);
                }
                for (int i = 1; i <= 12; i++) {
                    Integer num = -1;
                    for (Month a : sexnan) {
                        if (a.getMonth().equals(i)) {
                            num = a.getCount();
                        }
                        if (num == -1) {
                            num = 0;
                        }

                    }
                    boy.add(num);
                }
                Integer[] count1 = boy.toArray(new Integer[boy.size()]);

                Integer[] count2 = gol.toArray(new Integer[gol.size()]);
                Map<String, Object> map = new HashMap<>();
                map.put("months", Arrays.asList("一月份", "二月份", "三月份", "四月份", "五月份", "六月份", "七月份", "八月份", "九月份", "十月份", "十一月份", "十二月份"));
                map.put("nv", Arrays.asList(count2));
                map.put("nan", Arrays.asList(count1));
                String string = JSON.toJSONStringWithDateFormat(map, "yyyy年MM月dd日");
                //创建初始化对象
                GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-449e58270cd84abb855e9ccce2ac322e");
                //发送消息
                goEasy.publish("qi_xue", string);
                aaa += 1;
            }
        }
    }
}
