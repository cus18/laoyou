angular.module('controllers',[]).controller('extendMessageDetailCtrl',
    ['$scope','$interval','$rootScope','$stateParams','GetExtendMessageDetail',
        function ($scope,$interval,$rootScope,$stateParams,GetExtendMessageDetail) {

    $scope.$on('$ionicView.enter', function(){
        GetExtendMessageDetail.save({extendMessageId:$stateParams.extendMessageId},
            function(data){
            $scope.extendMessageDetail = data.responseData;
        })
    });

}])