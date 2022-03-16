<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    var $content_body = $("#content_body");
    var $choose_status = $("#choose_status");
    $("#newest").bind('click',function () {
        $content_body.load("/question/findAll?currentPage=1");
    });
    $("#most").bind('click',function () {
        $content_body.load("/question/findAllByMost?currentPage=1");
    });
    function toQuestion(id) {
        alert(id);
    }
</script>


<span class="dropdown" style="padding-left: 2%">
    <button class="btn btn-default dropdown-toggle" type="button"style="padding-left:3%;padding-right: 3%;" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
        ${choose_status}
        <span class="caret"></span>
    </button>
    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
        <li><a id="newest" href="javascript:void(0);">最新</a></li>
        <li><a id="most" href="javascript:void(0);">最热</a></li>
    </ul>
</span>
<span style="float: right;padding-right: 50px;">
        <form method="post" action="${pageContext.request.contextPath}/question/put" onsubmit="return check()">
            <div style="padding-left: 20px;">
                <span style="float: top">发布帖子：</span><textarea id="content" name="content" style="height: 40px;width: 400px;resize: none"></textarea>
                <span style="padding-left: 20px">
                    <input id="replybtn" type="submit" value="发布" />
                </span>
            </div>
        </form>
</span>

<h4 class="box-body">
    <ul>
        <table width="1200px">
            <c:forEach items="${questionList}" var="question">
                <tr>
                    <td width="800px">
                        <li style="line-height: 30px"><a href="${pageContext.request.contextPath}/question/findQuestionById?id=${question.id}&currentPage=1">${question.content}</a></li>
                    </td>
                    <td width="40px"> </td>
                    <td width="170px">
                        <fmt:formatDate value="${question.date}" type="both"/>
                    </td>
                    <td width="40px"></td>
                    <td width="150px">
                        用户：${question.username}
                    </td>
                </tr>
            </c:forEach>

        </table>
    </ul>

    <nav aria-label="...">
        <ul class="pagination">

                <c:choose>
                <c:when test="${currentPage == 1}">
                    <li class="disabled">
                        <a href="javascript:void(0);" aria-label="Next">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="javascript:void(0);" onclick="a1()" aria-label="Next">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:otherwise>
                </c:choose>
            </li>


            <c:forEach begin="${currentPage>2?currentPage-2:1}" end="${pages-currentPage>2?currentPage+2:pages}" var="i">
                <c:choose>
                    <c:when test="${currentPage == i}">
                        <li class="active">
                            <a href="javascript:void(0);" onclick="a2(${i})">${i}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="javascript:void(0);" onclick="a2(${i})">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${pages-currentPage>2}">
                <li class="disabled">
                    <a href="javascript:void(0);" aria-label="Next">
                        <span aria-hidden="true">...</span>
                    </a>
                </li>
            </c:if>
            <c:choose>
                <c:when test="${currentPage == pages}">
                    <li class="disabled">
                        <a href="javascript:void(0);" onclick="a3()" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="javascript:void(0);" onclick="a4()" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
                <span style="font-size: 20px;margin-left: 20px;">
                    共${total}条记录，共${pages}页
                </span>
        </ul>
    </nav>

</h4>

<script>
        //获取显示提问div区域
        var $content_body = $("#content_body");

        //status用来访问区分是访问最热帖子还是最新帖子
        function a1() {
            $content_body.load("${pageContext.request.contextPath}/question/findAll${status}?currentPage=${currentPage - 1}");
        }
        function a2(i) {
            $content_body.load("${pageContext.request.contextPath}/question/findAll${status}?currentPage="+i);
        }

        function a3() {
            $content_body.load("${pageContext.request.contextPath}/question/findAll${status}?currentPage=${pages}");
        }

        function a4() {
            $content_body.load("${pageContext.request.contextPath}/question/findAll${status}?currentPage=${currentPage + 1}");
        }

        function check() {
            var content = document.getElementById("content");
            if(content.value == ""){
                alert("请输入帖子内容！");
                content.focus();
                return false;
            }else {
                return true;
            }
        }
</script>
