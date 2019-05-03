import com.java.dao.UserDao;
import com.java.domain.User;
import org.junit.Test;

/**
 * 测试连接数据库是否成功
 */
public class UserDaoTest {

    @Test
    public void testLogin() throws Exception {
        User user = new User();
        user.setUid( "13795725154" );
        user.setUpwd( "123456" );

        UserDao dao = new UserDao();
        User user1 = dao.login( user );
        System.out.println( user1 );

    }
} 
