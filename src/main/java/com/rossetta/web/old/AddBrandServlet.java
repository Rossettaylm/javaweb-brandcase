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

@WebServlet(urlPatterns = "/addBrandServlet")
public class AddBrandServlet extends HttpServlet {
    private BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从前端获取输入的数据
        BufferedReader br = request.getReader();
        String jsonString = br.readLine();

        // 将json数据封装为brand对象
        Brand brand = JSON.parseObject(jsonString, Brand.class);

        brandService.addBrand(brand);

        // 响应一个成功的标识
        response.getWriter().write("success");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
