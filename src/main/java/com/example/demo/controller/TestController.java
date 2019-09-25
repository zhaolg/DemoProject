package com.example.demo.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lingang.zhao
 * @version 1.0
 * @date 2019-08-16 14:13
 */
@Slf4j
@RestController
public class TestController {

    @RequestMapping(path = "/1")
    public String test1() {
        initFlowRules();
        try (Entry entry = SphU.entry("HelloWorld")) {
            // 被保护的逻辑
//            Thread.sleep(1000);
            System.out.println("hello world");
        } catch (BlockException ex) {
            // 处理被流控的逻辑
            System.out.println("blocked!");
            return "sentinel is error";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return "sentinel is 1";
    }

    @RequestMapping(path = "/2")
    @SentinelResource("HelloWorld")
    public String test2() {
        initFlowRules();
        try {
//            Thread.sleep(1000);
            System.out.println("hello world");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "sentinel is 2";
    }

    private static void initFlowRules() {
        List<FlowRule> rules = Lists.newArrayList();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(1);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

}
