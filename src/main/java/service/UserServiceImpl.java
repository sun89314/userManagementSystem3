package service;

import dao.UserDao;
import dao.UserDaoImpl;
import domain.PageBean;
import domain.User;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
    @Override
    public List<User> findall() {
        //use dao to complete the search

        List<User> list = dao.findall();
        return list;
    }

    @Override
    public User login(User user) {
        User loginUser = dao.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        return loginUser;
    }

    @Override
    public void add(User user) {
        dao.add(user);
    }


    @Override
    public void update(User user) {
        dao.update(user);
    }


    @Override
    public void delete(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public User findById(String id) {
        return dao.findById(Integer.parseInt(id));

    }

    @Override
    public void deleteUsers(String[] id) {
        for(String i:id){
            dao.delete(Integer.parseInt(i));
        }
    }

    @Override
    public PageBean<User> findUserByPage(String curreentPage, String rows, Map<String, String[]> condition) {
        PageBean<User> pb = new PageBean<>();
        int currentPage = Integer.parseInt(curreentPage);
        pb.setCurrentPage(currentPage);
        int rowNumber = Integer.parseInt(rows);
        pb.setRows(rowNumber);




        pb.setTotalCount(dao.findTotalCount(condition));
        int totalPage = pb.getTotalCount()% pb.getRows()==0 ? pb.getTotalCount()/pb.getRows() :pb.getTotalCount()/rowNumber + 1;
        pb.setTotalPage(totalPage);

        List<User> list = dao.findLimited((currentPage- 1) * rowNumber,rowNumber,condition);

        pb.setList(list);

        return pb;

    }

//    @Override
//    public PageBean<User> search(String name, String curreentPage, String rows) {
//        PageBean<User> pb = new PageBean<>();
//        int currentPage = Integer.parseInt(curreentPage);
//        pb.setCurrentPage(currentPage);
//        int rowNumber = Integer.parseInt(rows);
//        pb.setRows(rowNumber);
//        pb.setTotalCount(dao.findTotalCount(name));
//        int totalPage = pb.getTotalCount()% pb.getRows()==0 ? pb.getTotalCount()/pb.getRows() :pb.getTotalCount()/rowNumber + 1;
//        pb.setTotalPage(totalPage);
//        List<User> searchlist = dao.search(name, currentPage, rowNumber);
//        pb.setList(searchlist);
//        return pb;
//    }
}
