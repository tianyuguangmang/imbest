//ajax封装
function wAjax(url, methods, data, cb){
	ajax({
		url: url,
		type: methods,
		data: data,
		dataType: 'JSON',
		success: function(res, xml) {
			var msg = JSON.parse(res);

			if(msg.code == "25763"){
                layer.confirm(msg.message, {
                    title:"平台提示",
                    btn: ['确定','取消'] //按钮
                }, function(){
                    reUpload.certificatedLayer();
                    return false;
                }, function(){
                    $(".layui-layer-close1").click();
                    return false;
                });


                return false;
				//用户实名未通过

			}else if(msg.code == "25713"){
                layer.confirm(msg.message, {
                    title:"平台提示",
                    btn: ['确定','取消'] //按钮
                }, function(){
                    reUpload.headReupload();
                    return false;
                }, function(){
                    $(".layui-layer-close1").click();
                    return false;
                });
                return false;
				//头像未通过

			}else if(msg.code == "25714"){

                layer.confirm(msg.message, {
                	title:"平台提示",
                    btn: ['确定','取消'] //按钮
                }, function(){
                    reUpload.coverBannerReupload();
                    return false;
                }, function(){
                    $(".layui-layer-close1").click();
                    return false;
                });

				//厂图未通过

			} else if (msg.code == "25072"){
				// 未登录
				/*layer.open({
					title:"穿商甲温馨提示",
					icon: 6,
					content: "亲~请登录后，再行操作"
				});*/
				// window.location.href = "http://www.chuanshangjia.com/login";// when program ues malls login
				window.location.href = "https://chuanshangjia.com/login?redirectURL=https%3A%2F%2Fsns.chuanshangjia.com%2Findex";// when program use sns(self) login
				return false;
			} else {
                cb(res,xml);
			}
		},
		fail: function(status) {
			//console.log(status);
		}

	});
}
/**
 * 格式化发送的数据
 * @param  Object _object 传入对象参数
 
 */
function formatParams(_object){
	var _arr = [];
	for(var name in _object){
		_arr.push(name + "=" + _object[name]);
	};
	return _arr.join("&");
}
function ajax(options){
	options = options || {};
	options.type = (options.type || 'GET').toUpperCase();
	options.dataType = options.dataType || 'json';

	var params = formatParams(options.data);
	var xhr = null;
	//非ie的数据请求
	
	if(window.XMLHttpRequest){
		xhr = new window.XMLHttpRequest();
	}else{
		xhr = new window.ActiveXObject('Microsoft.XMLHTTP');//ie的数据请求
	}
	//接收数据
	xhr.onreadystatechange = function() {
		if(xhr.readyState === 4){
			var _status = xhr.status;
			if(_status>=200&&_status<=300){
				options.success&&options.success(xhr.responseText, xhr.responseXML);
			}else{
				options.fail&&options.fail(status);
			}
		}
	}
	//连接与发送
	if(options.type === 'GET'){
		var timestamp= new Date().getTime();
		xhr.open('GET', options.url+"?"+params+"&t="+timestamp, true);
		xhr.setRequestHeader("openId","obZUP0SLAQi9oAk7EdGORntuHBIc");
		xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');

		xhr.send(JSON.stringify(options.data));
	}else if(options.type === 'POST'){
		xhr.open('POST', options.url, true);
		xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
		xhr.setRequestHeader("OpenId","obZUP0SLAQi9oAk7EdGORntuHBIc");
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8');
		xhr.send(params);
	}else if(options.type === 'JSON_POST'){
		xhr.setRequestHeader("OpenId","obZUP0SLAQi9oAk7EdGORntuHBIc");
		
		xhr.open('POST', options.url, true);
		//xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
		xhr.setRequestHeader("OpenId","obZUP0SLAQi9oAk7EdGORntuHBIc");
		xhr.setRequestHeader('Content-Type', 'application/json');
		xhr.send(JSON.stringify(options.data));
	}else if(options.type === 'FILE'){
		xhr.open('POST', options.url, true);
		xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
		xhr.send(options.data);
	}else if(options.type === "QINIU"){
        xhr.open('POST', options.url, true);
		xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
        xhr.setRequestHeader("Content-Type", "application/octet-stream");
        xhr.setRequestHeader("Authorization", "UpToken "+options.data.qiniuToken);
        xhr.send(options.data.pic);
	}

}
