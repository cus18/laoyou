/**
 * Created by 郑强丽 on 2017/6/15.
 */
angular.module('controllers',['ngFileUpload']).controller('uploadMultiPractitionerCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','UserLogin','Global','$location','$anchorScroll','Upload',
        function ($scope,$interval,$rootScope,$stateParams,$state,UserLogin,Global,$location,$anchorScroll,Upload){

            $location.hash('wrapper');
            $anchorScroll();


            $scope.uploadMultiPractitionerCancel = function()
            {
                $state.go("practitioner");
            }

            $scope.data = {file: null};

            $scope.upload = function () {
                if (!$scope.data.file) {
                    return;
                }
                console.log($scope.data.file);
                var fileName = $scope.data.file.name;
                var suffix = fileName.substring(fileName.lastIndexOf("."),fileName.length);

                if(suffix == '.xlsx' || suffix == '.xls') {

                    var url = "doctor/uploadDoctorFile";

                    Upload.upload({
                        url: url,
                        file: $scope.data.file
                    }).success(function (data) {
                        if(data.result == Global.SUCCESS){
                            $state.go('practitioner');
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
