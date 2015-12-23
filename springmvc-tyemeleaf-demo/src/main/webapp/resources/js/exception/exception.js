/**
 * 对jquery的ajax的封装，支持请求本地数据和请求远程http服务 该js必须放在jquery.js后面
 */
var otsRedirect = null;
var global_lang = "zh_CN";
(function() {
	var ex_message = "<div><h5>Message:</h5></div>";
	ex_message+="<div>"+exception_message.message+"</div>"; 
	ex_message+= "<div><h5>StackTrace:</h5></div>";
	var stackTrace = exception_message.stackTrace;
	for ( var i = 0; i < stackTrace.length; i++) {
		ex_message += "<div>" + stackTrace[i].className + "."
				+ stackTrace[i].methodName + "(" + stackTrace[i].fileName + ":"
				+ stackTrace[i].lineNumber + ")" + "</div>";
	}
	$("#exception_id").html(ex_message);
})();