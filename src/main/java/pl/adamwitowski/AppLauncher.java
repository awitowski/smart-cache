package pl.adamwitowski;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "pl.adamwitowski")
public class AppLauncher {

    private final static Logger logger = Logger.getLogger(AppLauncher.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppLauncher.class, args);

        Cache cache = new CacheResolver();

        logger.info(cache.getCache());

        cache.cacheItem("A", "A");
        logger.info(cache.getCache());

        cache.cacheItem("B", "B");
        logger.info(cache.getCache());

        cache.cacheItem("C", "C");
        logger.info(cache.getCache());

        cache.cacheItem("D", "D");
        logger.info(cache.getCache());

        cache.cacheItem("E", "E");
        logger.info(cache.getCache());

        cache.cacheItem("F", "F");
        logger.info(cache.getCache());
    }
}
