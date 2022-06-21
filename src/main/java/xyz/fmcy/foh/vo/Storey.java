package xyz.fmcy.foh.vo;

/**
 * 描述楼层
 *
 * @author 付高宏
 * @date 2022/6/21 20:34
 */
public class Storey {
    public Integer number;

    public Storey() {
    }

    public Storey(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public int move(int step) {
        this.number += step;
        return number;
    }

    @Override
    public String toString() {
        return "Storey{" +
                "number=" + number +
                '}';
    }
}
