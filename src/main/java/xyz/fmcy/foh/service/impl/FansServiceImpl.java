package xyz.fmcy.foh.service.impl;

import org.springframework.stereotype.Service;
import xyz.fmcy.foh.mapper.FansMapper;
import xyz.fmcy.foh.mapper.UserMapper;
import xyz.fmcy.foh.pojo.Fans;
import xyz.fmcy.foh.service.FansService;
import xyz.fmcy.foh.vo.VUser;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 付高宏
 * @date 2022/6/15 20:54
 */
@Service
public class FansServiceImpl implements FansService {

    @Resource
    private FansMapper fansMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    public Integer fansPageNumber;

    @Override
    public Integer userFansNumber(Integer id) {
        return fansMapper.fansNumberByUid(id);
    }

    @Override
    public Integer userConcernNumber(Integer id) {
        return fansMapper.concernNumberByUid(id);
    }

    @Override
    public List<VUser> findFans(Integer id, Integer start) {
        return fansMapper.findFansByUid(id, start * fansPageNumber, fansPageNumber).stream()
                .map(uid -> userMapper.findUserByUid(uid))
                .map(user -> new VUser(user.getId(), user.getName(), user.getSign(), user.getGender(), user.getAge()))
                .collect(Collectors.toList());
    }

    @Override
    public List<VUser> findConcernNumber(Integer id, Integer start) {
        return fansMapper.findConcernByUid(id, start * fansPageNumber, fansPageNumber).stream()
                .map(uid -> userMapper.findUserByUid(uid))
                .map(user -> new VUser(user.getId(), user.getName(), user.getSign(), user.getGender(), user.getAge()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isFans(Fans fans) {
        return fansMapper.isFans(fans) > 0;
    }

    @Override
    public boolean addFans(Fans fans) {
        return fansMapper.addFans(fans) > 0;
    }

    @Override
    public boolean relieve(Fans fans) {
        return fansMapper.relieve(fans) > 0;
    }
}
