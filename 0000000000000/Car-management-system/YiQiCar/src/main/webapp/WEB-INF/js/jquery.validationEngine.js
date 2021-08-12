/*
 *插件的表单验证方法如下：
 *1. validate[required,custom[onlyLetter],length[0,100]]
 *
 *参数说明：required表示表单必填，custom[]表示验证的条件，length表示长度
 *formValidation插件其它说明：
 *optional: Special: 表单值不为空的情况
 *required: 必埴
 *length[0,100] : 长度范围
 *minCheckbox[7] : 最小复选框数
 *confirm[fieldID] : 匹配其它字段 (如:confirm password)
 *telephone : 匹配电话号码规则
 *email : 匹配电子邮箱规则
 *onlyNumber : 匹配数字规则
 *noSpecialCaracters : 匹配字符规则
 *onlyLetter : 匹配字母规则
 *date : 匹配 YYYY-MM-DD 格式
 *
 *formValidation插件应用实例
 *一，验证单选框
 *<input class="validate[required] radio" type="radio" name="radiogoupe" value="5"/>
 *<input class="validate[required] radio" type="radio" name="radiogoupe" value="3"/>
 *<input class="validate[required] radio" type="radio" name="radiogoupe" value="9"/>
 *
 *二，验证复选框
 *<input class="validate[minCheckbox[2]] checkbox" type="checkbox" name="checkboxgroupe" value="5"/>
 *<input class="validate[minCheckbox[2]] checkbox" type="checkbox" name="checkboxgroupe" value="3"/>
 *<input class="validate[minCheckbox[2]] checkbox" type="checkbox" name="checkboxgroupe" value="9"/>
 *
 *三，验证下拉框
 *<select name="sport" id="sport" class="validate[required]">
 *	<option value="1">biuuu_1</option>
 *	<option value="2">biuuu_2</option>
 *	<option value="3">biuuu_3</option>
 *	<option value="4">biuuu_4</option>
 *</select>
 *
 *四，验证电话号码
 *<input class="validate[required,custom[telephone]] text-input" type="text" name="telephone"/>
 *
 *五，验证邮箱
 *<input class="validate[required,custom[email]] text-input" type="text" name="email" id="email" />
 *
 *如上实例，使用formValidation插件实现表单验证方法比较简单，主要在于其实现的个性化错误提示，同时，可自定义匹配规则，如下：
 *"telephone":{
 *	"regex":"/^[0-9-()]+$/",
 *	"alertText":"* 请输入正确的电话号码"},
 *
 *其中regex表示匹配规则
 *这样使用alertText就可以实现自定义的表单错误提示文本，这与Validation插件的使用方法相同，使用jQuery表单验证插件 formValidation实现个性化错误提示。
 */

$(document).ready(function() {

	// SUCCESS AJAX CALL, replace "success: false," by:     success : function() { callSuccessFunction() }, 
	$("[class^=validate]").validationEngine({
		success :  false,
		failure : function() {}
	})
});

jQuery.fn.validationEngine = function(settings) {
	if($.validationEngineLanguage){					// IS THERE A LANGUAGE LOCALISATION ?
		allRules = $.validationEngineLanguage.allRules
	}else{
		allRules = {"required":{    			  // Add your regex rules here, you can take telephone as an example
							"regex":"none",
							"alertText":"* 必填项",
							"alertTextCheckboxMultiple":"* 必选项",
							"alertTextCheckboxe":"* 必选项"},
						"length":{
							"regex":"none",
							"alertText":"* 请核对字数",
							"alertText2":"",
							"alertText3": ""},
						"minCheckbox":{
							"regex":"none",
							"alertText":"* 超出选择范围"},	
						"confirm":{
							"regex":"none",
							"alertText":"* 输入内容不同"},		
						"telephone":{
							"regex":"/^[0-9\-\(\)\ ]+$/",
							"alertText":"* 请核对您的电话号码"},	
						"email":{
							"regex":"/^[a-zA-Z0-9_\.\-]+\@([a-zA-Z0-9\-]+\.)+[a-zA-Z0-9]{2,4}$/",
							"alertText":"* 请核对您的Email"},	
						"date":{
                             "regex":"/^[0-9]{4}\-\[0-9]{1,2}\-\[0-9]{1,2}$/",
                             "alertText":"* 时间格式为YYYY-MM-DD"},
						"onlyNumber":{
							"regex":"/^[0-9\ ]+$/",
							"alertText":"* 只允许填写数字"},	
						"noSpecialCaracters":{
							"regex":"/^[0-9a-zA-Z]+$/",
							"alertText":"* 不允许特殊字符"},	
						"onlyLetter":{
							"regex":"/^[a-zA-Z\ \']+$/",
							"alertText":"* 只允许填写字母"}
					}	
	}

 	settings = jQuery.extend({
		allrules:allRules,
		success : false,
		failure : function() {}
	}, settings);	


	$("form").bind("submit", function(caller){   // ON FORM SUBMIT, CONTROL AJAX FUNCTION IF SPECIFIED ON DOCUMENT READY
		if(submitValidation(this) == false){
			if (settings.success){
				settings.success && settings.success(); 
				return false;
			}
		}else{
			settings.failure && settings.failure(); 
			return false;
		}
	})
	$(this).not("[type=checkbox]").bind("blur", function(caller){loadValidation(this)})
	$(this+"[type=checkbox]").bind("click", function(caller){loadValidation(this)})
	
	var buildPrompt = function(caller,promptText) {			// ERROR PROMPT CREATION AND DISPLAY WHEN AN ERROR OCCUR
		var divFormError = document.createElement('div')
		var formErrorContent = document.createElement('div')
		var arrow = document.createElement('div')
		
		
		$(divFormError).addClass("formError")
		$(divFormError).addClass($(caller).attr("name"))
		$(formErrorContent).addClass("formErrorContent")
		$(arrow).addClass("formErrorArrow")

		$("body").append(divFormError)
		$(divFormError).append(arrow)
		$(divFormError).append(formErrorContent)
		$(arrow).html('<div class="line10"></div><div class="line9"></div><div class="line8"></div><div class="line7"></div><div class="line6"></div><div class="line5"></div><div class="line4"></div><div class="line3"></div><div class="line2"></div><div class="line1"></div>')
		$(formErrorContent).html(promptText)
	
		callerTopPosition = $(caller).offset().top;
		callerleftPosition = $(caller).offset().left;
		callerWidth =  $(caller).width()
		callerHeight =  $(caller).height()
		inputHeight = $(divFormError).height()

		callerleftPosition = callerleftPosition + callerWidth -30
		callerTopPosition = callerTopPosition  -inputHeight -10
	
		$(divFormError).css({
			top:callerTopPosition,
			left:callerleftPosition,
			opacity:0
		})
		$(divFormError).fadeTo("fast",0.8);
	};
	var updatePromptText = function(caller,promptText) {	// UPDATE TEXT ERROR IF AN ERROR IS ALREADY DISPLAYED
		updateThisPrompt =  $(caller).attr("name")
		$("."+updateThisPrompt).find(".formErrorContent").html(promptText)
		
		callerTopPosition  = $(caller).offset().top;
		inputHeight = $("."+updateThisPrompt).height()
		
		callerTopPosition = callerTopPosition  -inputHeight -10
		$("."+updateThisPrompt).animate({
			top:callerTopPosition
		});
	}
	var loadValidation = function(caller) {		// GET VALIDATIONS TO BE EXECUTED
		
		rulesParsing = $(caller).attr('class');
		rulesRegExp = /\[(.*)\]/;
		getRules = rulesRegExp.exec(rulesParsing);
		str = getRules[1]
		pattern = /\W+/;
		result= str.split(pattern);	
		
		var validateCalll = validateCall(caller,result)
		return validateCalll
		
	};
	var validateCall = function(caller,rules) {	// EXECUTE VALIDATION REQUIRED BY THE USER FOR THIS FILED
		var promptText =""	
		var prompt = $(caller).attr("name");
		var caller = caller;
		isError = false;
		callerType = $(caller).attr("type");
		
		for (i=0; i<rules.length;i++){
			switch (rules[i]){
			case "optional": 
				if(!$(caller).val()){
					closePrompt(caller)
					return isError
				}
			break;
			case "required": 
				_required(caller,rules);
			break;
			case "custom": 
				 _customRegex(caller,rules,i);
			break;
			case "length": 
				 _length(caller,rules,i);
			break;
			case "minCheckbox": 
				 _minCheckbox(caller,rules,i);
			break;
			case "confirm": 
				 _confirm(caller,rules,i);
			break;
			default :;
			};
		};
		if (isError == true){
			
			if($("input[name="+prompt+"]").size()> 1 && callerType == "radio") {		// Hack for radio group button, the validation go the first radio
				caller = $("input[name="+prompt+"]:first")
			}
			($("."+prompt).size() ==0) ? buildPrompt(caller,promptText)	: updatePromptText(caller,promptText)
		}else{
			closePrompt(caller)
		}		
		
		/* VALIDATION FUNCTIONS */
		function _required(caller,rules){   // VALIDATE BLANK FIELD
			callerType = $(caller).attr("type")
			
			if (callerType == "text" || callerType == "password" || callerType == "textarea"){
				
				if(!$(caller).val()){
					isError = true
					promptText += settings.allrules[rules[i]].alertText+"<br />"
				}	
			}
			if (callerType == "radio" || callerType == "checkbox" ){
				callerName = $(caller).attr("name")
		
				if($("input[name="+callerName+"]:checked").size() == 0) {
					isError = true
					if($("input[name="+callerName+"]").size() ==1) {
						promptText += settings.allrules[rules[i]].alertTextCheckboxe+"<br />" 
					}else{
						 promptText += settings.allrules[rules[i]].alertTextCheckboxMultiple+"<br />"
					}	
				}
			}	
			if (callerType == "select-one") { // added by paul@kinetek.net for select boxes, Thank you
					callerName = $(caller).attr("name");
				
				if(!$("select[name="+callerName+"]").val()) {
					isError = true;
					promptText += settings.allrules[rules[i]].alertText+"<br />";
				}
			}
			if (callerType == "select-multiple") { // added by paul@kinetek.net for select boxes, Thank you
					callerName = $(caller).attr("id");
				
				if(!$("#"+callerName).val()) {
					isError = true;
					promptText += settings.allrules[rules[i]].alertText+"<br />";
				}
			}
		}
		function _customRegex(caller,rules,position){		 // VALIDATE REGEX RULES
			customRule = rules[position+1]
			pattern = eval(settings.allrules[customRule].regex)
			
			if(!pattern.test($(caller).attr('value'))){
				isError = true
				promptText += settings.allrules[customRule].alertText+"<br />"
			}
		}
		function _confirm(caller,rules,position){		 // VALIDATE FIELD MATCH
			confirmField = rules[position+1]
			
			if($(caller).attr('value') != $("#"+confirmField).attr('value')){
				isError = true
				promptText += settings.allrules["confirm"].alertText+"<br />"
			}
		}
		function _length(caller,rules,position){    // VALIDATE LENGTH
		
			startLength = eval(rules[position+1])
			endLength = eval(rules[position+2])
			feildLength = $(caller).attr('value').length

			if(feildLength<startLength || feildLength>endLength){
				isError = true
				promptText += settings.allrules["length"].alertText+startLength+settings.allrules["length"].alertText2+endLength+settings.allrules["length"].alertText3+"<br />"
			}
		}
		function _minCheckbox(caller,rules,position){    // VALIDATE CHECKBOX NUMBER
		
			nbCheck = eval(rules[position+1])
			groupname = $(caller).attr("name")
			groupSize = $("input[name="+groupname+"]:checked").size()
			
			if(groupSize > nbCheck){	
				isError = true
				promptText += settings.allrules["minCheckbox"].alertText+"<br />"
			}
		}

		return(isError) ? isError : false;
	};
	var closePrompt = function(caller) {	// CLOSE PROMPT WHEN ERROR CORRECTED
		closingPrompt = $(caller).attr("name")

		$("."+closingPrompt).fadeTo("fast",0,function(){
			$("."+closingPrompt).remove()
		});
	};
	var submitValidation = function(caller) {	// FORM SUBMIT VALIDATION LOOPING INLINE VALIDATION
		var stopForm = false
		$(caller).find(".formError").remove()
		var toValidateSize = $(caller).find("[class^=validate]").size()
		
		$(caller).find("[class^=validate]").each(function(){
			var validationPass = loadValidation(this)
			return(validationPass) ? stopForm = true : "";	
		});
		if(stopForm){							// GET IF THERE IS AN ERROR OR NOT FROM THIS VALIDATION FUNCTIONS
			destination = $(".formError:first").offset().top;
			$("html:not(:animated),body:not(:animated)").animate({ scrollTop: destination}, 1100)
			return true;
		}else{
			return false
		}
	};
};