package xyz.fmcy.foh.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.fmcy.foh.pojo.Comments;

import java.util.List;

/**
 * @author 付高宏
 * @date 2022/6/20 22:41
 */
@Mapper
public interface CommentsMapper {
    List<Comments> findByTopicId(@Param("id") Integer id,@Param("start") Integer start,@Param("number") Integer number);

    List<Comments> subComments(@Param("parentId") Integer parentId);

    Comments getCommentById(@Param("id") Integer id);

    int addComment(Comments comments);

    int deleteComment(Integer id);
}
