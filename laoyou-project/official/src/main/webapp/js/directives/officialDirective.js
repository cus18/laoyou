define(['appOfficial','jquery'], function (app,$) {
    app
        .directive('header',function($location,GetCategory){
            return {
                restrict: 'E',
                templateUrl: '/js/views/header.html',
                replace: true,
                link:function(scope, element, attrs){
                    scope.searchShow = function(){
                        scope.searchArea = true;
                        scope.subNavArea = false;
                    }
                    scope.searchHide = function(){
                        scope.searchArea = false;
                        scope.subNavArea = true;
                    }
                    scope.areaHide = function(){
                        scope.searchArea = false;
                        scope.subNavArea = false;
                    }
                    GetCategory.get({},function(data){
                        scope.menuData = data.responseData;
                    })

                    // //平台、设备和操作系统
                    function browserRedirect() {
                        var sUserAgent= navigator.userAgent.toLowerCase();
                        var bIsIpad= sUserAgent.match(/ipad/i) == "ipad";
                        var bIsIphoneOs= sUserAgent.match(/iphone os/i) == "iphone os";
                        var bIsMidp= sUserAgent.match(/midp/i) == "midp";
                        var bIsUc7= sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
                        var bIsUc= sUserAgent.match(/ucweb/i) == "ucweb";
                        var bIsAndroid= sUserAgent.match(/android/i) == "android";
                        var bIsCE= sUserAgent.match(/windows ce/i) == "windows ce";
                        var bIsWM= sUserAgent.match(/windows mobile/i) == "windows mobile";

                        if (bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) {
                            window.location.href= 'http://mobile.hlsenior.com';
                        } else {
                            // window.location= 'PC站链接';
                        }
                    }
                    browserRedirect();
                }
            };
        })
        .directive('footer',function(){
            return{
                restrict: 'E',
                template: '<div class="footerContainer"><div class="footer"><img class="erweima" src="/images/erweima.png" alt="" />' +
                '<div><p>华录健康养老发展有限公司 版权所有</p><p>京ICP备05003852 北京东城区安定门外大街138号皇城国际A座611室' +
                '</p><p class="tel">业务咨询：<span>010-52199987</span></p></div><div class="footerR">' +
                '<p><a href="http://www.hualu.com.cn/" target="_blank">中国华录集团有限公司</a></p><p><a href="http://www.ehualu.com/" target="_blank">' +
                '北京易华录信息技术股份有限公司</a></p><p><a href="http://www.cncaprc.gov.cn/" target="_blank">全国老龄办信息中心</a></p></div></div></div>',
                replace: true
            }
        })
        .directive('page',function(){
            return{
                restrict: 'E',
                replace: true,
                template: '<ul ng-if="pageToolShow" class="pageTool clearfix"> <li><a href="javascript:;" ng-click="prevPage()">' +
                '上一页</a></li> <li ng-class="{\'active\': item == pageNo}" ng-repeat="item in param.pageNos"><a href="javascript:;" ' +
                'ng-click="choosePage(item)">{{item}}</a></li> <li>' +
                '<a href="javascript:;" ng-click="nextPage()">下一页</a></li> </ul>',
                link: function(scope,ele,attrs) {
                    scope.pageNo = 1;
                    scope.pageSize = 8;
                    scope.param = {
                        pageNos: [scope.pageNo,scope.pageNo+1,scope.pageNo+2,scope.pageNo+3,scope.pageNo+4,scope.pageNo+5],
                    };

                    scope.prevPage = function()
                    {
                        if(scope.pageNo > 1){
                            scope.pageNo--;
                            scope.loadPageList();
                        }
                        if(scope.pageNo+1>parseInt(scope.response.totalCount/scope.pageSize)+1)
                        {
                            scope.param.pageNos = [scope.pageNo];
                        }
                        else if(scope.pageNo+2>parseInt(scope.response.totalCount/scope.pageSize)+1)
                        {
                            scope.param.pageNos = [scope.pageNo,scope.pageNo+1];
                        }
                        else if(scope.pageNo+3>parseInt(scope.response.totalCount/scope.pageSize)+1)
                        {
                            scope.param.pageNos = [scope.pageNo,scope.pageNo+1,scope.pageNo+2];
                        }
                        else if(scope.pageNo+4>parseInt(scope.response.totalCount/scope.pageSize)+1)
                        {
                            scope.param.pageNos = [scope.pageNo,scope.pageNo+1,scope.pageNo+2,scope.pageNo+3];
                        }
                        else if(scope.pageNo+5>parseInt(scope.response.totalCount/scope.pageSize)+1)
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
                        if(scope.pageNo < parseInt(scope.response.totalCount/scope.pageSize)+1)
                        {
                            scope.pageNo++;
                            scope.loadPageList();
                        }
                        if(scope.pageNo+1>parseInt(scope.response.totalCount/scope.pageSize)+1)
                        {
                            scope.param.pageNos = [scope.pageNo];
                        }
                        else if(scope.pageNo+2>parseInt(scope.response.totalCount/scope.pageSize)+1)
                        {
                            scope.param.pageNos = [scope.pageNo,scope.pageNo+1];
                        }
                        else if(scope.pageNo+3>parseInt(scope.response.totalCount/scope.pageSize)+1)
                        {
                            scope.param.pageNos = [scope.pageNo,scope.pageNo+1,scope.pageNo+2];
                        }
                        else if(scope.pageNo+4>parseInt(scope.response.totalCount/scope.pageSize)+1)
                        {
                            scope.param.pageNos = [scope.pageNo,scope.pageNo+1,scope.pageNo+2,scope.pageNo+3];
                        }
                        else if(scope.pageNo+5>parseInt(scope.response.totalCount/scope.pageSize)+1)
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
                        if(scope.pageNo+1>parseInt(scope.response.totalCount/scope.pageSize)+1)
                        {
                            scope.param.pageNos = [scope.pageNo];
                        }
                        else if(scope.pageNo+2>parseInt(scope.response.totalCount/scope.pageSize)+1)
                        {
                            scope.param.pageNos = [scope.pageNo,scope.pageNo+1];
                        }
                        else if(scope.pageNo+3>parseInt(scope.response.totalCount/scope.pageSize)+1)
                        {
                            scope.param.pageNos = [scope.pageNo,scope.pageNo+1,scope.pageNo+2];
                        }
                        else if(scope.pageNo+4>parseInt(scope.response.totalCount/scope.pageSize)+1)
                        {
                            scope.param.pageNos = [scope.pageNo,scope.pageNo+1,scope.pageNo+2,scope.pageNo+3];
                        }
                        else if(scope.pageNo+5>parseInt(scope.response.totalCount/scope.pageSize)+1)
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
        })
})
