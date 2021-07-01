var commonDataController = {
    _ajaxHander: ajaxDataController(),
    _url: {
    	login:'/login'
    },
	login: function (params, callback) {
	        this._ajaxHander.post(this._url.login, params, function (data) {
	            callback(data);
	        })
	    } 
};