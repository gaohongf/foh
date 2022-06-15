package xyz.fmcy.foh.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.fmcy.foh.pojo.Avatar;

/**
 * 头像数据操作接口
 * @author 付高宏
 * @date 2022/6/15
 */
@Mapper
public interface AvatarMapper {
    /**
     * 根据id获取用户头像
     *
     * @param id 用户 id
     * @return 对应用户头像
     */
    Avatar findByUid(Integer id);

    /**
     * 修改一位用户的头像链接
     *
     * @param avatar {@link xyz.fmcy.foh.pojo.Avatar} 用户头像
     * @return SQL执行返回 0为修改失败 大于0为被修改的行数
     */
    int updateAvatar(Avatar avatar);

    /**
     * 为一位用户添加头像
     *
     * @param avatar {@link xyz.fmcy.foh.pojo.Avatar} 用户头像
     * @return SQL执行返回 0为添加失败 大于0为增加的行数
     */
    int insertAvatar(Avatar avatar);
}
