 //控制层 
app.controller('itemCatController' ,function($scope,$controller,itemCatService,typeTemplateService){
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		itemCatService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		itemCatService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		itemCatService.findOne(id).success(
			function(response) {
                $scope.entity = response;
            }
		);				
	}
	
	//保存

	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=itemCatService.update( $scope.entity ); //修改  
		}else{
			$scope.entity.parentId = $scope.parentId;
			serviceObject=itemCatService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
		        	$scope.findByParentId($scope.parentId)//重新加载
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 
	$scope.dele=function(parentId){
		//获取选中的复选框			
		itemCatService.dele( $scope.selectIds ,parentId).success(
			function(response){
				if(response.success){
					$scope.findByParentId(parentId);//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		itemCatService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
                $scope.reloadList();//刷新列表
			}			
		);
	}

	$scope.parentId=0;//上级id
    $scope.findByParentId=function (parentId) {
    	$scope.parentId = parentId;//将上级id记录
		itemCatService.findByParentId(parentId).success(function (response) {
			$scope.list=response;
        })
    }
    $scope.grade=1;//默认为1级

	//设置级别
	$scope.setGrade=function (value) {
		$scope.grade=value
		alert(value)
    }

	$scope.typeTemplateList={data:[]};//类型模板的下拉列表
	$scope.findTypeTemplateList=function(){
			typeTemplateService.selectOptionList().success(function (response) {
				$scope.typeTemplateList={data:response}
            })
	}
    //读取数据列表
	$scope.loadList=function (p_entity) {

		if ($scope.grade==1){//需给定义，否则报错grade defined!
			$scope.entity_2=null;
			$scope.entity_3=null;
		}
		if ($scope.grade==2){
			$scope.entity_2=p_entity;
			$scope.entity_3=null;
		}
		if ($scope.grade==3){
			$scope.entity_3=p_entity;
		}

		$scope.findByParentId(p_entity.id)
    }
});	
