import com.java.dao.UserDao;
import com.java.domain.Users;
import org.junit.Test;

/**
 * 测试连接数据库是否成功
 */
public class UserDaoTest {

    @Test
    public void testLogin() throws Exception {
        Users users = new Users();
        users.setUid( "13795725154" );
        users.setUpwd( "123456" );

        /*UserDao dao = new UserDao();
        Users users1 = dao.login( users );
        System.out.println( users1 );*/

    }
} 
