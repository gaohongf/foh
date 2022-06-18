package xyz.fmcy.foh.vo;

import org.springframework.context.annotation.Profile;
import org.springframework.web.multipart.MultipartFile;
import xyz.fmcy.foh.pojo.TopicType;

/**
 * @author 付高宏
 * @date 2022/6/17 23:15
 */
public class VTopicType extends TopicType {

    private MultipartFile file;

    private String suffix;

    public VTopicType(MultipartFile file, String suffix) {
        this.file = file;
        this.suffix = suffix;
    }

    public VTopicType(Integer id, String typename, String typeIcon, String bgImg, MultipartFile file, String suffix) {
        super(id, typename, typeIcon, bgImg);
        this.file = file;
        this.suffix = suffix;
    }

    public VTopicType(){
    }

    public MultipartFile getFile() {
        return file;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "VTopicType{" +
                "file=" + file +
                ", suffix='" + suffix + '\'' +
                '}';
    }
}
