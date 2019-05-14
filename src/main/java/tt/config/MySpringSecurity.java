package tt.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
///**
// * @author JerryTan
// * @date 2019/5/9 11:29
// * shiro 安全连接
// * 必须要继承 WebSecurityConfigurerAdapter
// * @EnableWebSecurity 开启一个配置
// */
//@EnableWebSecurity
//public class MySpringSecurity extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //定制请求授权规则
//        http.authorizeRequests().antMatchers("/","/url/**","resources/**","/user/**").permitAll()
//                .antMatchers("/level1/**").hasRole("VIP1")
//                .antMatchers("/level2/**").hasRole("VIP2")
//                . antMatchers("/level3/**").hasRole("VIP3");
//        /**
//         * 开启自动配置的登陆功能,效果如果沒有登陸,沒有去权限会来到登陆页面
//         * loginPage 跳转到登陆界面  默认获取是username ,password
//         * 一但定制loginPage，那么loginPage的post请求就是登陆处理逻辑,
//         * loginProcessingUrl("/login") 设置一个默认处理请求
//         */
////        http.formLogin().loginPage("/userlogin");
//        //采用默认的login
//        http.formLogin();
//        /**
//         * 记住密码 登陆成功以后将cookie发送给浏览器保存，点击注销可以删除cookie
//         * 设置记住密码的名字
//         */
//        http.rememberMe().rememberMeParameter("remeber");
//        //开启自动配置的注销功能 默认login?logout 定制规则
//        //注销成功来到首页
//        http.logout().logoutSuccessUrl("/");
//        //权限不足自动跳转
//        http.exceptionHandling().accessDeniedPage("/url/403");
//    }
//    //定制认证规则
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        使用springboot，权限管理使用spring security，使用内存用户验证，但无响应报错：
////        java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
////        解决方法：
////        这是因为Spring boot 2.0.3引用的security 依赖是 spring security 5.X版本，此版本需要提供一个PasswordEncorder的实例，否则后台汇报错误：
//        auth.inMemoryAuthentication().passwordEncoder(new PasswordEncoder() {
//                @Override
//                public String encode(CharSequence charSequence) {
//                    return charSequence.toString();
//                }
//                @Override
//                public boolean matches(CharSequence charSequence, String s) {
//                    return s.equals(charSequence.toString());
//                }
//            }).withUser("zhangsang").password("123456").roles("VIP1","VIP2")
//                .and()
//                .withUser("lisi").password("123456").roles("VIP2","VIP3")
//                .and()
//                .withUser("wangwu").password("123456").roles("VIP1","VIP3");
//    }
//}
