angular.module('controllers',[]).controller('productCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','UserLogin','Global','FindProduct','UpdateProduct','AddProduct','DelProduct',
        '$location','$anchorScroll',
        function ($scope,$interval,$rootScope,$stateParams,$state,UserLogin,Global,FindProduct,UpdateProduct,AddProduct,DelProduct,
                  $location,$anchorScroll) {

    $scope.product = {
        templateName : "",
        templateData : "<p>甲、乙双方本着平等、尊重和自愿的原则，签订本协议并接受以下条款约定：</p> " +
        "<p>一、甲方向乙方提供的服务</p> <p>服务项目：II型糖尿病健康管理服务</p> " +
        "<p>乙方在享受《国家基本公共卫生服务规范》所规定的基本医疗和基本公共卫生服务的基础上，还可享受到由甲方提供的以健康管理为内容、" +
        "主动服务为形式的个性化慢病健康管理服务：</p> <p>1.提供为期 1 年的个性化慢病健康管理服务；</p> <p>2.建立详细的个人健康档案；" +
        "</p> <p>3.每年提供至少 2 次的健康体检服务；</p> <p>4.每年提供至少 2 次的健康评估服务；</p> <p>5.提供为期 1 年的远程监测服务；" +
        "</p> <p>6.每月提供至少 2 次的上门随访服务；</p> <p>二、乙方对甲方的义务</p> <p>1.配合甲方建立个人健康服务，向甲方提供真实有效的健康资料，" +
        "及时将自己的健康状况告知甲方；</p> <p>2.配合甲方的诊疗及护理服务；</p> <p>3.主动采取健康生活方式，如戒烟、限酒等，减少各种危险因素；</p> " +
        "<p>4.接受甲方的电话随访；</p> <p>5.学习健康知识，按时参加健康讲座。</p> <p>三、签约服务费</p> <p>本协议签约服务费为 <em>540</em> 元/人/年。" +
        "</p> <p>本协议一式两份，甲乙双方各持一份。自 2017 年 04 月 24 日起生效，有效期为 1 年。合同期满，双方另行续约。</p>"
    };

    var edit = false;

    $scope.servicePackageContShow = function(){
        $scope.servicePackageCont = !$scope.servicePackageCont;
        $location.hash('wrapper');
        $anchorScroll();
    };

    $scope.goPackageMemberList = function(val){
        $rootScope.productServcieAction = "goPackageMemberList";
        $rootScope.productServicePackageName = val;
        $state.go("member");
    };

    FindProduct.save({},function(data){
        if(data.responseData){
            $scope.productServiceList = data.responseData;
        }
    });

    $scope.saveServicePackage = function(){

        if(edit)
        {
            $scope.servicePackageTemplateData = {
                healthServicePackageTemplateId : $scope.servicePackageTemplateData.healthServicePackageTemplateId,
                healthServicePackageTemplateName : $scope.product.templateName,
                healthServicePackageTemplateData : $scope.product.templateData
            }
            UpdateProduct.save($scope.servicePackageTemplateData,function(data){
                if(data.result == Global.SUCCESS){
                    $scope.servicePackageCont = !$scope.servicePackageCont;
                    $location.hash('wrapper');
                    $anchorScroll();
                    FindProduct.save({},function(data){
                        $scope.productServiceList = data.responseData;
                    });
                    edit = false;
                }
            })
        }
        else
        {
            $scope.servicePackageTemplateData = {
                healthServicePackageTemplateName : $scope.product.templateName,
                healthServicePackageTemplateData : $scope.product.templateData
            }
            AddProduct.save($scope.servicePackageTemplateData,function(data){
                if(data.result == Global.SUCCESS){
                    $scope.servicePackageCont = !$scope.servicePackageCont;
                    $location.hash('wrapper');
                    $anchorScroll();
                    FindProduct.save({},function(data){
                        if(data.responseData){
                            $scope.productServiceList = data.responseData;
                        }
                    });
                }
            })
        }


    }

    $scope.servicePackageContEdit = function(val)
    {
        $scope.servicePackageTemplateData = {
            healthServicePackageTemplateId : val,
        };

        FindProduct.save($scope.servicePackageTemplateData,function(data){
            if(data.responseData){
                $scope.product.templateName = data.responseData[0].healthServicePackageTemplateName;
                $scope.product.templateData = data.responseData[0].healthServicePackageTemplateData;
            }
        });
        $scope.servicePackageCont = !$scope.servicePackageCont;
        $location.hash('wrapper');
        $anchorScroll();

        edit = true;
    }

    $scope.servicePackageContDel = function(val)
    {
        $scope.servicePackageTemplateData = {
            healthServicePackageTemplateId : val
        };

        DelProduct.save($scope.servicePackageTemplateData,function(data){
            FindProduct.save({},function(data){
                if(data.responseData){
                    $scope.productServiceList = data.responseData;
                }
            });
        })
    }


}])