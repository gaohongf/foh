package xyz.fmcy.foh.pojo;

/**
 * @author 付高宏
 * @date 2022/6/22 23:32
 */
public class Praise {
    private Integer userid;
    private Integer targetId;

    public Praise() {
    }

    public Praise(Integer userid, Integer targetId) {
        this.userid = userid;
        this.targetId = targetId;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    @Override
    public String toString() {
        return "Praise{" +
                "userid=" + userid +
                ", targetId=" + targetId +
                '}';
    }
}
