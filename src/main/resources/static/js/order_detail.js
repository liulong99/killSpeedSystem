$(function(){
    getOrderDetail();
});

function  getOrderDetail() {
    var orderId=getQueryString("orderId");
    $.ajax({
        url: "/order/detail",
        type: "GET",
        data: {
            orderId: orderId
        },
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
    })
}

function render(detail){
    var goodsVo=detail.goodsVo;
    var miaoshaUser=detail.miaoshaUser;
    var orderInfo=detail.orderInfo;
    $("#goodsName").text(goodsVo.goodsName);
    $("#goodsImg").attr("src",goodsVo.goodsImg);
    $("#goodsPrice").text(orderInfo.goodsPrice);
    $("#createDate").text(orderInfo.createDate);
    var status="";
    if(orderInfo.status===0){
        status="未支付";
    }else if(orderInfo.status===1){
        status="待发货";
    }else if(orderInfo.status===2){
        status="已发货";
    }else if(orderInfo.status===3){
        status="已收藏";
    }else if(orderInfo.status===4){
        status="已退款";
    }else if(orderInfo.status===5){
        status="已完成";
    }
    $("#orderStatus").text(status);
    $("#nickname").text(miaoshaUser.nickname);
    $("#userId").text(orderInfo.userId);
}