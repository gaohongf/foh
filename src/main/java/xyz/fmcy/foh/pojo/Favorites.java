package xyz.fmcy.foh.pojo;

/**
 * 收藏信息映射
 * @author 付高宏
 * @date 2022/6/19 23:12
 */
public final class Favorites {
    /**
     * 用户 id
     * {@link xyz.fmcy.foh.pojo.User }
     */
    private Integer userId;
    /**
     * 帖子 id
     * {@link xyz.fmcy.foh.pojo.Topic}
     */
    private Integer topicId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Favorites() {
    }

    public Favorites(Integer userId, Integer topicId) {
        this.userId = userId;
        this.topicId = topicId;
    }
}
