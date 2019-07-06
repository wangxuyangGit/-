package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;
import entity.PageResult;
import entity.Result;

import java.util.List;
import java.util.Map;

public interface BrandService {
    /**
     * 查询所有品牌
     * @return
     */
    public List<TbBrand> findAll();


    //分页
    public PageResult findPage(int pageNum,int pageSize);
    //添加品牌
    public void add(TbBrand brand);
    //删除品牌
    public void delete(Long[] ids);
    //修改品牌
    public void update(TbBrand brand);
    //根据id查找需要 修改的品牌
    public TbBrand findOne(Long id);
    //分页查询
    public PageResult findPage(TbBrand brand,int pageNum,int pageSize);
    //查询相关品牌
    List<Map> selectOptionList();
}
