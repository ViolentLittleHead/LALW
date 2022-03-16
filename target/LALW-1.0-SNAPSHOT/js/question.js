$(document).ready(function () {
    //获取显示提问div区域
    var $content_body = $("#content_body");
    //把questionList.jsp页面加载到content_body标签中
    function initQuestion() {
        $content_body.load("/question/findAll?currentPage=1");
    }
    initQuestion();


});