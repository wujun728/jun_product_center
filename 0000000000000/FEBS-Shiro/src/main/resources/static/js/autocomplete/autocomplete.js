/**
A jQuery plugin for search hints

Author: Lorenzo Cioni - https://github.com/lorecioni
*/

(function($) {
	$.fn.autocomplete = function(params) {
		
		//Selections
		var currentSelection = -1;
		var currentProposals = [];
		
		//Default parameters
		params = $.extend({
			hints: [],
			keyname: '',
			valuename: '',
			placeholder: '',
			width: 300,
			height: 16,
			showButton: true,
			onSubmit: function(text){},
			onBlur: function(){}
		}, params);

		//Build messagess
		this.each(function() {
			//Container
			var searchContainer = $('<div></div>')
				.addClass('autocomplete-container')
				.css('height', params.height * 2);	
				
			//Text input		
			var input = $('<input type="text" autocomplete="off" name="query">')
                .attr('placeholder', params.placeholder)
				.addClass('autocomplete-input')
				.css({
					'width' : params.width,
					'height' : params.height
				});
			

			//Proposals
			var proposals = $('<div></div>')
				.addClass('proposal-box')
				.css("box-shadow", '0 2px 10px rgba(0, 0, 0, 0.075)')
				.css('width', params.width)
				.css("border", '0px solid #f2f2f2')
				.css('top', input.height());
			var proposalList = $('<ul></ul>')
				.addClass('proposal-list');

			proposals.append(proposalList);
				
			input.bind("keyup", function(e){
				if(e.which != 13 && e.which != 27 
						&& e.which != 38 && e.which != 40){				
					currentProposals = [];
					currentSelection = -1;
					proposalList.empty();
                    proposals.css({
                        "border": '0px solid #f2f2f2'
                    });
					if(input.val() != ''){
						var word = input.val();
						proposalList.empty();
						for(var i=0;i<params.hints.length;i++){
							for(var test in params.hints[i]){
								var keyvalue;
								if(test == params.keyname) keyvalue = params.hints[i][test];
								if(test == params.valuename && params.hints[i][test].indexOf(word) != -1){
									currentProposals.push(params.hints[i][test]);	
									var element = $('<li></li>')
										.html(params.hints[i][test])
										.addClass('proposal')
										.attr('value',keyvalue)
										.click(function(){
											input.val($(this).text());
											proposalList.empty();
											params.onSubmit($(this).attr("value"));
											proposals.css({
                                                "border": '0px solid #f2f2f2'
											});
										})
										.mouseenter(function() {
											$(this).addClass('selected');
										})
										.mouseleave(function() {
											$(this).removeClass('selected');
										});
									proposalList.append(element);
                                    proposals.css({
                                        "border": '1px solid #f2f2f2'
                                    });
								}
							}
						}
					}else{
                        proposals.css({
                            "border": '0px solid #f2f2f2'
                        });
					}
				}
			});


			input.blur(function(e){
				currentSelection = -1;
                setTimeout(function () {
                    proposalList.empty();
                },200);
                proposals.css({
                    "border": '0px solid #f2f2f2'
                });
				params.onBlur();
			});

			searchContainer.append(input);
			searchContainer.append(proposals);		
			
			if(params.showButton){
				//Search button
				var button = $('<button type="button" onclick="search()" class="btn btn-save">搜索</button>');
				searchContainer.append(button);	
			}
	
			$(this).append(searchContainer);	
			
			if(params.showButton){
				//Width fix
				searchContainer.css('width', params.width + button.width() + 50);
			}
		});

		return this;
	};

})(jQuery);