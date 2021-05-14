<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="breadcrumb">
    <li class="breadcrumb-item"><i class="fa fa-home home-icon" aria-hidden="true"></i></li>
    <li class="breadcrumb-item"><a href="#">Product</a></li>
    <li class="breadcrumb-item active" aria-current="page">Edit</li>
</div>
<!-- content -->
<!-- table -->
<div class="container-fluid ml-2 mt-5 mr-0">
    <div class="row">
        <div class="col-8">
            <!-- Table-->
            <table class="table table-striped table-border">
                <tbody class="inf">
                <tr>
                    <td>Full Name</td>
                    <td>${user.fullName}</td>
                </tr>
                <tr>
                    <td>Phone</td>
                    <td>${user.phone}</td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td>${user.email}</td>
                </tr>
                </tbody>
            </table>
            <!-- table-end -->
        </div>
    </div>
</div>
</body>
</html>
