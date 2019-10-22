package com.self.kitchen.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

@Component
public class MyRealm extends AuthorizingRealm {
   //授权 查询登陆用户的角色或者权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {


        return null;
    }
     //认证 生成凭证 登陆成功
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token =(UsernamePasswordToken)authenticationToken;
        if(token.getUsername()!=null){
            //登陆成功
            AuthenticationInfo info=new SimpleAuthenticationInfo(token.getPrincipal(),token.getPassword(),getName());
            return info;
        }
        return null;
    }
}
