package com.rossetta.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rossetta.pojo.Brand;
import com.rossetta.pojo.PageBean;
import com.rossetta.service.BrandService;
import com.rossetta.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by Rossetta on 2022/3/20
 * 自己定义了BaseServlet来继承，不必再写doGet和doPost方法
 * 而是通过基类分配的路径来进行方法访问
 */
@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private BrandService brandService = new BrandServiceImpl();

    /**
     * 查询所有
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 查询所有，获得一个brand的列表
        List<Brand> brands = brandService.selectAll();

        // 将brands传送给前端
        String s = JSON.toJSONString(brands);

        // 将字符串数据写到响应体，并设置编码格式
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }

    /**
     * 添加数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从前端获取输入的数据
        BufferedReader br = request.getReader();
        String jsonString = br.readLine();

        // 将json数据封装为brand对象
        Brand brand = JSON.parseObject(jsonString, Brand.class);

        brandService.addBrand(brand);

        // 响应一个成功的标识
        response.getWriter().write("success");
    }

    /**
     * 修改数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String brandStr = reader.readLine();
        Brand brand = JSON.parseObject(brandStr, Brand.class);
        brandService.updateBrand(brand);

        // 写到修改成功信息到响应
        response.getWriter().write("success");
    }

    /**
     * 通过id查询数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.从前端中获取id数据
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Integer id = JSON.parseObject(s, int.class);
        // 2.通过id查询出一个brand
        Brand brand = brandService.selectById(id);
        // 3.返回当前brand的json数据进行回显
        String jsonString = JSON.toJSONString(brand);
        // 将jsonString写到response中
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 删除一行数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.从前端中获取id
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Integer id = JSON.parseObject(s, int.class);

        // 2.执行删除命令
        brandService.deleteById(id);

        // 3.响应成功信息
        response.getWriter().write("success");
    }


    /**
     * 批量删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从前端中获取待删除的数据的id数组
        BufferedReader reader = request.getReader();
        String jsonString = reader.readLine();
        // 封装成int[]对象
        int[] ids = JSON.parseObject(jsonString, int[].class);
        brandService.deleteByIds(ids);
        // 返回删除成功标识
        response.getWriter().write("success");
    }

    public void updateStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 处理前端得到的json字符串
        BufferedReader reader = request.getReader();
        String s = reader.readLine();

        JSONObject jsonObject = (JSONObject) JSON.parse(s);
        int id = (int) jsonObject.get("id");
        int status = (int) jsonObject.get("status");

        brandService.updateStatus(id, status);

        // 响应信息
        response.getWriter().write("success");

    }

    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取参数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        // 类型转换
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        // 进行分页查询，得到一个pageBean对象
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);
        // 将对象转为json字符串
        String jsonString = JSON.toJSONString(pageBean);
        // 响应信息
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);

    }
}
