app.service('shoploginService',function ($http) {
    this.loginName = function () {
        return $http.get('../login/shoploginname.do')
}
})