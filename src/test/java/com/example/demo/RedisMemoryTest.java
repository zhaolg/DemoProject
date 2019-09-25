package com.example.demo;

import com.example.demo.redis.PECode;
import com.example.demo.redis.RedisKeyValue;
import com.example.demo.redis.RedisValue;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author lingang.zhao
 * @version 1.0
 * @date 2019/9/25 11:24 上午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisMemoryTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private Gson gson = new Gson();

    /**
     * 原始操作，不对任何key value 做处理
     */
    @Test
    public void testOldRedis() {

        String key = "0:1:201155:100:545953888";

        List<RedisKeyValue> values = Lists.newArrayList();
        for (int i = 0; i < 1000001; i++) {
            values.add(RedisKeyValue.builder().expireTime(Integer.MAX_VALUE).key(key + i).
                    value(RedisValue.builder().exp(String.valueOf(Integer.MAX_VALUE)).val("3.6230093077568726-0.3630194103106919" + i).build()).build());
            if (values.size() == 1000) {
                delRedis(values);
                values.clear();
                System.out.println("index" + i);
            }
        }
    }

    /**
     * key 分段进行 16进制压缩
     */
    @Test
    public void testBaseHexRedis() {

        String keyPrefix = "01:201155:";
        long business = 10054595993888L;
        List<RedisKeyValue> values = Lists.newArrayList();
        for (int i = 0; i < 1000001; i++) {
            String val = Long.toHexString(business + i);
            values.add(RedisKeyValue.builder().expireTime(Integer.MAX_VALUE).key(keyPrefix + val).
                    value(RedisValue.builder().exp(String.valueOf(Integer.MAX_VALUE)).val("1").build()).build());
            if (values.size() == 1000) {
                delRedis(values);
                values.clear();
                System.out.println("index" + i);
            }
        }
        if (!CollectionUtils.isEmpty(values)) {
            delRedis(values);
            values.clear();
        }
    }

    /**
     * key 分段进行 64 进制压缩
     */
    @Test
    public void testBase64Redis() {
        String keyPrefix = "01:201155:";
        long business = 10054595993888L;
        List<RedisKeyValue> values = Lists.newArrayList();
        for (int i = 0; i < 1000001; i++) {
            long def = business + i;
            String val = PECode.encode(def);
            values.add(RedisKeyValue.builder().expireTime(Integer.MAX_VALUE).key(keyPrefix + val).
                    value(RedisValue.builder().exp(String.valueOf(Integer.MAX_VALUE)).val("1").build()).build());
            if (values.size() == 1000) {
                delRedis(values);
                values.clear();
                System.out.println("index" + i);
            }
        }
        if (!CollectionUtils.isEmpty(values)) {
            delRedis(values);
            values.clear();
        }
    }

    /**
     * key 不压缩
     */
    @Test
    public void testRedis() {
        String keyPrefix = "01:201155:";
        long business = 10054595993888L;
        List<RedisKeyValue> values = Lists.newArrayList();
        for (int i = 0; i < 1000001; i++) {
            long def = business + i;
            String val = String.valueOf(def);
            values.add(RedisKeyValue.builder().expireTime(Integer.MAX_VALUE).key(keyPrefix + val).
                    value(RedisValue.builder().exp(String.valueOf(Integer.MAX_VALUE)).val("1").build()).build());
            if (values.size() == 1000) {
                delRedis(values);
                values.clear();
                System.out.println("index" + i);
            }
        }
        if (!CollectionUtils.isEmpty(values)) {
            delRedis(values);
            values.clear();
        }
    }

    /**
     * Hash 结构测试
     */
    @Test
    public void testHashRedis() {
        String keyPrefix = "01:201155:";
        long business = 10054595993888L;
        List<RedisKeyValue> values = Lists.newArrayList();
        for (int i = 0; i < 1000001; i++) {
            long def = business + i;
            String val = PECode.encode(def);
            values.add(RedisKeyValue.builder().expireTime(Integer.MAX_VALUE).key(keyPrefix + val).
                    value(RedisValue.builder().exp(String.valueOf(Integer.MAX_VALUE)).val("1").build()).build());
            if (values.size() == 1000) {
                delRedis(values);
                values.clear();
                System.out.println("index" + i);
            }
        }
        if (!CollectionUtils.isEmpty(values)) {
            delRedis(values);
            values.clear();
        }
    }

    private void setJsonRedis(List<RedisKeyValue> values) {
        redisTemplate.executePipelined(new RedisCallback<List<String>>() {
            @Override
            public List<String> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                values.forEach(val -> {
                    redisConnection.stringCommands().setEx(val.getKey().getBytes(), val.getExpireTime(), gson.toJson(val.getValue()).getBytes());
                });
                return null;
            }
        });
    }

    private void setStringRedis(List<RedisKeyValue> values) {
        redisTemplate.executePipelined(new RedisCallback<List<String>>() {
            @Override
            public List<String> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                values.forEach(val -> {
                    String value = "exp:"+ PECode.encode(Long.valueOf(val.getValue().getExp())) ;
                    redisConnection.stringCommands().setEx(val.getKey().getBytes(), val.getExpireTime(), value.getBytes());
                });
                return null;
            }
        });
    }

    private void setHashRedis(List<RedisKeyValue> values) {
        redisTemplate.executePipelined(new RedisCallback<List<String>>() {
            @Override
            public List<String> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                values.forEach(val -> {
                   Map<byte[], byte[]> map = Maps.newHashMap();
                    map.put("exp".getBytes(),val.getValue().getExp().getBytes());
                    map.put("val".getBytes(),val.getValue().getVal().getBytes());
                    redisConnection.hashCommands().hMSet(val.getKey().getBytes(), map);
                });
                return null;
            }
        });
    }

    private void delRedis(List<RedisKeyValue> values) {
        redisTemplate.executePipelined(new RedisCallback<List<String>>() {
            @Override
            public List<String> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                values.forEach(val -> {
                    redisConnection.del(val.getKey().getBytes());
                });
                return null;
            }
        });
    }

}
