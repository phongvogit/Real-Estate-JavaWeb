<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="customerListUrl" value="/admin/customer-list"/>
<c:url var="customerEditUrl" value="/admin/customer-edit"/>
<c:url var="customerAPI" value="/api/customer"/>
<c:url var="transactionAPI" value="/api/transaction"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
</head>
<body>
</nav>
<div class="breadcrumb">
    <li class="breadcrumb-item"><i class="fa fa-home home-icon" aria-hidden="true"></i></li>
    <li class="breadcrumb-item"><a href="#">Customer</a></li>
    <li class="breadcrumb-item active" aria-current="page">Edit</li>
</div>
<!-- Form -->
<form:form class="p-3 m-5" style="border: 1px solid rgba(0,0,0,.125);" commandName="modelSearch">
    <div class="form-group px-2">
        <div class="row my-2">
            <div class="col-4">
                <h5>Customer's Name</h5>
                <form:input path="fullName" cssClass="form-control" placeholder="Enter name"/>
            </div>
            <div class="col-4">
                <h5>Phone</h5>
                <form:input path="phone" cssClass="form-control" placeholder="Enter phone"/>
            </div>
        </div>
        <div class="row">
            <div class="col-6">
                <h5>Email</h5>
                <form:input path="email" cssClass="form-control" placeholder="Enter email"/>
            </div>
            <div class="col-6">
                <h5>Demand</h5>
                <form:input path="demand" cssClass="form-control" placeholder="Enter demand"/>
            </div>
        </div>
        <form:input path="id" id="id" type="hidden"/>
    </div>
</form:form>
    <c:forEach var="item" items="${transactionResponse.transactionTypeMaps}">
        <div class="p-3 mx-5 transactionInf">
            <div class="title mb-0 col-3 p-0">
                <label><h5 class="text-primary mr-2" >${item.value}</h5></label>
            </div>
            <hr class="mt-0">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Created Date</th>
                    <th scope="col" style="width:600px">Notes</th>
                    <th scope="col">Created By</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="transaction" items="${transactionResponse.transDtoLists}">
                    <c:if test="${transaction.type == item.key}">
                        <tr>
                            <td>${transaction.createdDate}</td>
                            <td>${transaction.note}</td>
                            <td>${transaction.createdBy}</td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:forEach>
</body>
</html>
