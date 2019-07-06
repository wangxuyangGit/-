app.controller('shoploginController', function ($scope, $controller, shoploginService) {

    $scope.loginName = function () {
        shoploginService.loginName().success(function (response) {
            $scope.loginName = response.loginname;
        })
    }
})