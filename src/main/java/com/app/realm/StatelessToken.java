package com.app.realm;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by zhangminjie on 15/5/20.
 */
public class StatelessToken implements AuthenticationToken {

    private String username;
    private String parm;
    private String tokenStr;
    public StatelessToken(String username,String parm,String tokenStr){
        this.username=username;
        this.parm = parm;
        this.tokenStr = tokenStr;

    }

    @Override
    public Object getPrincipal() {
        return this.username;
    }

    @Override
    public Object getCredentials() {
        return this.tokenStr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParm() {
        return parm;
    }

    public void setParm(String parm) {
        this.parm = parm;
    }

    public String getTokenStr() {
        return tokenStr;
    }

    public void setTokenStr(String tokenStr) {
        this.tokenStr = tokenStr;
    }
}
