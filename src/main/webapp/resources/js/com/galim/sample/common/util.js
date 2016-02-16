/**
 * 비통기 통신
 * 
 * @param url
 * @param param
 * @param sucessCallback
 * @param errorFunction
 */
var doAction = function(param, url, sucessCallback, errorCallback) {
	urlVal = contextPath + url;
	$.ajax({
		type : 'POST',
		url : urlVal,
		contentType : 'application/json; charset=utf-8',
		data : $.toJSON(param),
		dataType : 'json',
		async: true,
		beforeSend: function(xhr, settings) {
			showLoading();
		},
        complete: function(jqXHR, textStatus) {
        	hideLoading();
        },
		success : function(data) {
			if (sucessCallback) {
				sucessCallback.call(this, data);
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			// 세션 타임아웃
			if (jqXHR.status == 403) {
				window.location.reload();
				return;
			}
			if (errorCallback != null) {
				errorCallback.call(jqXHR, textStatus, errorThrown);
			} else {
				commonError.call(jqXHR, textStatus, errorThrown);
			}
		}
	});
};

/**
 * 동기 통신
 * 
 * @param url
 * @param param
 * @param sucessCallback
 * @param errorFunction
 */
var doActionSync = function(param, url, sucessCallback, errorCallback) {
	urlVal = contextPath + url;
	$.ajax({
		type : 'POST',
		url : urlVal,
		contentType : 'application/json; charset=utf-8',
		data : $.toJSON(param),
		dataType : 'json',
		async: false,
		beforeSend: function(xhr, settings) {
			showLoading();
		},
        complete: function(jqXHR, textStatus) {
        	hideLoading();
        },
		success : function(data) {
			if (sucessCallback) {
				sucessCallback.call(this, data);
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			// 세션 타임아웃
			if (jqXHR.status == 403) {
				window.location.reload();
				return;
			}
			if (errorCallback != null) {
				errorCallback.call(jqXHR, textStatus, errorThrown);
			} else {
				commonError.call(jqXHR, textStatus, errorThrown);
			}
		}
	});
};

var doTreeAction = function(operation, param, sucessCallback, errorCallback) {
	$.ajax({
		type : 'POST',
		url : contextPath + "/treeEdit/" + operation + ".do",
		contentType : 'application/json; charset=utf-8',
		data : $.toJSON(param),
		dataType : 'json',
		success : function(data) {
			if (sucessCallback) {
				sucessCallback.call("", data);
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			if (jqXHR.status == 403) {
				window.location.reload();
				return;
			}
			if (errorCallback != null) {
				errorCallback.call(jqXHR, textStatus, errorThrown);
			} else {
				commonError.call(jqXHR, textStatus, errorThrown);
			}
		}
	});
};

var showLoading = function() {
	var $loadingElement = $("<div id='dialog-loading'><div class='mask'></div></div>");
	var $maskMsgElement = $("<div class='loading_circle'>Loading...</div>");
	//var $maskMsgElement = $("<div class='loading_circle'><img src='"+contextPath+"/resources/img/common/loading.gif'></div>");
	
	$loadingElement.append($maskMsgElement);
	$("body").append($loadingElement);
	$maskMsgElement.css("margin-left", "-" + String($maskMsgElement.outerWidth()/2.0) + "px");
	
	return true;
};

/**
 * Loading End
 * 화면 overlay 및 loading 해제
 */
var hideLoading = function() {
	$("body").find("#dialog-loading").remove();
};

/**
 * 폼 ajax 전송
 * 
 * @param url
 * @param params
 * @returns {___anonymous954_1115}
 */
var getFormAjaxData = function(url, params, paramFunc) {
	var func;
	if (paramFunc == null) {
		func = function() {
			window.location = url;
		};
	} else {
		func = paramFunc;
	}

	var options = {
		action : url,
		data : params,
		resetForm : true,
		success : func,
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status + "_" + textStatus + "_" + errorThrown + "_" + params);
		}
	};
	return options;
};
/**
 * 에러처리 공통화 만들기
 * 
 * @param jqXHR
 * @param textStatus
 * @param errorThrown
 * @return
 */
var commonError = function(jqXHR, textStatus, errorThrown) {
	alert("서버에서 오류가 발생했습니다. \n 관리자에게 문의바랍니다.");
};
/**
 * Input 파라미터 가져오기
 * 
 * @return
 */
var getInputData = function(objList, prefix) {
	var data = {};
	$.each(objList, function(i, obj) {
		if (this.type == "checkbox") {
			if (prefix) {
				if ($(this).is(":checked")) {
					data[this.name.replace(prefix + "_", "")] = true;
				} 
				else {
					data[this.name.replace(prefix + "_", "")] = false;
				}
			} 
			else {
				if ($(this).is(":checked")) {
					data[this.name] = true;
				} 
				else {
					data[this.name] = false;
				}
			}
		}
		// select option 일 경우
		else if (this.type == "select-one") {
			if ($(this).hasClass("keyword")) {
				// 값이 있을때만
				if ($("input.param[name='" + this.name + "']").val() != null&& $("input.param[name='"+ this.name + "']").val() != "") {
					data[this.options[this.selectedIndex].value] = $("input.param[name='" + this.name + "']").val();
				}
			} 
			else {
				if (prefix) {
					if ($(this).val() != null && $(this).val() != "") {
						data[this.name.replace(prefix + "_", "")] = this.options[this.selectedIndex].value;
					}
				} else {
					if ($(this).val() != null && $(this).val() != "") {
						data[this.name] = this.options[this.selectedIndex].value;
					}
				}
			}
		}
		// 일반 input type 일 경우
		else if (this.type == "text" || this.type == "textarea" || this.type == "hidden" || this.type == "password") {
			if (!$(this).hasClass("param")) {
				if (prefix) {
					data[this.name.replace(prefix + "_", "")] = this.value;
				} else {
					data[this.name] = this.value;
				}
			}
		} 
		else if (this.type == "radio") {
			if (prefix) {
				data[this.name.replace(prefix + "_", "")] = $("input[name='" + this.name + "']:checked").val();
			} 
			else {
				data[this.name] = $("input[name='" + this.name + "']:checked").val();
			}
		}
	});
	return data;
};

/**
 * select option 넣기
 * 
 * @param targetID
 * @param objList
 * @param isAll
 */
var selectOption = function(targetID, objList, selectID) {
	var html = "";

	$.each(objList, function(index, item) {
		if (selectID && item.ID == selectID) {
			html += "<option value='" + item.ID + "' selected='selected'>"
					+ item.NAME + "</option>";
		} else {
			html += "<option value='" + item.ID + "'>" + item.NAME
					+ "</option>";
		}
	});
	// 비우기
	$(targetID).html("");
	$(html).appendTo($(targetID));
};


/**
 * 자바스크립트 Date 객체를 Time 스트링으로 변환
 * 
 * @param date
 * @returns {String}
 */
var toTimeString = function(date, isFullDate) {
	var year = date.getFullYear();
	var month = date.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
	var day = date.getDate();
	var msecond = date.getTime(); // Returns the number of milliseconds since
									// midnight Jan 1, 1970

	if (("" + month).length == 1) {
		month = "0" + month;
	}
	if (("" + day).length == 1) {
		day = "0" + day;
	}

	if (isFullDate) {
		return msecond;
	} else {
		return (year + "-" + month + "-" + day);
	}
};

var trime = function(str) {
	if (typeof str == "undefined")
		return "";

	return str.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
};

var checkInputData = function(cssname) {
	var data = getInputData($("." + cssname));
	var isvalid = true;
	$.each(data, function(i, value) {
		if (typeof value == "undefined")
			isvalid = false;
		else if (trime(value) == "")
			isvalid = false;
	});

	//if (isvalid == false)
		//alert("필수 사항을 빠짐없이 입력해주세요.");

	return isvalid;
};

/**
 * 최근
 * @param target
 * @returns {String}
 */
var setMonthCalendar = function(target) {
	var now = new Date();
	if(target.contains("calender_startCalender")) {
		now.setMonth(now.getMonth() - 1);
		return now.getFullYear() + "-" + (now.getMonth() + 1) + "-" + now.getDate();	
	}
	else {
		return now.getFullYear() + "-" + (now.getMonth() + 1) + "-" + now.getDate();
	}
};

var setWeekCalendar = function(target) {
	var now = new Date();
	if(target.contains("calender_startCalender")) {
		now.setDate(now.getDate() - 7);
		return now.getFullYear() + "-" + (now.getMonth() + 1) + "-" + now.getDate();	
	}
	else {
		return now.getFullYear() + "-" + (now.getMonth() + 1) + "-" + now.getDate();
	}
};

var getDefaultPhoneNumber = function(number) {
	var length = number.length;
	var prefix = number.substring(0, 2);
	if (prefix == '+82') {
		number = '0' + number.substring(3, length - 1);
	}
	return number;
};

var getObjectLength = function(obj) {
	var p, len = 0; // 한글문자열 체크를 위함
	for (p = 0; p < obj.val().length; p++) {
		(obj.val().charCodeAt(p) > 255) ? len += 2 : len++; // 한글체크
	}
	return len;
};

var isValidPassword = function(pwd, min, max) {
	var regexp = /[0-9a-zA-Z-_=+\|()*&^%$#@!~`?><\;,.:']/;
	var eng_regexp = /[a-zA-Z]/;
	var num_regexp = /[0-9]/;
	var chac_regexp = /[~!\#$^&*\=+|:;?"<,.>']/;
	var eng_count = 0;
	var num_count = 0;
	var char_count = 0;
	if(pwd.length<min) {
		return false;
	}
	if(pwd.length>max) {
		return false;
	}
	for(var i=0; i<pwd.length; i++){
		if(pwd.charAt(i) != " " && regexp.test(pwd.charAt(i)) == false ){
        	return false;
        }
        if(pwd.charAt(i) != " " && eng_regexp.test(pwd.charAt(i)) == true ){
        	eng_count++;
        }
        if(pwd.charAt(i) != " " && num_regexp.test(pwd.charAt(i)) == true ){
        	num_count++;
        }
        if(pwd.charAt(i) != " " && chac_regexp.test(pwd.charAt(i)) == true ){
        	char_count++;
        }
    }   	
	if((eng_count>0) && (num_count>0) && (char_count>0)) {
		return true;
	}else {
		return false;
	}
};


var isGap = function(str) {
	for(var i=0; i<str.length; i++) {
		var temp = str.charAt(i);
		if(temp == ' ' || temp == '-' || 
				temp == '_' || temp == '.') {
			return true;
		}
	}
	return false;
};

function isValidMail(email) {
	 /* 체크사항 
    - @가 2개이상일 경우 
    - .이 붙어서 나오는 경우 
    -  @.나  .@이 존재하는 경우 
    - 맨처음이.인 경우 
    - @이전에 하나이상의 문자가 있어야 함 
    - @가 하나있어야 함 
    - Domain명에 .이 하나 이상 있어야 함 
    - Domain명의 마지막 문자는 영문자 2~4개이어야 함 */ 

   var check1 = /(@.*@)|(\.\.)|(@\.)|(\.@)|(^\.)/;  
   var check2 = /^[a-zA-Z0-9\-\.\_]+\@[a-zA-Z0-9\-\.]+\.([a-zA-Z]{2,4})$/; 
    
   if (!check1.test(email) && check2.test(email)) { 
       return true; 
   }else { 
       return false; 
   } 
}

var checkNumber = function(str) {
	for(var i=0; i<str.length; i++) {
		if(str.charAt(i) < '0' || str.charAt(i) > '9') {
			return false;
		}
	}
	return true;
};

var getByte = function(str) {
	var byte = 0;
	if(str.length != 0) {
		for(var i=0; i<str.length; i++) {
			var temp = str.charAt(i);
			if(escape(temp).length > 4) {
				byte += 2;
			}else {
				byte ++;
			}
		}
	}
	return byte;
};

var remove_xss = function (str){
	str = str.replace(/</g, "&lt;");
	str = str.replace(/>/g, "&gt;");
	str = str.replace("\"","&gt;");
	str = str.replace("&", "&amp;");
	str = str.replace("%00", null);
	str = str.replace("\"", "&#34;");
	str = str.replace("\'", "&#39;");
	str = str.replace("%", "&#37;");    
	str = str.replace("../", "");
	str = str.replace("..\\\\", "");
	str = str.replace("./", "");
	str = str.replace("%2F", "");
	str = str.replace("&lt;p&gt;", "<p>");
	str = str.replace("&lt;P&gt;", "<P>");
	str = str.replace("&lt;br&gt;", "<br>");
	str = str.replace("&lt;BR&gt;", "<BR>");
	return str;
};