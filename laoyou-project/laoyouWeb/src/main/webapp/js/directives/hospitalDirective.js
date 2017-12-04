define(['appHospital','jquery'], function (app,$) {
    app
        .directive('hospitalMenu', ['$rootScope','$state','UserGetUser','UserLoginOut','Global','HospitalUtil',
            function ($rootScope,$state,UserGetUser,UserLoginOut,Global,HospitalUtil) {
                return {
                    restrict: 'EAC',
                    template: function(tElement,tAttrs){

                        var _html = '';
                        _html += '<nav class="navbar navbar-default navbar-cls-top "role="navigation" style="margin-bottom: 0;width:100%;"> ' +
                            '<div class="navbar-header"> {{loginUser.sysHospitalUserDTO.officeName}}' +
                            ' </div> <div style="color: white;padding: 15px 50px 5px 50px;float: right;font-size: 16px;">' +
                            '欢迎您！{{loginUser.phone}} &nbsp;&nbsp;<a class="btn btn-danger square-btn-adjust" ng-click="loginOut()">退出</a> ' +
                            '</div> </nav> <!-- /. NAV TOP  --> <nav class="navbar-default navbar-side" role="navigation" id="navBar" style="background:#f5f5f5;overflow-y:auto;"> ' +
                            '<div class="sidebar-collapse" style="padding-bottom:100px;"> <ul class="nav" id="main-menu" style="background: #f5f5f5"> ' +
                            '<li style="display:block;" class="text-center"> <img src="images/logo.png" class="user-image img-responsive"/> </li>';

                        if(tAttrs.title == "index")
                        {
                            _html += '<li style="display:block;" ng-show="loginUser.sysHospitalUserDTO.sysRoleID == 2"><a class="active-menu" ui-sref="index"><img src="images/index02.png">';
                        }
                        else
                        {
                            _html += '<li style="display:block;" ng-show="loginUser.sysHospitalUserDTO.sysRoleID == 2"><a ui-sref="index"><img src="images/index01.png">';
                        }
                        _html += '首页 </a> </li>';

                        if(tAttrs.title == "member")
                        {
                            _html += '<li style="display:block;"><a class="active-menu" ui-sref="member"><img src="images/member02.png">';
                        }
                        else
                        {
                            _html += '<li style="display:block;"><a ui-sref="member"><img src="images/member01.png">';
                        }
                        _html += '会员管理 </a> </li>';

                        if(tAttrs.title=='practitioner')
                        {
                            _html += '<li style="display:block;" ng-show="loginUser.sysHospitalUserDTO.sysRoleID == 1 || loginUser.sysHospitalUserDTO.sysRoleID == 2"><a class="active-menu" ui-sref="practitioner"><img src="images/practitioner02.png">';
                        }
                        else
                        {
                            _html += '<li style="display:block;" ng-show="loginUser.sysHospitalUserDTO.sysRoleID == 1 || loginUser.sysHospitalUserDTO.sysRoleID == 2"><a ui-sref="practitioner"><img src="images/practitioner01.png">';
                        }
                        _html += '医护管理 </a> </li>';

                        if(tAttrs.title=='product')
                        {
                            _html += '<li style="display:block;" ng-show="loginUser.sysHospitalUserDTO.sysRoleID == 1 || loginUser.sysHospitalUserDTO.sysRoleID == 2"><a class="active-menu" ui-sref="product"><img src="images/product02.png">';
                        }
                        else
                        {
                            _html += '<li style="display:block;" ng-show="loginUser.sysHospitalUserDTO.sysRoleID == 1 || loginUser.sysHospitalUserDTO.sysRoleID == 2"><a ui-sref="product"><img src="images/product01.png">';
                        }
                        _html += '产品服务 </a> </li>';

                        if(tAttrs.title=='device')
                        {
                            _html += '<li style="display:block;" ng-show="loginUser.sysHospitalUserDTO.sysRoleID == 1 || loginUser.sysHospitalUserDTO.sysRoleID == 2"><a class="active-menu" ui-sref="device"><img src="images/device02.png">';
                        }
                        else
                        {
                            _html += '<li style="display:block;" ng-show="loginUser.sysHospitalUserDTO.sysRoleID == 1 || loginUser.sysHospitalUserDTO.sysRoleID == 2"><a ui-sref="device"><img src="images/device01.png">';
                        }
                        _html += '设备管理 </a> </li>';

                        if(tAttrs.title=='setting')
                        {
                            _html += '<li style="display:block;" ng-show="loginUser.sysHospitalUserDTO.sysRoleID == 2 || loginUser.sysHospitalUserDTO.sysRoleID == 3"><a class="active-menu" ui-sref="setting"><img src="images/setting02.png">';
                        }
                        else
                        {
                            _html += '<li style="display:block;"><a ui-sref="setting"><img src="images/setting01.png">';
                        }
                        _html += '机构设置 </a> </li>';

                        _html += '<li style="display:block;" ng-show="loginUser.sysHospitalUserDTO.sysRoleID == 1 || loginUser.sysHospitalUserDTO.sysRoleID == 2"><a href="javascript:;"><img src="images/device01.png">居家短时服务<span class="fa arrow"></span></a><ul id="livingMenu" class="nav nav-second-level" style="padding-left:22px;">';

                        if(tAttrs.title=='livingAllOrder')
                        {
                            _html += '<li><a class="active-menu" ui-sref="livingAllOrder">';
                        }
                        else
                        {
                            _html += '<li><a ui-sref="livingAllOrder">';
                        }
                        _html += '全部订单 </a> </li>';

                        if(tAttrs.title=='livingAllService')
                        {
                            _html += '<li><a class="active-menu" ui-sref="livingAllService">';
                        }
                        else
                        {
                            _html += '<li><a ui-sref="livingAllService">';
                        }
                        _html += '全部服务 </a></li>';

                        if(tAttrs.title=='livingOrderList')
                        {
                            _html += '<li><a class="active-menu" ui-sref="livingOrderList">';
                        }
                        else
                        {
                            _html += '<li><a ui-sref="livingOrderList">';
                        }
                        _html += '订单处理 </a> </li>';

                        if(tAttrs.title=='livingServiceList')
                        {
                            _html += '<li><a class="active-menu" ui-sref="livingServiceList">';
                        }
                        else
                        {
                            _html += '<li><a ui-sref="livingServiceList">';
                        }
                        _html += '服务管理 </a></ul></li> </li>';

                        _html += '<li style="display:block;"><a href="javascript:;"><img src="images/device01.png">活动管理<span class="fa arrow"></span></a><ul id="activeMenu" class="nav nav-second-level" style="padding-left:22px;">';

                        if(tAttrs.title=='activeAllList')
                        {
                            _html += '<li><a class="active-menu" ui-sref="activeAllList">';
                        }
                        else
                        {
                            _html += '<li><a ui-sref="activeAllList">';
                        }
                        _html += '全部活动 </a> </li>';

                        if(tAttrs.title=='activeList')
                        {
                            _html += '<li><a class="active-menu" ui-sref="activeList">';
                        }
                        else
                        {
                            _html += '<li><a ui-sref="activeList">';
                        }
                        _html += '活动列表 </a></ul></li> </li>';

                        _html += '<li style="display:block;" ng-show="loginUser.sysHospitalUserDTO.sysRoleID == 1 || loginUser.sysHospitalUserDTO.sysRoleID == 2"><a href="javascript:;"><img src="images/device01.png">讲堂管理<span class="fa arrow"></span></a><ul id="videoMenu" class="nav nav-second-level" style="padding-left:22px;">';

                        if(tAttrs.title=='liveVideo')
                        {
                            _html += '<li><a class="active-menu" ui-sref="video({type:\'liveVideo\'})">';
                        }
                        else
                        {
                            _html += '<li><a ui-sref="video({type:\'liveVideo\'})">';
                        }
                        _html += '直播管理 </a> </li>';

                        if(tAttrs.title=='video')
                        {
                            _html += '<li><a class="active-menu" ui-sref="video({type:\'video\'})">';
                        }
                        else
                        {
                            _html += '<li><a ui-sref="video({type:\'video\'})">';
                        }
                        _html += '视频管理 </a></ul></li> </li>';

                        _html += '<li style="display:block;" ng-show="loginUser.sysHospitalUserDTO.sysRoleID == 1 || loginUser.sysHospitalUserDTO.sysRoleID == 2"><a href="javascript:;"><img src="images/device01.png">用户与权限<span class="fa arrow"></span></a><ul id="limitsMenu" class="nav nav-second-level" style="padding-left:22px;">';

                        if(tAttrs.title=='limitsSetting')
                        {
                            _html += '<li><a class="active-menu" ui-sref="limitsSetting">';
                        }
                        else
                        {
                            _html += '<li><a ui-sref="limitsSetting">';
                        }
                        _html += '机构管理 </a> </li>';

                        if(tAttrs.title=='limitsUsers')
                        {
                            _html += '<li><a class="active-menu" ui-sref="limitsUsers">';
                        }
                        else
                        {
                            _html += '<li><a ui-sref="limitsUsers">';
                        }
                        _html += '用户管理 </a></ul></li> </li>';

                        _html += '<li style="display:block;" ng-show="loginUser.sysHospitalUserDTO.sysRoleID == 1 || loginUser.sysHospitalUserDTO.sysRoleID == 2"><a href="javascript:;"><img src="images/device01.png">CMS<span class="fa arrow"></span></a><ul id="cmsMenu" class="nav nav-second-level" style="padding-left:22px;">';

                        if(tAttrs.title=='cmsPicture')
                        {
                            _html += '<li><a class="active-menu"  ui-sref="cmsPicture">';
                        }
                        else
                        {
                            _html += '<li><a ui-sref="cmsPicture">';
                        }
                        _html += '图片资源位管理 </a> </li>';

                        if(tAttrs.title=='cmsTemplate')
                        {
                            _html += '<li><a class="active-menu" ui-sref="cmsTemplate">';
                        }
                        else
                        {
                            _html += '<li><a ui-sref="cmsTemplate">';
                        }
                        _html += '消息与提醒模板 </a> </li>';

                        if(tAttrs.title=='cmsSend')
                        {
                            _html += '<li><a class="active-menu" ui-sref="cmsSend">';
                        }
                        else
                        {
                            _html += '<li><a ui-sref="cmsSend">';
                        }
                        _html += '发送消息与提醒 </a> </li>';

                        if(tAttrs.title=='cmsHistory')
                        {
                            _html += '<li><a class="active-menu" ui-sref="cmsHistory">';
                        }
                        else
                        {
                            _html += '<li><a ui-sref="cmsHistory">';
                        }
                        _html += '发送历史 </a></ul></li> </li>';

                        _html += '</ul><div style="color:#9b9b9b;font-size:12px;position:fixed;left:10px;bottom:15px;">版权所有 © 华录健康养老发展有限公司</div></div></nav>';
                        return _html;
                    },
                    link:function(scope,ele,attrs){
                        UserGetUser.get({},function(data){
                            HospitalUtil.checkResponseData(data);
                            if(data.result == Global.SUCCESS){
                                scope.loginUser = data.responseData;
console.log(data)
                            }
                        })

                        scope.loginOut = function(){
                            UserLoginOut.get({},function(data){
                                if(data.result == Global.LOGIN_OUT){
                                    $state.go('login')
                                }
                            })
                        };

                        $(function(){
                            //左侧二级菜单
                            $('#navBar').css('height',$(window).height() + 'px');
                            $('#main-menu li').click(function(){
                                $(this).find('ul li').toggle();
                            })
                            if(attrs.title == 'liveVideo' || attrs.title == 'video'){
                                $('#videoMenu li').show();
                            }
                            if(attrs.title.substr(0,6) == 'limits'){
                                $('#limitsMenu li').show();
                            }
                            if(attrs.title.substr(0,3) == 'cms'){
                                $('#cmsMenu li').show();
                            }
                            if(attrs.title.substr(0,6) == 'living'){
                                $('#livingMenu li').show();
                            }
                            if(attrs.title.substr(0,6) == 'active'){
                                $('#activeMenu li').show();
                            }
                        })


                    }
                }
        }])
        .directive('pageTool', ['$rootScope','$state','$timeout',
            function ($rootScope,$state,$timeout) {
                return {
                    restrict: 'EAC',
                    replace: true,
                    template: '<div class="row"><div class="col-sm-6"> ' +
                    '<div class="dataTables_info" role="alert" aria-live="polite" aria-relevant="all">' +
                    '显示第{{param.pageFrom}}到{{param.pageTo}}条记录 </div> ' +
                    '</div> <div class="col-sm-6"> <div class="dataTables_paginate paging_simple_numbers"> ' +
                    '<ul class="pagination"> <li class="paginate_button previous"><a href="javascript:;" ng-click="prevPage()">' +
                    '上一页</a></li> <li ng-class="{\'active\': item == pageNo}" ng-repeat="item in param.pageNos"><a href="javascript:;" ' +
                    'ng-click="choosePage(item)">{{item}}</a></li> <li class="paginate_button next">' +
                    '<a href="javascript:;" ng-click="nextPage()">下一页</a></li> </ul> </div> </div> </div>',
                    link: function(scope,ele,attrs) {

                        scope.pageNo = 1;
                        scope.pageSize = 10;
                        scope.pageSizes = [10,25,50,100];
                        scope.param = {
                            searchValue : "",
                            pageNos: [scope.pageNo,scope.pageNo+1,scope.pageNo+2,scope.pageNo+3,scope.pageNo+4,scope.pageNo+5],
                            pageFrom : (scope.pageNo-1)*scope.pageSize+1,
                            pageTo : (scope.pageNo-1)*scope.pageSize+scope.pageSize
                        };

                        scope.prevPage = function()
                        {
                            if(scope.pageNo > 1){
                                scope.pageNo--;
                                scope.loadPageList();
                            }
                            if(scope.pageNo+1>parseInt(scope.response.count/scope.pageSize)+1)
                            {
                                scope.param.pageNos = [scope.pageNo];
                            }
                            else if(scope.pageNo+2>parseInt(scope.response.count/scope.pageSize)+1)
                            {
                                scope.param.pageNos = [scope.pageNo,scope.pageNo+1];
                            }
                            else if(scope.pageNo+3>parseInt(scope.response.count/scope.pageSize)+1)
                            {
                                scope.param.pageNos = [scope.pageNo,scope.pageNo+1,scope.pageNo+2];
                            }
                            else if(scope.pageNo+4>parseInt(scope.response.count/scope.pageSize)+1)
                            {
                                scope.param.pageNos = [scope.pageNo,scope.pageNo+1,scope.pageNo+2,scope.pageNo+3];
                            }
                            else if(scope.pageNo+5>parseInt(scope.response.count/scope.pageSize)+1)
                            {
                                scope.param.pageNos = [scope.pageNo,scope.pageNo+1,scope.pageNo+2,scope.pageNo+3,scope.pageNo+4];
                            }
                            else
                            {
                                scope.param.pageNos = [scope.pageNo,scope.pageNo+1,scope.pageNo+2,scope.pageNo+3,scope.pageNo+4,scope.pageNo+5];
                            }
                        }

                        scope.nextPage = function()
                        {
                            if(scope.pageNo < parseInt(scope.response.count/scope.pageSize)+1)
                            {
                                scope.pageNo++;
                                scope.loadPageList();
                            }
                            if(scope.pageNo+1>parseInt(scope.response.count/scope.pageSize)+1)
                            {
                                scope.param.pageNos = [scope.pageNo];
                            }
                            else if(scope.pageNo+2>parseInt(scope.response.count/scope.pageSize)+1)
                            {
                                scope.param.pageNos = [scope.pageNo,scope.pageNo+1];
                            }
                            else if(scope.pageNo+3>parseInt(scope.response.count/scope.pageSize)+1)
                            {
                                scope.param.pageNos = [scope.pageNo,scope.pageNo+1,scope.pageNo+2];
                            }
                            else if(scope.pageNo+4>parseInt(scope.response.count/scope.pageSize)+1)
                            {
                                scope.param.pageNos = [scope.pageNo,scope.pageNo+1,scope.pageNo+2,scope.pageNo+3];
                            }
                            else if(scope.pageNo+5>parseInt(scope.response.count/scope.pageSize)+1)
                            {
                                scope.param.pageNos = [scope.pageNo,scope.pageNo+1,scope.pageNo+2,scope.pageNo+3,scope.pageNo+4];
                            }
                            else
                            {
                                scope.param.pageNos = [scope.pageNo,scope.pageNo+1,scope.pageNo+2,scope.pageNo+3,scope.pageNo+4,scope.pageNo+5];
                            }
                        }

                        scope.choosePage = function(val)
                        {
                            scope.pageNo = val;
                            scope.loadPageList();
                            if(scope.pageNo+1>parseInt(scope.response.count/scope.pageSize)+1)
                            {
                                scope.param.pageNos = [scope.pageNo];
                            }
                            else if(scope.pageNo+2>parseInt(scope.response.count/scope.pageSize)+1)
                            {
                                scope.param.pageNos = [scope.pageNo,scope.pageNo+1];
                            }
                            else if(scope.pageNo+3>parseInt(scope.response.count/scope.pageSize)+1)
                            {
                                scope.param.pageNos = [scope.pageNo,scope.pageNo+1,scope.pageNo+2];
                            }
                            else if(scope.pageNo+4>parseInt(scope.response.count/scope.pageSize)+1)
                            {
                                scope.param.pageNos = [scope.pageNo,scope.pageNo+1,scope.pageNo+2,scope.pageNo+3];
                            }
                            else if(scope.pageNo+5>parseInt(scope.response.count/scope.pageSize)+1)
                            {
                                scope.param.pageNos = [scope.pageNo,scope.pageNo+1,scope.pageNo+2,scope.pageNo+3,scope.pageNo+4];
                            }
                            else
                            {
                                scope.param.pageNos = [scope.pageNo,scope.pageNo+1,scope.pageNo+2,scope.pageNo+3,scope.pageNo+4,scope.pageNo+5];
                            }
                        }

                        scope.searchPageList = function()
                        {
                            scope.loadPageList();
                        }

                        scope.displayPageList = function()
                        {
                            scope.pageNo = 1;
                            scope.loadPageList();
                        }

                        scope.loadPageList();
                    }
                }
        }])
        .directive('contenteditable',function(){
            return {
            restrict:'A',
            require:'?ngModel',
            link:function(scope,element,atrrs,ngModel){
                if(!ngModel)return;
                ngModel.$render=function(){
                    element.html(ngModel.$viewValue||'');
                }
                element.on('blur keyup change', function() {
                    scope.$apply(read);
                });
                function read() {
                    var html = element.html();
                    ngModel.$setViewValue(html);
                }
            }
        }})
        .directive('imgUpload',function(){
            return {
                //通过设置项来定义
                restrict: 'AE',
                scope: false,
                template: '<div class="fl"><input type="button" id="storeBtn" style="padding:0; position: absolute; top: 0; left: 0; background: none; border: none;color: #fff; width:84px; height: 30px; line-height: 30px;" value="选择文件"><input type="file" name="img" id="file" ng-disabled="imgDisabled" style="position: absolute; top: 0; left: 0; opacity: 0;height: 30px;" accept=".jpg,.png"></div>', //name:img 与接口字段相对应。
                replace: true,
                link: function(scope, ele, attrs) {
                    ele.bind('click', function() {
                        $('#file').val('');
                    });
                    ele.bind('change', function() {
                        scope.file = ele[0].children[1].files;
                        if (scope.file[0].size > 52428800) {
                            alert("图片大小不大于50M");
                            scope.file = null;
                            return false;
                        }
                        scope.fileName = scope.file[0].name;
                        var postfix = scope.fileName.substring(scope.fileName.lastIndexOf(".") + 1).toLowerCase();
                        if (postfix != "jpg" && postfix != "png") {
                            alert("图片仅支持png、jpg类型的文件");
                            scope.fileName = "";
                            scope.file = null;
                            scope.$apply();
                            return false;
                        }
                        scope.$apply();
                        scope.reader = new FileReader(); //创建一个FileReader接口
                        if (scope.file) {
                            //获取图片（预览图片）
                            scope.reader.readAsDataURL(scope.file[0]); //FileReader的方法，把图片转成base64
                            scope.reader.onload = function(ev) {
                                scope.$apply(function() {
                                    scope.thumb = {
                                        imgSrc: ev.target.result //接收base64，scope.thumb.imgSrc为图片。
                                    };
                                });
                            };

                        } else {
                            alert('上传图片不能为空!');
                        }
                    });
                }
            }})
        .directive('detailPage',function(){
            return{
                restrict:'EAC',
                replace:true,
                template:'<div class="row"><div class="col-sm-4">' +
                '<button class="btn btn-default" ng-click="detailPage.prevDatas()" style="margin-right:20px;">上一页</button>当前是第{{pageNum}}页' +
                '<button class="btn btn-default" ng-click="detailPage.nextDatas()" style="margin-left:20px;">下一页</button></div></div>',
                link: function(scope,ele,attrs) {

                    scope.pageNum = 1;
                    scope.pageSize = 10;
                    scope.detailPage = {};

                    scope.detailPage.nextDatas = function(){
                        if(scope.hint != 'none'){
                            scope.pageNum++;
                        }
                        scope.detailPageList();
                    }

                    scope.detailPage.prevDatas = function(){
                        scope.pageNum > 1? scope.pageNum--:scope.pageNum = 1;
                        scope.detailPageList();
                    }
                }
            }
        })
        .directive('delTool',function(){
            return {
                restrict:'EAC',
                replace:true,
                template:'<div class="alertContainer" ng-if="alertInfo"><div class="alertTit">{{delInfo}}</div><div class="alertBtn">'+
                '<div ng-click="delSure()">确认</div><div class="keepLight" ng-click="delCancle()">取消</div></div></div>',
                link:function(scope,element,atrrs){
                    scope.delInfo = atrrs.info;
                    scope.delete = function(id){
                        scope.alertInfo = true;
                        scope.delSure = function(){
                            scope.delFun(id);
                        }
                        scope.delCancle = function(){
                            scope.alertInfo = false;
                        }
                    }
                }
            }})

        /*问卷后台*/
        .directive('surveyMenu', ['$rootScope','$state','UserGetUser','UserLoginOut','Global','List','SurveyUtil',
            function ($rootScope,$state,UserGetUser,UserLoginOut,Global,List,SurveyUtil) {
                return {
                    restrict: 'EAC',
                    template: function(tElement,tAttrs){

                        var _html = '';
                        _html += '<nav class="navbar navbar-default navbar-cls-top "role="navigation" style="margin-bottom: 0;width:100%;"> ' +
                            '<div class="navbar-header"> 调研问卷管理平台' +
                            ' </div> <div style="color: white;padding: 15px 50px 5px 50px;float: right;font-size: 16px;">' +
                            '欢迎您！{{loginUser.phone}} &nbsp;&nbsp;<a class="btn btn-danger square-btn-adjust" ng-click="loginOut()">退出</a> ' +
                            '</div> </nav> <!-- /. NAV TOP  --> <nav class="navbar-default navbar-side" role="navigation" id="navBar" style="background:#f5f5f5;height:100%;"> ' +
                            '<div class="sidebar-collapse" style="padding-bottom:100px;"> <ul class="nav" id="main-menu" style="background: #f5f5f5"> ' +
                            '<li style="display:block;" class="text-center"> <img src="images/logo.png" class="user-image img-responsive"/> </li>';


                        if(tAttrs.title == "singleStatistic")
                        {
                            _html += '<li style="display:block;"><a class="active-menu" ui-sref="singleStatistic"><img src="images/member02.png">';
                        }
                        else
                        {
                            _html += '<li style="display:block;"><a ui-sref="singleStatistic"><img src="images/member01.png">';
                        }
                        _html += '单因素分布比例 </a> </li>';

                        if(tAttrs.title=='crossStatistic')
                        {
                            _html += '<li style="display:block;"><a class="active-menu" ui-sref="crossStatistic"><img src="images/practitioner02.png">';
                        }
                        else
                        {
                            _html += '<li style="display:block;"><a ui-sref="crossStatistic"><img src="images/practitioner01.png">';
                        }
                        _html += '交叉分析 </a> </li>';

                        if(tAttrs.title=='diyStatistic')
                        {
                            _html += '<li style="display:block;"><a class="active-menu" ui-sref="diyStatistic"><img src="images/product02.png">';
                        }
                        else
                        {
                            _html += '<li style="display:block;"><a ui-sref="diyStatistic"><img src="images/product01.png">';
                        }
                        _html += '自定义查询 </a> </li>';

                        return _html;
                    },
                    link:function(scope,ele,attrs){
                        UserGetUser.get({},function(data){
                            SurveyUtil.checkResponseData(data);
                            if(data.result == Global.SUCCESS){
                                scope.loginUser = data.responseData;
                            }
                        })

                        scope.loginOut = function(){
                            UserLoginOut.get({},function(data){
                                if(data.result == Global.LOGIN_OUT){
                                    $state.go('surveyLogin')
                                }
                            })
                        };
                        //获取问题
                        List.save({},function(data){
                            scope.questionNamesAll = data.responseData;
                            angular.forEach(data.responseData,function(data,index){
                                if(data.questionName == '年龄'){
                                    data.questionData = [
                                        {questionItem:'0-65',questionItemName:'小于65岁'},
                                        {questionItem:'65-69',questionItemName:'65-69岁'},
                                        {questionItem:'70-74',questionItemName:'70-74岁'},
                                        {questionItem:'75-79',questionItemName:'75-79岁'},
                                        {questionItem:'80-84',questionItemName:'80-84岁'},
                                        {questionItem:'85-200',questionItemName:'85岁及以上'}
                                    ]
                                }
                                if(data.questionName == '性别'){
                                    data.questionData = [
                                        {questionItem:'A',questionItemName:'男'},
                                        {questionItem:'B',questionItemName:'女'}
                                    ]
                                }
                                if(data.questionType == 'select'){
                                    data.questionData = [
                                        {questionItem:'右安门街道',questionItemName:'右安门街道'},
                                        {questionItem:'太平桥街道',questionItemName:'太平桥街道'},
                                        {questionItem:'西罗园街道',questionItemName:'西罗园街道'},
                                        {questionItem:'大红门街道',questionItemName:'大红门街道'},
                                        {questionItem:'南苑街道',questionItemName:'南苑街道'},
                                        {questionItem:'东高地街道',questionItemName:'东高地街道'},
                                        {questionItem:'东铁匠营街道',questionItemName:'东铁匠营街道'},
                                        {questionItem:'卢沟桥街道',questionItemName:'卢沟桥街道'},
                                        {questionItem:'丰台街道',questionItemName:'丰台街道'},
                                        {questionItem:'新村街道',questionItemName:'新村街道'},
                                        {questionItem:'长辛店街道',questionItemName:'长辛店街道'},
                                        {questionItem:'云岗街道',questionItemName:'云岗街道'},
                                        {questionItem:'方庄地区',questionItemName:'方庄地区'},
                                        {questionItem:'宛平城地区',questionItemName:'宛平城地区'},
                                        {questionItem:'马家堡街道',questionItemName:'马家堡街道'},
                                        {questionItem:'和义街道',questionItemName:'和义街道'},
                                        {questionItem:'南苑乡',questionItemName:'南苑乡'},
                                        {questionItem:'卢沟桥乡',questionItemName:'卢沟桥乡'},
                                        {questionItem:'王佐镇',questionItemName:'王佐镇'},
                                        {questionItem:'花乡',questionItemName:'花乡'}
                                    ]
                                }
                                if(data.questionId == 9) {
                                    data.questionName = '老人户籍所在的街道';
                                }
                                if(data.questionType == 'single' || data.questionType == 'multi' || data.questionName == '年龄' || data.questionName == '性别' || data.questionId == 9){
                                    scope.questionNames.push(data);
                                }
                            })
                        })

                    }
                }
            }])
        .directive('surveyLoading', [function(){
            return {
                restrict: 'EAC',
                replace: true,
                template: '<div ng-show="loadShow" class="panel panel-default" style="position:fixed;left:45%;top:45%;z-index:5;">'
                            + '<div class="panel-body loading">数据量过大，正在查询，请稍等…</div>'
                            + '</div>',
                link: function(scope,ele,attrs) {}
            }
        }])



})
