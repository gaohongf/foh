package xyz.fmcy.foh.service.impl;

import org.springframework.stereotype.Service;
import xyz.fmcy.foh.mapper.AdminMapper;
import xyz.fmcy.foh.pojo.Admin;
import xyz.fmcy.foh.service.AdminService;
import xyz.fmcy.foh.vo.combo.KeyAndValue;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理服务实现
 *
 * @author MZH
 * @date 2022/06/17 16：01
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper mapper;

    @Override
    public Admin selectByPassword(String password) {
        return mapper.selectByPassword(password);
    }

    @Override
    public Admin selectByUser(String user) {
        return mapper.selectByUser(user);
    }

    @Override
    public KeyAndValue<Boolean, Object> login(Admin admin) {
        if (admin == null) {
            return new KeyAndValue<>(false, "err3");
        }
        Admin admin1 = selectByUser(admin.getUser());
        if (admin1 != null) {
            if (admin1.getPassword().equals(admin.getPassword())) {
                return new KeyAndValue<>(true, admin1);
            }
            return new KeyAndValue<>(false, "err2");
        }
        return new KeyAndValue<>(false, "err1");
    }
}
