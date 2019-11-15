package com.offcn.sellergoods.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.entity.PageResult;
import com.offcn.entity.Result;
import com.offcn.pojo.TbUser;
import com.offcn.usercenter.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usercenter")
public class UserController {

    @Reference
    UserService userService;

    @RequestMapping("/findPage")
    public PageResult findPage(int pageNum, int pageSize, @RequestBody TbUser tbUser){
        PageResult page = userService.findPage(pageNum, pageSize, tbUser);
        return page;
    }


    @RequestMapping("/delete")
    public Result delete(Long[] ids){
        try {
            userService.delete(ids);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

}
