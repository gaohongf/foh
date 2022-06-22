package xyz.fmcy.foh.pojo;

import java.util.Date;

/**
 * 帖子数据映射
 * @author 付高宏
 * @date 2022/6/15 21:51
 */
public class Topic {
    private Integer id;
    private String title;
    private Integer typeid;
    private Integer authorid;
    private String content;
    private Date time;

    public Topic() {
    }

    public Topic(Integer id, String title, Integer typeid, Integer authorid, String content, Date time) {
        this.id = id;
        this.title = title;
        this.typeid = typeid;
        this.authorid = authorid;
        this.content = content;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", typeid=" + typeid +
                ", authorid=" + authorid +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
