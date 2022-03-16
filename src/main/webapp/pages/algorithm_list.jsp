<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	if(request.getSession().getAttribute("user") == null){
		response.sendRedirect("/login.jsp");
	}
%>

<html>
<head>

    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>数据 - AdminLTE2定制版</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">

    <!-- Tell the browser to be responsive to screen width -->
    <meta
            content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
            name="viewport">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/morris/morris.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.theme.default.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/select2/select2.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/adminLTE/css/skins/_all-skins.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.skinNice.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap-slider/slider.css">

</head>

<body class="hold-transition skin-purple sidebar-mini">

<div class="wrapper">

    <!-- 页面头部 -->
    <jsp:include page="header.jsp"></jsp:include>
    <!-- 页面头部 /-->

    <!-- 导航侧栏 -->
    <jsp:include page="aside.jsp"></jsp:include>
    <!-- 导航侧栏 /-->

    <c:set var="result" value="${sessionScope.result}"/>
    <%--提示信息--%>
    <c:if test="${result!=null}">
        <script>
            alert('${result}');
        </script>
    </c:if>
    <c:remove  var="result"  scope="session"  />
    <!-- 内容区域 -->
    <div class="content-wrapper">

        <!-- 内容头部 -->
        <section class="content-header">
            <h1>
                学习<small>算法</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/index.jsp"><i
                        class="fa fa-dashboard"></i> 首页</a></li>
                <li><a
                        href="#">学习</a></li>

                <li class="active">算法</li>
            </ol>
        </section>
        <!-- 内容头部 /-->

        <!-- 正文区域 -->
        <section class="content"> <!-- .box-body -->
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">论坛供大家讨论学习，请勿谈论与学习无关的话题(文件上传中的图片无法读取，请勿上传图片)</h3>
                    <br><br>
                    <%--内容区域--%>
                    <div id="content_body">
                        <span style="float: left;padding-left: 50px;margin-bottom: 20px">
                                <form action="${pageContext.request.contextPath}/algorithm/upload" method="post" enctype="multipart/form-data" onsubmit="return check()">
                                    <span style="float:left;font-size: 15px">
                                        上传算法标题标题：<input type="text" id="title" name="title" style="height: 40px;width: 200px;resize: none" >
                                    </span>
                                    <span style="float:left;font-size: 15px;height: 40px;width: 150px;padding-left: 20px">
                                        <input type="file" id="file" name="uploadFile" >
                                    </span >
                                    <span style="float:left;font-size: 15px;padding-left: 100px">
                                        <input style="height: 40px;width: 150px;" id="replybtn" type="submit" value="上传" >
                                    </span>
                                </form>
                        </span>
                        <h4 class="box-body">
                            <ul>
                                <table width="1200px">
                                    <c:forEach items="${algorithmList}" var="algorithm">
                                        <tr>
                                            <td width="800px">
                                                <li style="line-height: 30px"><a href="javascript:void(0);" onclick="findAlgorithmById(${algorithm.id})">${algorithm.title}</a></li>
                                            </td>
                                            <td width="30px"> </td>
                                            <td width="180px">
                                                <fmt:formatDate value="${algorithm.date}" type="both"/>
                                            </td>
                                            <td width="40px"></td>
                                            <td width="150px">
                                                用户：${algorithm.username}
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
                                    <span style="font-size: 20px;margin-left: 10px;">
										共${total}条记录，共${pages}页
									</span>
                                </ul>
                            </nav>

                        </h4>

                    </div>
                </div>
            </div>
        </section>

        <!-- 正文区域 /-->

    </div>
    <!-- @@close -->
    <!-- 内容区域 /-->

    <!-- 底部导航 -->
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 1.0
        </div>
        <strong>Copyright &copy; 2022-2022 蓝点

        </strong> All rights reserved. </footer>
    <!-- 底部导航 /-->

</div>

<script src="../plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="../plugins/jQueryUI/jquery-ui.min.js"></script>
<script src="../plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="../plugins/raphael/raphael-min.js"></script>
<script src="../plugins/sparkline/jquery.sparkline.min.js"></script>
<script src="../plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="../plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="../plugins/knob/jquery.knob.js"></script>
<script src="../plugins/daterangepicker/moment.min.js"></script>
<script src="../plugins/daterangepicker/daterangepicker.js"></script>
<script src="../plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
<script src="../plugins/datepicker/bootstrap-datepicker.js"></script>
<script
        src="../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script
        src="../plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<script src="../plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="../plugins/fastclick/fastclick.js"></script>
<script src="../plugins/iCheck/icheck.min.js"></script>
<script src="../plugins/adminLTE/js/app.min.js"></script>
<script src="../plugins/treeTable/jquery.treetable.js"></script>
<script src="../plugins/select2/select2.full.min.js"></script>
<script src="../plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
<script
        src="../plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
<script src="../plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
<script
        src="../plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
<script src="../plugins/bootstrap-markdown/js/markdown.js"></script>
<script src="../plugins/bootstrap-markdown/js/to-markdown.js"></script>
<script src="../plugins/ckeditor/ckeditor.js"></script>
<script src="../plugins/input-mask/jquery.inputmask.js"></script>
<script
        src="../plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="../plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script src="../plugins/datatables/jquery.dataTables.min.js"></script>
<script src="../plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="../plugins/chartjs/Chart.min.js"></script>
<script src="../plugins/flot/jquery.flot.min.js"></script>
<script src="../plugins/flot/jquery.flot.resize.min.js"></script>
<script src="../plugins/flot/jquery.flot.pie.min.js"></script>
<script src="../plugins/flot/jquery.flot.categories.min.js"></script>
<script src="../plugins/ionslider/ion.rangeSlider.min.js"></script>
<script src="../plugins/bootstrap-slider/bootstrap-slider.js"></script>
<script>

    function a1() {
        window.location.href="${pageContext.request.contextPath}/algorithm/algorithmList?currentPage=${currentPage-1}";
    }
    function a2(i) {
        window.location.href ="${pageContext.request.contextPath}/algorithm/algorithmList?currentPage="+i;
    }

    function a3() {
        window.location.href="${pageContext.request.contextPath}/algorithm/algorithmList?currentPage=${pages}";
    }

    function a4() {
        window.location.href ="${pageContext.request.contextPath}/algorithm/algorithmList?currentPage=${currentPage+1}";
    }

    function findAlgorithmById(id) {
        var $content_body = $("#content_body");
        $content_body.load("${pageContext.request.contextPath}/algorithm/algorithmById?id="+id);
    }

    function check() {
        var title = $("#title");
        var file = $("#file");
        if(title.val() == ""){
            alert("请输入标题内容！");
            title.focus();
            return false;
        }else if (file.val() == ""){
            alert("请选择文件！");
            return false;
        }else {
            return true;
        }
    }


</script>



</body>

</html>