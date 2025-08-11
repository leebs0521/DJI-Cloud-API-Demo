package com.dji.sample.component.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis 작업 유틸리티 클래스
 * 
 * Redis 데이터베이스와의 상호작용을 위한 유틸리티 메서드들을 제공합니다.
 * Hash, String, List, ZSet 등 다양한 Redis 데이터 타입에 대한 작업을 지원합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/4/19
 */
@Component
public class RedisOpsUtils {

    /** Redis 템플릿 */
    private static RedisTemplate<String, Object> redisTemplate;

    /**
     * Redis 템플릿을 설정합니다.
     * 
     * @param redisTemplate Redis 템플릿
     */
    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisOpsUtils.redisTemplate = redisTemplate;
    }

    /**
     * Hash 타입에 필드를 설정합니다. (HSET)
     * 
     * @param key Redis 키
     * @param field Hash 필드
     * @param value 설정할 값
     */
    public static void hashSet(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * Hash 타입에서 필드 값을 가져옵니다. (HGET)
     * 
     * @param key Redis 키
     * @param field Hash 필드
     * @return 필드 값
     */
    public static Object hashGet(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * Hash 타입의 모든 필드를 가져옵니다. (HKEYS)
     * 
     * @param key Redis 키
     * @return 모든 필드 집합
     */
    public static Set<Object> hashKeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * Hash 타입에서 필드가 존재하는지 확인합니다. (HEXISTS)
     * 
     * @param key Redis 키
     * @param field Hash 필드
     * @return 필드 존재 여부
     */
    public static boolean hashCheck(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * Hash 타입에서 필드를 삭제합니다. (HDEL)
     * 
     * @param key Redis 키
     * @param fields 삭제할 필드 배열
     * @return 삭제 성공 여부
     */
    public static boolean hashDel(String key, Object[] fields) {
        return redisTemplate.opsForHash().delete(key, fields) > 0;
    }

    /**
     * Hash 타입의 필드 개수를 가져옵니다. (HLEN)
     * 
     * @param key Redis 키
     * @return 필드 개수
     */
    public static long hashLen(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 키의 만료 시간을 설정합니다. (EXPIRE)
     * 
     * @param key Redis 키
     * @param timeout 만료 시간 (초)
     * @return 설정 성공 여부
     */
    public static boolean expireKey(String key, long timeout) {
        return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * String 타입에 값을 설정합니다. (SET)
     * 
     * @param key Redis 키
     * @param value 설정할 값
     */
    public static void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * String 타입에서 값을 가져옵니다. (GET)
     * 
     * @param key Redis 키
     * @return 저장된 값
     */
    public static Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * String 타입에 값을 설정하고 만료 시간을 지정합니다. (SETEX)
     * 
     * @param key Redis 키
     * @param value 설정할 값
     * @param expire 만료 시간 (초)
     */
    public static void setWithExpire(String key, Object value, long expire) {
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    /**
     * 키의 남은 만료 시간을 가져옵니다. (TTL)
     * 
     * @param key Redis 키
     * @return 남은 만료 시간 (초)
     */
    public static long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 키가 존재하는지 확인합니다. (EXISTS)
     * 
     * @param key Redis 키
     * @return 키 존재 여부
     */
    public static boolean checkExist(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 키를 삭제합니다. (DEL)
     * 
     * @param key Redis 키
     * @return 삭제 성공 여부
     */
    public static boolean del(String key) {
        return RedisOpsUtils.checkExist(key) && redisTemplate.delete(key);
    }

    /**
     * 패턴에 맞는 모든 키를 가져옵니다. (KEYS)
     * 
     * @param pattern 키 패턴
     * @return 패턴에 맞는 키 집합
     */
    public static Set<String> getAllKeys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * List 타입에 값을 오른쪽에 추가합니다. (RPUSH)
     * 
     * @param key Redis 키
     * @param value 추가할 값들
     */
    public static void listRPush(String key, Object... value) {
        if (value.length == 0) {
            return;
        }
        for (Object val : value) {
            redisTemplate.opsForList().rightPush(key, val);
        }
    }

    /**
     * List 타입에서 범위의 값을 가져옵니다. (LRANGE)
     * 
     * @param key Redis 키
     * @param start 시작 인덱스
     * @param end 끝 인덱스
     * @return 범위의 값 리스트
     */
    public static List<Object> listGet(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * List 타입의 모든 값을 가져옵니다. (LRANGE)
     * 
     * @param key Redis 키
     * @return 모든 값 리스트
     */
    public static List<Object> listGetAll(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * List 타입의 길이를 가져옵니다. (LLEN)
     * 
     * @param key Redis 키
     * @return 리스트 길이
     */
    public static Long listLen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * ZSet 타입에 값을 추가합니다. (ZADD)
     * 
     * @param key Redis 키
     * @param value 추가할 값
     * @param score 점수
     * @return 추가 성공 여부
     */
    public static Boolean zAdd(String key, Object value, double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * ZSet 타입에서 값을 삭제합니다. (ZREM)
     * 
     * @param key Redis 키
     * @param value 삭제할 값들
     * @return 삭제 성공 여부
     */
    public static Boolean zRemove(String key, Object... value) {
        return redisTemplate.opsForZSet().remove(key, value) > 0;
    }

    /**
     * ZSet 타입에서 범위의 값을 가져옵니다. (ZRANGE)
     * 
     * @param key Redis 키
     * @param start 시작 인덱스
     * @param end 끝 인덱스
     * @return 범위의 값 집합
     */
    public static Set<Object> zRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * ZSet 타입에서 최소 점수의 값을 가져옵니다. (ZRANGE)
     * 
     * @param key Redis 키
     * @return 최소 점수의 값
     */
    public static Object zGetMin(String key) {
        Set<Object> objects = zRange(key, 0, 0);
        if (CollectionUtils.isEmpty(objects)) {
            return null;
        }
        return objects.iterator().next();
    }

    /**
     * ZSet 타입에서 값의 점수를 가져옵니다. (ZSCORE)
     * 
     * @param key Redis 키
     * @param value 조회할 값
     * @return 값의 점수
     */
    public static Double zScore(String key, Object value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * ZSet 타입에서 값의 점수를 증가시킵니다. (ZINCRBY)
     * 
     * @param key Redis 키
     * @param value 점수를 증가시킬 값
     * @param delta 증가시킬 점수
     * @return 증가된 점수
     */
    public static Double zIncrement(String key, Object value, double delta) {
        return redisTemplate.opsForZSet().incrementScore(key, value, delta);
    }
}
