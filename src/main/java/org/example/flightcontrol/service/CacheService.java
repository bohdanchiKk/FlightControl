package org.example.flightcontrol.service;

public interface CacheService<T> {
    T getFromCache(String key);
    void addToCache(String key, T value);
}
