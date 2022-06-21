package xyz.fmcy.foh.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import xyz.fmcy.foh.pojo.Comments;

import java.util.List;

/**
 * @author 付高宏
 * @date 2022/6/20 22:51
 */
@Service
public interface CommentsService {
    List<Comments> findByTopicId(Integer id, Integer start);

    List<Comments> subComments(Integer parentId);

    boolean addComment(Comments comments);

    boolean deleteComment(Integer id);

    Comments getCommentById(@Param("id") Integer id);
}
