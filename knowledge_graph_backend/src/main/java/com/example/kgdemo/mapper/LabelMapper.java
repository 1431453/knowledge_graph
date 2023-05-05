package com.example.kgdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.kgdemo.entity.Label;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LabelMapper extends BaseMapper<Label> {

    /*使用mybatis plus提供的BaseMapper，就不用写SQL语句了，继承它可以使用它提供的方法
    * mybatis plus只对单表查询进行了增强，对于多表查询还是用mybatis
    */
    //查询所有
    @Select("select * from label where name = #{name}")
    List<Label> findByName(String name);


}
