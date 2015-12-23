/**
 * 对jquery的ajax的封装，支持请求本地数据和请求远程http服务 该js必须放在jquery.js后面 同时还负责初始化各种js变量
 * ots_global.calendarLang 初始化calendar组件的国际化信息。
 */
(function() {
	var hiddenRequestAttribute = null;
	var _json_att = "_json_att";
	var hiddenFormId = "_es_hiddenform";
	var hiddenform = "<form  method='post' id='" + hiddenFormId
			+ "'><input type='hidden' name='" + _json_att + "'></input></form>";
	var jqueryAjax = $.ajax;
	ots_global = {};
	$.ajax = function(url, options) {
		if (typeof url === "object") {
			options = url;
			url = undefined;
		}
		options = options || {};
		var isAlert = options.isAlert || true;
		if (options.success) {
			var successOrginal = options.success;
			options.success = function(data, status, jqXHR) {
				if (data && data.validateMessagesShowId) {
					var messages = data.messages;
					if (messages && messages.length > 0) {
						var messageString = "";
						for ( var int = 0; int < messages.length; int++) {
							messageString += messages[int] + "\n";
						}
						dhtmlx.alert({
							title : messages["message.info"],
							ok : messages["button.ok"],
							text : messageString,
							callback : function() {
								if(data.url) {
									window.location = ctx+data.url;
								}
							}
						});
					}

					var validateMessages = data.validateMessages;
					var messageString = "";
					for ( var key in validateMessages) {
						messageString += key + " :" + validateMessages[key]
								+ "</br>";
					}
					if (messageString) {
						if (isAlert) {
							dhtmlx.alert({
								title : messages["message.info"],
								ok : messages["button.ok"],
								text : messageString,
								callback : function() {
								}
							});
						} else {
							$("#" + data.validateMessagesShowId).html(
									messageString).show();
						}
					} else {
						$("#" + data.validateMessagesShowId).html("").hide();
					}
					if (data.attributes) {
						hiddenRequestAttribute = data.attributes;
					} 

				}
				successOrginal.call(this, data, status, jqXHR);
			};
		}
		var data = options.data || {};
		if (hiddenRequestAttribute) {
			data[_json_att] = hiddenRequestAttribute;
		}  else {
			data[_json_att] = $("input[name=_json_att]").val();
		}
		if (sessionInit != null) {
			data["REPEAT_SUBMIT_TOKEN"] = sessionInit.REPEAT_SUBMIT_TOKEN;
		}
		
		options.data = data;
		jqueryAjax.call(this, url, options);
	};
	if (typeof otsRedirect == "undefined") {
		otsRedirect = function(method, url, data, target) {
			data = data || {};
			if (method && method == "post") {
				if ($("#" + hiddenFormId).length == 0) {
					$(document.body).append(hiddenform);
				}
				if (hiddenRequestAttribute) {
					$("#" + hiddenFormId + " input[name='" + _json_att + "']")
							.val(hiddenRequestAttribute);
				}
				$(
						"#" + hiddenFormId + " input[name='" + _json_att
								+ "'] ~ input").remove();
				if (sessionInit != null) {
					$("#" + hiddenFormId).append("<input type='hidden' name='REPEAT_SUBMIT_TOKEN'></input>");
					$("#" + hiddenFormId + " input[name='REPEAT_SUBMIT_TOKEN']").val(sessionInit.REPEAT_SUBMIT_TOKEN);
				}
				if (data) {
					for ( var name in data) {
						var input = "<input type='hidden' name='" + name
								+ "'></input>";
						$("#" + hiddenFormId).append(input);
						$("#" + hiddenFormId + " input[name='" + name + "']")
								.val(data[name]);
					}
					;
				}
				if (target != null) {
					$("#" + hiddenFormId).attr("target", target);
				}
				$("#" + hiddenFormId).attr("action", url);
				$("#" + hiddenFormId).submit();
			} else {
				if (hiddenRequestAttribute) {
					if (url.indexOf("?") > 0) {
						url += "&" + _json_att + "=" + hiddenRequestAttribute;
					} else {
						url += "?" + _json_att + "=" + hiddenRequestAttribute;
					}
				}
				if (data) {
					for ( var name in data) {
						if (url.indexOf("?") > 0) {
							url += "&" + name + "=" + data[name];
						} else {
							url += "?" + name + "=" + data[name];
						}
					}
				}
				switch (target) {
				case "_blank":
					window.open(url);
					break;
				default:
					window.location.href = url;
				}
			}
		};
	}
	;

	// 初始化日历控件的中文信息
	(function() {
		var langData = {
			// date format
			dateformat : '%Y-%m-%d',
			// full names of months
			monthesFNames : [ "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月",
					"9月", "10月", "11月", "12月" ],
			// shortened names of months
			monthesSNames : [ "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月",
					"9月", "10月", "11月", "12月" ],
			// full names of days
			daysFNames : [ "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" ],
			// shortened names of days
			daysSNames : [ "日", "一", "二", "三", "四", "五", "六" ],
			// starting day of a week. Number from 1(Monday) to 7(Sunday)
			weekstart : 1
		};
		ots_global.calendarLang = ots_global.calendarLang || {};
		ots_global.calendarLang["zh_CN"] = langData;
	})();

})();