package dao;

import domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public List<User> findall();
    public List<User> findLimited(int start, int rows, Map<String, String[]> condition);
    public List<User> search(String name,int start,int rows);
    public User findUserByUsernameAndPassword(String username,String password);
    public void add(User user);
    public void update(User user);
    public void delete(int id);
    public User findById(int id);
    public int findTotalCount();


    int findTotalCount(Map<String, String[]> condition);
}
