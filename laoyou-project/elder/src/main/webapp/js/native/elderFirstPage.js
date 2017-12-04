document.write('<scr'+'ipt src="js/libs/jquery/jquery-2.1.3.min.js?ver=1.0.7"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/important/appInterface.js"></scr'+'ipt>');
document.write('<scr'+'ipt src="js/libs/important/WebViewJavascriptBridge.js"></scr'+'ipt>');
// document.write('<scr'+'ipt src="js/libs/jquery/jquery.touchSlider.min.js"></scr'+'ipt>');
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

var elderFirstPageInit = function(){

    $("body").css("visibility","visible");

    $("#groupChat").click(function(){
        window.WebViewJavascriptBridge.callHandler(
            'enterGroupTalk','',function(responseData){});
    });

    $("#healthServicePackage").click(function(){
        window.location.href="ionic#/healthService/healthServicePackage,";
    })

    $("#healthArchive").click(function(){
        window.location.href="ionic#/healthService/healthArchives,basicInfo";
    })

    $("#healthAssessment").click(function(){
        window.location.href="ionic#/healthService/healthAssessment,";
    })

    $("#detection").click(function(){
        window.location.href="ionic#/detectionDiagnose/detection,bloodSugarTable";
    })

    $("#testReport").click(function(){
        window.location.href="ionic#/detectionDiagnose/testReport,";
    })

    $("#diagnose").click(function(){
        window.location.href="ionic#/detectionDiagnose/diagnoseReport,";
    })

    $("#medicineIntervention").click(function(){
        window.location.href="ionic#/interventionGuidance/medicineIntervention,interventionPlan,";
    })

    $("#mealRecord").click(function(){
        window.location.href="ionic#/interventionGuidance/mealRecord,,";
    })

    $("#sportRecord").click(function(){
        window.location.href="ionic#/interventionGuidance/sportRecord,week,";
    })
}
