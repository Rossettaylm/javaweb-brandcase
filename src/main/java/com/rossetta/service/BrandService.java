package com.rossetta.service;

import com.rossetta.pojo.Brand;
import com.rossetta.pojo.PageBean;

import java.util.List;

/**
 * Created by Rossetta on 2022/3/20
 * Brand服务的接口，通过接口实现一个具体的实现类，可以把服务层和servlet解耦合
 */
public interface BrandService {

    List<Brand> selectAll();

    void addBrand(Brand brand);

    Brand selectById(int id);

    void updateBrand(Brand brand);

    void deleteById(int id);

    void deleteByIds(int[] ids);

    void updateStatus(int id, int status);

    PageBean<Brand> selectByPage(int currentPage, int pageSize);

    PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand);
}
