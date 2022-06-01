package xyz.fmcy.foh.service.impl;

import org.springframework.stereotype.Service;
import xyz.fmcy.foh.mapper.UserMapper;
import xyz.fmcy.foh.pojo.User;
import xyz.fmcy.foh.pojo.combo.KeyAndValue;
import xyz.fmcy.foh.service.UserService;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public KeyAndValue<Boolean, String> addUser(User user) {
        if (user == null || hasUserByUserName(user.getUsername())) return new KeyAndValue<>(false, "msg2");
        return userMapper.addUser(user) > 0 ? new KeyAndValue<>(true, "msg1") : new KeyAndValue<>(false, "msg2");
    }

    @Override
    public boolean hasUserByUserName(String username) {
        return findUserByUserName(username) != null;
    }

    @Override
    public boolean updateUserNickname(User user) {
        if (user == null || (user.getUid() == null && user.getUsername() == null) || "".equals(user.getUsername())) {
            return false;
        }
        return userMapper.updateUserNickname(user) > 0;
    }

    @Override
    public KeyAndValue<Boolean, Object> login(User user) {
        if (user == null) return new KeyAndValue<>(false, "err3");
        User userByUserName = findUserByUserName(user.getUsername());
        if (userByUserName != null) {
            if (userByUserName.getPassword().equals(user.getPassword())) {
                return new KeyAndValue<>(true, userByUserName);
            }
            return new KeyAndValue<>(false, "err2");
        }
        return new KeyAndValue<>(false, "err1");
    }

    @Override
    public User findUserByUid(Integer uid) {
        return userMapper.findUserByUid(uid);
    }

    @Override
    public User findUserByUserName(String username) {
        return userMapper.findUserByUserName(username);
    }
}
