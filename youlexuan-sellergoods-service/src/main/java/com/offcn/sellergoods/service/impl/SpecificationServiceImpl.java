package com.offcn.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.offcn.entity.SpecificationVO;
import com.offcn.mapper.TbSpecificationMapper;
import com.offcn.mapper.TbSpecificationOptionMapper;
import com.offcn.pojo.TbSpecification;
import com.offcn.pojo.TbSpecificationExample;
import com.offcn.pojo.TbSpecificationOption;
import com.offcn.pojo.TbSpecificationOptionExample;
import com.offcn.sellergoods.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    TbSpecificationMapper tbSpecificationMapper;

    @Autowired
    TbSpecificationOptionMapper optionMapper;


    @Override
    public List<TbSpecification> findAll() {
        return tbSpecificationMapper.selectByExample(null);
    }

    @Override
    public void add(SpecificationVO specificationVO) {
        //规格表
        TbSpecification specification = specificationVO.getSpecification();
        //添加spcification要返回id，给规格选项的外键

        tbSpecificationMapper.insert(specification);

        Long id = specification.getId();//返回的主键

        List<TbSpecificationOption> list = specificationVO.getSpecificationOption();

        for (TbSpecificationOption option:list) {
            option.setSpecId(id);
            optionMapper.insert(option);
        }

    }

    @Override
    public SpecificationVO findOne(Long id) {

        TbSpecification specification = tbSpecificationMapper.selectByPrimaryKey(id);

        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);

        List<TbSpecificationOption> options = optionMapper.selectByExample(example);

        SpecificationVO specificationVO = new SpecificationVO();
        specificationVO.setSpecification(specification);
        specificationVO.setSpecificationOption(options);


        return specificationVO;
    }

    @Override
    public void updateSpecificationAndOption(SpecificationVO specificationVO) {

        //有id
        TbSpecification specification = specificationVO.getSpecification();
        tbSpecificationMapper.updateByPrimaryKey(specification);

        //先将规格选项删除，再来重新添加
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(specification.getId());//外键     delete from option where specid = 11
        optionMapper.deleteByExample(example);

        List<TbSpecificationOption> list = specificationVO.getSpecificationOption();
        for(TbSpecificationOption option : list){
            option.setSpecId(specification.getId());
            optionMapper.insert(option);
        }

    }

    @Override
    public void delete(Long[] ids) {
        for (Long id: ids) {
            tbSpecificationMapper.deleteByPrimaryKey(id);
            //删除规格选项
            TbSpecificationOptionExample example = new TbSpecificationOptionExample();
            TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(id);
            optionMapper.deleteByExample(example);
        }
    }

    @Override
    public List<TbSpecification> findAll(TbSpecification specification) {


        TbSpecificationExample example = new TbSpecificationExample();
        TbSpecificationExample.Criteria criteria = example.createCriteria();

        if(specification!=null && specification.getSpecName()!=null){
            criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
        }

        return tbSpecificationMapper.selectByExample(example);

    }
}
