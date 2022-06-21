package xyz.fmcy.foh.vo;

import xyz.fmcy.foh.pojo.Avatar;
import xyz.fmcy.foh.pojo.Comments;

import java.util.Date;
import java.util.List;

/**
 * @author 付高宏
 * @date 2022/6/20 23:13
 */
public class VComments extends Comments {
    private VUser user;
    private Avatar avatar;
    private List<VComments> subComments;

    public VComments(VUser user, Avatar avatar) {
        this.user = user;
        this.avatar = avatar;
    }

    public VComments() {
    }

    public VComments(VUser user, Avatar avatar, Comments comments) {
        this(user, avatar);
        if (comments == null) {
            return;
        }
        this.setId(comments.getId());
        this.setContent(comments.getContent());
        this.setTargetId(comments.getTargetId());
        this.setParent(comments.getParent());
        this.setTime(comments.getTime());
    }

    public List<VComments> getSubComments() {
        return subComments;
    }

    public void setSubComments(List<VComments> subComments) {
        this.subComments = subComments;
    }

    public VComments(Integer targetId, Integer targetType, String content, Integer uid, Date time, Integer parent, VUser user, Avatar avatar) {
        super(targetId, targetType, content, uid, time, parent);
        this.user = user;
        this.avatar = avatar;
    }

    public VComments(Integer id, Integer targetId, Integer targetType, String content, Integer uid, Date time, Integer parent, VUser user, Avatar avatar) {
        super(id, targetId, targetType, content, uid, time, parent);
        this.user = user;
        this.avatar = avatar;
    }

    public VUser getUser() {
        return user;
    }

    public void setUser(VUser user) {
        this.user = user;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

}
