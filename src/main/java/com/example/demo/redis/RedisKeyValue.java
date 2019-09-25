package com.example.demo.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lingang.zhao
 * @version 1.0
 * @date 2019/9/25 11:32 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RedisKeyValue {
    private String key;
    private long expireTime;
    private RedisValue value;

}