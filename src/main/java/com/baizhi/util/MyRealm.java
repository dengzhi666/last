package com.baizhi.util;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Authority;
import com.baizhi.entity.Role;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Override //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("进行认证中");
        AdminDao adminDao = (AdminDao)SpringContextUtil.getBean(AdminDao.class);
        String username = (String) authenticationToken.getPrincipal();
        System.out.println(username);
        Admin admin = adminDao.selectByName(username);
        if(admin == null){
            return null;
        }else{
            SimpleAuthenticationInfo s = new SimpleAuthenticationInfo(username,admin.getPassword(), ByteSource.Util.bytes(admin.getSalt()),this.getName());
            return s;
        }
    }


    @Override  // 授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进行授权中");
        String username = (String) principalCollection.getPrimaryPrincipal();
        AdminDao adminDao = (AdminDao)SpringContextUtil.getBean(AdminDao.class);
        Admin admin = adminDao.selectByName(username);
        List<Role> roles = admin.getRoles();

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : roles) {
            simpleAuthorizationInfo.addRole(role.getRoleName());
            List<Authority> authorities = role.getAuthorities();
            for (Authority authority : authorities) {
                simpleAuthorizationInfo.addStringPermission(authority.getAuthorityName());
            }
        }
        return  simpleAuthorizationInfo;
    }

}
