package com.app.realm;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhangminjie on 15/5/20.
 */
public class StatelessAuthcFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //servletRequest.get
       HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String token=request.getHeader("token");
        if (!"get".equals(request.getMethod().toLowerCase())) {
            response.getWriter().write("Method  error！！");
            return false;
        }
        if (null==token||"".equals(token)){
            response.getWriter().write("Token is null！！");
            return false;
        }

        String pwd = "c8ba2fb1600e2e52e4a0fb7f49ae5634";
         pwd=getToken(pwd);
        String uid="huawei";
        StatelessToken statelessToken = new StatelessToken(uid, pwd, pwd);
        try {
//            SecurityUtils.getSubject().
            super.getSubject(request, response).login(statelessToken);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        //servletRequest.getParameter("")
        return true;
    }


    private String getToken(String pwd) {
       // String pwd="Pr0d1234";
        String name = "huawei";
        String ss="123456";
        //String ss =new SecureRandomNumberGenerator().nextBytes().toHex();
        SimpleHash simpleHash=new SimpleHash("md5",pwd,name+ss,3);
        return simpleHash.toHex();
    }
}
