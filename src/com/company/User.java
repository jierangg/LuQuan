package com.company;

public class User {
    String User_Agent;
    String Cookie;
    String Remark;

    public User(String user_Agent, String cookie, String remark) {
        User_Agent = user_Agent;
        Cookie = cookie;
        Remark = remark;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getUser_Agent() {
        return User_Agent;
    }

    public void setUser_Agent(String user_Agent) {
        User_Agent = user_Agent;
    }

    public String getCookie() {
        return Cookie;
    }

    public void setCookie(String cookie) {
        Cookie = cookie;
    }
}
