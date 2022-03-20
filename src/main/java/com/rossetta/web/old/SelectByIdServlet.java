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
import java.io.BufferedReader;
import java.io.IOException;

/**
 * 通过id显示修改brand的对话框并回显数据
 */
@WebServlet(urlPatterns = "/selectByIdServlet")
public class SelectByIdServlet extends HttpServlet {
    private BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从前端中获取id数据
        String id = request.getParameter("id");
//         通过id查询出一个brand
        Brand brand = brandService.selectById(Integer.parseInt(id));
        // 返回当前brand的json数据进行回显
        String jsonString = JSON.toJSONString(brand);
        // 将jsonString写到response中
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
