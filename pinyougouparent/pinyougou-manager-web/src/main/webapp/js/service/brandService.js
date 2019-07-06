app.service("brandService",function ($http) {
    this.findAll=function () {
        return $http.get('../brand/findAll.do')
    }
    this.findPage=function (page,row) {
        return  $http.get('../brand/findPage.do?page='+page+'&row='+row)
    }
    this.findOne=function (id) {
        return $http.get('../brand/findOne.do?id='+id)
    }
    this.saves=function (entity) {
        return  $http.post("../brand/saves.do",entity)
    }
    this.update=function (entity) {
        return  $http.post("../brand/update.do", $scope.entity)
    }
    this.dele=function (ids) {
        return $http.get('../brand/dele.do?ids=' +ids)
    }
    this.search=function (page,row,searchEntity) {
        return $http.post("../brand/search.do?page="+page+"&row="+row,searchEntity)
    }
    this.selectOptionList=function () {
        return $http.get('../brand/selectOptionList.do')
    }
});