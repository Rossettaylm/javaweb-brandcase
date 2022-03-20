package com.rossetta.web.old;
/**
 * Created by Rossetta on 2022/3/19
 */

import com.alibaba.fastjson.JSON;
import com.rossetta.pojo.Brand;
import com.rossetta.service.BrandService;
import com.rossetta.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/selectAllServlet")
public class SelectAllServlet extends HttpServlet {

    private BrandService brandService = new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 查询所有，获得一个brand的列表
        List<Brand> brands = brandService.selectAll();
        
        // 将brands传送给前端
        String s = JSON.toJSONString(brands);

        // 将字符串数据写到响应体，并设置编码格式
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
