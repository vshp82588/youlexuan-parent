package com.offcn.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.offcn.mapper.TbItemCatMapper;
import com.offcn.pojo.TbItemCat;
import com.offcn.pojo.TbItemCatExample;
import com.offcn.sellergoods.service.IFenLeiService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class FenLeiServiceImpl implements IFenLeiService {
    @Autowired
    private TbItemCatMapper tb;
    @Override
    public List getFenLeiMSgSer() {
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
        criteria.andParentIdEqualTo((long)0);
        List<TbItemCat> tbItemCats = tb.selectByExample(tbItemCatExample);
        System.out.println(tbItemCats);

        return tbItemCats;
    }
}
