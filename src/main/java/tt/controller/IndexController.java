package tt.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author JerryTan
 * @date 2019/5/7 14:20
 */
@Controller
@RequestMapping("/url")
public class IndexController {
    @RequestMapping("/{url}")
    public String url(@PathVariable("url")String url){
        return url;
    }
}
