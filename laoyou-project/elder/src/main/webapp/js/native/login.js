/**
 * Created by 郑强丽 on 2017/8/16.
 */

document.write('<scr'+'ipt src="js/libs/jquery/jquery-2.1.3.min.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/angular/angular.min.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/angular/angular-resource.min.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/angular/angular-sanitize.min.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/angular/angular-ui-router.min.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/angular/angular-locale_zh-cn.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/important/ocLazyLoad.require.min.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/tool/highcharts.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/tool/highcharts-ng.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/tool/ng-infinite-scroll.min.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/tool/moment.min.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/tool/fullcalendar.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/tool/calendar.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/tool/gcal.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/tool/ui-bootstrap-tpls-0.9.0.js?ver=1.0.7"></scr'+'ipt>');


var loginPageInit = function(){
    $("body").css("visibility","visible");

    /*
     *获取验证码
     */
    var codeOnOff = true;
    $('#getCodeBtn').click(function(){
        var phone = $('#userPhone').val();
        if(codeOnOff){
            if(/^1(3|4|5|7|8)\d{9}$/.test(phone)) {

                var param = '{"phoneNum":' + phone  + '}';
                $.ajaxSetup({
                    contentType : 'application/json'
                });
                $.post('laoyou/sendIdentifying',param,
                    function(data) {
                    }, 'json');

                var second = 59;
                $('#getCodeBtn').html(second + '秒后重发').addClass('null');

                var timer = setInterval(function () {
                    if (second <= 0) {
                        clearInterval(timer);
                        $('#getCodeBtn').html('重发验证码').removeClass('null');
                        codeOnOff = true;
                        second = 59;
                    } else {
                        second--;
                        $('#getCodeBtn').html(second + '秒后重发');
                        codeOnOff = false;
                    }
                }, 1000)
            }else{
                $('#errorHint').show().html('请输入正确的手机号');
            }
        }
    })

    $('.inputItem input').focus(function(){
        $('#errorHint').hide();
    })

    /*
     *提交
     */
    $('#loginBtn').click(function(){
        var phone = $('#userPhone').val();
        var code = $('#userPhoneCode').val();
        if(phone && code){
            var param = '{"phoneNum":"' + phone +  '","identifyNum":"' + code + '","source":"elder"' + '}';
            $.ajaxSetup({
                contentType : 'application/json'
            });
            $.post('laoyou/login',param,
                function(data) {
                    if(data!=null ){
                        if(data.result == '0x00001')
                        {
                            window.WebViewJavascriptBridge.callHandler(
                                'loginUserInfo',
                                data.responseData,
                                function(responseData) {
                                    if(responseData=='0x00008')
                                    {
                                        // $rootScope.loginToken = data.responseData.loginToken;
                                        // $state.go("healthService", {firstMenu: 'healthServicePackage', secondMenu: ''});
                                    }
                                }
                            );
                        }
                        else if(data.result == '0x00002')
                        {
                            //验证码不正确
                            $('#errorHint').show().html('验证码输入错误');
                        }
                    }
                }, 'json');
        }
    })

}


