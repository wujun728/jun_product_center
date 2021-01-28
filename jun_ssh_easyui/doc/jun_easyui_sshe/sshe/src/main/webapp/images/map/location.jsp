<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page
	import="com.apps.map.Constants
         ,com.apps.map.PositionInfo
         ,com.apps.map.LocationUtil;"%>
<%
	String path = request.getContextPath();
	String skinPath = path + "/webfiles/common/skin";
	String webfilesPath = path + "/webfiles";
%>
<%
	String location = null;
	try {
		location = Constants.MAP_INIT_LOCATION_DEF;

	} catch (Exception e) {
		location = null;
	}
	
	location = (location == null ? "31.99527,118.73444" : location);

	String useProxy = null;
	try {
		useProxy = "1";
	} catch (Exception e) {
		useProxy = null;
	}
	useProxy = (useProxy == null ? "1" : useProxy);
%>
<script type="text/javascript">
    window.mapLocation = '<%=location%>
	';
	window.useProxy =
<%=useProxy%>
	;
</script>
<script type="text/javascript"
	src="<%=webfilesPath%>/common/js/gis/mapbar.js"></script>
<script type="text/javascript"
	src="<%=webfilesPath%>/common/js/gis/search.js"></script>

