package com.offcn.entity;

//自定义的组合实体类

import com.google.gson.Gson;
import com.offcn.pojo.TbSpecification;
import com.offcn.pojo.TbSpecificationOption;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/*
entity = {
        specification:{'specName':'颜色'},
        specificationOption:[
            {
            'optionName':'黑色',
            'orders':'1'
            },
            {
            'optionName':'白色',
            'orders':'2'
            }
        ]
}*/
public class SpecificationVO implements Serializable {

    TbSpecification specification;
    List<TbSpecificationOption> specificationOption;



    public TbSpecification getSpecification() {
        return specification;
    }

    public void setSpecification(TbSpecification specification) {
        this.specification = specification;
    }

    public List<TbSpecificationOption> getSpecificationOption() {
        return specificationOption;
    }

    public void setSpecificationOption(List<TbSpecificationOption> specificationOption) {
        this.specificationOption = specificationOption;
    }

    public static void main(String[] args) {
        TbSpecification specification = new TbSpecification();
        specification.setSpecName("颜色");

        TbSpecificationOption option = new TbSpecificationOption();
        option.setOptionName("白色");
        option.setOrders(1);
        TbSpecificationOption option2 = new TbSpecificationOption();
        option2.setOptionName("黑色");
        option2.setOrders(2);

        List<TbSpecificationOption> options = Arrays.asList(option, option2);

        SpecificationVO specificationVO = new SpecificationVO();
        specificationVO.setSpecification(specification);
        specificationVO.setSpecificationOption(options);

        Gson gson = new Gson();
        String s = gson.toJson(specificationVO);
        System.out.println(s);

    }
}
