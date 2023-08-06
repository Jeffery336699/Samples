package com.wangyz.viewbinder;


import com.wangyz.annotation.seriable.Seriable;

// TODO: 草他妈的,你定义注解,你得用上,不然那边annotation-compiler中AbstractProcessor不会被调用
@Seriable
public class Article {
    private String title;
    private String content;
}
