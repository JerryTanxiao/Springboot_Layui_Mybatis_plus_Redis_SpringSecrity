package tt.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import tt.model.ResultModel;
import tt.pojo.User;
import tt.pojo.Users;
import tt.service.UserService;
import tt.service.UsersService;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jerry
 * @since 2019-05-10
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UsersController {
    @Autowired
    UsersService usersService;
    @RequestMapping("/u")
    public Map<String,Object> users(@RequestParam(value = "page",required = false,defaultValue = "1")Long current, @RequestParam(value = "limit",required = false,defaultValue = "10")Long size){
        Page<Users> page = new Page<Users>(current,size);
        IPage<Users> page1 = usersService.page(page);
        Map<String, Object> map = new HashMap<>(16);
        map.put("data",page1.getRecords());
        map.put("count",page1.getTotal());
        //返回码 layui  分页必须要
        map.put("code",0);
        map.put("msg","");
        return map;
    }

    /**
     * 返回图片
     * @param files
     * @return
     */
    @RequestMapping("/uploadImage")
    public Map<String,Object> uploadImage(@RequestParam("file")MultipartFile[] files) throws Exception {
        URL url = ResourceUtils.getURL("classpath:");
        log.debug("路径{}",url);
        File file1 = new File(url.getPath(), "/static/img");
        if (!file1.exists()) {
            file1.mkdirs();
        }
        String filename="";
        for (MultipartFile file : files) {
            filename=file.getOriginalFilename();
            file.transferTo(new File(file1,file.getOriginalFilename()));
        }
        Map map=new HashMap(16);
       map.put("src","../resources/img/"+filename);
//        map.put("src",file1.getAbsoluteFile()+File.separator+filename);
        return new ResultModel().code(0).data(map).msg("图片上传成功！");
    }
    @RequestMapping("/inUsers")
    public Boolean inUser(@RequestBody Users users) {
        System.out.println("user"+users);
        return usersService.saveOrUpdate(users);
    }
    @RequestMapping("/show1")
    public ModelAndView showTitle(ModelAndView mv,@RequestParam(value = "id",required = false,defaultValue = "")Integer id){
        mv.setViewName("show");
        mv.addObject("id",id);
//        Users user = usersService.getById(id);
        return mv;
    }
    @RequestMapping("/show")
    public Users showa(@RequestParam("id")Integer id){
        Users users = usersService.getById(id);
        return users;
    }
}

