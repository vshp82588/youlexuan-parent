package com.offcn.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.offcn.entity.PageResult;
import com.offcn.mapper.TbSellerMapper;
import com.offcn.pojo.TbSeller;
import com.offcn.pojo.TbSellerExample;
import com.offcn.pojo.TbSellerExample.Criteria;
import com.offcn.sellergoods.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 商户服务实现层
 *
 * @author Administrator
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private TbSellerMapper sellerMapper;

    /**
     * 查询全部
     */
    @Override
    public List<TbSeller> findAll() {
        return sellerMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbSeller> page = (Page<TbSeller>) sellerMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(TbSeller seller) {
        seller.setStatus("0");//未审核
        seller.setCreateTime(new Date());
        sellerMapper.insert(seller);
    }


    /**
     * 修改
     */
    @Override
    public void update(TbSeller seller) {
        sellerMapper.updateByPrimaryKey(seller);
    }

    /**
     * 根据ID获取实体
     *
     * @param
     * @return
     */
    @Override
    public TbSeller findOne(String sellerId) {
        return sellerMapper.selectByPrimaryKey(sellerId);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(String[] sellerIds) {
        for (String sellerId : sellerIds) {
            sellerMapper.deleteByPrimaryKey(sellerId);
        }
    }


    @Override
    public PageResult findPage(TbSeller seller, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbSellerExample example = new TbSellerExample();
        Criteria criteria = example.createCriteria();

        if (seller != null) {
            if (seller.getName() != null && seller.getName().length() > 0) {
                criteria.andNameLike("%" + seller.getName() + "%");
            }
            if (seller.getNickName() != null && seller.getNickName().length() > 0) {
                criteria.andNickNameLike("%" + seller.getNickName() + "%");
            }


            if (seller.getStatus() != null && seller.getStatus().length() > 0) {

//				criteria.andStatusLike("%"+seller.getStatus()+"%");
                // status = 1,2,3
                String[] split = seller.getStatus().split(",");

                //  1,2,3
                List<String> strings = Arrays.asList(split);
                criteria.andStatusIn(strings);//select * from seller where status in (1,2)
            }

        }

        Page<TbSeller> page = (Page<TbSeller>) sellerMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void updateStatus(String sellerId, String stauts) {
		/*TbSeller seller = sellerMapper.selectByPrimaryKey(sellerId);
		seller.setStatus(stauts);
		sellerMapper.updateByPrimaryKey(seller);*/

        TbSeller seller = new TbSeller();
        seller.setSellerId(sellerId);
        seller.setStatus(stauts);

        sellerMapper.updateByPrimaryKeySelective(seller);
    }

    public static void main(String[] args) {
        String status = "1,2,3,6";
        String[] split = status.split(",");
        System.out.println(split);
    }

}
