<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đăng nhập</title>
</head>
<body>
	<div class="container-fluid bg">
		<div class="row">
			<div class="col-md-4 col-sm-4 col-xs-12"></div>
			<div class="col-md-4 col-sm-4 col-xs-12">
				<c:if test="${param.incorrectAccount != null}">
					<div class="alert alert-danger">
						Username or password incorrect
					</div>
				</c:if>
				<c:if test="${param.accessDenied != null}">
					<div class="alert alert-danger">
						you Not authorize
					</div>
				</c:if>
				<c:if test="${param.sessionTimeout != null}">
					<div class="alert alert-danger">
						session timeout
					</div>
				</c:if>
				<form class="form-container" action="j_spring_security_check"  id="formLogin" method="post">
					<h1 class="text-center">Login</h1>
					<div class="form-group">
						<label>Username</label>
						<input type="text" name="j_username" class="form-control" id="userName"  aria-describedby="emailHelp" placeholder="Enter email">
					</div>
					<div class="form-group">
						<label>Password</label>
						<input type="password"  name="j_password" class="form-control" id="password" placeholder="Password">
					</div>
					<button type="submit" class="btn btn-success btn-block mt-3">Submit</button>
				</form>
			</div>
			<div class="col-md-4 col-sm-4 col-xs-12"></div>
		</div>
	</div>
</body>
</html>