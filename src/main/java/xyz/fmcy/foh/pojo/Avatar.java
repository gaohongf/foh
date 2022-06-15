package xyz.fmcy.foh.pojo;

/**
 * 映射用户头像的实体类
 * <br>
 * id:头像拥有者用户 {@link User}
 * avatar:头像链接
 *
 * @author 付高宏
 */
public class Avatar {
    private Integer id;
    private String avatar;

    @Override
    public String toString() {
        return "Avatar{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Avatar(Integer id, String avatar) {
        this.id = id;
        this.avatar = avatar;
    }
}
