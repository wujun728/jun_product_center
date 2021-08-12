package cc.mrbird.common.service;

import cc.mrbird.common.domain.RedisInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisService {

    /**
     * 获取 redis 的详细信息
     *
     * @return List
     */
    List<RedisInfo> getRedisInfo();

    /**
     * 获取 redis key 数量
     *
     * @return Map
     */
    Map<String, Object> getKeysSize();

    /**
     * 获取 redis 内存信息
     *
     * @return Map
     */
    Map<String, Object> getMemoryInfo();

    /**
     * 获取 key
     * @param pattern 正则
     * @return Set
     */
    Set<String> getKeys(String pattern);

    /**
     * get命令
     *
     * @param key key
     * @return String
     */
    String get(String key);

    /**
     * set命令
     *
     * @param key   key
     * @param value value
     * @return String
     */
    String set(String key, String value);

    /**
     * del命令
     *
     * @param key key
     * @return Long
     */
    Long del(String... key);

    /**
     * exists命令
     *
     * @param key key
     * @return Boolean
     */
    Boolean exists(String key);

    /**
     * pttl命令
     *
     * @param key key
     * @return Long
     */
    Long pttl(String key);

    /**
     * pexpire命令
     *
     * @param key         key
     * @param milliscends 毫秒
     * @return Long
     */
    Long pexpire(String key, Long milliscends);
}
