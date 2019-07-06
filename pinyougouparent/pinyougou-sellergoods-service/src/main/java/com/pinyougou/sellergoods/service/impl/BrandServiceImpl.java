package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.sellergoods.service.BrandService;
import entity.PageResult;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {

@Autowired
   private TbBrandMapper tbBrandMapper;
    @Override
    public List<TbBrand> findAll() {
        return tbBrandMapper.selectByExample(null);
    }

    /*品牌分页*/

    public PageResult findPage( int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbBrand> tbBrands = (Page<TbBrand>) tbBrandMapper.selectByExample(null);
        return new PageResult(tbBrands.getTotal(),tbBrands.getResult());
    }

    /**
     * 添加品牌
     * @param brand
     * @return
     */
    @Override
    public void add(TbBrand brand) {
        tbBrandMapper.insert(brand);
    }

    /**
     * 批量删除，根据id
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            tbBrandMapper.deleteByPrimaryKey(id);
        }

    }

    /**
     * 修改根据id查到的品牌
     * @param brand
     */
    @Override
    public void update(TbBrand brand) {

        tbBrandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public TbBrand findOne(Long id) {
        return tbBrandMapper.selectByPrimaryKey(id);
    }

    /**
     * 分页查询
     * @param brand
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageResult findPage(TbBrand brand, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbBrandExample example = new TbBrandExample();
       TbBrandExample.Criteria criteria = example.createCriteria();
       if (brand.getName()!=null&&brand.getName().length()>0){
           criteria.andNameLike("%"+brand.getName()+"%");
       }
        if (brand.getFirstChar()!=null&&brand.getFirstChar().length()>0){
            criteria.andFirstCharLike("%"+brand.getFirstChar()+"%");
        }
        Page<TbBrand> tbBrands = (Page<TbBrand>) tbBrandMapper.selectByExample(example);

        return new PageResult(tbBrands.getTotal(),tbBrands.getResult());
    }

    @Override
    public List<Map> selectOptionList() {
        return tbBrandMapper.selectOptionList();
    }


}
