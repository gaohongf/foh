package xyz.fmcy.foh.pojo;


/**
 * 粉丝标记数据映射
 * @author 付高宏
 * @date 2022/6/15 19:25
 */
public class Fans {
    private Integer uid;
    private Integer target;

    @Override
    public String toString() {
        return "Fans{" +
                "uid=" + uid +
                ", target=" + target +
                '}';
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public Fans() {
    }

    public Fans(Integer uid, Integer target) {
        this.uid = uid;
        this.target = target;
    }
}
