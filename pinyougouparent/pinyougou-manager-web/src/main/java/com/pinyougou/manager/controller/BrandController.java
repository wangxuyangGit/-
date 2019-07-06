package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;

import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Reference
    private BrandService brandService;

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping("/findAll.do")
    public List<TbBrand> findAll() {
        return brandService.findAll();
    }


    /**
     * 分页查询
     *
     * @param page
     * @param row
     * @return
     */
    @RequestMapping("/findPage.do")
    public PageResult findPage(int page, int row) {
        return brandService.findPage(page, row);
    }

    /**
     * 添加数据
     *
     * @param brand
     * @return
     */
    @RequestMapping("/saves.do")
    public Result add(@RequestBody TbBrand brand) {
        try {
            brandService.add(brand);
            return new Result(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败");
        }
    }

    /**
     * 批量删除，根据数据id
     *
     * @param ids
     * @return
     */
    @RequestMapping("/dele.do")
    public Result delete(Long[] ids) {
        try {
            brandService.delete(ids);
            return new Result(true, "删除成功");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");

        }
    }

    /**
     * 根据品牌的id，确定对其的操作
     * @param id
     * @return
     */
    @RequestMapping("/findOne.do")
    public TbBrand findOne(Long id){
        return brandService.findOne(id);
    }

    /**
     * 修改品牌内容
     * @param brand
     * @return
     */
    @RequestMapping("/update.do")
    public Result update(@RequestBody TbBrand brand){
        try {
            brandService.update(brand);
            return new Result(true,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"修改失败");
        }

    }

    /**
     * 分页条件查询
     * @param brand
     * @param page
     * @param row
     * @return
     */
    @RequestMapping("/search.do")
    public PageResult search(@RequestBody TbBrand brand,int page, int row){
        return brandService.findPage(brand,page,row);
    }

    /**
     * 查询模板列表中的品牌信息
     */
    @RequestMapping("/selectOptionList.do")
    public List<Map> selectOptionList(){
        return brandService.selectOptionList();
    }
}
