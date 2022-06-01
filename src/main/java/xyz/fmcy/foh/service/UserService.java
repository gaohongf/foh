package xyz.fmcy.foh.service;

import org.springframework.stereotype.Service;
import xyz.fmcy.foh.pojo.User;
import xyz.fmcy.foh.pojo.combo.KeyAndValue;
@Service
public interface UserService {
    /**
     * 使用账号与密码创建一位用户
     *
     * @param user 会将自增的-uid-自动填入传入的对象
     */
    KeyAndValue<Boolean,String> addUser(User user);

    /**
     * 判断是否已有用户
     * @param username 用户名
     * @return  true:用户已存在 false:用户不存在
     */
    boolean hasUserByUserName(String username);
    /**
     * 修改用户的昵称
     *
     * @param user 用户资料:添加新昵称并至少包含准确的-uid-或-username-其中一项
     */
    boolean updateUserNickname(User user);

    /**
     * 账号密码登录
     * @return 登录自己的账号密码
     */
    KeyAndValue<Boolean,Object> login(User user);
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
}
