var app = angular.module('app', ['ui.router', 'ngAnimate','blocks.meizi','ng-layer']);

app.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/login");
    $stateProvider
        .state('login', {
            url: "/login",
            views: {
            	'' :{
            		templateUrl: "web/login/login.view.html",
                    controller: "loginCtrl",
            	}
            },
        })
        .state('home', {
        	url: "/home",
            views: {
            	'' :{
            		templateUrl: "web/home/home.view.html",
                    controller: "homeCtrl",
            	},
            },
//        	templateUrl: 'home/home.view.html',
//            controller: "homeCtrl",
//            params: {
//            },
        })
        .state('home.user', {
            url: "/user",
            views: {
                'main-container': {
                    templateUrl: "web/home/user.template.html",
                    controller: "userCtrl",
                }
            }
        })
        .state('home.server', {
        	url: "/server",
        	views: {
        		'main-container': {
        			templateUrl: "web/home/server.template.html",
        			controller: "serverCtrl",
        		}
        	}
        })
        .state('home.computer', {
        	url: "/computer",
        	views: {
        		'main-container': {
        			templateUrl: "web/home/computer.template.html",
        			controller: "computerCtrl",
        		}
        	}
        })
        .state('home.set', {
        	url: "/set",
        	views: {
        		'main-container': {
        			templateUrl: "web/home/set.template.html",
        			controller: "setCtrl",
        		}
        	}
        })
        .state('home.repertory', {
        	url: "/repertory",
        	views: {
        		'main-container': {
        			templateUrl: "web/home/repertory.template.html",
        			controller: "repertoryCtrl",
        		}
        	}
        })
        .state('home.lyRecord', {
        	url: "/lyRecord",
        	views: {
        		'main-container': {
        			templateUrl: "web/home/lyRecord.template.html",
        			controller: "lyRecordCtrl",
        		}
        	}
        })
        .state('home.eqp', {
        	url: "/eqp",
        	views: {
        		'main-container': {
        			templateUrl: "web/eqp/eqp.template.html",
        			controller: "eqpCtrl",
        		}
        	}
        })
        .state('home.eqpMaintain', {
        	url: "/eqpMaintain",
        	views: {
        		'main-container': {
        			templateUrl: "web/eqp/eqpMaintain.template.html",
        			controller: "eqpMaintainCtrl",
        		}
        	}
        })
        
  
});