package com.offcn.shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/manager")
public class ManagerGoods {


    @RequestMapping("/goods")
    public Map getGoods(){


        return null;
    }
}
