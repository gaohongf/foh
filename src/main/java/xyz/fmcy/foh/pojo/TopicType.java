package xyz.fmcy.foh.pojo;

/**
 * 帖子类型映射
 * @author 付高宏
 * @date 2022/6/15 21:33
 */
public final class TopicType {
    private Integer id;
    private String typename;
    private String typeIcon;

    public TopicType() {
    }

    public TopicType(Integer id, String typename, String typeIcon) {
        this.id = id;
        this.typename = typename;
        this.typeIcon = typeIcon;
    }

    @Override
    public String toString() {
        return "TopicType{" +
                "id=" + id +
                ", typename='" + typename + '\'' +
                ", typeIcon='" + typeIcon + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getTypeIcon() {
        return typeIcon;
    }

    public void setTypeIcon(String typeIcon) {
        this.typeIcon = typeIcon;
    }
}
