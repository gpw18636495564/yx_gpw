import com.baizhi.Yx_Application;
import com.baizhi.dao.CategoryDao;
import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Yx_Application.class)
public class CateYest {
    @Autowired
    private CategoryService service;
    @Autowired
    private CategoryDao dao;
    @Test
    public void t1(){
        Category category = new Category();
        category.setCate_name("动漫");
        service.insertOne(category);
    }
//    @Test
//    public void t2(){
//        Category category = new Category();
//        category.setCate_name("西游记");
//        service.insertTwo(category,"d54899f7-459a-4cce-b0c3-d9dbe6f958c1");
//    }
    @Test
    public void t3(){
        List<Category> categories = service.selectOne(1, 1);
        categories.forEach(a -> System.out.println(a));
    }
    @Test
    public void t4(){
        List<Category> categories = service.selectTwo("d54899f7-459a-4cce-b0c3-d9dbe6f958c1",1, 1);
        categories.forEach(a -> System.out.println(a));
    }
    @Test
    public void t5(){
            service.delete("97eef630-85a7-41b7-9375-407458106502");
    }
    @Test
    public void t6(){
        List<com.baizhi.po.Category> list = dao.pageAll();
        list.forEach(a -> System.out.println(a));
    }
}
