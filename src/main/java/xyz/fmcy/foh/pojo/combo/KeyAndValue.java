package xyz.fmcy.foh.pojo.combo;

public class KeyAndValue<K,V> {

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
}
