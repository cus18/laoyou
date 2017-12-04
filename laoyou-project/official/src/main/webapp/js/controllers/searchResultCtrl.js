/**
 * Created by 郑强丽 on 2017/7/25.
 */
angular.module('controllers',[]).controller('searchResultCtrl',
    ['$scope','$timeout','$rootScope','$stateParams','$state','SearchNews','$location','$anchorScroll',
        function ($scope,$timeout,$rootScope,$stateParams,$state,SearchNews,$location,$anchorScroll) {

            $scope.keyWord = $stateParams.keyWord;

            $scope.loadPageList = function(){
                $location.hash('wrapper');
                $anchorScroll();
                $scope.search = function(){
                    if($scope.keyWord != ''){
                        SearchNews.save({
                            pageNo:$scope.pageNo,
                            pageSize:$scope.pageSize,
                            requestData:$scope.keyWord
                        },function(data){
                            if(data.responseData){
                                $scope.response = data.responseData;
                                if($scope.response.totalCount > $scope.pageSize){
                                    $scope.pageToolShow = true;
                                }
                                $timeout(function(){
                                    $('.newsArticle').show()
                                    for(var i = 0; i < $('.newsArticle').length; i++){
                                        var articleCont = $('.newsArticle').eq(i).html().replace(/&gt;/g,'>').replace(/&lt;/g,'<');
                                        $('.newsArticle').eq(i).html(articleCont);
                                        var articleOmit = $('.newsArticle').eq(i).text().substring(0,160);
                                        $('.newsArticle').eq(i).text(articleOmit + '......');
                                    }
                                },500)
                            }
                        })
                    }
                }
                $scope.search();

                $('input').on('input propertychange',function(){
                    $(document).keypress(function (e) {
                        if (e.keyCode == 13){
                            $scope.search();
                        }
                    })
                })
            }



        }])
