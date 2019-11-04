function ajax(url,callbackFn,errorFn){
    let xhr = new XMLHttpRequest()
    xhr.open('GET',url);
    xhr.send(null);
    xhr.onload = function(){
        if(xhr.status == 200){
            let obj = JSON.parse(xhr.responseText);
            callbackFn(obj);
        }
    }
    xhr.onerror = function(){
        if(errorFn){
            errorFn();
        }
    }
}