document.write('<scr'+'ipt src="js/libs/jquery-2.1.3.min.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/WebViewJavascriptBridge.js"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/important/appInterface.js"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/jquery.touchSlider.js"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/jquery.touchSlider.js"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/angular.min.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/angular-resource.min.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/angular-sanitize.min.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/angular-ui-router.min.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/i18n/angular-locale_zh-cn.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/ocLazyLoad.require.min.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/highcharts.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/highcharts-ng.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/ng-infinite-scroll.min.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/moment.min.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/fullcalendar.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/calendar.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/gcal.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/ui-bootstrap-tpls-0.9.0.js?ver=1.0.7"></scr'+'ipt>');

var getUrlParams = function()
{
    var args=new Object();
    var query=location.search.substring(1);//获取查询串
    var pairs=query.split("&");//在逗号处断开
    for(var i=0;i<pairs.length;i++)
    {
        var pos=pairs[i].indexOf('=');//查找name=value
        if(pos==-1)   continue;//如果没有找到就跳过
        var argname=pairs[i].substring(0,pos);//提取name
        var value=pairs[i].substring(pos+1);//提取value
        args[argname]=unescape(value);//存为属性
    }
    return args;
}

var getElderInfo = function(){
    var args = getUrlParams();
    var param = {groupID: args["groupId"]};
    $.ajaxSetup({
        contentType : 'application/json'
    });
    $.get('laoyou/getEasemobGroupByGroupID',param,
        function(data) {
        if(data.result=="0x00001")
        {
            if(data.responseData.elderPhoto){
                var imgSrc = data.responseData.elderPhoto;
            }else{
                var imgSrc = 'images/user_photo.png';
            }

            var elderInfo = '<div class="elderContaine bgf clearfix">' +
                '<img class="elderPic" src="' + imgSrc + '" alt=""> ' +
                '<div class="elderInf fl"> ' +
                '<h2>' + data.responseData.elderName+ '</h2> <p>' +
                '<span>档案编号</span>'+ data.responseData.elderMemberCardID +
                '</p> </div> </div>'
            $('#elderInfo').html(elderInfo);

            $("body").css("visibility","visible");

            sessionStorage.setItem("groupId", args["groupId"]);
            //localStorage.groupId = args["groupId"];

            $('#elderInfo').click(function(){
                window.location.href="#/memberInfo";
            });



        }
        else
        {
            window.location.href="#/login";
        }
        },
        'json');
}

var practitionerFirstPageInit = function(){

    $("#groupChat").click(function(){
        window.WebViewJavascriptBridge.callHandler(
            'enterGroupTalk','',function(responseData){});
    });

    $("#healthServicePackage").click(function(){
        window.location.href="#/healthService/healthServicePackage,";
    })

    $("#healthArchive").click(function(){
        window.location.href="#/healthService/healthArchives,basicInfo";
    })

    $("#healthAssessment").click(function(){
        window.location.href="#/healthService/healthAssessment,";
    })

    $("#detection").click(function(){
        window.location.href="#/detectionDiagnose/detection,bloodSugarTable";
    })

    $("#testReport").click(function(){
        window.location.href="#/detectionDiagnose/testReport,";
    })

    $("#diagnose").click(function(){
        window.location.href="#/detectionDiagnose/diagnoseReport,";
    })

    $("#medicineIntervention").click(function(){
        window.location.href="#/interventionGuidance/medicineIntervention,interventionPlan,";
    })

    $("#mealRecord").click(function(){
        window.location.href="#/interventionGuidance/mealRecord,,";
    })

    $("#sportRecord").click(function(){
        window.location.href="#/interventionGuidance/sportRecord,week,";
    })

    getElderInfo();

    $("#backToNative").click(function(){
        window.WebViewJavascriptBridge.callHandler(
            'backToNative','',function(responseData){});
    });


}
