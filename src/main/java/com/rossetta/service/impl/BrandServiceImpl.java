package com.rossetta.service.impl;

import com.rossetta.mapper.BrandMapper;
import com.rossetta.pojo.Brand;
import com.rossetta.pojo.PageBean;
import com.rossetta.service.BrandService;
import com.rossetta.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * Created by Rossetta on 2022/3/19
 * BrandService接口的具体实现类
 */
public class BrandServiceImpl implements BrandService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Brand> selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectAll();
        // 关闭资源
        sqlSession.close();
        return brands;
    }

    /**
     * 增加一个品牌的具体实现类方法
     *
     * @param brand
     */
    @Override
    public void addBrand(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.addBrand(brand);

        // 提交事务
        sqlSession.commit();
        sqlSession.close();

    }

    /**
     * 通过id查询brand的实现类方法
     *
     * @param id
     * @return
     */
    @Override
    public Brand selectById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        Brand brand = mapper.selectById(id);
        sqlSession.close();
        return brand;
    }

    /**
     * 修改一个brand的实现类方法
     *
     * @param brand
     */
    @Override
    public void updateBrand(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.updateBrand(brand);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Override
    public void deleteByIds(int[] ids) {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();

    }

    /**
     * 根据id修改数据状态
     *
     * @param id
     * @param status
     */
    @Override
    public void updateStatus(int id, int status) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.updateStatus(id, status);
        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 根据id删除一行数据
     *
     * @param id
     */
    @Override
    public void deleteById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 分页查询
     * @param currentPage 当前页
     * @param pageSize 一页显示的数据条数
     * @return
     */
    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;
        // 2.查询当前页显示的数据
        List<Brand> rows = mapper.selectByPage(begin, size);
        // 3.查询总数据条数
        int totalSize = mapper.selectTotalSize();

        // 封装到PageBean对象中
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalSize(totalSize);

        sqlSession.close();

        return pageBean;
    }
}
