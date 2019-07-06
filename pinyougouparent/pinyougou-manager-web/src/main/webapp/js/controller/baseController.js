app.controller("baseController",function ($scope) {

    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 5,
        perPageOptions: [1, 2, 3, 4, 5],
        onChange: function () {
            $scope.reloadList();//刷新
        }
    };
    $scope.reloadList = function () {
        $scope.search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage)
    }

    /**
     * 将选中的id存入数组中
     * @type {Array}
     */
    $scope.selectIds = [];
    $scope.checks = function ($event, id) {
        if ($event.target.checked) {
            $scope.selectIds.push(id)
        } else {
            var index = $scope.selectIds.indexOf(id);
            $scope.selectIds.splice(index, 1);//参数1：索引，参数2：该索引位置的参数个数
        }

    };
    //将从数据库读取到的json字符串转换成json对象
    $scope.jsonToString = function (jsonString,key) {
        var json=JSON.parse(jsonString)
        var value="";
        for (var i = 0; i < json.length; i++) {
                if (i > 0) {
                    value +=",";
                }
                value +=json[i][key]
        }
        return value;
    }
})