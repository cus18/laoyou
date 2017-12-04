define(['appElder','jquery'], function (app,$) {
    app
        .directive('elderHead', ['$rootScope','$state',
            function ($rootScope,$state) {
                return {
                    restrict: 'EAC',
                    replace: true,
                    template: '<header><div style="float:left;margin-top:4%;margin-left:2%;font-size:0.34rem;" ' +
                    'ng-click="return()">&lt; 返回</div> <h1>{{elderName}}</h1> <div style="float:right;' +
                    'margin-top:4%;font-size:0.34rem;margin-right:2%;"ng-click="enterService()">服务 ></div></header>',
                    link: function(scope,ele,attrs) {

                        // scope.enterGroupTalk = function(){
                        //     window.WebViewJavascriptBridge.callHandler('enterGroupTalk','',function(responseData){});
                        // }

                        scope.return = function(){
                            connectWebViewJavascriptBridge(function() {
                                window.WebViewJavascriptBridge.callHandler('returnNative','',function(responseData) {});});
                        }

                        scope.enterService = function(){
                            window.location.href = "ionic#/healthServiceList";
                        }


                    }
                }
        }])
        .directive('pageLoading', [function(){
            return {
                restrict: 'EAC',
                replace: true,
                template: '<span class="spinner-loader" ' +
                'style="position:absolute;top:60%;right:45%;z-index:999" ng-if="loadingStatus">Loading&#8230;</span>',
                link: function(scope,ele,attrs) {}
            }
        }])
})
