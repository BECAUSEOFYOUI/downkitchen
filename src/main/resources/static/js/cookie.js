var parseCookies = function(query){
    var reg = /([^=; \s]+)[=\s]*([^; \s]*)/g;
    var obj = {};
    while (reg.exec(query)){
        obj[RegExp.$1] = RegExp.$2;
    }
    return obj;
}

function setCookie(k,v,d){
    let date = new Date();
    date.setDate(date.getDate()+d)
    let cookie = `${k}=${v};expires=${date};path=/`
    document.cookie = cookie;
}
function getCookie(k){
    let obj = parseCookies(document.cookie)
    return obj[k];
}
function removeCookie(k){
    setCookie(k,'',-10)
}