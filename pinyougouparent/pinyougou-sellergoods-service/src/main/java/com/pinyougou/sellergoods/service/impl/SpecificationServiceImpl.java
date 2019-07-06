package com.pinyougou.sellergoods.service.impl;
import java.util.List;
import java.util.Map;

import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationExample.Criteria;
import com.pinyougou.sellergoods.service.SpecificationService;

import entity.PageResult;
import pojo.Specification;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;
	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper;
	/**
	 * 查询全部
	 */
	@Override
	public List<TbSpecification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbSpecification> page=   (Page<TbSpecification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Specification specification) {
		//新增规格行
		TbSpecification tbSpecification = specification.getSpecification();
		specificationMapper.insert(tbSpecification);
		//新增规格option
		List<TbSpecificationOption> specificationOptionList = specification.getSpecificationOptionList();
		for (TbSpecificationOption specificationOption : specificationOptionList) {
			specificationOption.setSpecId(tbSpecification.getId());//获取规格行中的id即spec_id
			specificationOptionMapper.insert(specificationOption);//将获取到的加入到组合类中
		}
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Specification specification){
			//保存修改后的规格，并且删除原来的规格option
		specificationMapper.updateByPrimaryKey(specification.getSpecification());
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
		//以规格中的id为条件进行删除
		 criteria.andSpecIdEqualTo(specification.getSpecification().getId());
		 specificationOptionMapper.deleteByExample(example);
		 //保存
		List<TbSpecificationOption> specificationOptionList = specification.getSpecificationOptionList();
		for (TbSpecificationOption specificationOption : specificationOptionList) {
			specificationOptionMapper.insert(specificationOption);
		}
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id){
		//构建组合实体类
		Specification specification = new Specification();
		//查询规格
		TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
		//查询规格option
		TbSpecificationOptionExample tbSpecificationOptionExample = new TbSpecificationOptionExample();
		TbSpecificationOptionExample.Criteria criteria = tbSpecificationOptionExample.createCriteria();
		//根据规格的id查询
		criteria.andSpecIdEqualTo(id);
		List<TbSpecificationOption> tbSpecificationOptions = specificationOptionMapper.selectByExample(tbSpecificationOptionExample);


		specification.setSpecification(tbSpecification);
		specification.setSpecificationOptionList(tbSpecificationOptions);
		return specification;

	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			specificationMapper.deleteByPrimaryKey(id);
			TbSpecificationOptionExample example = new TbSpecificationOptionExample();
			TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
			//以id为条件进行规格option的删除
			criteria.andSpecIdEqualTo(id);
			//删除
			specificationOptionMapper.deleteByExample(example);
		}		
	}

    /**
     * 分页查询
     * @param specification
     * @param pageNum 当前页 码
     * @param pageSize 每页记录数
     * @return
     */
		@Override
	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);


		TbSpecificationExample example=new TbSpecificationExample();
		Criteria criteria = example.createCriteria();
		
		if(specification!=null){
						if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
	
		}
		
		Page<TbSpecification> page= (Page<TbSpecification>)specificationMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 规格下拉列表的查询
	 * @return
	 */
	@Override
	public List<Map> selectOptionList() {
		return specificationMapper.selectOptionList();
	}


}
