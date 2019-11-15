package com.offcn.sellergoods.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.entity.PageResult;
import com.offcn.entity.Result;
import com.offcn.pojo.TbBrand;
import com.offcn.sellergoods.service.BrandService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    BrandService brandService;

    //查询所有，不带有分页
    @RequestMapping("/findAll")
    public List<TbBrand> findAll(){
        return brandService.findAll();
    }

    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(int pageNum , int pageSize){   // url?pageNum=1&pageSize=2
        return brandService.findPage(pageNum,pageSize);
    }

    //@ResponseBody响应成json数据
    //@RequestBody发送过来的json转换成pojo
    @RequestMapping("/add")
    public Result add( @RequestBody TbBrand tbBrand){  //angular传递json过来，将json转换成pojo
        if(tbBrand.getFirstChar().length()>1){
            return new Result(false,"首字母长度不能大于1");
        }
        try {
            brandService.add(tbBrand);
            return new Result(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }

    @RequestMapping("/findOne")
    public TbBrand findOne( @RequestParam("tbid") Long id){ // ?id
        return brandService.findOne(id);
    }


    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand tbBrand){
        try {
            brandService.update(tbBrand);
            return new Result(true,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"修改成功");
        }
    }

    @RequestMapping("/delete")
    public Result delete(Long[] ids){
        try {
            brandService.delete(ids);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除成功");
        }
    }


    //带分页的条件查询
    @RequestMapping("/search")
    public PageResult search(int pageNum , int pageSize,@RequestBody TbBrand brand){
        PageResult page = brandService.findPage(pageNum, pageSize, brand);
        return page;
    }

}
