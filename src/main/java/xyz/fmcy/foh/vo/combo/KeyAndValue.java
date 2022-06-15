package xyz.fmcy.foh.vo.combo;

/**
 * 简单键值对模型  类似于{@link java.util.Map.Entry} {true:"张三"}
 *
 * @param <K> key
 * @param <V> value
 * @author 付高宏
 */
public class KeyAndValue<K, V> {

    private K key;

    private V value;

    @Override
    public String toString() {
        return "KeyAndValue{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public KeyAndValue() {
    }

    public KeyAndValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof KeyAndValue) {
            return ((KeyAndValue<?,?>) obj).getKey().equals(this.key) && ((KeyAndValue<?,?>) obj).getValue().equals(this.value);
        } else {
            return false;
        }
    }
}
