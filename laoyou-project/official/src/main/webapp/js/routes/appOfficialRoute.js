/**
 * 路由
 */
define(['appOfficial'], function(app){
    return app
        .config(['$stateProvider','$urlRouterProvider',
            function($stateProvider,$urlRouterProvider) {
                var loadFunction = function($templateCache, $ocLazyLoad, $q, $http,name,files,htmlURL){
                    lazyDeferred = $q.defer();
                    return $ocLazyLoad.load ({
                        name: name,
                        files: files
                    }).then(function() {
                        return $http.get(htmlURL)
                            .success(function(data, status, headers, config) {
                                return lazyDeferred.resolve(data);
                            }).
                            error(function(data, status, headers, config) {
                                return lazyDeferred.resolve(data);
                            });
                    });
                };

                $stateProvider

                    .state('index', {
                        url: '/index',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'indexCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.indexCtrl',
                                    ['/js/controllers/indexCtrl.js?ver='+officialVersion],
                                    '/js/views/index.html?ver='+officialVersion);
                            }
                        }
                    })
                    .state('searchResult', {
                        url: '/searchResult/:keyWord',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'searchResultCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.searchResultCtrl',
                                    ['/js/controllers/searchResultCtrl.js?ver='+officialVersion],
                                    '/js/views/searchResult.html?ver='+officialVersion);
                            }
                        }
                    })
                    .state('pages', {
                        url: '/pages/:catalogId,:articleId',
                        templateProvider: function() { return lazyDeferred.promise; },
                        controller: 'pagesCtrl',
                        resolve: {
                            load: function($templateCache, $ocLazyLoad, $q, $http) {
                                loadFunction($templateCache, $ocLazyLoad, $q, $http,'app.pagesCtrl',
                                    ['/js/controllers/pagesCtrl.js?ver='+officialVersion],
                                    '/js/views/pages.html?ver='+officialVersion);
                            }
                        }
                    })


                $urlRouterProvider.otherwise('/index')
            }])
})