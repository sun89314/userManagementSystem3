package dao;

import domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCutils;

import java.util.*;

public class UserDaoImpl implements  UserDao{
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCutils.getDataScource());
    @Override
    public List<User> findall() {

        String sql = "select * from user";

        List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));

        return list;
    }
    @Override
    public List<User> findLimited(int start, int rows, Map<String, String[]> condition) {

        String sql = "select * from user  where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {

            //排除分页条件参数
            if("curreentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//？条件的值
            }
        }

        //添加分页查询
        sb.append(" limit ?,? ");
        //添加分页查询参数值
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        System.out.println(sql);
        System.out.println(params);

        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }

    @Override
    public User findUserByUsernameAndPassword(String username,String password) {
        String sql = "select * from user where username = ? and password = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;

        }

    }

    @Override
    public List<User> search(String name,int start,int rows) {

        String sql = "select * from user where name like ? limit ?, ?";

        List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class),name,start,rows);

        return list;

    }

    @Override
    public int findTotalCount() {
        String sql = "select count(*) from user";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
        return integer;
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql = "select count(*) from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = condition.keySet();
        List<String> params = new ArrayList<String>();
        for(String key:keySet){
            if("curreentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            String value = condition.get(key)[0];
            if(value !=null && !"".equals(value)){

                sb.append(" and "+key +" like ? ");
                params.add(value);
            }
        }
        sql = sb.toString();
        System.out.println(params);
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class,params.toArray());
        return integer;
    }

    @Override
    public void add(User user) {
        String sql = "insert into user(name,gender,age,address,qq,email) values(?,?,?,?,?,?)";

        jdbcTemplate.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());

    }

    @Override
    public void update(User user) {
        String sql = "UPDATE user SET gender = ?, age = ?,address = ?, qq=?,email = ? WHERE (id= ?)";

        jdbcTemplate.update(sql,user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());

    }

    @Override
    public User findById(int id) {
        String sql = "select * from user where id =?";

        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
        return user;


    }

    @Override
    public void delete(int id) {
        String sql = "delete from user where id = ?";
        jdbcTemplate.update(sql,id);
    }
}
