package pl.adamwitowski;

public class Item implements CacheItem {

    private String key;
    private Object value;

    public Item() {
    }

    public Item(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Item{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}
