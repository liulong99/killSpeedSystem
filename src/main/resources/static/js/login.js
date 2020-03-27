// 回车切换焦点换行
$(function() {
    var inputArr = $('.focus'); // 定义全部焦点
    inputArr.bind('keydown', function(e) {// 绑定事件
        var key = e.which;// 定义按键
        if (key == 13) {// 回车事件
            e.preventDefault();
            var nxtIdx = inputArr.index(this) + 1;
            $(".focus:eq(" + nxtIdx + ")").focus();
            if (nxtIdx == inputArr.length) {
                $(':input:submit').focus();// 获取提交按钮焦点
            }
        }
    });
});
function login(){
    $("#loginForm").validate({
        submitHandler:function(form){
            //异步提交
            doLogin();
        }
    });
}
function doLogin(){
    g_showLoading();
    var salt=g_passsword_salt;
    var pass=$("#password").val();
    var str=""+salt.charAt(0)+salt.charAt(1)+salt.charAt(2)+pass+salt.charAt(2)+salt.charAt(3);
    var password=md5(str);
    $.ajax({
        url:"/login/doLogin",
        type:'POST',
        data:{
            mobile:$("#mobile").val(),
            password:password
        },
        success:function (data) {
            layer.closeAll();
            if(data.code==0){
                layer.msg("登录成功");
                window.location.href="/goods/toList";
            }else{
                layer.msg(data.msg);
            }
        },
        error:function(){
            layer.closeAll();
        }
    });
}
function register() {
    window.location.href="/register/toRegister";
}