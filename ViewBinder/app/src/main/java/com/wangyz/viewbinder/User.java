package com.wangyz.viewbinder;


import com.wangyz.annotation.Seriable;
// TODO: 草他妈的,你定义注解,你得用上,不然那边annotation-compiler中AbstractProcessor不会被调用
public class User {
    @Seriable
    private String username;
    @Seriable
    private String password;

    private String three;
    private String four;
}
