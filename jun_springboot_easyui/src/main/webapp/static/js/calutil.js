Date.prototype.format = function(format) {
	/*
	 * eg:format="YYYY-MM-dd hh:mm:ss"; format1="YYYY/MM/dd hh:mm:ss"
	 * format2="YYYY-MM-dd"
	 */
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};

/**
 * 日期工具类，包括日期格式化、日期解析、一个月的日历
 * 默认：ISO格式
 * 
 */
var calUtil = {
	ISO:{
		DATE:"yyyy-MM-dd",
		DATE_TIME:"yyyy-MM-dd hh:mm:ss"
	},
	compare:function(date1_str,date2_str){
		var date1_time=calUtil.parseDate(date1_str).getTime();
		var date2_time=calUtil.parseDate(date2_str).getTime();
		console.log(date1_time+"\t"+date2_time)
		if(date1_time>date2_time){
			return 1
		}else if(date1_time==date2_time){
			return 0;
		}else{
			return -1;
		}
	},	
	//------------------时间格式化--------------------
	formatLong:function(value){
		if(!value) return "";
		var year = value.toString().substring(0, 4);
		var month = value.toString().substring(4, 6);
		var date = value.toString().substring(6, 8);
		if (value.toString().length > 8) {
			var hour = value.toString().substring(8, 10);
			var minute = value.toString().substring(10, 12);
			var second = value.toString().substring(12, 14);
			return year + "-" + month + "-" + date + " " + hour + ":"
					+ minute + ":" + second;
		} else {
			return year + "-" + month + "-" + date;
		}
	},
	//参数为Date类型，或毫秒数
	formatDate:function(date){
		if(!date)
			return null;
		if(Object.prototype.toString.call(date) != "[object Date]"){
			if(typeof date=="string" && date.indexOf(" ") != -1){
				date=date.split(" ").join("T");
			}
			date=new Date(date);
		}
		return date.format(calUtil.ISO.DATE);
	},
	//参数为Date类型，或毫秒数
	formatDateTime:function(date){	
		if(!date)
		return null;
		if(Object.prototype.toString.call(date) != "[object Date]")
			date=new Date(date);
		return date.format(calUtil.ISO.DATE_TIME);
	},
	formatDateByNum : function(year, month, day) {
		var date = new Date(year, month-1, day);
		return date.format(calUtil.ISO.DATE);
	},
	formatDateByEN : function(date) {
		var info = date.toString().split(" ");
		return info[1] + " " + info[2] + ", " + info[3];
	},
	formatDateByCN : function(date) {
		if(Object.prototype.toString.call(date) != "[object Date]")
			date=new Date(date);
		var syear = date.getFullYear(), smonth = date.getMonth() + 1, sday = date.getDate();
		return syear + "年" + smonth + "月" + sday + "日";
	},
	
	//------------------时间解析--------------------
	
	parseLong:function(value){
		if(!value) return null;
		var year = value.toString().substring(0, 4);
		var month = value.toString().substring(4, 6);
		var date = value.toString().substring(6, 8);
		if (value.toString().length > 8) {
			var hour = value.toString().substring(8, 10);
			var minute = value.toString().substring(10, 12);
			var second = value.toString().substring(12, 14);
			return new Date(year,month-1,date,hour,minute,second);
		}else{
			return new Date(year,month-1,date);
		}
	},
	parseDate : function(strDate) {
		if(!strDate) return null;
		var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/, function(a) {
			return parseInt(a, 10) - 1;
		}).match(/\d+/g) + ')');
		return date;
	},
	getDateByWeekDay:function(theDate,weekday){
		var firstDateOfWeek = calUtil.getFirstDateOfWeek(theDate);
		var tempDate = new Date(theDate);
		tempDate.setDate(firstDateOfWeek.getDate() + weekday);
		return tempDate;
	},
	//一段时间的date数组
	periodArr:function(theDate,days){
		var dateArr = new Array();
		for ( var i = 0; i < days; i++) {
			var tempDate = new Date(theDate);
			tempDate.setDate(theDate.getDate() + i);
			dateArr.push(tempDate);
		}
		return dateArr;
	},
	
	getWeekDateArr : function(theDate) {
		var firstDateOfWeek = calUtil.getFirstDateOfWeek(theDate);
		return calUtil.periodArr(firstDateOfWeek,7);
	},
	// 得到每周的第一天(周日)
	getFirstDateOfWeek : function(theDate) {
		var firstDateOfWeek;
		theDate.setDate(theDate.getDate() - theDate.getDay()); //	 
		firstDateOfWeek = theDate;
		return firstDateOfWeek;
	},
	// 得到每周的最后一天(周六)
	getLastDateOfWeek : function(theDate) {
		var lastDateOfWeek;
		theDate.setDate(theDate.getDate() + 6 - theDate.getDay()); //	 
		lastDateOfWeek = theDate;
		return lastDateOfWeek;
	},

	getNextdayDate : function(theDate) {
		var target = new Date();
		var targetday_milliseconds = theDate.getTime() + 1000 * 60 * 60 * 24 * 1;
		target.setTime(targetday_milliseconds); // 注意，这行是关键代码
		return target;
	},
	getNextdayDateStr : function(theDate) {
		return calUtil.getNextdayDate(theDate).format(calUtil.ISO.DATE);
	},
	getCNDayOfWeek : function(objDate) {
		var aryDay = new Array("日","一","二","三","四","五","六");  
		return aryDay[objDate.getDay()];
	},
	getDaysInmonth : function(iMonth, iYear) {
		var dPrevDate = new Date(iYear, iMonth, 0);
		return dPrevDate.getDate();
	},

	bulidCal : function(iYear, iMonth) {
		var aMonth = new Array();
		aMonth[0] = new Array(7);
		aMonth[1] = new Array(7);
		aMonth[2] = new Array(7);
		aMonth[3] = new Array(7);
		aMonth[4] = new Array(7);
		aMonth[5] = new Array(7);
		aMonth[6] = new Array(7);
		var dCalDate = new Date(iYear, iMonth - 1, 1);
		var iDayOfFirst = dCalDate.getDay();
		var iDaysInMonth = calUtil.getDaysInmonth(iMonth, iYear);
		var iVarDate = 1;
		var d, w;
		aMonth[0][0] = "日";
		aMonth[0][1] = "一";
		aMonth[0][2] = "二";
		aMonth[0][3] = "三";
		aMonth[0][4] = "四";
		aMonth[0][5] = "五";
		aMonth[0][6] = "六";
		for (d = iDayOfFirst; d < 7; d++) {
			aMonth[1][d] = iVarDate;
			iVarDate++;
		}
		for (w = 2; w < 7; w++) {
			for (d = 0; d < 7; d++) {
				if (iVarDate <= iDaysInMonth) {
					aMonth[w][d] = iVarDate;
					iVarDate++;
				}
			}
		}
		return aMonth;
	}
};