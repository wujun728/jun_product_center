function getUrlParams(url) {
    if(url.indexOf("#")>=0){
        url = url.substring(0,url.indexOf("#"))
    }
    const paramsRegex = /[?&]+([^=&]+)=([^&]*)/gi;
    const params = {};
    let match;
    while (match = paramsRegex.exec(url)) {
        params[match[1]] = match[2];
    }
    return params;
}