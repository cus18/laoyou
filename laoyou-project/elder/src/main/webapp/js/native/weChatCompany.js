/**
 * Created by 郑强丽 on 2017/9/14.
 */
document.write('<scr'+'ipt src="js/libs/jquery/jquery-2.1.3.min.js?ver=1.0.7"></scr'+'ipt>');


var weChatPageInit = function(){
    $("body").css("visibility","visible");

    /*tab切换*/
    $('.menu_tab li').click(function(){
        $(this).addClass('active').siblings('li').removeClass('active');
        $('.content').children('div').eq($(this).index()).show().siblings().hide();
    })



}

