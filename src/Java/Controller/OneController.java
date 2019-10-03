package Java.Controller;

import Java.Pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;

import static java.lang.System.out;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value={"/"})
public class OneController {

    static{
        //out.println()
        out.println("init starting ...");

        // 指定全局配置文件
        String resource = "mybatis-config.xml";
        // 读取配置文件
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        }catch(IOException e){
            out.println("init get error:"+e.getMessage());
        }
        if(null != inputStream){
            out.println("开始测试mybatis");
            // 构建sqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            // 获取sqlSession
            SqlSession sqlSession = sqlSessionFactory.openSession();
            // 操作CRUD，第一个参数：指定statement，规则：命名空间+“.”+statementId
            // 第二个参数：指定传入sql的参数：这里是用户id
            User user = sqlSession.selectOne("TestMapper.selectUser");
            System.out.println(user);
            out.println("测试结束");
        }else
            out.println("inputStream is null");

    }

    @RequestMapping(value={"/index"}, method = GET)
    public String defaultController(){
        out.println("defaultController get msg");
        return "index";
    }

    @RequestMapping(value={"/page2"}, method = {GET})
    public String controller2(){
        out.println("controller2 get msg ");
        return "page2";
    }
}
