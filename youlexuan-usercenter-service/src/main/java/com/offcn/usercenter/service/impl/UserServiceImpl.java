package com.offcn.usercenter.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.offcn.entity.PageResult;
import com.offcn.mapper.TbUserMapper;
import com.offcn.pojo.TbUser;
import com.offcn.pojo.TbUserExample;
import com.offcn.usercenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    TbUserMapper userMapper;

    @Override
    public PageResult findPage(int pageNum, int pageSize, TbUser tbUser) {

        //1.设置分页参数
        PageHelper.startPage(pageNum,pageSize);

        TbUserExample tbUserExample = new TbUserExample();//select * from user;
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();//拼装where后的内容的条件

        //会员来源（source_type）  用户名（模糊查询）  注册手机号（phone）
        if(tbUser!=null){
            if(tbUser.getSourceType()!=null && tbUser.getSourceType().length()>0){
//            if(tbUser.getSourceType().length()>0 && tbUser.getSourceType()!=null ){
                //添加"会员来源"
               /* criteria.andUsernameLike();
                criteria.andNameLike();  select * from user where username like ? and name like ?*/
               criteria.andSourceTypeEqualTo(tbUser.getSourceType());
            }
            if(tbUser.getName()!=null && tbUser.getName().length()>0){
                criteria.andNameLike("%"+tbUser.getName()+"%");
            }
        }

        Page<TbUser> page = (Page<TbUser>) userMapper.selectByExample(tbUserExample);

        PageResult pageResult = new PageResult();
        pageResult.setTotal(page.getTotal());//总记录数
        pageResult.setRows(page.getResult());//当前页的数据

        return pageResult;
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id :ids) {
            userMapper.deleteByPrimaryKey(id);
        }

        /*TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        List<Long> list = Arrays.asList(ids);
        criteria.andIdIn(list);
        // delete from user where id in （1,3,4）；
        userMapper.deleteByExample(tbUserExample);*/

    }
}
