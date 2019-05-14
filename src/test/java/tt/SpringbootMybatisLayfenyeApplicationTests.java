package tt;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.*;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import tt.pojo.User;
import tt.pojo.Users;
import tt.service.UserService;
import tt.service.UsersService;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisLayfenyeApplicationTests {
    @Autowired
    UserService userService;
    /**
     * 需要导入spring-boot-starter-data-redis ，String k-v
     */
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    /**
     * 键值，对象
     */
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisTemplate<Object,Object> myRedisTemplate;
    @Test
    public void contextLoads() {
        Page<User> page = new Page<User>(1,3);
        IPage<User> page1 = userService.page(page);
        List<User> users = page1.getRecords();
    }

    /**
     * Redis常见五大数据类型
     * String （字符串）,list（列表）,set(集合),hash(散列),zset(有序集合)
     * stringRedisTemplate.opsForValue() String[字符串]
     *stringRedisTemplate.opsForList();[list（列表）]
     *  stringRedisTemplate.opsForSet();
     *  stringRedisTemplate.opsForHash();
     *  stringRedisTemplate.opsForZSet();
     */
    @Test
    public void Test01(){
//        stringRedisTemplate.opsForValue().append("msg","hello");
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);
        stringRedisTemplate.opsForList().leftPush("mylist","1");
        stringRedisTemplate.opsForList().leftPush("mylist","2");
    }
    /**
     * 测试保存对象
     */
    @Test
    public void Test02(){
        User byId = userService.getById("1");
        System.out.println(byId);
        //默认如果保存对象，使用jdk序列化机制，序列化的数据保存到redis中
       /* redisTemplate.opsForValue().set("emp-01",byId);*/
        //改变默认的序列化规则
        //json方式存储
        myRedisTemplate.opsForValue().set("user-01",byId);
    }

    /**
     * 邮箱测试 采用JavaMain进行发送
     */
    @Autowired
    JavaMailSenderImpl mailSender;
    @Test
    public void  Test03(){
        //创建一个简单的消息邮箱
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("您的验证码是");
        message.setText("您的验证码是"+ UUID.randomUUID());
        message.setTo("871840459@qq.com");
        message.setFrom("JerryTan_xiao@163.com");
        mailSender.send(message);
    }

    /**
     * 创建一个复杂的消息带附件
     */
    @Test
    public void Test04() throws Throwable{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        //邮箱设置
        helper.setSubject("您的验证码是！");
        helper.setText("<b style='color:red'>"+UUID.randomUUID()+"</b>",true);
        helper.setTo("871840459@qq.com");
        helper.setFrom("JerryTan_xiao@163.com");
        //上传文件
        helper.addAttachment("2.jpg",new File("C:\\Users\\Administrator\\Pictures\\2.jpg"));
        mailSender.send(message);
    }
    @Autowired
    UsersService usersService;
    @Test
    public void Test07() throws FileNotFoundException {
        JsonParser parser=new JsonParser();  //创建JSON解析器
        //通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
        JsonElement parse = parser.parse(new FileReader("E:\\idea\\springboot_mybatis_layfenye\\src\\main\\resources\\static\\newsList.json"));//创建JsonObject对象
        JsonObject jsonObject = (JsonObject) parse;
        JsonArray data = jsonObject.get("data").getAsJsonArray();//拿到对应的json
        //创建一个Gson对象
        Gson gson=new Gson();
        List<Users> users1 = new ArrayList<>();
        Iterator<JsonElement> it = data.iterator();
        //转json不能连用data.iterator() 不然死锁
        while (it.hasNext()){
            JsonElement next = it.next();
            Users users = gson.fromJson(next, Users.class);
            System.out.println(users);
            users1.add(users);
        }
        System.out.println(users1);
        usersService.saveBatch(users1);
    }
}
