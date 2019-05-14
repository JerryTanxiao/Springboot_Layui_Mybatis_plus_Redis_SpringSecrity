package tt.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tt.pojo.Users;
import tt.service.UsersService;

import java.util.List;

@Controller
public class KungfuController {
	@Autowired
	UsersService usersService;
	private final String PREFIX = "pages/";
	/**
	 * 欢迎页
	 * @return
	 */
	@GetMapping("/")
	public ModelAndView index(ModelAndView mv, @RequestParam(value = "more",required = false,defaultValue = "")String more) {
		Page<Users> page=null;
		IPage<Users> page1=null;
		if (more != null) {
			List<Users> list = usersService.list();
			mv.addObject("e",list);
		}else {
			 page = new Page<>(1,3);
			 page1= usersService.page(page);
			mv.addObject("e",page1.getRecords());
		}
		//		return "welcome";
		mv.setViewName("index");
		return mv;
	}
	
	/**
	 * 登陆页
	 * @return
	 */
	@GetMapping("/userlogin")
	public String loginPage() {
		return PREFIX+"login";
	}
	
	
	/**
	 * level1页面映射
	 * @param path
	 * @return
	 */
	@GetMapping("/level1/{path}")
	public String level1(@PathVariable("path")String path) {
		return PREFIX+"level1/"+path;
	}
	
	/**
	 * level2页面映射
	 * @param path
	 * @return
	 */
	@GetMapping("/level2/{path}")
	public String level2(@PathVariable("path")String path) {
		return PREFIX+"level2/"+path;
	}
	
	/**
	 * level3页面映射
	 * @param path
	 * @return
	 */
	@GetMapping("/level3/{path}")
	public String level3(@PathVariable("path")String path) {
		return PREFIX+"level3/"+path;
	}


}
