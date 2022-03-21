package com.rossetta.mapper;

import com.rossetta.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {

    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    @Insert("insert into tb_brand values(null, #{brandName}, " + "#{companyName}, #{ordered}, " +
            "#{description}, #{status})")
    void addBrand(Brand brand);

    @Select("select * from tb_brand where id = #{id}")
    @ResultMap("brandResultMap")
    Brand selectById(int id);

    @Update("update tb_brand set brand_name = #{brandName},company_name = #{companyName}," +
            "ordered = #{ordered},description = #{description},status = #{status} where id = #{id}")
    void updateBrand(Brand brand);

    @Delete("delete from tb_brand where id = #{id};")
    void deleteById(int id);

    void deleteByIds(@Param("ids") int[] ids);

    @Update("update tb_brand set status =  #{status} where id = #{id};")
    void updateStatus(@Param("id") int id, @Param("status") int status);

    // 分页查询
    @Select("select * from tb_brand limit #{begin}, #{size};")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin") int begin, @Param("size") int size);

    @Select("select count(*) from tb_brand;")
    int selectTotalSize();

    // 条件分页查询
    List<Brand> selectByPageAndCondition(@Param("begin") int begin,
                                         @Param("size") int size,
                                         @Param("brand") Brand brand);

    int selectTotalSizeByCondition(Brand brand);

}

