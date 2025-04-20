package com.mftplus.shop.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class CacheEvictLevelAspect {
    private final CacheManager cacheManager;

    public CacheEvictLevelAspect(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
    @Before("execution(* com.mftplus.shop.service..*.*(..))")
    public void clearCache(JoinPoint joinPoint) {
        Class<?> clazz = joinPoint.getTarget().getClass();
        CacheEvictLevel annotation = clazz.getAnnotation(CacheEvictLevel.class);
        if (annotation == null) return;

        String cacheName = Arrays.toString(annotation.cacheNames());
        Cache cache = cacheManager.getCache(cacheName);

        if (cache != null) {
            cache.clear();
            log.debug("Cleared cache [{}] from class [{}]", cacheName, clazz.getSimpleName());
        } else {
            log.warn("Cache [{}] not found to clear!", cacheName);
        }
    }

}
