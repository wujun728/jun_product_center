
//获取Cookie
//<div class="css2"align="center" id="apDiv17" style="margin-left: -32px;"><a style="color:red" href="javascript:goPersonalInformation()",'_blank')">NEW-P-个人信息</a></div>
//<script type="text/javascript" src="personal_information.js"/*tpa=http://ics.chinasoftinc.com/personal_information_web/personal_information.js*/ id="personal_information" webUrl="index-4.htm#/"/*tpa=http://ics.chinasoftinc.com/personal_information_web/#/*/></script>
 
var tempUrl=document.getElementById('personal_information').getAttribute('webUrl');

function getCookie(cookieName) {
  var strCookie = document.cookie;
  var arrCookie = strCookie.split("; ");
  for (var i = 0; i < arrCookie.length; i++) {
    var arr = arrCookie[i].split("=");
    if (cookieName == arr[0]) {
      return arr[1];
    }
  }
  return "";
}

function goPersonalInformation() {
  window.open(tempUrl + "?ROLTPAToken=" + encodeURIComponent(getCookie("ROLTPAToken")) + "&sourceCode=0")
}
