<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="${pageContext.request.contextPath}/img/user2-160x160.jpg"
					class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<p>
					<security:authentication property="principal.username" />
				</p>
				<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
			</div>
		</div>

		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">菜单</li>
			<li id="admin-index"><a
				href="${pageContext.request.contextPath}/pages/main.jsp"><i
					class="fa fa-dashboard"></i> <span>首页</span></a></li>

			<li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>
					<span>计算工具</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>


			</a>
				<ul class="treeview-menu">

					<li><a
						href="${pageContext.request.contextPath}/pages/determinant.jsp"> <i
							class="fa fa-circle-o"></i> 行列式计算
					</a></li>
					<li><a
						href="${pageContext.request.contextPath}/pages/matrix.jsp"> <i
							class="fa fa-circle-o"></i> 矩阵计算
					</a></li>
					<li><a
						href="${pageContext.request.contextPath}/pages/equations.jsp"> <i
							class="fa fa-circle-o"></i> 解线性方程组
					</a></li>
					<li><a
							href="${pageContext.request.contextPath}/pages/judgeLinear.jsp"> <i
							class="fa fa-circle-o"></i> 线性相关判断
					</a></li>
				</ul></li>
			<li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
					<span>学习</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">

					<li><a
						href="${pageContext.request.contextPath}/pages/forum.jsp">
							<i class="fa fa-circle-o"></i> 问答论坛
					</a></li>
					<li><a
						href="${pageContext.request.contextPath}/algorithm/algorithmList?currentPage=1">
							<i class="fa fa-circle-o"></i> 算法
					</a></li>

				</ul></li>

			<li id="my-index"><a
					href="${pageContext.request.contextPath}/pages/main.jsp"><i
					class="fa fa-dashboard"></i> <span>我的信息</span></a>
			</li>
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>