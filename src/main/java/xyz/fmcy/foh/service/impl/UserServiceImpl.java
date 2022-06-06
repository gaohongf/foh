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
        if (user == null || hasUserByPhone(user.getPhone())) return new KeyAndValue<>(false, "msg2");
        return userMapper.addUser(user) > 0 ? new KeyAndValue<>(true, "msg1") : new KeyAndValue<>(false, "msg2");
    }

    @Override
    public boolean hasUserByPhone(String phone) {
        return findUserByPhone(phone) != null;
    }

    @Override
    public KeyAndValue<Boolean, Object> login(User user) {
        if (user == null) return new KeyAndValue<>(false, "err3");
        User userByUserName = findUserByPhone(user.getPhone());
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
    public User findUserByPhone(String phone) {
        return userMapper.findUserByPhone(phone);
    }
}
