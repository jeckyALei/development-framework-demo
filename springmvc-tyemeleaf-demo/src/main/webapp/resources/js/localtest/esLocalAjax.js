/**
 * 对jquery的ajax的封装，支持请求本地数据和请求远程http服务
 * 该js必须放在jquery.js后面
 */
var otsRedirect = null;
var global_lang = "zh_CN";
(function() {
	var isEsAJaxLocal = true;
    var jqueryAjax = $.ajax;
    $.ajax = function(url,options){
    	if ( typeof url === "object" ) {
			options = url;
			url = undefined;
		}
		options = options || {};
    	if(isEsAJaxLocal){
    		var url = options.url;
    		if(localData){
    			var complete = options.complete;
    			var success = options.success;
    			if(success){
    				success.call(this,localData[url]);
    			}
    			if(complete){
    				success.call(this,localData[url]);
    			}
    		}
    	}else{
    		jqueryAjax(options);
    	}
    };
    otsRedirect = function(method, url){
    	if(url && url.indexOf("?")>0){
    		url = url.subString(0,url.indexOf("?"))
    	}
    	if(localUrl[url]){
    		window.location.href = localUrl[url];
    	}else{
    		alert("找不到"+url+"，请配置localdata.js");
    	}
    };
})();