package tt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author JerryTan
 * @date 2019/5/9 17:33
 */
@ControllerAdvice
@Slf4j
public class AllExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public ModelAndView mv001(Exception e){
        log.debug("异常信息是"+e.getMessage());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("exc");
        mv.addObject("title","异常信息");
        mv.addObject("code",e.getMessage());
        mv.addObject("Cause",e.getCause());
        log.debug("异常ModelAndView{}",mv);
        return mv;
    }
}
