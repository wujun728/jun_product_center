/*
 * 版权所有(C) 浙江大道网络科技有限公司2011-2020
 * Copyright 2009-2020 Zhejiang GreatTao Factoring Co., Ltd.
 *   
 * This software is the confidential and proprietary information of
 * Zhejiang GreatTao Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Zhejiang GreatTao
 */

/* 全局文件上传回调模块 版本：16.05.13.1*/

/**
 * 功能:回显gtUpload上传控件资源
 * 作者:Kings
 * html格式:必须有个隐藏的input,它必须和上传按钮是兄弟级 class带上input-gtattachurl value=对应的资源链接
 * <div class="upload-item">
 <p class="upload-title">额度通知书</p>
 <p class="upload-file"><i class="icon icon-file-o fz-70"></i></p>
 <p class="upload-btns">
 <a type="button" class="btn btn-sm btn-block btn-info gtuploader" data-type="9">上传</a>
 <input type="hidden" class="gtuploaderinput input-gtattachurl" data-type="9" value="${creditattachementList[2].url}"/>
 </p>
 </div>
 */
 var notFountUrl = "http://errorpage.b0.upaiyun.com/dev-img-zone-404";
if (typeof(envir) !== 'undefined' && (envir == "stage" || envir == "pe")) {
    notFountUrl = "https://errorpage.b0.upaiyun.com/dev-img-zone-404";
}



function echoGtUploadRes(){
    echoGtUploadResClazz(".gtuploader");
}

function echoGtUploadResClazz(clazz) {
    $(".input-gtattachurl").each(function () {
        var picker = $(this).siblings(clazz);
        var url = $(this).val();
        echoGtUploadResMethd(url, picker);
    })
}


/**
 * 功能:回显gtUpload下载的资源
 * 作者:Kings
 * html格式:下载按钮class必须带上 btn-gtdownload href=对应的资源链接
 <div class="upload-item">
 <p class="upload-title">反保理授信资料</p>
 <p class="upload-file"><i class="icon icon-file-o fz-70"></i></p>
 <p class="upload-btns">
 <a type="button" class="btn btn-sm btn-block btn-success btn-gtdownload" href="${creditattachementList[0].url}" target="_blank">下载</a>
 </p>
 </div>
 *调用方式:echoGtDownloadRes();
 */
var echoGtDownloadRes = function () {
    $(".upload-item .btn-gtdownload").each(function () {
        var picker = $(this);
        var url = $(this).attr("href");
        if(!$(picker).attr("href")){//give a default url
            url = notFountUrl;
            $(this).attr("href",url);
        }
        echoGtUploadResMethd(url, picker);
    })
}

/**
 * 功能:回显gtUpload方法
 * 作者:Kings
 * 参数:[url:资源链接 picker:上传按钮dom对象]
 * html格式:在<p class="upload-btns">下放入一个class="input-gtattachurl"的input把上传成功后的url地址赋值给它
 */
var echoGtUploadResMethd = function (url, picker) {
    var picReg = /\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/;
    var wordReg = /\.(doc|docx)$/;
    var excelReg = /\.(xls|xlsx)$/;
    var pdfReg = /\.(pdf)$/;
    var txtReg = /\.(txt)$/;
    var rarReg = /\.(rar|)$/;
    var zipReg = /\.(zip|7z)$/;
    var pptReg = /\.(ppt|pptx)$/;
    var apkReg = /\.(apk)$/;
    var musicReg = /\.(mp3)$/;
    var fileEle = $(picker).closest(".upload-btns").siblings(".upload-file");//upload-file
    var iEle = $(fileEle).find("i");//i节点
    if ($(iEle).parent(".a-file").length != 0) {//去掉之前包裹的a
        $(iEle).unwrap();
    }

    if ($(fileEle).find("img").length != 0) {//上传之前去掉img
        $(fileEle).find("img").parent("a").remove();
        $(fileEle).find("img").remove();
    }

    //默认不用a去包裹img
    var warpImg = false;
    //正则替换class
    if (picReg.test(url)) {
        $(iEle).hide();
        $(fileEle).find("img").remove();
        $(fileEle).append("<img src=\"" + url + "\">");
        warpImg = true;
    } else if (wordReg.test(url)) {
        $(iEle).attr("class", "fa fa-file-word-o fz-70");
    } else if (excelReg.test(url)) {
        $(iEle).attr("class", "fa fa-file-excel-o fz-70");
    } else if (pdfReg.test(url)) {
        $(iEle).attr("class", "fa fa-file-pdf-o fz-70");
    } else if (txtReg.test(url)) {
        $(iEle).attr("class", "fa fa-file-text-o fz-70");
    } else if (rarReg.test(url)) {
        $(iEle).attr("class", "fa fa-file-zip-o fz-70");
    } else if (zipReg.test(url)) {
        $(iEle).attr("class", "fa fa-file-zip-o fz-70");
    } else if (pptReg.test(url)) {
        $(iEle).attr("class", "fa fa-file-powerpoint-o fz-70");
    } else if (apkReg.test(url)) {
        $(iEle).attr("class", "fa fa-android fz-70");
    }else if(musicReg.test(url)){
        $(iEle).attr("class", "fa fa-music fz-70");
    }
    else {
        $(iEle).hide();
        $(fileEle).find("img").remove();
        $(fileEle).append("<img src='"+notFountUrl+"'>");
        //$(fileEle).append("<img src=\"" + url + "\">");
        warpImg = true;
    }

    if (warpImg && url) {//a标签包裹img
        $(iEle).siblings("img").wrap("<a href='" + url + "' target='_blank' class='a-file'></a>");
    } else {//a标签包裹i
        if(url){
            $(iEle).wrap("<a href='" + url + "' target='_blank' class='a-file'></a>");
            $(iEle).show();
        }
    }
    $(picker).closest(".upload-btns").find(".input-gtattachurl").val(url);//add url to input
};