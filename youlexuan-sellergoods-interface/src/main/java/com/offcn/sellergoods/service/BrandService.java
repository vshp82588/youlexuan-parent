package com.offcn.sellergoods.service;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbBrand;

import java.util.List;

public interface BrandService {
    /**
     * 查询所有的品牌列表，不带分页
     * @return
     */
    public List<TbBrand> findAll();


    /**
     * 分页查询
     * @param pageNum  第几页
     * @param pageSize  每页显示多少条
     * @return
     */
    public PageResult findPage(int pageNum , int pageSize);


    /**
     * 带有条件的分页查询
     * @param pageNum
     * @param pageSize
     * @param tbBrand
     * @return
     */
    public PageResult findPage(int pageNum , int pageSize,TbBrand tbBrand);




    /**
     * 新增品牌
     * @param brand
     */
    public void add(TbBrand brand);


    /**
     * 根据id查询
     * @param id
     * @return
     */
    public TbBrand findOne(Long id);


    /**
     * 修改（根据id修改）
     * @param tbBrand
     */
    public void update(TbBrand tbBrand);


    /**
     * 删除
     * @param ids
     */
    public void delete(Long[] ids);

}
