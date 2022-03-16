<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>

</script>
<div style="padding-left: 50px;padding-top: 20px">
    <button type="button" onclick="javascript:history.go('/algorithm/algorithmList?currentPage=1')" style="height: 40px;width: 130px;" >
        <span class="glyphicon glyphicon-circle-arrow-left"></span>
        返回
    </button>
</div>

<h4 class="box-body">
    <ul>
        <table width="1000px">
                <tr>
                    <td width="500px">
                        <li style="line-height: 30px">${algorithm.title}</li>
                    </td>
                    <td width="40px"> </td>
                    <td width="60px"><a href="${pageContext.request.contextPath}/algorithm/downloadById?id=${algorithm.id}">下载</a></td>
                    <td width="40px"> </td>
                    <td width="170px">
                        <fmt:formatDate value="${algorithm.date}" type="both"/>
                    </td>
                    <td width="40px"></td>
                    <td width="150px">
                        用户：${algorithm.username}
                    </td>
                </tr>
            <tr>
                <td> </td>
            </tr>
            <tr>
                <td>大致内容：</td>
            </tr>
            <tr>
                <td> </td>
            </tr>
                <tr>
                    <td colspan="7">${algorithm.content}
                    </td>
                </tr>
        </table>
    </ul>

</h4>

