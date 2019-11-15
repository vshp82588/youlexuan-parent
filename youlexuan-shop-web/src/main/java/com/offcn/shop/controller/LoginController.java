package com.offcn.shop.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

//    @Reference
//    private RedisTemplate redisTemplate;

    @RequestMapping("/name")
    public Map getName(){
        String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
        Map map = new HashMap();
        map.put("sellerId",sellerId);

        Date date = new Date();

        Object key ="";

        System.out.println(sellerId);
        /*Boolean aBoolean = redisTemplate.hasKey(sellerId);*/
       /* if(aBoolean){
            key = redisTemplate.boundHashOps(sellerId).values();
        }else {
            key =date;
        }*/
        System.out.println(key);
        map.put("date",key);

        //redisTemplate.boundHashOps(sellerId).put(sellerId,date);


        return map;
    }

}
