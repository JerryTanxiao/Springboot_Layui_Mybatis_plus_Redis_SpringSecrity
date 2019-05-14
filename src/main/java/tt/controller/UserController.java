package tt.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import tt.pojo.User;
import tt.service.UserService;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jerry
 * @since 2019-05-07
 */
@Slf4j
@RestController
@RequestMapping("/user")
@CacheConfig(cacheNames = "users"/*,cacheManager = "redisCacheManager"*/)
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RedisCacheManager redisCacheManager;
    @RequestMapping("/u")
//    @Cacheable(value = "users",keyGenerator = "myKeyGenerator")
    public Map<String,Object> users(@RequestParam(value = "page",required = false,defaultValue = "1")Long current, @RequestParam(value = "limit",required = false,defaultValue = "10")Long size){
        Page<User> page = new Page<User>(current,size);
        IPage<User> page1 = userService.page(page);
        Map<String, Object> map = new HashMap<>(16);
//        redisCacheManager.getCache("users").put("uu",page1.getRecords()); //使用缓存管理器编写内部缓存
        map.put("data",page1.getRecords());
        map.put("count",page1.getTotal());
        //返回码 layui  分页必须要
        map.put("code",0);
        map.put("msg","");
        return map;
    }

    /**
     * 批量插入
     * @return
     */
    @RequestMapping("/in")
    public String in(){
        List<User> users=new ArrayList<>();
        for (int i = 0; i <1000 ; i++) {
            User user = new User();
            user.setName("admin"+i);
            user.setUser("user"+i);
            users.add(user);
           if(i==100){
               /**
                * 快速插入
                */
               userService.saveBatch(users);
               users.clear();
           }
        }
        return "ok";
    }
    @RequestMapping("/editUser")
    public Boolean addUser(@RequestBody User user){
        if (user.getId()!=null) {
            return userService.saveOrUpdate(user);
        }
        else{
            return userService.save(user);
        }
    }
    @RequestMapping("/deleteUserByIds")
    public Boolean deleteUserByIds(@RequestParam("id") String id){
        return userService.removeById(id);
    }
}

