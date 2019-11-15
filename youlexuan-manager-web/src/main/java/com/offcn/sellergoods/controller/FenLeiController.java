package com.offcn.sellergoods.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.sellergoods.service.IFenLeiService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fenlei")
public class FenLeiController {
    @Reference
    private  IFenLeiService ifen;
    @RequestMapping("/get")
    public Map getFenLei() {
        Map map = new HashMap();
        List list = new ArrayList();
         list = ifen.getFenLeiMSgSer();
         map.put("list",list);

        return map;
    }
}
