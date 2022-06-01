package xyz.fmcy.foh.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.fmcy.foh.pojo.User;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 使用账号与密码创建一位用户
     *
     * @param user 会将自增的-uid-自动填入传入的对象
     */
    int addUser(User user);

    /**
     * 修改用户的昵称
     *
     * @param user 用户资料:添加新昵称并至少包含准确的-uid-或-username-其中一项
     */
    int updateUserNickname(User user);

    /**
     * 通过用户的-uid-来查询用户
     * @param uid 用户的id
     * @return 用户实体对象
     */
    User findUserByUid(Integer uid);

    /**
     * 通过用户的-username-来查询用户
     * @param username 用户名
     * @return 用户实体对象
     */
    User findUserByUserName(String username);

    /*以下内容为管理需要*/

    /**
     * 查看用户总数
     * @return 用户数量
     */
    int userNumber();

    /**
     * 查询一定范围内的用户
     * @param start 开头
     * @param number 查询数量
     * @return  被查询的用户
     */
    List<User> findUserByLimit(@Param("start") int start,@Param("number") int number);
}
