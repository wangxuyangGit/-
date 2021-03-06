//服务层
app.service('specificationService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../specification/findAll.do');		
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get('../specification/findPage.do?page='+page+'&rows='+rows);
	}
	//根据id修改
	this.findOne=function(id){
		return $http.get('../specification/findOne.do?id='+id);
	}
	//增加 
	this.add=function(entity){
		return $http.post('../specification/add.do',entity );
	}
	//修改 
	this.update=function(entity){
		return $http.post('../specification/update.do',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('../specification/delete.do?ids='+ids);
	}
	//分页条件查询
	this.search=function(page,rows,entity){
		return $http.post('../specification/search.do?page='+page+"&rows="+rows, entity);
	}
    //规格下拉列表
    this.selectOptionList=function(){
        return $http.get('../specification/selectOptionList.do');
    }

});
