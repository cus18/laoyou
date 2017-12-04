define(['appPractitioner','jquery'], function (app,$) {
    app
        .directive('practitionerHead', ['$rootScope','$state',
            function ($rootScope,$state) {
                return {
                    restrict: 'EAC',
                    replace: true,
                    template: '<header><div style="float:left;margin-top:4%;margin-left:2%;font-size:0.34rem;" ' +
                    'ng-click="enterGroupTalk()">&lt; 群聊</div> <h1>{{elderName}}</h1> <div style="float:right;' +
                    'margin-top:4%;font-size:0.34rem;margin-right:2%;" ng-click="enterMember()">会员 ></div> </header>',
                    link: function(scope,ele,attrs) {

                        scope.enterGroupTalk = function(){
                            window.WebViewJavascriptBridge.callHandler('enterGroupTalk','',function(responseData){});
                        }

                        scope.enterMember = function(){
                            window.location.href = "native?groupId=" + sessionStorage.getItem("groupId");
                        }

                    }
                }
            }])
        .directive('pageLoading', [function(){
                return {
                    restrict: 'EAC',
                    replace: true,
                    template: '<span class="spinner-loader" ' +
                    'style="position:absolute;top:60%;right:45%;" ng-if="loadingStatus">Loading&#8230;</span>',
                    link: function(scope,ele,attrs) {}
                }
            }])
        .directive('pageHint', [function(){
            return {
                restrict: 'EAC',
                replace: true,
                template: function(tElement,tAttrs){
                    var _html = '<div class="CommentHint" ng-if="InfErrorHint">' + tAttrs.cont + '</div>';
                    return _html;
                },
                link: function(scope,ele,attrs) {}
            }
        }])

})
