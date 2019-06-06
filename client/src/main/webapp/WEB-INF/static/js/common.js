//需先使用JQuery.serialize()
function parseToJSONString(data, isStringify) {
    data = decodeURIComponent(data,true);
    var fields = data.split('&');
    var propsJson = {};
    for (var i = 0; i < fields.length; i++) {
        var prop = fields[i].split('=');
        propsJson[prop[0]] = prop[1];
    }
    if (!isStringify)
        return propsJson;
    return JSON.stringify(propsJson);
}

//需先使用JQuery.serialize()
function parseToJSONArrayString(data, columnNum, key, otherField, isStringify) {
    data = decodeURIComponent(data,true);
    var fields = data.split('&');
    var jsonArray = [];
    var propsJson = {};
    for (var i = 0; i < fields.length; i++) {
        var prop = fields[i].split('=');
        var name = prop[0].split(".");
        if (name[1] != null) {//有前缀
            propsJson[name[1]] = prop[1];
        } else {
            propsJson[prop[0]] = prop[1];
        }
        if ((i+1)%columnNum == 0) {//一行结束
            if (key != null &&otherField != null) {
                propsJson[key] = otherField;
            }
            jsonArray.push(propsJson);
            propsJson = {};
        }
    }
    if (!isStringify)
        return jsonArray;
    return JSON.stringify(jsonArray);
}

//装填非表单字段
function setDetailColumn(data, formId) {
    $(formId).each(function(){
        let key = $(this).data("form");
        let value ="";
        if (key.endsWith("Enum")) {
            value = data[key].desc;
        } else {
            value = data[key];
        }
        $(this).text(value);
    });
}

var cookie = {
    set:function(key,val,time){//设置cookie
        var date=new Date(); //获取当前时间
        var expiresDays=time;  //将date设置为n天以后的时间
        date.setTime(date.getTime()+expiresDays*60*1000); //格式化为cookie识别的时间 单位：分钟
        document.cookie=key + "=" + val +";expires="+date.toGMTString();  //设置cookie
    },
    get:function(key) {//获取cookie
        var cookieValue;
        var getCookie = document.cookie.replace(/[ ]/g, "");  //获取cookie，并且将获得的cookie格式化，去掉空格字符
        var arrCookie = getCookie.split(";");  //将获得的cookie以"分号"为标识 将cookie保存到arrCookie的数组中
        for (var i = 0; i < arrCookie.length; i++) {
            var arr = arrCookie[i].split("=");   //将单条cookie用"等号"为标识，将单条cookie保存为arr数组
            if (key == arr[0]) {  //匹配变量名称，其中arr[0]是指的cookie名称，如果该条变量为cookieValue则执行判断语句中的赋值操作
                cookieValue = arr[1];   //将cookie的值赋给变量cookieValue
                break;   //终止for循环遍历
            }
        }
        return cookieValue;
    },
    delete:function(key){ //删除cookie方法
        var date = new Date(); //获取当前时间
        date.setTime(date.getTime()-10000); //将date设置为过去的时间
        document.cookie = key + "=v; expires =" +date.toGMTString();//设置cookie
    }
};

var data = {
    respMessage:""
};

//下载模板
$("#template").click(function () {
    var url = localHost() + "template";
    var xhr = new XMLHttpRequest();
    xhr.open("get", url, true);
    xhr.responseType = "blob"; // 返回类型blob，XMLHttpRequest支持二进制流类型

    xhr.onprogress = function(progress) {
        if (progress.lengthComputable) {
            console.log(progress.loaded / progress.total * 100);
        }
    };

    xhr.onloadstart = function() {
        console.log('started...');
    };

    xhr.onload = function() {
        if (this.status === 200) {
            let blob = this.response; //使用response作为返回，而非responseText
            let reader = new FileReader();
            reader.readAsDataURL(blob); // 转换为base64，可以直接放入a标签href
            reader.onload = function(e) {
                // 转换完成，创建一个a标签用于下载
                let a = document.createElement("a");
                a.download = "导入订单模板.xlsx";
                a.href = e.target.result;
                a.click();
            };
        } else {
            xhr.responseType = "text";
            console.log(this.response);
            smallNotify(this.response);
        }
    };
    xhr.send(); //开始上传
});

//上传
$("#upload").change(function () {
    var files = document.getElementById("upload").files;//只能用javascript
    if(files.length == 0) return;

    var formData=new FormData();
    formData.append("file", files[0]);//限单传

    var url = localHost() + "upload";
    var xhr = new XMLHttpRequest();
    xhr.open("post", url, true);

    //上传进度事件
    // xhr.upload.addEventListener("progress", function(result) {
    //     if (result.lengthComputable) {
    //         //上传进度
    //         var percent = (result.loaded / result.total * 100).toFixed(2)
    //         console.log("上传进度："+percent);
    //     }
    // }, false);
    xhr.upload.onprogress = function(progress) {
        if (progress.lengthComputable) {
            console.log(progress.loaded / progress.total * 100);
            // progressBar.max = progress.total;
            // progressBar.value = progress.loaded;
        }
    };
    xhr.upload.onloadstart = function() {
        console.log('started...');
    };

    xhr.addEventListener("readystatechange", function() {
        var result = xhr;
        if(result.readyState == 4) {
            if (result.status != 200) { //error
                var respones = JSON.parse(result.response);
                if (typeof respones.message == "string") {
                    var message = JSON.parse(respones.message);
                    data.respMessage = message.message;
                } else {
                    data.respMessage = respones.message.message;
                }
                notification(data);
                // console.log( result.status, result.statusText, result.response);
            } else if (result.status == 200) { //finished
                data.respMessage =  result.response;
                notification(data);
            }
            document.getElementById("upload").value = '';
        }

    });
    xhr.send(formData); //开始上传
});

