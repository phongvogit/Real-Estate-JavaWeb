<%@ page import="com.laptrinhjavaweb.security.utils.SecurityUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="userEdit" value="/admin/user/edit"/>
<c:url var="userList" value="/admin/user/list"/>
<c:url var="userAPI" value="/api/user"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- <title>Trang chủ</title> -->
</head>
<body>
<div class="breadcrumb">
    <li class="breadcrumb-item"><i class="fa fa-home home-icon" aria-hidden="true"></i></li>
    <li class="breadcrumb-item"><a href="#">Customer</a></li>
    <li class="breadcrumb-item active" aria-current="page">List & Search</li>
</div>
<div class="widget-box" style="background-image: none">
    <!-- Sub-Btn -->
    <security:authorize access="hasRole('MANAGER')">
        <div class="row sub-btn">
            <div class="col-12">
                <a type="button" class="btn btn-outline-dark" href="${userEdit}" data-toggle="tooltip" title='Add more'>
                    <i class="fa fa-plus-circle" aria-hidden="true" style="color: black;"></i>
                </a>
                <button type="button" class="btn btn-outline-danger" data-toggle="tooltip" title='Delete' id="btnDelete">
                    <i class="fa fa-trash" aria-hidden="true"></i>
                </button>
            </div>
        </div>
    </security:authorize>

    <!-- Table-->
    <div class="widget-table mb-1">
        <table class="table table-hover table-border">
            <thead>
            <tr>
                <th scope="col"><input type="checkbox" id="check-all"></th>
                <th scope="col">UserName</th>
                <th scope="col">FullName</th>
                <th scope="col">Manipulation</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${model}">
                    <tr>
                        <th class="check-box"><input type="checkbox" id="" value="${item.id}"></th>
                        <td>${item.userName}</td>
                        <td>${item.fullName}</td>
                        <td>
<%--                            Edit Profile--%>
                            <c:forEach var="role" items="${item.roles}">
                                <c:if test="${authority == 'MANAGER'}">
                                    <a type="submit" class="btn btn-info"  title='Update' href="${userEdit}/${item.id}">
                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                    </a>
                                </c:if>
                                <c:if test="${(role.code == 'STAFF' && authority ==  'STAFF') &&
                                                (item.userName == SecurityUtils.getPrincipal().getUsername())}">
                                    <a type="submit" class="btn btn-info"  title='Update' href="${userEdit}/${item.id}">
                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                    </a>
                                </c:if>
                                <c:if test="${(role.code == 'STAFF' && authority ==  'STAFF') &&
                                                (item.userName != SecurityUtils.getPrincipal().getUsername())}">
                                    <p>No Authorization</p>
                                </c:if>
                                <c:if test="${role.code == 'MANAGER' && authority ==  'STAFF'}">
                                    <p>No Authorization</p>
                                </c:if>
                            </c:forEach>
<%--                            <security:authorize access="hasRole('MANAGER')">--%>
<%--                                <button type="button" class="btn btn-info" data-toggle="modal" onclick="assignmentCustomer(${item.id})" data-target="#exampleModal">--%>
<%--                                    <i class="fa fa-newspaper-o" aria-hidden="true"></i>--%>
<%--                                </button>--%>
<%--                            </security:authorize>--%>
<%--                            <a type="submit" class="btn btn-info"  title='Update' href="${customerEditURL}/${item.id}">--%>
<%--                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i>--%>
<%--                            </a>--%>
<%--                            <a type="button" class="btn btn-info" title='See more' href= "${customerViewURL}/${item.id}">--%>
<%--                                <i class="fa fa-eye" aria-hidden="true"></i>--%>
<%--                            </a>--%>
                        </td>
                    </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="bg-white text-center mx-auto" style="width: 250px">
        <nav aria-label="Page navigation example mx-auto ">
            <ul class="pagination" id="pagination">
            </ul>
        </nav>
    </div>
</div>
<!-- Sub-script-->
<script>
    $("#btnDelete").click(function() {
        var ids = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        var result = confirm("Want to Delete");
        if(result){
            deleteBuilding(ids);
        }
    });
    function deleteBuilding(ids) {
        $.ajax({
            url: '${userAPI}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(ids),
            success: function (result) {
                window.location.href="${userList}";
            },
            error: function (error) {
                console.log("failed");
            }
        });
    }
</script>
<!-- Sub-script-->
</body>
</html>