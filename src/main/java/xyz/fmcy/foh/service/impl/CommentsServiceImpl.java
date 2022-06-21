package xyz.fmcy.foh.service.impl;

import org.springframework.stereotype.Service;
import xyz.fmcy.foh.mapper.CommentsMapper;
import xyz.fmcy.foh.pojo.Comments;
import xyz.fmcy.foh.service.CommentsService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 付高宏
 * @date 2022/6/20 23:05
 */
@Service
public class CommentsServiceImpl implements CommentsService {

    @Resource
    private CommentsMapper commentsMapper;

    @Resource
    private Integer commentsPageNumber;

    @Override
    public List<Comments> findByTopicId(Integer id, Integer start) {
        return commentsMapper.findByTopicId(id, start * commentsPageNumber, commentsPageNumber);
    }

    @Override
    public List<Comments> subComments(Integer parentId) {
        return commentsMapper.subComments(parentId);
    }

    @Override
    public boolean addComment(Comments comments) {
        if (comments == null) {
            return false;
        }
        return commentsMapper.addComment(comments) > 0;
    }

    @Override
    public boolean deleteComment(Integer id) {
        return commentsMapper.deleteComment(id) > 0;
    }

    @Override
    public Comments getCommentById(Integer id) {
        return commentsMapper.getCommentById(id);
    }
}
