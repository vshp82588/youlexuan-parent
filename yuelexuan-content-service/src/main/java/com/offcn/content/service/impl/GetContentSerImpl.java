package com.offcn.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.offcn.content.service.IGetContentSer;
import com.offcn.mapper.TbContentCategoryMapper;
import com.offcn.mapper.TbContentMapper;
import com.offcn.pojo.TbContent;
import com.offcn.pojo.TbContentCategory;
import com.offcn.pojo.TbContentCategoryExample;
import com.offcn.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class GetContentSerImpl implements IGetContentSer {

    @Autowired
    private TbContentMapper tb;

    @Autowired
    private TbContentCategoryMapper tc;
    @Override
    public List getMsgContent() {
        TbContentExample tbContentExample = new TbContentExample();
        List<TbContent> tbContents = tb.selectByExample(tbContentExample);
        return tbContents;
    }

    @Override
    public List getMsgConCateroey() {
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        List<TbContentCategory> tbContentCategories = tc.selectByExample(tbContentCategoryExample);

        return tbContentCategories;
    }

    @Override
    public int AddMsgCon(int ss,String title,String url,int num,boolean selected) {
        String status ="0";
        TbContent tbContent = new TbContent();
        tbContent.setCategoryId((long)ss);
        tbContent.setTitle(title);
        tbContent.setSortOrder(num);
        tbContent.setUrl(url);
        if(selected==true){
            status="1";
        }
        tbContent.setStatus(status);
        int insert = tb.insert(tbContent);


        return insert;
    }
}
