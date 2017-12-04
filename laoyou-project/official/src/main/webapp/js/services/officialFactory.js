var official = 'official/';


define(['appOfficial'], function (app) {
    app
        .factory('GetNews',['$resource',function ($resource){
            return $resource(official + 'getNews');
        }])
        .factory('GetCategory',['$resource',function ($resource){
            return $resource(official + 'getCategory');
        }])
        .factory('SearchNews',['$resource',function ($resource){
            return $resource(official + 'searchNews');
        }])

})
