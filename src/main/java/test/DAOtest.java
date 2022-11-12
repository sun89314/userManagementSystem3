package test;

import dao.UserDaoImpl;
import domain.User;
import org.junit.Test;

import java.util.List;

public class DAOtest {
    @Test
    public void testFindAll(){
        UserDaoImpl userDao = new UserDaoImpl();
        List<User> list = userDao.findall();

        System.out.println(list);
    }
    @Test
    public void findUserByUsernameAndPassword(){
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.findUserByUsernameAndPassword("slt","1");

        System.out.println(user);
    }
}
