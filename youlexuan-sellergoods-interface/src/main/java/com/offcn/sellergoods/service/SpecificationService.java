package com.offcn.sellergoods.service;

import com.offcn.entity.SpecificationVO;
import com.offcn.pojo.TbSpecification;

import java.util.List;

public interface SpecificationService {
    public List<TbSpecification> findAll();
    public void add(SpecificationVO specificationVO);
    public SpecificationVO findOne(Long id);
    public void updateSpecificationAndOption(SpecificationVO specificationVO);

    /**
     *  删除规格以及级联删除规格选项
     * @param id 规格的主键
     */
    public void delete(Long[] id);

    /**
     * 条件查询
     * @return
     */
    public List<TbSpecification> findAll(TbSpecification specification);
}
