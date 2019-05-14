package tt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching    //1、开启基于注解的缓存 @EnableCaching
/*@EnableScheduling//开启基于注解的定时任务
@EnableAsync //开启异步注解功能  要在方法上面标示*/
public class SpringbootMybatisLayfenyeApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisLayfenyeApplication.class, args);
    }
}
