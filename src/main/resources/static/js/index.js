$(function () {

    $("#mainPanel").load("/system/organization");
    // alert($(".nav li").first().attr('data-menuUrl'));

    $(".nav li").click(function (){
        $("#mainPanel").load($(this).attr('data-menuUrl'));
        //把之前已有的active去掉
        $(".active").removeClass("active");
        //当前点击的li加上cl   `ass
        $(this).addClass("active");
    });
});