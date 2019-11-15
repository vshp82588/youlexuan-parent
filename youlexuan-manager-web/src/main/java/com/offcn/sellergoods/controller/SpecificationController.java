package com.offcn.sellergoods.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.entity.Result;
import com.offcn.entity.SpecificationVO;
import com.offcn.pojo.TbSpecification;
import com.offcn.sellergoods.service.SpecificationService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/specification")
public class SpecificationController {

    @Reference
    SpecificationService specificationService;

    @RequestMapping("/findAll")
    public List<TbSpecification> findAll(){
        return specificationService.findAll();
    }

    @RequestMapping("/add")
    public Result add(@RequestBody SpecificationVO specificationVO){
        try {
            specificationService.add(specificationVO);
            return new Result(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }

    @RequestMapping("/findOne")
    public SpecificationVO findOne(Long id){
        SpecificationVO one = specificationService.findOne(id);
        return one;
    }

    @RequestMapping("/update")
    public Result update(@RequestBody SpecificationVO specificationVO){
        try {
            specificationService.updateSpecificationAndOption(specificationVO);
            return new Result(true,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true,"修改失败");
        }
    }

    @RequestMapping("/delete")
    public Result delete(Long[]  ids){
        try {
            specificationService.delete(ids);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

    @RequestMapping("/findByCondition")
    public List<TbSpecification> findByCondition(@RequestBody TbSpecification specification){
        List<TbSpecification> all = specificationService.findAll(specification);
        return all;
    }

}
