app.controller('loginController',function ($scope,$controller,loginService) {

    $scope.loginName=function () {
        loginService.loginName().success(function (response) {
            $scope.loginName=response.loginname;
        })
    }
})