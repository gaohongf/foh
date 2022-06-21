package xyz.fmcy.foh.pojo;

/**
 * 禁止
 *
 * @author MZH
 * @date 2022/06/20
 */
public class Ban {
    private Integer id;
    private Integer userId;
    private Integer state;

    public Ban(Integer id, Integer userId, Integer state) {
        this.id = id;
        this.userId = userId;
        this.state = state;
    }

    public Ban() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Ban{" +
                "id=" + id +
                ", userId=" + userId +
                ", state=" + state +
                '}';
    }
}
