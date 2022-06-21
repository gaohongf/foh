package xyz.fmcy.foh.pojo;

/**
 * 管理员实体类
 * <br>
 * id:标识管理员
 * user:管理员登录账户
 * password:管理员登录密码
 * adminName:管理员昵称
 *
 * @author 缪正浩
 * @date 2022/06/17 15:15
 */
public class Admin {
    private Integer id;
    private String user;
    private String password;
    private String adminName;

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", adminName='" + adminName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Admin(Integer id, String user, String password, String adminName) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.adminName = adminName;
    }
}
