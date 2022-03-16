<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	if(request.getSession().getAttribute("user") == null){
		response.sendRedirect("/login.jsp");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

		<!-- 内容区域 -->
		<div class="content-wrapper">

			<!-- 内容头部 -->
			<section class="content-header">
			<h1>
				计算工具<small>矩阵计算</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="${pageContext.request.contextPath}/index.jsp"><i
						class="fa fa-dashboard"></i> 首页</a></li>
				<li><a
					href="#">计算工具</a></li>

				<li class="active">矩阵计算</li>
			</ol>
			</section>
			<!-- 内容头部 /-->

				<!-- 正文区域 -->
			<form action="#"
				  method="post">
				<section class="content"> <!-- .box-body -->
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title">注意：一行元素用空格隔开，换行表示下一行(每行的首位不要有空格，行之间不要有空行)；只涉及一个矩阵的运算请在矩阵A区域内输入</h3>
						</div>
						<div style="width: 30%;height: 320px;float: left;margin: 20px">
							输入矩阵A：<br>
							<textarea id="matrixA" style="height: 50%;width: 100%"></textarea><br>
							输入矩阵B：<br>
							<textarea id="matrixB" style="height: 50%;width: 100%"></textarea>
						</div>
						<div align="center" style="width: 20%;height: 150px;float: left;margin: 20px;"><br><br>
							<input style="width:50%" type="button" id="addBtn" onclick="add()" class="btn btn-default" value="相&nbsp;&nbsp;加"></input><br>
							<input style="width:50%" type="button" id="subtractBtn" onclick="subtract()" class="btn btn-default" value="相&nbsp;&nbsp;减"></input><br>
							<input style="width:50%" type="button" id="multiplyBtn" onclick="multiply()" class="btn btn-default" value="相&nbsp;&nbsp;乘"></input><br>
							<input style="width:50%" type="button" id="transposeBtn" onclick="transpose()" class="btn btn-default" value="转&nbsp;&nbsp;置"></input><br>
							<input style="width:50%" type="button" id="inverseBtn" onclick="inverse()" class="btn btn-default" value="求逆矩阵"></input><br>
							<input style="width:50%" type="button" id="rankBtn" onclick="rank()" class="btn btn-default" value="求矩阵的秩"></input><br>
							<input style="width:50%" type="button" disabled="true" id="eigenValueBtn" onclick="eigenValue()" class="btn btn-default" value="求矩阵特征值"></input><br>
							<input style="width:50%" type="button" id="judgeSameBtn" onclick="judgeSame()" class="btn btn-default" value="判断是否相似"></input><br>
						</div>
						<div style="width: 30%;height: 300px;float: left;margin: 20px">
							输出：<br>
							<textarea id="result" style="height: 100%;width: 100%"></textarea>
						</div>
					</div>

				</section>
			</form>
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
		<script>
			$.widget.bridge('uibutton', $.ui.button);
		</script>
		<script src="../plugins/bootstrap/js/bootstrap.min.js"></script>
		<script src="../plugins/raphael/raphael-min.js"></script>
		<script src="../plugins/morris/morris.min.js"></script>
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

			function add() {
				$.ajax({
					url: '${pageContext.request.contextPath}/matrix/add',
					type: 'post',
					data:{"matrixA": $("#matrixA").val(),"matrixB":$("#matrixB").val()},
					success: function (s) {
						var elementById = document.getElementById("result");
						elementById.value=s;
					}
				});
			}

			function subtract() {
				$.ajax({
					url: '${pageContext.request.contextPath}/matrix/subtract',
					type: 'post',
					data:{"matrixA": $("#matrixA").val(),"matrixB":$("#matrixB").val()},
					success: function (r) {
						var elementById = document.getElementById("result");
						elementById.value=r;
					}
				});
			}

			function multiply() {
				$.ajax({
					url: '${pageContext.request.contextPath}/matrix/multiply',
					type: 'post',
					data:{"matrixA": $("#matrixA").val(),"matrixB":$("#matrixB").val()},
					success: function (r) {
						var elementById = document.getElementById("result");
						elementById.value=r;
					}
				});
			}

			function transpose() {
				$.ajax({
					url: '${pageContext.request.contextPath}/matrix/transpose',
					type: 'post',
					data:{"matrixA": $("#matrixA").val()},
					success: function (r) {
						var elementById = document.getElementById("result");
						elementById.value=r;
					}
				});
			}
			function inverse() {
				$.ajax({
					url: '${pageContext.request.contextPath}/matrix/inverse',
					type: 'post',
					data:{"matrixA": $("#matrixA").val()},
					success: function (r) {
						var elementById = document.getElementById("result");
						elementById.value=r;
					}
				});
			}

			function rank() {
				$.ajax({
					url: '${pageContext.request.contextPath}/matrix/rank',
					type: 'post',
					data:{"matrixA": $("#matrixA").val()},
					success: function (r) {
						var elementById = document.getElementById("result");
						elementById.value=r;
					}
				});
			}

			function eigenValue() {
				$.ajax({
					url: '${pageContext.request.contextPath}/matrix/eigenValue',
					type: 'post',
					data:{"matrixA": $("#matrixA").val()},
					success: function (r) {
						var elementById = document.getElementById("result");
						elementById.value=r;
					}
				});
			}

			function judgeSame() {
				$.ajax({
					url: '${pageContext.request.contextPath}/matrix/judgeSame',
					type: 'post',
					data:{"matrixA": $("#matrixA").val(),"matrixB":$("#matrixB").val()},
					success: function (r) {
						var elementById = document.getElementById("result");
						elementById.value=r;
					}
				});
			}

		</script>
</body>

</html>