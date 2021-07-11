
function _createButton(arg) {
	arg = arg || {};
	var name = arg.name || '',
		btn = K('<a href="javascript:;" class="ke-dialog-btn" title="' + name + '">'+name+'</a>');
	if (arg.click) {
		btn.click(arg.click);
	}
	return btn;
}

// create KToolbar class
function KDialog(options) {
	this.init(options);
}
_extend(KDialog, KWidget, {
	init : function(options) {
		var self = this;
		options.z = options.z || 811213;
		options.shadowMode = false;
		options.autoScroll = _undef(options.autoScroll, true);
		KDialog.parent.init.call(self, options);
		var title = options.title,
			body = K(options.body, self.doc),
			previewBtn = options.previewBtn,
			yesBtn = options.yesBtn,
			noBtn = options.noBtn,
			closeBtn = options.closeBtn,
			showMask = _undef(options.showMask, true);

		self.div.addClass('ke-dialog ke-animated').bind('click,mousedown', function(e){
			e.stopPropagation();
		});
		var contentDiv = K('<div class="ke-dialog-content"></div>').appendTo(self.div);
		var headerDiv = K('<div class="ke-dialog-header"></div>');
		contentDiv.append(headerDiv);
		headerDiv.html(title);
		self.closeIcon = K('<span class="ke-dialog-icon-close" title="' + closeBtn.name + '"></span>').click(closeBtn.click);
		headerDiv.append(self.closeIcon);
		self.draggable({
			clickEl : headerDiv,
			beforeDrag : options.beforeDrag
		});
		var bodyDiv = K('<div class="ke-dialog-body"></div>');
		contentDiv.append(bodyDiv);
		bodyDiv.append(body);
		var footerDiv = K('<div class="ke-dialog-footer"></div>');
		if (previewBtn || yesBtn || noBtn) {
			self.div.append(footerDiv);
			contentDiv.height(self.div.height() - footerDiv.height());
		}
		_each([
			{ btn : previewBtn, name : 'preview' },
			{ btn : yesBtn, name : 'yes' },
			{ btn : noBtn, name : 'no' }
		], function() {
			if (this.btn) {
				var button = _createButton(this.btn);
				button.addClass('ke-dialog-' + this.name);
				footerDiv.append(button);
			}
		});
		if (self.height) {
			bodyDiv.height(_removeUnit(self.height) - headerDiv.height() - footerDiv.height());
		}
		self.div.width(self.div.width());
		self.div.height(self.div.height());
		self.mask = null;
		if (showMask) {
			var docEl = _docElement(self.doc),
				docWidth = Math.max(docEl.scrollWidth, docEl.clientWidth),
				docHeight = Math.max(docEl.scrollHeight, docEl.clientHeight);
			self.mask = _widget({
				x : 0,
				y : 0,
				z : self.z - 1,
				cls : 'ke-dialog-mask',
				width : docWidth,
				height : docHeight
			});
		}
		self.autoPos(self.div.width(), self.div.height());
		self.footerDiv = footerDiv;
		self.bodyDiv = bodyDiv;
		self.headerDiv = headerDiv;
		self.isLoading = false;
	},
	setMaskIndex : function(z) {
		var self = this;
		self.mask.div.css('z-index', z);
	},
	showLoading : function(msg) {
		msg = _undef(msg, '');
		var self = this, body = self.bodyDiv;
		self.loading = K('<div class="ke-dialog-loading"><div class="ke-inline-block ke-dialog-loading-content" style="margin-top:' + Math.round(body.height() / 3) + 'px;">' + msg + '</div></div>')
			.width(body.width()).height(body.height())
			.css('top', self.headerDiv.height() + 'px');
		body.css('visibility', 'hidden').after(self.loading);
		self.isLoading = true;
		return self;
	},
	hideLoading : function() {
		this.loading && this.loading.remove();
		this.bodyDiv.css('visibility', 'visible');
		this.isLoading = false;
		return this;
	},
	remove : function() {
		var self = this;
		if (self.options.beforeRemove) {
			self.options.beforeRemove.call(self);
		}
		self.mask && self.mask.remove();
		self.iframeMask && self.iframeMask.remove();
		self.closeIcon.unbind();
		K('input', self.div).unbind();
		K('button', self.div).unbind();
		self.footerDiv.unbind();
		self.bodyDiv.unbind();
		self.headerDiv.unbind();
		K('iframe', self.div).each(function() {
			//this.src = 'javascript:false';
			K(this).remove();
		});
		KDialog.parent.remove.call(self);
		return self;
	}
});

function _dialog(options) {
	return new KDialog(options);
}
K.DialogClass = KDialog;
K.dialog = _dialog;
