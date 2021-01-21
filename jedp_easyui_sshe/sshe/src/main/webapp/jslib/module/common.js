/**
 * 字符串的时间格式判断  
 * YYYY-MM-DD
 * 
 * @param startTime
 * @param endTime
 * @returns {Boolean}
 */
function checkValidTime(startTime, endTime) {
	if (startTime.length > 0 && endTime.length > 0) {
		var startTmp = startTime.split("-");
		var endTmp = endTime.split("-");
		var sd = new Date(startTmp[0], startTmp[1], startTmp[2]);
		var ed = new Date(endTmp[0], endTmp[1], endTmp[2]);
		if (sd.getTime() > ed.getTime()) {
			//alert("开始日期不能大于结束日期");
			return false;
		}
	}
	return true;
}