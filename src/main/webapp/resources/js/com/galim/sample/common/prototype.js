/**
 * Array remove
 * @param idx
 */
Array.prototype.remove = function(idx){
   var temp = new Array();
   var i = this.length;

   while(i > idx){
       var kk = this.pop();
       temp.push(kk);

       i--;
   }

   for(var i=temp.length - 2; i>=0; i--){
       this.push(temp[i]);
   }
};

/**
 * Array contains
 * @param element
 * @returns {Boolean}
 */
Array.prototype.contains = function (element) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == element) {
			return true;
		}
	}
	return false;
};

String.prototype.contains = function(it) { 
	return this.indexOf(it) != -1; 
};

String.prototype.replaceAt=function(index, char) {
    return this.substr(0, index) + char + this.substr(index + 1);
};
/**
 * 서버로 보내기 위한 데이터 모음
 * @returns
 */
function ActionContext() {
	this.action = new Array();
}
ActionContext.prototype.action = null;
/*
 * CRUD 타입 
 */
ActionContext.prototype.listType = "list";
ActionContext.prototype.selectType = "select";
ActionContext.prototype.updateType = "update";
ActionContext.prototype.deleteType = "delete";
ActionContext.prototype.insertType = "insert";
ActionContext.prototype.commandType = "command";
// 등록, 변경, 삭제 요청 처리
ActionContext.prototype.addRequestType = "addRequest";
// 자동, 수동 모드 전환
ActionContext.prototype.requestModeType = "requestMode";

ActionContext.prototype.addDeviceType = "addDevice";

/*
 * ActionContext에서 사용하는 상수 모음
 */
ActionContext.prototype.queryName = "QUERYID";
ActionContext.prototype.actionName = "ACTION";
ActionContext.prototype.responseID = "RESPONSEID";

/**
 * insert
 * @param queryID
 * @param obj
 */
ActionContext.prototype.insertAction = function(responseID, queryID, obj) {
	obj[this.responseID] = responseID;
	obj[this.queryName] = queryID;
	obj[this.actionName] = this.insertType;
	this.action.push(obj);
};
/**
 * select
 * @param queryID
 * @param obj
 */
ActionContext.prototype.selectAction = function(responseID, queryID, obj) {
	obj[this.responseID] = responseID;
	obj[this.queryName] = queryID;
	obj[this.actionName] = this.selectType;
	this.action.push(obj);
};
/**
 * delete
 * @param queryID
 * @param obj
 */
ActionContext.prototype.deleteAction = function(responseID, queryID, obj) {
	obj[this.responseID] = responseID;
	obj[this.queryName] = queryID;
	obj[this.actionName] = this.deleteType;
	this.action.push(obj);
};
/**
 * update
 * @param queryID
 * @param obj
 */
ActionContext.prototype.updateAction = function(responseID, queryID, obj) {
	obj[this.responseID] = responseID;
	obj[this.queryName] = queryID;
	obj[this.actionName] = this.updateType;
	this.action.push(obj);
};

ActionContext.prototype.listAction = function(responseID, queryID, obj) {
	obj[this.responseID] = responseID;
	obj[this.queryName] = queryID;
	obj[this.actionName] = this.listType;
	this.action.push(obj);
};

ActionContext.prototype.commandAction = function(responseID, obj) {
	obj[this.responseID] = responseID;
	obj[this.actionName] = this.commandType;
	this.action.push(obj);
};
ActionContext.prototype.addRequestAction = function(responseID, obj) {
	obj[this.responseID] = responseID;
	obj[this.actionName] = this.addRequestType;
	this.action.push(obj);
};
ActionContext.prototype.requestModeAction = function(responseID, obj) {
	obj[this.responseID] = responseID;
	obj[this.actionName] = this.requestModeType;
	this.action.push(obj);
};

ActionContext.prototype.addDeviceAction = function(responseID, obj) {
	obj[this.responseID] = responseID;
	obj[this.actionName] = this.addDeviceType;
	this.action.push(obj);
};
/**
 * 모아놓은 파라미터 리턴
 * @returns
 */
ActionContext.prototype.getAction = function() {
	return this.action;;
};

/**
 * 서버로 보내기 위한 데이터 모음
 * @returns
 */
function GridPrototype() {
	
}
GridPrototype.prototype.search = function(target, prefix) {
	this.grid.commonGrid("setUrlParam", getInputData(target, prefix));
	this.grid.commonGrid("reload");
};

GridPrototype.prototype.getExportQuery = function() {
	var obj = this.grid.commonGrid("getUrlParam");
	var query = "";
	
	for(var name in obj) {
		switch(name) {
		// 쓸모없는값들 걸러내기
		case "pagw" :
		case "row" :
		case "QUERY_ID" :
			break;
		default :
			query = query + "?" + name + "=" + obj[name];
		}
	}
	query = "QUERY_ID=" + this.getName() + ".export";
	return query;
};
function CalenderPrototype() {
	this.initCalender();
}
CalenderPrototype.prototype.changeDateValue = function(date) {
	$("#" + date.data('altfield')).html(date.val());
	$("#" + date.data('altfield').split("_")[1]).html(date.val());
	date.data('altfield') == "calender_startText" ? $("#STARTDATE").val(date.val()) : $("#ENDDATE").val(date.val());
};
CalenderPrototype.prototype.initCalender = function(target) {
	var object = this;
	
	$("#calenderButton").click(function() {
		$("#dialogModelCalender").dialog({
			width: 480,
			height: 300,
			resizable: false,
			modal: true,
		});
	});
	
	var dates = $(".calender").each(function(index, item) {
		var date = $(this);
		date.datepicker({
	        defaultDate: date.data('defaultdate'),
			onSelect : function( selectedDate ) {
				var option = this.id == "calender_startCalender" ? "minDate" : "maxDate";
				var id = this.id == "calender_startCalender" ? "calender_endCalender" : "calender_startCalender";
				$("#" + id).datepicker("option", option, selectedDate);
				$("#" + date.data('altfield')).html(selectedDate);
			}
		});
		object.changeDateValue(date);
	});
	
	$("#weekSearchButton, #monthSearchButton").click(function() {
		var targetID = this.id;
		$.each(dates, function(index, item) {
			var date = $(item);
			var text = targetID == "weekSearchButton" ? setWeekCalendar(item.id) : setMonthCalendar(item.id);
			$("#" + item.id).datepicker("setDate", text);
			
			object.changeDateValue(date);
		});
		target.search($("#searchDiv div :input"));
	});
};