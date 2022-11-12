package service;

import domain.PageBean;
import domain.User;

import java.util.List;
import java.util.Map;

/*
@interface of user control
 */
public interface UserService {
    /**
     * find all the user information
     * @return
     */
    public List<User> findall();
    public User login(User user);
    public void add(User user);
    public void update(User user);
    public void delete(String id);
    public void deleteUsers(String[] id);
    public User findById(String id);


    PageBean<User> findUserByPage(String curreentPage, String rows, Map<String, String[]> condition);

    //PageBean<User> search(String name, String curreentPage, String rows);
}
