package xyz.fmcy.foh.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.fmcy.foh.pojo.Admin;

import java.util.List;

/**
 * 管理员数据操作
 *
 * @author MZH
 * @date 2022/06/17 16:00
 */
public interface AdminMapper {
    /**
     * 查询密码
     *
     * @param password 密码
     * @return {@link List}<{@link Admin}>
     */
    Admin selectByPassword(@Param("password") String password);

    /**
     * 选择用户
     *
     * @param user 用户
     * @return {@link List}<{@link Admin}>
     */
    Admin selectByUser(@Param("user") String user);
}
