'use strict';
layui.use(['jquery'], function() {
  window.jQuery = window.$ = layui.jquery;
  $(".layui-canvs").width($(window).width());
  $(".layui-canvs").height($(window).height());

});

var ip = "";
var city = "";

function init() {
  ip = returnCitySN["cip"]; //获取IP
  $.ajax({
    url: 'http://api.map.baidu.com/location/ip?ip=' + ip + '&ak=PFyDQ8MwCtLemRPt1wr0jUVDMhFi8Gv1',
    type: 'POST',
    dataType: 'jsonp',
    success: function(data) {
      city = data.content.address_detail.province + "," + data.content.address_detail.city; //获取城市
    },
    error: function(XMLHttpRequest, textStatus, errorThrown) {
      alert(XMLHttpRequest.status);
      alert(XMLHttpRequest.readyState);
      alert(textStatus);
    }
  });
}
init();

function empLogin() {
  var no = $("#no").val();
  var pass = $("#pass").val();

  saveIpInfo(no);

  $.ajax({
    url: "/emp/emp/login",
    type: "POST",
    dataType: "json",
    data: {
      "no": no,
      "pass": pass
    },
    success: function(data) {
      if (data.message.indexOf("成功") != -1) {
        if(typeof($.cookie('userId')) != "undefined") {
        	$.removeCookie('userId',{ path: '/'});
        }
        
        var expiresDate= new Date();
        expiresDate.setTime(expiresDate.getTime() + (3 * 60 * 60 * 1000));
        $.cookie('userId', data.id,{
          expires: expiresDate, 
          path:'/'
        })　
        
        location.href = "index.html";
      } else {
        //    重新加载登录页面
        alert(data.message);
        location.reload();
      }
    },
    error: function(XMLHttpRequest, textStatus, errorThrown) {
      alert(XMLHttpRequest.status);
      alert(XMLHttpRequest.readyState);
      alert(textStatus);
    }
  });
}

function dateFormat(fmt, date) {
  let ret;
  const opt = {
    "Y+": date.getFullYear().toString(), // 年
    "m+": (date.getMonth() + 1).toString(), // 月
    "d+": date.getDate().toString(), // 日
    "H+": date.getHours().toString(), // 时
    "M+": date.getMinutes().toString(), // 分
    "S+": date.getSeconds().toString() // 秒
    // 有其他格式化字符需求可以继续添加，必须转化成字符串
  };
  for (let k in opt) {
    ret = new RegExp("(" + k + ")").exec(fmt);
    if (ret) {
      fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
    };
  };
  return fmt;
}

function saveIpInfo(no) {
  $.ajax({
    url: "/emp/loginlog/addLoginlog",
    type: "POST",
    dataType: "json",
    data: {
      "ip": ip,
      "no": no,
      "createtime": dateFormat("YYYY-mm-dd HH:MM:SS", new Date()),
      "location": city
    },
    success: function(data) {
      var str = data.message;
    },
    error: function(XMLHttpRequest, textStatus, errorThrown) {
      alert(XMLHttpRequest.status);
      alert(XMLHttpRequest.readyState);
      alert(textStatus);
    }
  });
}
