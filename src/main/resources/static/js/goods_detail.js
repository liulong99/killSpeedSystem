$(function(){
    // countDown();
    getDetail();
});

function getDetail(){
    var goodsId=getQueryString("goodsId");
    $.ajax({
        url: "/goods/detail/"+goodsId,
        type: "GET",
        success:function (data) {
            if(data.code === 0){
                render(data.data)
            }else{
                layer.msg(data.msg)
            }
        },
        error:function () {
            layer.msg("客户端请求有误");
        }
    });
}

function render(detail){
    var miaoshaStatus=detail.miaoshaStatus;
    var remainSecond=detail.remainSecond;
    var goodsVo=detail.goodsVo;
    var miaoshaUser=detail.miaoshaUser;
    $("#goodsName").text(goodsVo.goodsName);
    $("#goodsImg").attr("src",goodsVo.goodsImg);
    $("#startTime").text(new Date(goodsVo.startDate).Format("yyyy-MM-dd hh:mm:ss"));
    $("#remainSecond").val(remainSecond);
    $("#goodsId").val(goodsVo.id);
    $("#goodsPrice").text(goodsVo.goodsPrice+"元");
    $("#miaoshaPrice").text(goodsVo.miaoshaPrice+"元");
    $("#stockCount").text(goodsVo.stockCount+"件");
    countDown();
}

function countDown(){
    var remainSecond=$("#remainSecond").val();
    var timeout;
    if(remainSecond>0){
        //秒杀未开始，倒计时,按钮不可点
        $("#buyButton").attr("disabled",true);
        $("#miaoshaTip").html("秒杀倒计时："+remainSecond+"秒");
        timeout=setTimeout(function () {
            $("#countDown").text(remainSecond-1);
            $("#remainSecond").val(remainSecond-1);
            countDown();
        },1000);
    }else if(remainSecond == 0){
        //秒杀进行中,按钮可点
        $("#buyButton").attr("disabled",false);
        if(timeout){
            clearTimeout(timeout);
        }
    }else{
        //秒杀已结束,按钮不可点
        $("#buyButton").attr("disabled",true);
    }
}