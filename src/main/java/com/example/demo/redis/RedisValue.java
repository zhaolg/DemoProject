package com.example.demo.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lingang.zhao
 * @version 1.0
 * @date 2019/9/25 12:33 下午
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RedisValue {
    private String val;
    private String exp;
}
