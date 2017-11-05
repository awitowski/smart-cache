package pl.adamwitowski;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CacheResolver implements Cache {
    private int maxSize = 3;
    private final Map<String, CacheItem> cache;

    public CacheResolver() {
        cache = new LinkedHashMap<String, CacheItem>() {

            @Override
            protected boolean removeEldestEntry(final Map.Entry eldest) {
                return size() > maxSize;
            }
        };
    }

    @Override
    public void setCacheSize(int maxSize){
        this.maxSize = maxSize;
    }

    @Autowired
    public Map<String, CacheItem> getCache(){
        return cache;
    }

    @Override
    public CacheItem cacheItem(Object item, String key) {
        return cache.put(key, new Item(key, item));
    }

    @Override
    public void invalidateCache() {
        cache.clear();
    }

    @Override
    public CacheView getView() {
        return this;
    }

    @Override
    public int size() {
        return maxSize;
    }

    @Override
    public CacheItem getItem(int index) {
        return (new ArrayList<>(cache.values())).get(index);
    }

    @Override
    public CacheItem getItem(String key) {
        return cache.get(key);
    }


}
