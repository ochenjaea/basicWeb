$(document).ready(function() {
	$.datepicker.regional['ko'] = {
		closeText : '닫기',
		prevText : '이전달',
		nextText : '다음달',
		currentText : '오늘',
		monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월',
				'9월', '10월', '11월', '12월' ],
		monthNamesShort : [ '1', '2', '3', '4', '5', '6', '7', '8',
				'9', '10', '11', '12' ],
		dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
		dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
		dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
		weekHeader : 'Wk',
		dateFormat : 'yy-mm-dd',
		firstDay : 0,
		isRTL : false,
		showMonthAfterYear : true,
		yearSuffix : '년'
	};
	// datepicker locale
	$.datepicker.setDefaults($.datepicker.regional["ko"]);

	$("#" + pageName + "Menu").addClass("current");
	$("#" + subPageName + "SubMenu").addClass("current");

	if ($('.nav > ul > li.current').children('ul').length > 0) {
		$('#header').css('height', '109px');
		$('.bg-nav-sub').show();
	}
	$('.util .support a').mouseover(function() {
		$(this).parent().children('ul').show();
	});
	$('.util .support').mouseleave(function() {
		$(this).children('ul').hide();
	});

	/* tab */
	$('.tab-type1 li').eq(0).addClass('first');
	$('.tab-type1-gray li').eq(0).addClass('first');
	$('.repo-detail-tab li:last').addClass('last');
	
	$(".modal-button .close").click(function(event) {
		var id = $(this).parent().parent().attr("id");
		$("#" + id).dialog("close");
	});
	
	/* user-content-menu2 2013-02-04 */
	$('.user-content-menu2 a.v3').click(function(){
		var sublink = $(this).parent().children('.v3-sublink');
		if (sublink.is(':hidden'))
		{
			sublink.show();
		} else {
			sublink.hide();
		}
		return false;
	});
	$('.user-content-menu2 a.v3').parent().mouseleave(function(){
		$('.v3-sublink').hide();
	});

	/* selectbox */
	if ($('body').find('.selectbox'.length > 0))
	{
		$(".selectbox").selectbox();
	}
});

var initMessage = function(initCallback, target) {
	$.i18n.properties({
		path:contextPath + "/properties/" + currentLocale,
	    mode:'map',  
	    callback: function() {
	    	code.init(initCallback, target);
		}
	});
};


//파라미터 받기
var paramRequest = function()
{
	this.getParameter = function( name )
	{
		var rtnval = '';
		var nowAddress = decodeURIComponent(location.href);
		var parameters = (nowAddress.slice(nowAddress.indexOf('?')+1,nowAddress.length)).split('&');
		for(var i = 0 ; i < parameters.length ; i++)
		{
			var varName = parameters[i].split('=')[0];
			if(varName.toUpperCase() == name.toUpperCase())
			{
				rtnval = parameters[i].split('=')[1];
				
			}
		}
		return rtnval;
	}		
};

//컨테스트 패스
var getContextPath = function(){
    var offset=location.href.indexOf(location.host)+location.host.length;
    var ctxPath=location.href.substring(offset,location.href.indexOf('/',offset+1));
    return ctxPath;
};

//페이징처리
var pageMake = function(count, pageSize, curPage, perPage){
	
	var obj = new Object();
	
	if (curPage == 0)
		curPage = 1;

	// 시작 페이지 끝페이지 생성
	var start = ((curPage - 1) * perPage) + 1;
	var end = curPage * perPage;
	
	obj["START"] = start;
	obj["END"] = end;
	
	var pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
	var checkPage = curPage % pageSize;
	if (checkPage == 0) {
		curPage = curPage - 1;
	}
	var resultpage = parseInt(curPage / pageSize);
	var startPage = resultpage * pageSize + 1;
	var endPage = startPage + perPage - 1;
	if (endPage > pageCount)
		endPage = pageCount;
	if (checkPage == 0) {
		curPage = curPage + 1;
	}
	
	obj["STARTPAGE"] = parseInt(startPage);
	obj["ENDPAGE"] = parseInt(endPage);
	obj["PAGECOUNT"] = parseInt(pageCount);
	obj["PAGESIZE"] = parseInt(pageSize);
	obj["CURPAGE"] = parseInt(curPage);
	
	return obj;		
};