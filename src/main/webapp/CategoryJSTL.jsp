<%@ page language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="model.*"%>
<%@ page import="view.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${not empty param.language}">
	<c:set var="language" value="${param.language}" scope="session" />
</c:if>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="bundles.Bundle" />
<html>
<head>
<title><fmt:message key="categorie.titre.page" /></title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Tempusdominus Bootstrap 4 -->
<link rel="stylesheet"
	href="plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- iCheck -->
<link rel="stylesheet"
	href="plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- JQVMap -->
<link rel="stylesheet" href="plugins/jqvmap/jqvmap.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="dist/css/adminlte.min.css">
<!-- overlayScrollbars -->
<link rel="stylesheet"
	href="plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<!-- Daterange picker -->
<link rel="stylesheet"
	href="plugins/daterangepicker/daterangepicker.css">
<!-- summernote -->
<link rel="stylesheet" href="plugins/summernote/summernote-bs4.min.css">
</head>
<body bgcolor="#FFFFFF">
	<c:url value="" var="lngFr">
		<c:param name="language" value="fr" />
	</c:url>
	<c:url value="" var="lngEn">
		<c:param name="language" value="en" />
	</c:url>
	<c:url value="" var="lngEs">
		<c:param name="language" value="es" />
	</c:url>
	<p align="right">
		<a href='<c:out value="${lngFr}"/>'>FR</a> - <a
			href='<c:out value="${lngEn}"/>'>EN</a> - <a
			href='<c:out value="${lngEs}"/>'>ES</a>
	</p>
	<h1>
		<fmt:message key="categorie.titre.liste" />
	</h1>

	<%--  <c:set var = "now" value = "<%= new java.util.Date()%>" /> --%>
	<jsp:useBean id="now" class="java.util.Date" />
	<h2>
		<fmt:formatDate type="both" dateStyle="long" timeStyle="long"
			value="${now}" />
	</h2>

	<form method='post' action='admin/adminCategorie'>
		<table border='1' style="text-align:right">
			<tr>
				<td>Search a Category :<input type='text' name='motCle'
					value='${catForm.motCle}'></td>
				<td><input type='submit' name='chercheCat' value='Chercher'></td>
			</tr>
		</table>
	</form>
	<form method='post' action='admin/adminCategorie'>
		<table class="table table-striped table-hover">
			<thead class="thead-dark">
				<tr>
					<th>Id Cat�gorie</th>
					<th>Nom Cat�gorie</th>
					<th>Description</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td></td>
					<td><input type='text' name='nomCat'></td>
					<td><input type='text' name='description' size='40'></td>
					<td><input type='submit' name='addCat' value='Ajouter'></td>
				</tr>
				<c:if test="${not empty catForm}">

					<c:forEach items="${catForm.lesCats}" var="cat">
						<tr>
							<td>${cat.id}</td>
							<td>${cat.nom}</td>
							<td>${cat.description}</td>
							<c:url value="admin/adminCategorie" var="myURL">
								<c:param name="idCat" value="${cat.id}" />
							</c:url>
							<td><a href='<c:out value="${myURL}"/>'>Supprimer</a></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</form>


	<!-- jQuery -->
	<script src="plugins/jquery/jquery.min.js"></script>
	<!-- jQuery UI 1.11.4 -->
	<script src="plugins/jquery-ui/jquery-ui.min.js"></script>
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
		$.widget.bridge('uibutton', $.ui.button)
	</script>
	<!-- Bootstrap 4 -->
	<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- ChartJS -->
	<script src="plugins/chart.js/Chart.min.js"></script>
	<!-- Sparkline -->
	<script src="plugins/sparklines/sparkline.js"></script>
	<!-- JQVMap -->
	<script src="plugins/jqvmap/jquery.vmap.min.js"></script>
	<script src="plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
	<!-- jQuery Knob Chart -->
	<script src="plugins/jquery-knob/jquery.knob.min.js"></script>
	<!-- daterangepicker -->
	<script src="plugins/moment/moment.min.js"></script>
	<script src="plugins/daterangepicker/daterangepicker.js"></script>
	<!-- Tempusdominus Bootstrap 4 -->
	<script
		src="plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
	<!-- Summernote -->
	<script src="plugins/summernote/summernote-bs4.min.js"></script>
	<!-- overlayScrollbars -->
	<script
		src="plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
	<!-- AdminLTE App -->
	<script src="dist/js/adminlte.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="dist/js/demo.js"></script>
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<script src="dist/js/pages/dashboard.js"></script>
</body>
</html>
	