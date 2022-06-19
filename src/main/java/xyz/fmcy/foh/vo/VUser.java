package xyz.fmcy.foh.vo;

import xyz.fmcy.foh.pojo.User;

/**
 * 安全的User实体视图对象
 * @see xyz.fmcy.foh.pojo.User
 * @author 付高宏
 * @date 2022/6/15 20:40
 */
public class VUser {
    private Integer uid;

    private String name;

    private String sign;

    private String gender;

    private Integer age;

    @Override
    public String toString() {
        return "VUser{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", sign='" + sign + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public VUser() {
    }

    public VUser(User user){
        if (user == null) return;
        this.uid = user.getId();
        this.age=user.getAge();
        this.gender=user.getGender();
        this.name=user.getName();
        this.sign=user.getSign();
    }

    public VUser(Integer uid, String name, String sign, String gender, Integer age) {
        this.uid = uid;
        this.name = name;
        this.sign = sign;
        this.gender = gender;
        this.age = age;
    }
}
