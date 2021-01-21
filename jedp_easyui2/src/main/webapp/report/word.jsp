<%@ page contentType="application/msword; charset=gbk" %>
<%@ page language="java" pageEncoding="GBK"%>
<%
	HttpSession ses=request.getSession();
	String filename = new String(((String)ses.getAttribute("tableTitle")).getBytes("GBK"),"ISO-8859-1"); 
    response.addHeader("Content-Disposition", "filename=" + filename + ".doc");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
   <style type="text/css">
body {
font-family:Verdana,微软雅黑,Arial,Sans
}

.titlep {
font-size:13px;
font-weight:700;
color:#000;
text-align:left;
margin-bottom:5px;
padding:5px 2px 2px
}

.formtable {
border:1px solid #888;
border-collapse:collapse;
border-spacing:2px;
border-color:gray
}

table.formtable>tbody>tr>td {
padding:2px;
border:1px solid #888;
vertical-align:middle;
}

TD.label {
background:#f3f3f3;
vertical-align:middle;
text-align:right
}
    </style>
</head>
<body>
<%
out.println((String)ses.getAttribute("tableHtml"));  
%>
</body>
</html>