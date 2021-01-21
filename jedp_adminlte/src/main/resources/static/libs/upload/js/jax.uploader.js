(function ($) {
    $.fn.extend({
        upload: function (options, success, error) {
            var self = this,
                op = Object.prototype,
                os = op.toString;

            function isFun(it) {
                return os.call(it) === '[object Function]';
            }

            //Allow for no options.
            if (isFun(options)) {
                error = success;
                success = options;
                options = {}
            }

            /**支持大小 默认2M**/
            var fileSize = options.size;
            if (!fileSize) {
                fileSize = 2;
            }

            /**支持格式*/
            var imgAccept = options.imgAccept || '';
            //加入额外的格式支持 写法如 <a class="btn btn-sm btn-block btn-primary gtuploader" type="button" data-filetype="pdf,ppt">上传</a>
            var dataFiletype = $(this).attr("data-filetype");
            if (dataFiletype) {
                imgAccept += "," + dataFiletype;
            }
            var accepts = "jpg,jpeg,bmp,png,gif,txt,xls,xlsx,doc,docx,pdf,ppt,pptx,mp3";
            if (typeof(imgAccept) == "boolean") {
                if (imgAccept == true) {
                    accepts = "jpg,jpeg,bmp,png,gif";
                }
            } else if (typeof(imgAccept) == "string") {
                accepts = imgAccept;
            }
            if(typeof(options.accepts) == "string"){
                accepts=options.accepts;
            }
            var basic_server = "http://www.nbclass.com/upload";
            var swf = "https://static.dev.egtcp.com/static/plugins/gt_uploader/Uploader.swf";


            var watermark = $(this).attr("data-watermark");
            if (watermark != "true") {
                watermark = false;
            }
            var watermarkType = $(this).attr("data-watermarkType");

            var defaults = {
                server: basic_server,
                swf: swf,
                autoUpload: true,
                progress: true,
                aspectRatio: 1, // 裁减比例
                crop: false, // 是否开启裁减功能
                pick: {id: self}
            }
            if (watermark) {
                $.extend(defaults, {
                    formData: {watermark: watermark, watermarkType: watermarkType}
                })
            }
            var Uploader = {
                uploader: null,
                options: options || {},
                defaults: {
                    server: basic_server,
                    swf:swf,
                    autoUpload: true,
                    progress: true,
                    aspectRatio: 1, // 裁减比例
                    crop: false, // 是否开启裁减功能
                    pick: {id: self}
                },
                progressbar: $('<div class="uploader-progressbar"><div><div></div></div></div>'),

                // inject the default properties to the option.
                initialize: function () {
                    for (var i in this.defaults) {
                        if (options[i] == null)
                            options[i] = this.defaults[i];
                    }
                    this.run();
                },

                run: function () {
                    var me = this, options = me.options;
                    if (options.crop)
                        Crop.registCrop();
                    var uploader = new WebUploader.Uploader(options);

                    uploader.on('uploadProgress', function (file, percentage) {
                        var picker = document.getElementById('rt_' + file.source.ruid).parentNode;
                        if (options.progress)
                            me.addProgressbar(percentage, picker);
                    }).on('uploadSuccess', function (file, resp) {
                        var picker = document.getElementById('rt_' + file.source.ruid).parentNode;
                        uploader.cancelFile(file);
                        if(resp.status=="success"){
                            if (success) success(resp.url, picker);
                        }else{/*失败*/
                            layer.msg(resp.msg, function(){
                            });
                        }

                    }).on('uploadError', function (file, reason) {
                        var picker = document.getElementById('rt_' + file.source.ruid).parentNode;
                        layer.msg("上传失败", function(){
                        });

                    }).on('beforeFileQueued', function (file) {
                        //var picker = document.getElementById('rt_' + file.source.ruid).parentNode;
                        //var error = $(picker).prev().parents().prev("div").prev("div").find(".gt-upload-error");

                        //检查格式
                        if (fileSize * 1024 * 1024 < file.size) {
                            if(options.lang === 'en'){
                                layer.msg("File Exceeds Size Limits.", function(){
                                });
                            } else {
                                layer.msg("尺寸超过限制", function(){
                                });
                            }
                            
                            //console.log(0);
                            //$(error).text("尺寸超过限制");
                            //$(error).removeClass("hide");
                            return false;
                        }

                        //检查文件类型
                        var extsCount = 0;
                        var exts = accepts.split(",");
                        for (var i = 0; i < exts.length; i++) {
                            if (file.type.indexOf(exts[i]) >= 0 || file.ext.indexOf(exts[i]) >= 0) {
                                extsCount++;
                            }
                        }

                        if (extsCount == 0) {
                            layer.msg("文件格式不正确", function(){
                            });
                            //console.log(1);
                            //$(error).text("文件格式不正确");
                            //$(error).removeClass("hide");
                            return false;
                        }

                    }).on('fileQueued', function (file) {
                        var picker = document.getElementById('rt_' + file.source.ruid).parentNode;
                        if (options.progress)
                            me.addProgressbar(0.01, picker);
                        if (options.crop) {

                            Crop.addCrop(uploader, file);

                        } else {
                            if (options.autoUpload) {
                                uploader.upload();
                            } else
                                success(uploader, file);
                        }

                    }).on('uploadComplete', function () {
                        if (options.progress) setTimeout(function () {
                            me.progressbar.hide();
                        }, 300);
                    });

                },

                addProgressbar: function (percentage, picker) {
                    var me = this;

                    $(picker).after(me.progressbar);
                    me.progressbar.show();
                    //me.addProgressbar = function(percentage) {
                    me.progressbar.children('div').attr('style', 'width: ' + percentage.toFixed(2) * 100 + '%');
                    //};
                }

            }
            //console.log("gt.uploader.js swf:",Uploader.defaults.swf);
            var Crop = {
                $container: null,
                $image: null,
                $photobox: null,
                file: null,
                cropTemplate: ['<div class="cropper-photobox">', '	<div class="photobox">', '		<div class="close photobox-close">X</div>', '		<h4 class="photobox-header-title">剪切你的图片</h4>', '		<div class="cropper-wraper">', '    		<div class="img-container"><img class="cropper-img" src="" alt="" /></div>', ' 		</div>', '		<div class="photobox-footer text-right">', '			<a class="button tiny upload-btn">提交</a>', '		</div>', '	</div>', '	<div class="photobox-overlay photobox-overlay-active"></div>', '</div>',].join(''),

                // inject dom to property.
                initialize: function (uploader) {
                    var me = this;
                    $('body').append(this.cropTemplate);
                    me.$container = $('.cropper-wraper');
                    me.$image = $('.cropper-img');
                    me.$photobox = $(".cropper-photobox");
                    me.photobox = $(".photobox");

                    me.$photobox.on('click', function (event) {
                        var target = event.target;
                        if (target == document.getElementsByClassName('photobox-close')[0] || target == document.getElementsByClassName('photobox-overlay')[0]) {
                            closeBox();
                            return;
                        }
                        // do upload if target is upload button.
                        if (target == document.getElementsByClassName('upload-btn')[0]) {
                            if (me.progressbar)
                                me.progressbar.show();
                            uploader.upload();
                            me.$photobox.hide();
                        }

                    });

                    function closeBox() {
                        me.$photobox.hide();
                        uploader.cancelFile(me.file);
                        Uploader.progressbar.hide();
                    }

                    //check key down is Esc? yes ---> close the box.
                    document.onkeydown = function (e) {
                        var e = e || event || arguments.callee.caller.arguments[0];
                        if (e.keyCode === 27) closeBox();
                    }
                },

                addCrop: function (uploader, file) {
                    var me = this;
                    me.initialize(uploader, file);

                    me.addCrop = function (uploader, file) {
                        me.file = file;
                        uploader.makeThumb(file, function (err, src) {
                            me.$image.cropper.setDefaults({
                                aspectRatio: Uploader.options.aspectRatio
                            });
                            me.$image.cropper('setImgSrc', src);
                            me.$photobox.show();

                            // set photobox left
                            setTimeout(function () {
                                var css = {
                                    "top": (window.innerHeight - me.photobox.height()) / 2 + 'px',
                                    "left": (window.innerWidth - me.photobox.width()) / 2 + 'px',
                                }
                                me.photobox.css(css);
                            }, 100);
                        }, 1, 1);

                    };

                    me.addCrop(uploader, file);
                },

                registCrop: function () {
                    var me = this;
                    WebUploader.Uploader.register({
                        'before-send-file': 'cropImage'
                    }, {
                        cropImage: function (file) {
                            var image = new WebUploader.Lib.Image(), deferred = WebUploader.Deferred();
                            file = this.request('get-file', file);
                            deferred.always(function () {
                                image.destroy();
                                image = null;
                            });
                            image.once('error', deferred.reject);
                            image.once('load', function () {
                                var data = me.$image.cropper('getData');
                                image.crop(data.x1, data.y1, data.width, data.height, 1);
                            });
                            image.once('complete', function () {
                                var blob, size;
                                try {
                                    blob = image.getAsBlob();
                                    size = file.size;
                                    file.source = blob;
                                    file.size = blob.size;
                                    file.trigger('resize', blob.size, size);
                                    deferred.resolve();
                                } catch (e) {
                                    console.log(e);
                                    deferred.resolve();
                                }
                            });

                            file._info && image.info(file._info);
                            file._meta && image.meta(image._meta);
                            image.loadFromBlob(file.source);
                            return deferred.promise();
                        }
                    })
                }
            }
            Uploader.initialize();
        }
    });

})(jQuery);