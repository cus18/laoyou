angular.module('controllers',['ngFileUpload']).controller('uploadMultiMemberCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','UserLogin','Global','$location','$anchorScroll','Upload',
        function ($scope,$interval,$rootScope,$stateParams,$state,UserLogin,Global,$location,$anchorScroll,Upload){

        $location.hash('wrapper');
        $anchorScroll();


        $scope.uploadMultiMemberCancel = function()
        {
            $state.go("member");
        }

        $scope.data = {file: null};

        $scope.upload = function () {
            if (!$scope.data.file) {
                return;
            }
            console.log($scope.data.file);
            var fileName = $scope.data.file.name;
            var suffix = fileName.substring(fileName.lastIndexOf("."),fileName.length);

            if(suffix == '.xlsx' || suffix == '.xls'){
                var url = "member/uploadMemberFile";

                Upload.upload({
                    url: url,
                    file: $scope.data.file
                }).success(function (data) {
                    if(data.result == Global.SUCCESS){
                        $state.go('member');
                    }
                    console.log(data);
                }).error(function () {

                });
            }
            else
            {
                $scope.hintText = '请上传后缀名为.xlsx或.xls的文件';
            }


        }

        $scope.submit = function () {
            $scope.upload();
        };


}])