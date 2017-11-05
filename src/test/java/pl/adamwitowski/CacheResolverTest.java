package pl.adamwitowski;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppLauncher.class)
@ActiveProfiles("test")
public class CacheResolverTest {

    @Autowired
    private Cache cacheResolver;

    @Before
    public void setUp() throws Exception {
        cacheResolver.cacheItem(1, "A");
        cacheResolver.cacheItem(2, "B");
        cacheResolver.cacheItem(3, "C");
    }

    @After
    public void clear() {
        cacheResolver.invalidateCache();
    }

    @Test
    public void testCacheSize() {
        cacheResolver.setCacheSize(5);

        int given = cacheResolver.size();
        int expected = 5;

        Assertions.assertThat(given).isEqualTo(expected);
    }

    @Test
    public void testPutObjectToCache() {
        cacheResolver.cacheItem(4, "D");
        cacheResolver.cacheItem(5, "E");
        cacheResolver.cacheItem(6, "F");

        Assertions.assertThat(cacheResolver.getCache())
                .hasSize(3)
                .containsKeys("D", "E", "F");
    }

    @Test
    public void testGetItemByIndex() {
        CacheItem given = cacheResolver.getItem(2);
        CacheItem expected = new Item("C", 3);

        Assertions.assertThat(given).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void testGetItemByKey() {
        CacheItem given = cacheResolver.getItem("B");
        CacheItem expected = new Item("B", 2);

        Assertions.assertThat(given).isEqualToComparingFieldByField(expected);
    }
}
