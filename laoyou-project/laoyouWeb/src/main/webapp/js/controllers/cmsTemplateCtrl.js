angular.module('controllers',[]).controller('cmsTemplateCtrl',
    ['$scope','$interval','$rootScope','$stateParams','$state','Global','$timeout','GetNotificationTemplateList',
        'AddNotificationTemplate','GetNotificationTemplate','UpdateNotificationTemplate','DeleteNotificationTemplate',
        'GetRemindTemplateEntityList','GetRemindTemplateEntityByID','AddRemindTemplate','UpdateRemindTemplate',
        'DeleteRemindTemplate',
        function ($scope,$interval,$rootScope,$stateParams,$state,Global,$timeout,GetNotificationTemplateList,
                  AddNotificationTemplate,GetNotificationTemplate,UpdateNotificationTemplate,DeleteNotificationTemplate,
                  GetRemindTemplateEntityList,GetRemindTemplateEntityByID,AddRemindTemplate,UpdateRemindTemplate,
                  DeleteRemindTemplate) {


            $scope.loadPageList = function(){
                if(!$scope.param.searchValue){
                    $scope.param.searchValue = '';
                }
                if(!$scope.startUpdateDate){
                    $scope.startUpdateDate = '';
                }
                if(!$scope.endUpdateDate){
                    $scope.endUpdateDate = '';
                }
                GetNotificationTemplateList.save({
                    title:$scope.title,
                    startUpdateDate:$scope.startUpdateDate,
                    endUpdateDate:$scope.endUpdateDate,
                    pageNo:$scope.pageNo,
                    pageSize:$scope.pageSize
                },function(data){
                    if(data.responseData){
                        $scope.newsTemplateList = data.responseData;
                    }
                });
                GetRemindTemplateEntityList.save({
                    title:$scope.title,
                    startUpdateDate:$scope.startUpdateDate,
                    endUpdateDate:$scope.endUpdateDate,
                    pageNo:$scope.pageNo,
                    pageSize:$scope.pageSize
                },function(data){
                    if(data.responseData){
                        angular.forEach(data.responseData.list,function(data){
                            if(data.type == '1')
                            {
                                data.type = '系统提醒'
                            }
                            else if(data.type == '2')
                            {
                                data.type = '自定义提醒'
                            }
                        });
                        $scope.remindTemplateList = data.responseData;
                    }
                })
            };

            $timeout(function(){
                //calendar
                $(".date-picker").datepicker({
                    language: "zh-CN",
                    autoclose: true
                });
            },1000);

            //新增消息模板
            $scope.addNewsTemplate = function(){
                $scope.newsAlert = true;
                $scope.popNewsTitle = '新增消息模板';
                $scope.newsType = 'new';
                $scope.newsTemplate = {};
            };

            //修改消息模板
            $scope.updateNewsTemplate = function(id){
                $scope.newsAlert = true;
                $scope.popNewsTitle = '修改消息模板';
                $scope.newsType = 'update';
                GetNotificationTemplate.get({id:id},function(data){
                    if(data.responseData){
                        $scope.newsTemplate = data.responseData;
                    }
                })
            };

            //保存消息模板
            var submitOnOffNews = true;        //防止多次提交
            $scope.subNewsForm = function(isValid)
            {
                if(isValid){
                    if(submitOnOffNews) {
                        submitOnOffNews = false;
                        if($scope.newsType == 'new'){
                            AddNotificationTemplate.save($scope.newsTemplate,function(data){
                                if(data.result == Global.SUCCESS){
                                    $scope.newsAlert = false;
                                    $scope.loadPageList();
                                }
                            })
                        }
                        else if($scope.newsType == 'update')
                        {
                            UpdateNotificationTemplate.save($scope.newsTemplate,function(data){
                                if(data.result == Global.SUCCESS){
                                    $scope.newsAlert = false;
                                    $scope.loadPageList();
                                }
                            })
                        }
                    }
                }else{
                    alert('信息不完整')
                }
            };

            $scope.close = function()
            {
                $scope.newsAlert = false;
                $scope.remindAlert = false;
            };

            //新增老友提醒模板
            $scope.addRemindTemplate = function(){
                $scope.remindAlert = true;
                $scope.popRemindTitle = '新增自定义提醒';
                $scope.remindType = 'new';
                $scope.remindTemplate = {};
            };

            //修改老友提醒模板
            $scope.updateRemindTemplate = function(id){
                $scope.remindAlert = true;
                $scope.popRemindTitle = '修改自定义提醒';
                $scope.remindType = 'update';
                GetRemindTemplateEntityByID.get({id:id},function(data){
                    if(data.responseData){
                        $scope.remindTemplate = data.responseData;
                    }
                })
            };

            //保存老友提醒模板
            var submitOnOffRemind = true;        //防止多次提交
            $scope.subRemindForm = function(isValid)
            {
                if(isValid){
                    if(submitOnOffRemind) {
                        submitOnOffRemind = false;
                        if($scope.remindType == 'new'){
                            AddRemindTemplate.save($scope.remindTemplate,function(data){
                                if(data.result == Global.SUCCESS){
                                    $scope.remindAlert = false;
                                    $scope.loadPageList();
                                }
                            })
                        }
                        else if($scope.remindType == 'update')
                        {
                            UpdateRemindTemplate.save($scope.remindTemplate,function(data){
                                if(data.result == Global.SUCCESS){
                                    $scope.remindAlert = false;
                                    $scope.loadPageList();
                                }
                            })
                        }
                    }
                }else{
                    alert('信息不完整')
                }
            };

            //删除模板
            $scope.delete = function(type,id){
                $scope.alertInfo = true;
                $scope.delSure = function(){
                    if(type == 'news'){
                        DeleteNotificationTemplate.get({id:id},function(data){
                            if(data.result == Global.SUCCESS){
                                $scope.alertInfo = false;
                                $scope.loadPageList();
                            }
                        })
                    }
                    else if(type == 'remind')
                    {
                        DeleteRemindTemplate.get({id:id},function(data){
                            if(data.result == Global.SUCCESS){
                                $scope.alertInfo = false;
                                $scope.loadPageList();
                            }
                        })
                    }
                }
                $scope.delCancle = function(){
                    $scope.alertInfo = false;
                }
            }

        }])