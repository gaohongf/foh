package xyz.fmcy.foh.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import xyz.fmcy.foh.pojo.Admin;
import xyz.fmcy.foh.vo.combo.KeyAndValue;

import java.util.List;

/**
 * 管理服务
 *
 * @author MZH
 * @date 2022/06/17 16:00
 */
@Service
public interface AdminService {
    /**
     * 查询密码
     *
     * @param password 密码
     * @return {@link List}<{@link Admin}>
     */
    Admin selectByPassword(String password);

    /**
     * 选择用户
     *
     * @param user 登录账号
     * @return {@link List}<{@link Admin}>
     */
    Admin selectByUser(String user);

    /**
     * 登录
     *
     * @param admin 管理员实体类信息
     * @return {@link KeyAndValue}<{@link Boolean}, {@link Object}>
     */
    KeyAndValue<Boolean, Object> login(Admin admin);
}