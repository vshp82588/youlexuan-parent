package com.offcn.sellergoods.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.content.service.IGetContentSer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Reference
    private IGetContentSer ig;
    @RequestMapping("/get")
    public Map get(){
        Map map = new HashMap();
        List msgContent = ig.getMsgContent();
        map.put("list",msgContent);

        return map;
    }
    @RequestMapping("/add")
    public Map add(@RequestParam(value = "ss",required = false) int ss,
                   @RequestParam(value = "title",required = false) String title,
                   @RequestParam(value = "url",required = false) String url,
                   @RequestParam(value = "num",required = false) int num,
                   @RequestParam(value = "selected",required = false) boolean selected){
        System.out.println(ss);
        Map map = new HashMap();
        if (title != null && ss != 0){
            ig.AddMsgCon(ss,title,url,num,selected);
        }

        List msgConCateroey = ig.getMsgConCateroey();
        map.put("list",msgConCateroey);

        return map;
    }
}
