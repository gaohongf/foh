package xyz.fmcy.foh.pojo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public final class User {
    private Integer id;

    private Integer age;

    private String gender;

    private String sign;

    @NotEmpty(message = "用户名不能为空")
    private String name;

    @Pattern(regexp = "^(\\d){11}$", message = "手机号为11位数字")
    private String phone;

    @Size(min = 6, max = 30, message = "密码位数在6-30位之间")
    private String password;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", sign='" + sign + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(Integer id, Integer age, String gender, String sign, String name, String phone, String password) {
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.sign = sign;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public User() {
    }

}
