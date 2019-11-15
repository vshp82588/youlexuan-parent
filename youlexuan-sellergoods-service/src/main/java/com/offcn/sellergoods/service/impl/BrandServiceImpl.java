package com.offcn.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.offcn.entity.PageResult;
import com.offcn.mapper.TbBrandMapper;
import com.offcn.pojo.TbBrand;
import com.offcn.pojo.TbBrandExample;
import com.offcn.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.github.pagehelper.PageHelper.startPage;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    TbBrandMapper brandMapper;

    @Override
    public List<TbBrand> findAll() {
        List<TbBrand> tbBrands = brandMapper.selectByExample(null);//查询所有
        return tbBrands;
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {

        startPage(pageNum,pageSize);
        Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(null);

        PageResult pageResult = new PageResult();
        pageResult.setRows(page.getResult());
        pageResult.setTotal(page.getTotal());

        return pageResult;
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize, TbBrand tbBrand) {

        startPage(pageNum,pageSize);
        //设置查询条件
        //你想查询哪一个pojo，就new一个 对应的Example对象
        TbBrandExample tbBrandExample = new TbBrandExample();//构造查询条件
        TbBrandExample.Criteria criteria = tbBrandExample.createCriteria();

        //select * from tb_brand

        if(tbBrand!=null){
            if(tbBrand.getName()!=null && tbBrand.getName().length()>0){
                //查询条件中有name
                criteria.andNameLike("%"+tbBrand.getName()+"%"); //select * from tb_brand  where name like ?
            }

            if(tbBrand.getFirstChar()!=null && tbBrand.getFirstChar().length()>0){
                //查询条件有首字母
                criteria.andFirstCharEqualTo(tbBrand.getFirstChar());// select * from tb_brand  where name like ? and first_char = ?
            }
        }

        Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(tbBrandExample);

        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void add(TbBrand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public TbBrand findOne(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(TbBrand tbBrand) {
        //注意：tbBrand需要有id主键
        brandMapper.updateByPrimaryKey(tbBrand);
    }

    @Override
    public void delete(Long[] ids) {
        for(Long id:ids){
            brandMapper.deleteByPrimaryKey(id);
        }
    }
}
