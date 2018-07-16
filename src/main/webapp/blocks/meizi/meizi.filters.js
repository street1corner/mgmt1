(function(){
	'use strict';
	var lang = 'zh-CN';
	
	
	angular.module('blocks.meizi').filter('message', ['localization', function(localization) {
	    return function(input, type, sub) {
	        if (!localization[lang] || !localization[lang].message)
	          return input;
	        try {
	          if (sub) {
	            return localization[lang].message[type][sub][input] || input;
	          } else if (type) {
	            return localization[lang].message[type][input] || input;
	          } else {
	            return localization[lang].message[input] || input;
	          }
	        } catch (e) {
	          return input;
	        }
	      };
	    }]);
	
	//过滤器:每天分为上下
	angular.module('blocks.meizi').filter('replacea', function () {
		return function (input) {
			if(input%2 != 0){
				return '下'
			}else{
				return '上'
			}
			
		}
	});
})();