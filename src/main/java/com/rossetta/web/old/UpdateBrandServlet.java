package com.rossetta.web.old;
/**
 * Created by Rossetta on 2022/3/19
 */

import com.alibaba.fastjson.JSON;
import com.rossetta.pojo.Brand;
import com.rossetta.service.BrandService;
import com.rossetta.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = "/updateBrandServlet")
public class UpdateBrandServlet extends HttpServlet {
    private BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String brandStr = reader.readLine();
        Brand brand = JSON.parseObject(brandStr, Brand.class);
        brandService.updateBrand(brand);

        // 写到修改成功信息到响应
        response.getWriter().write("success");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
