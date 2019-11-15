package com.offcn.usercenter.service;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbUser;

public interface UserService {
    /**
     * 带有条件的分页查询
     * @param pageNum
     * @param pageSize
     * @param tbUser
     * @return
     */
    public PageResult findPage(int pageNum, int pageSize, TbUser tbUser);


    /**
     * 批量删除
     * @param ids
     */
    public void delete(Long[] ids);
}
