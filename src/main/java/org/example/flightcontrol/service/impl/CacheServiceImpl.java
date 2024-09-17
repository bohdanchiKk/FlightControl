package org.example.flightcontrol.service.impl;

import org.example.flightcontrol.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class CacheServiceImpl implements CacheService {
    private static final Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);
    private final Map<String, Object> cache = new LinkedHashMap<String, Object>(16, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            boolean shouldRemove = size() > 10;
            if (shouldRemove) {
                logger.info("Evicting cache entry: {}", eldest.getKey());
            }
            return shouldRemove;
        }
    };

    @Override
    public Object getFromCache(String key) {
        Object value = cache.get(key);
        if (value != null) {
            logger.debug("Cache hit for key: {}", key);
        } else {
            logger.debug("Cache miss for key: {}", key);
        }
        return value;
    }

    @Override
    public void addToCache(String key, Object value) {
        cache.put(key, value);
        logger.info("Added key: {} to cache", key);
    }
}
