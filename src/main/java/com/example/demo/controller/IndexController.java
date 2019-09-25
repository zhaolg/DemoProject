package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lingang.zhao
 * @version 1.0
 * @date 2019-08-16 14:13
 */
@Slf4j
@RestController
public class IndexController {

    @RequestMapping(path = "/")
    public String test1() {
        String request = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>周杰伦</title>\n" +
                "</head>\n" +

                "<center>\n" +
                "    <body>\n" +
                "<img src=\"https://tool.lu/netcard/\">" +
                "    </body>\n" +
                "</center>\n" +
                "</html>";
        return request;
    }


}
