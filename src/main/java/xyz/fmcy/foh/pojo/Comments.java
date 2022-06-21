package xyz.fmcy.foh.pojo;

import java.util.Date;

/**
 * @author 付高宏
 * @date 2022/6/20 22:39
 */
public class Comments {
    private Integer id;
    private Integer targetId;
    private Integer targetType;
    private String content;
    private Integer uid;
    private Date time;
    private Integer parent;

    public Comments() {
    }

    public Comments(Integer targetId, Integer targetType, String content, Integer uid, Date time, Integer parent) {
        this.targetId = targetId;
        this.targetType = targetType;
        this.content = content;
        this.uid = uid;
        this.time = time;
        this.parent = parent;
    }

    public Comments(Integer id, Integer targetId, Integer targetType, String content, Integer uid, Date time, Integer parent) {
        this.id = id;
        this.targetId = targetId;
        this.targetType = targetType;
        this.content = content;
        this.uid = uid;
        this.time = time;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", targetId=" + targetId +
                ", targetType=" + targetType +
                ", content='" + content + '\'' +
                ", uid=" + uid +
                ", time=" + time +
                ", parent=" + parent +
                '}';
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
