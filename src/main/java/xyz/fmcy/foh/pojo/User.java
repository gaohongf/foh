package xyz.fmcy.foh.pojo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {
    private Integer uid;

    @NotEmpty(message = "用户名不能为空")
    @Size(min = 4,max = 20,message = "用户名字符位数在4-20位之间")
    @Pattern(regexp = "^[a-zA-Z\\d]{4,20}$",message = "")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 6,max = 30,message = "密码字符位数在6-30位之间")
    private String password;
    @Size(max = 20)
    private String nickname;

    public User() {
    }

    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public User(Integer uid, String username, String password, String nickname) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
