function init() {
  if (typeof($.cookie('userId')) != "undefined") {
    $.ajax({
      url: "/emp/emp/getEmp",
      type: "POST",
      dataType: "json",
      data: {
        "id": $.cookie('userId')
      },
      success: function(data) {
        analyseData(data);
      },
      error: function(XMLHttpRequest, textStatus, errorThrown) {
        alert(XMLHttpRequest.status);
        alert(XMLHttpRequest.readyState);
        alert(textStatus);
      }
    });
  } else {
    alert("用户未登录！");
    window.location.href = "login.html";
  }
}

init();

function addUserInfo(id, no, pass, photo) {
  return "<a  href=\"javascript:;\">\
            <img src=\"/emp/upload/userHeadPhoto/" + photo + "\" class=\"layui-nav-img\">\
            " + no + "\
          </a>\
          <dl class=\"layui-nav-child\">\
            <dd><a href=\"javascript:showTab(1001,'userInfo.html?id=" + id + "','我的信息');\">我的信息</a></dd>\
            <dd><a href=\"javascript:showTab(1001,'photo.html?id=" + id + "&photo=" + photo + "','更改头像');\">更改头像</a></dd>\
            <dd><a href=\"javascript:showTab(1002,'password.html?id=" + id + "&pass=" + pass + "','修改密码');\">修改密码</a></dd>\
          </dl>";
}

function analyseData(data) {
  var id = data.emp.id;
  var no = data.emp.no;
  var pass = data.emp.pass;
  var photo = data.emp.photo;
  
  var text = addUserInfo(id, no, pass, photo);
  $('#userInfo').empty();
  $('#userInfo').append(text);
}

function signOut() {
  if(typeof($.cookie('userId')) != "undefined") {
  	$.removeCookie('userId',{ path: '/'});
  }
  location.href = "login.html";
}
