package com.offcn.service.custom;

import com.offcn.pojo.TbSeller;
import com.offcn.sellergoods.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {

    /*@Reference
    SellerService sellerService;*/
    /*
    * 注入服务
    * */
    SellerService sellerService;
    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    /*
    * 自定义认证
    * */
    @Override
    public UserDetails loadUserByUsername(String sellerId) throws UsernameNotFoundException {
        //sellerId 从表单中输入的账号

        //根据sellerId查询正确的密码，返回一个当前sellerId对应的账号信息（账号+密码+角色）
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority("ROLE_SELLER"));//构建一个角色列表,当前用户的角色可以从数据库中读取

        /*// 表单中输入 任意的sellerid，密码输入  123qwe
        if(sellerId.equals("admin")){
            //页面上输入  admin  123qwe ，认证成功
            return new User(sellerId,"123qwe",list);
        }

        if(sellerId.equals("root")){
            return new User(sellerId,"123456",list);
        }*/

        //查询sellerId正确的密码
        TbSeller one = sellerService.findOne(sellerId);


        if(one==null){
            return null;//认证失败
        }else{
            if(one.getStatus().equals("1")){//status=1 审核通过，可以登录
                String password = one.getPassword();
                return new User(sellerId,password,list);//如果表单中的password和数据库中读取到的password一致，说明认证成功
            }else{
                return null;
            }
        }

    }
}
