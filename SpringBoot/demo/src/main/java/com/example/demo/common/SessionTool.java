package com.example.demo.common;

import javax.servlet.http.HttpServletRequest;

public class SessionTool {

    public Object get(HttpServletRequest request,String key){
        return request.getSession().getAttribute(key);
    }

    public void set(HttpServletRequest request,String key,Object value){
        request.getSession().setAttribute(key,value);
    }

}
