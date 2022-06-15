package xyz.fmcy.foh.service;

import org.springframework.stereotype.Service;
import xyz.fmcy.foh.pojo.Fans;
import xyz.fmcy.foh.vo.VUser;

import java.util.List;

/**
 * @author 付高宏
 * @date 2022/6/15 20:35
 */
@Service
public interface FansService {
    /**
     * 查看用户粉丝数量
     *
     * @param id 用户id
     * @return 数量
     */
    Integer userFansNumber(Integer id);

    /**
     * 查看用户关注数量
     *
     * @param id 用户id
     * @return 数量
     */
    Integer userConcernNumber(Integer id);

    /**
     * 查询用户的粉丝
     *
     * @param id    用户id
     * @param start 查询起点
     * @return {@link xyz.fmcy.foh.vo.VUser} 粉丝的部分用户信息
     */
    List<VUser> findFans(Integer id, Integer start);

    /**
     * 查询用户的关注
     *
     * @param id    用户id
     * @param start 查询起点
     * @return {@link xyz.fmcy.foh.vo.VUser} 被关注者的部分用户信息
     */
    List<VUser> findConcernNumber(Integer id, Integer start);

    /**
     * 查看是否为关注关系
     *
     * @param fans {@link xyz.fmcy.foh.pojo.Fans}
     * @return false:不是目标粉丝 true:是目标粉丝
     */
    boolean isFans(Fans fans);

    /**
     * 追加一条粉丝记录
     *
     * @param fans {@link xyz.fmcy.foh.pojo.Fans}
     * @return false:追加失败 true:追加成功
     */
    boolean addFans(Fans fans);

    /**
     * 移除一个粉丝关系
     *
     * @param fans {@link xyz.fmcy.foh.pojo.Fans}
     * @return false:移除失败 true:移除成功
     */
    boolean relieve(Fans fans);
}
