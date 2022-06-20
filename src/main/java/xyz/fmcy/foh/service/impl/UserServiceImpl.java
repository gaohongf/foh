package xyz.fmcy.foh.service.impl;

import org.springframework.stereotype.Service;
import xyz.fmcy.foh.config.UserAvatarConfig;
import xyz.fmcy.foh.mapper.AvatarMapper;
import xyz.fmcy.foh.mapper.UserMapper;
import xyz.fmcy.foh.pojo.Avatar;
import xyz.fmcy.foh.pojo.User;
import xyz.fmcy.foh.vo.combo.KeyAndValue;
import xyz.fmcy.foh.service.UserService;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private AvatarMapper avatarMapper;
    @Resource
    private UserAvatarConfig userAvatarConfig;

    @Override
    public KeyAndValue<Boolean, String> addUser(User user) {
        KeyAndValue<Boolean, String> msg = new KeyAndValue<>();
        if (user == null) {
            msg.setKey(false);
            msg.setValue("msg2");
            return msg;
        }
        if (hasUserByPhone(user.getPhone())) {
            msg.setKey(false);
            msg.setValue("err4");
            return msg;
        }
        return userMapper.addUser(user) > 0 ? new KeyAndValue<>(true, "msg1") : new KeyAndValue<>(false, "msg2");
    }

    @Override
    public boolean hasUserByPhone(String phone) {
        return findUserByPhone(phone) != null;
    }

    @Override
    public KeyAndValue<Boolean, Object> login(User user) {
        if (user == null) return new KeyAndValue<>(false, "err3");
        User UserByPhone = findUserByPhone(user.getPhone());
        if (UserByPhone != null) {
            if (UserByPhone.getPassword().equals(user.getPassword())) {
                return new KeyAndValue<>(true, UserByPhone);
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

    @Override
    public Avatar findAvatarByUid(Integer id) {
        Avatar avatar = avatarMapper.findByUid(id);
        if (avatar == null) {
            avatar = new Avatar(id,"default-avatar/def01.png");
        }else {
            String s = userAvatarConfig.getResource().replaceFirst("/", "") + avatar.getAvatar();
            avatar.setAvatar(s);
        }
        return avatar;
    }

    @Override
    public boolean updateAvatar(Avatar avatar) {
        if (findAvatarByUid(avatar.getId()) == null) {
            return avatarMapper.insertAvatar(avatar) > 0;
        } else {
            return avatarMapper.updateAvatar(avatar) > 0;
        }
    }

}
