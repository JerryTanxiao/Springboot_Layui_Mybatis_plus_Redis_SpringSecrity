package tt.config;

import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import sun.rmi.runtime.Log;

/**
 * @author JerryTan
 * @date 2019/5/7 9:50
 */
@Component
@EnableTransactionManagement
@Configurable
@MapperScan(basePackages = "tt.dao")
public class MybatisPlusConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
    /**
     * Sql 注入器，逻辑删除插件
     */
    public ISqlInjector iSqlInjector(){
        return new DefaultSqlInjector();
    }
    /**
     * 性能分析插件
     * 性能分析拦截器，用于输出每条 SQL 语句及其执行时间
     */
    //设置dev，test环境开启
    @Bean
    @Profile({"dev","test"})
    public PerformanceInterceptor performanceInterceptor(){
        return new PerformanceInterceptor();
    }
    /**
     * 乐观锁插件
     */
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }
}
