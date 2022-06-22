package xyz.fmcy.foh.vo;

import xyz.fmcy.foh.pojo.Topic;
import xyz.fmcy.foh.pojo.TopicType;
import xyz.fmcy.foh.pojo.User;

import java.util.Date;

/**
 * @author 付高宏
 * @date 2022/6/22 12:34
 */
public class VTopic extends Topic {
    private VUser author;

    private TopicType type;

    public VUser getAuthor() {
        return author;
    }

    public void setAuthor(VUser author) {
        this.author = author;
    }

    public TopicType getType() {
        return type;
    }

    public void setType(TopicType type) {
        this.type = type;
    }

    public VTopic() {
    }

    public VTopic(VUser author) {
        this.author = author;
    }

    public VTopic(Integer id, String title, Integer typeid, Integer authorid, String content, Date time, VUser author) {
        super(id, title, typeid, authorid, content, time);
        this.author = author;
    }

    public VTopic(Topic topic){
        this.setId(topic.getId());
        this.setAuthorid(topic.getAuthorid());
        this.setContent(topic.getContent());
        this.setTime(topic.getTime());
        this.setTitle(topic.getTitle());
        this.setTypeid(topic.getTypeid());
    }

    @Override
    public String toString() {
        return "VTopic{" +
                "author=" + author +
                ", type=" + type +
                '}';
    }
}
