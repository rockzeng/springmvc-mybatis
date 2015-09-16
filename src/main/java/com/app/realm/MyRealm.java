package com.app.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * Created by zhangminjie on 15/5/18.
 */
public class MyRealm extends AuthorizingRealm {

    public boolean supports(AuthenticationToken token) {
        //仅支持StatelessToken类型的Token
        return token instanceof StatelessToken||token instanceof UsernamePasswordToken;
    }
    //private static final long serialVersionUID = 1L;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String currentUsername = (String)super.getAvailablePrincipal(principalCollection);
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        //实际中可能会像上面注释的那样从数据库取得
        if(null!=currentUsername && "zhang".equals(currentUsername)){
            //添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
            simpleAuthorInfo.addRole("admin");
            //添加权限
            simpleAuthorInfo.addStringPermission("admin:manage");
            System.out.println("已为用户[zhangsan]赋予了[admin]角色和[admin:manage]权限");
            return simpleAuthorInfo;
        }else if(null!=currentUsername && "lisi".equals(currentUsername)){
            System.out.println("当前用户[lisi]无授权");
            return simpleAuthorInfo;
        }
        //若该方法什么都不做直接返回null的话,就会导致任何用户访问/admin/listUser.jsp时都会自动跳转到unauthorizedUrl指定的地址
        //详见applicationContext.xml中的<bean id="shiroFilter">的配置
//        return null;
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken instanceof StatelessToken) {
            return api(authenticationToken);
        }
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        if ("zhangsan".equals(upToken.getUsername())) {
            AuthenticationInfo authenticationInfo =
                    new SimpleAuthenticationInfo("zhangsan","zhangsan",this.getName());
            this.setSession("currentUser", "zhangsan");
            //Base64.decode()
            //Md5Hash().fromBase64String()
            return authenticationInfo;
//            simpleAuthorizationInfo.
        }
        return null;
    }

    private AuthenticationInfo api(AuthenticationToken authenticationToken) {
        StatelessToken statelessToken = (StatelessToken) authenticationToken;
        //statelessToken.getUsername();
        //statelessToken.getTokenStr();
        String pwd="c8ba2fb1600e2e52e4a0fb7f49ae5634";
        pwd = getToken(statelessToken.getUsername(), pwd);
        SimpleAuthenticationInfo ai = new SimpleAuthenticationInfo("huawei",pwd,"huawei");
        //ai.setCredentialsSalt(ByteSource.Util.bytes(statelessToken.getUsername()+statelessToken.getTokenStr()));
        return ai;
    }

    private void setSession(Object key, Object value){
        Subject currentUser = SecurityUtils.getSubject();
        if(null != currentUser){
            Session session = currentUser.getSession();
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if(null != session){
                session.setAttribute(key, value);
            }
        }
    }
    private String getToken(String name,String pwd) {
        //String pwd="Pr0d1234";
        //String name = "huawei";
        String ss = "123456";
        //String ss =new SecureRandomNumberGenerator().nextBytes().toHex();
        SimpleHash simpleHash=new SimpleHash("md5",pwd,name+ss,3);
        return simpleHash.toHex();
    }
}
