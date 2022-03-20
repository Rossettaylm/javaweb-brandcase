package com.rossetta.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Rossetta on 2022/3/20
 * 根据资源路径来进行方法分发
 */
public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取请求路径
        String uri = req.getRequestURI();  //   /brand_case/brand/selectAll

        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index + 1);//   selectAll

        // 2.执行方法
        // 2.1 获取BrandServlet的字节码对象，其中this---谁调用我，我代表谁---通过子类BrandServlet调用，所以代表BrandServlet
        Class<? extends BaseServlet> cls = this.getClass();
        // 2.2 获取方法Method的对象，并通过invoke执行
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class,
                    HttpServletResponse.class);

            method.invoke(this, req, resp);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
