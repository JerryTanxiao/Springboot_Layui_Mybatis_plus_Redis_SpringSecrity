package tt.service.Impl;
import org.springframework.cache.annotation.CacheConfig;
import tt.pojo.User;
import tt.dao.UserDao;
import tt.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
/**
 *  服务实现类
 * @author Jerry
 * @since 2019-05-07
 */
@Service
//抽取缓存的公共位置
@CacheConfig(cacheNames = "user",keyGenerator = "myKeyGenerator")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}
