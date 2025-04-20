package com.mftplus.shop.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class CacheableLevelAspect {

    private final CacheManager cacheManager;

    public CacheableLevelAspect(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
    @Around("execution(* com.mftplus.shop.service..*.*(..))")
    public Object handleCaching(ProceedingJoinPoint joinPoint) throws Throwable {
        Class<?> clazz = joinPoint.getTarget().getClass();
        CacheableLevel annotation = clazz.getAnnotation(CacheableLevel.class);
        if (annotation == null) return joinPoint.proceed();

        String cacheName = annotation.cacheName();
        String methodSignature = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        String key = methodSignature + Arrays.toString(args);

        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            log.warn("Cache [{}] not found!", cacheName);
            return joinPoint.proceed();
        }
        Cache.ValueWrapper cached = cache.get(key);
        if (cached != null) {
            log.debug("Cache hit [{}] -> key [{}]", cacheName, key);
            return cached.get();
        }

        Object result = joinPoint.proceed();
        cache.put(key, result);
        log.debug("Cache miss [{}] -> caching key [{}]", cacheName, key);
        return result;
    }

}
//--
