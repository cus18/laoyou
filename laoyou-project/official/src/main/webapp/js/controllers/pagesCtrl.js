/**
 * Created by 郑强丽 on 2017/8/2.
 */

angular.module('controllers',[]).controller('pagesCtrl',
    ['$scope','$timeout','$rootScope','$stateParams','$state','GetNews','$location','$anchorScroll',
        function ($scope,$timeout,$rootScope,$stateParams,$state,GetNews,$location,$anchorScroll) {

            $scope.catalogId = $stateParams.catalogId;
            $scope.articleId = $stateParams.articleId;

            $scope.loadPageList = function(){
                $location.hash('wrapper');
                $anchorScroll();
                GetNews.save({
                    pageNo:$scope.pageNo,
                    pageSize:$scope.pageSize,
                    requestData:{
                        category:{id:$scope.catalogId},
                        id:$scope.articleId
                    }
                },function(data){
                    if(data.responseData){
                        $scope.response = data.responseData;
                        $scope.articleCont = data.responseData.news[0].articleData.content;
                        if($scope.response.totalCount > $scope.pageSize){
                            $scope.pageToolShow = true;
                        }
                        $timeout(function(){
                            $('#article').html($scope.articleCont);
                            $('.newsArticle').show()
                            for(var i = 0; i < $('.newsArticle').length; i++){
                                var articleCont = $('.newsArticle').eq(i).html().replace(/&gt;/g,'>').replace(/&lt;/g,'<');
                                $('.newsArticle').eq(i).html(articleCont);
                                var articleOmit = $('.newsArticle').eq(i).text().substring(0,140);
                                $('.newsArticle').eq(i).text(articleOmit + '......');
                            }
                        },500)

                    }
                })
            }


        }])

