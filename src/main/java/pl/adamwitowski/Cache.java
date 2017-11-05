package pl.adamwitowski;

import java.util.Map;

public interface Cache extends CacheView {

    void setCacheSize(int maxSize);

    Map<String, CacheItem> getCache();

    CacheItem cacheItem(Object item, String key);

    void invalidateCache();

    CacheView getView();
}
