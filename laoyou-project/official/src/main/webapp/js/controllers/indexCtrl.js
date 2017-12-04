/**
 * Created by 郑强丽 on 2017/7/21.
 */
angular.module('controllers',[]).controller('indexCtrl',
    ['$scope','$interval','$timeout','$rootScope','$stateParams','$state','GetNews',
        function ($scope,$interval,$timeout,$rootScope,$stateParams,$state,GetNews) {

            //banner
            $scope.slides= [
                {image:'/images/banner2.jpg',href:''},
                {image:'/images/banner3.jpg',href:''},
                {image:'/images/banner1.jpg',href:''}
            ];

            function bannerPic(){
                var i = -1;
                var timer;
                function move(){
                    $('.bannerItem').eq(i).fadeIn(600).siblings().fadeOut(600);
                }
                function setint(){
                    i++;
                    if(i >= $('.bannerItem').length){
                        i = 0;
                    }
                    move();
                }
                timer = $interval(setint,5000)

                $scope.change = function(act){
                    $interval.cancel(timer);
                    timer = null;
                    if(act == 'next'){
                        i++;
                        if(i >= $('.bannerItem').length){
                            i = 0;
                        }
                    }else if(act == 'prev'){
                        i--;
                        if(i < 0){
                            i = $('.bannerItem').length-1;
                        }
                    }
                    move();
                    timer = $interval(setint,5000)
                }
            }
            bannerPic();

            $scope.indexProject = function(act){
                if(act == 'move')
                {
                    $('.projectText').stop().animate({'bottom':'0px'},200);
                }
                else if(act == 'out')
                {
                    $('.projectText').stop().animate({'bottom':'-92px'},300);
                }

            }

            //新闻中心内容
            function contentMove(obj_li,icon_ul){
                var li = '<li class="active"></li>';
                var num = 1;
                var li_height = obj_li.height();

                for(var i = 0; i < obj_li.length-1; i++)
                {
                    li += '<li></li>'
                }
                icon_ul.html(li);

                function move(){
                    obj_li.parents('ul').stop().animate({'top': -li_height * num});
                    num ++;
                    if( num == obj_li.length){
                        num = 0;
                    }
                    icon_ul.find('li').removeClass('active').eq(num-1).addClass('active');
                }

                $interval(function(){
                    move();
                },3000)

                icon_ul.find('li').click(function(){
                    num = $(this).index();
                    move();
                })
            }

            GetNews.save({
                pageNo:'1',
                pageSize:'6',
                requestData:{
                    category:{id:'edef3527821f407687ce890f031e98ba'},
                    id:''
                }
            },function(data){
                if(data.responseData){
                    $scope.newsList = data.responseData.news;
                    $timeout(function(){
                        $('.newsArticle').show()
                        for(var i = 0; i < $('.newsArticle').length; i++){
                            var articleCont = $('.newsArticle').eq(i).html().replace(/&gt;/g,'>').replace(/&lt;/g,'<');
                            $('.newsArticle').eq(i).html(articleCont);
                            var articleOmit = $('.newsArticle').eq(i).text().substring(0,113);
                            $('.newsArticle').eq(i).text(articleOmit + '...');
                        }
                        contentMove($('#newsContent li'),$('.newsIcon'));
                    },500)
                }

            })

        }])
