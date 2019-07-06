app.controller("brandController", function ($scope,$controller,brandService) {
    $controller("baseController",{$scope:$scope})

    /*查询所有*/
    $scope.findAll = function () {
        brandService.findAll().success(
            function (response) {
                $scope.list = response;
            }
        )
    };


    /**
     * 分页查询
     * @param page
     * @param row
     */
    $scope.findPage = function (page, row) {
        brandService.findPage(page,row).success(function (response) {
                $scope.list = response.rows;//每页显示的记录
                $scope.paginationConf.totalItems = response.total;//总记录数
            }
        )
    };
        //根据查到的id进行修改
    $scope.findOne = function (id) {
        brandService.findOne(id) .success(
            function (response) {
                $scope.entity = response;
            }
        )
    }

    /**
     * 添加数据,修改数据
     */
    $scope.save = function () {
        var object=null;
        if ($scope.entity.id != null) {
            object=brandService.update($scope.entity)
        }else{
            object=brandService.saves($scope.entity)
        }
        object.success(
            function (response) {
                if (response.success) {
                    $scope.reloadList();
                } else {
                    alert(response.message);
                }
            }
        );
    }

    /**
     * 根据上面选中的id对数据进行修改
     *
     */
    $scope.dele = function () {
        brandService.dele($scope.selectIds).success(
            function (response) {
                if (response.success) {
                    $scope.reloadList();
                } else {
                    alert(response.message);
                }
            }
        )
    }
    /**
     * 分页条件查询
     *
     */
    $scope.searchEntity={};//初始化，不是null而是空的对象
    $scope.search=function (page,row) {
        brandService.search(page,row,$scope.searchEntity) .success(
            function (response) {
                $scope.list=response.rows;//每页的记录
                $scope.paginationConf.totalItems=response.total;//总数
            }
        )
    }
});