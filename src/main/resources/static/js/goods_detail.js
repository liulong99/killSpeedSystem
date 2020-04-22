$(function(){
    countDown();
});
function countDown(){
    var remainSecond=$("#remainSecond").val();
    var timeout;
    if(remainSecond>0){
        //秒杀未开始，倒计时,按钮不可点
        $("#buyButton").attr("disabled",true);
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