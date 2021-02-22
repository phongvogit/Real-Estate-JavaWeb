<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="x-ua-compatible" content="ie=edge,chrome=1" />
	<meta charset="utf-8" />
	<title><dec:title default="dashboard - ace admin" /></title>

	<meta name="description" content="overview &amp; stats" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />


	<!-- bootstrap & fontawesome -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"  crossorigin="anonymous">
	<link rel="stylesheet" href="<c:url value="/admin/assets/font-awesome/4.5.0/css/font-awesome.min.css"/>" />

	<!-- css -->
	<link rel="stylesheet" href="<c:url value='/admin/assets/css/style.css' />" />


    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script src="<c:url value='/paging/jquery.twbsPagination.js' />"></script>
	<base href="/" />
</head>
<body class="no-skin">
	<!-- Navbar -->
    <%@ include file="/common/admin/navbar.jsp" %>
    <!-- Navbar -->
	<dec:body/>

	<!-- page specific plugin scripts -->
</body>
</html>