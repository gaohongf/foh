package xyz.fmcy.foh.vo;

import xyz.fmcy.foh.pojo.Topic;

import java.util.List;

/**
 * @author 付高宏
 * @date 2022/6/18 23:10
 */
public class UserTopicPage {
    private Boolean hasNextPage;

    private List<Topic> topics;

    public Boolean getHasNextPage() {
        return hasNextPage;
    }

    @Override
    public String toString() {
        return "UserTopicPage{" +
                "hasNextPage=" + hasNextPage +
                ", topics=" + topics +
                '}';
    }

    public UserTopicPage() {
    }

    public void setHasNextPage(Boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public UserTopicPage(Boolean hasNextPage, List<Topic> topics) {
        this.hasNextPage = hasNextPage;
        this.topics = topics;
    }
}
