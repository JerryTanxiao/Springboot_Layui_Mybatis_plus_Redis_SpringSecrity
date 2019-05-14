package tt.config;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author JerryTan
 * @date 2019/5/8 11:48
 */
@Configurable
@Component
public class MyCacheKey {
    /**
     * 自动生成主键自定义 缓存的
     * @return
     */
    @Bean("myKeyGenerator")
    public KeyGenerator keyGenerator(){
       return new KeyGenerator(){
           @Override
           public Object generate(Object o, Method method, Object... objects) {
               return method.getName()+"["+ Arrays.asList(objects).toString() +"]";
           }
       };
    }
}
