		template.helper("codeClass", function(value, codeClass) {
			var value = $.dictManager.getCodeItemName(codeClass, value);
			console.log(JSON.stringify(value));
			return value;
		});

		template.helper("long8data", function(value) {
			return formatForDateTimeBox(value);
		});
		template.helper("long8dataToCNdata",parseLong8Date);
		template.helper("long14dataToCNdata",parseLong14Date);
		template.helper("formatMonth",formatMonth);
		template.helper("formatDate",calUtil.formatDate);
		template.helper("formatDateByCN",calUtil.formatDateByCN);
		template.helper("formatDateTime",calUtil.formatDateTime);
		template.helper("moneyToFixed2",function(value){
			if(isNaN(value)){
				return "";
			}
			return Number(value).toFixed(2);
		});
		