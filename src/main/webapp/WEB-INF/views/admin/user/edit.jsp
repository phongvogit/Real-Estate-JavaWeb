<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="userEdit" value="/admin/user/edit"/>
<c:url var="userAPI" value="/api/user"/>
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
<c:if test="${empty model.id}">
    <form:form class="p-3 mx-auto" style="border: 1px solid rgba(0,0,0,.125); width:500px !important"  id="formEdit" method="get">
        <c:if test="${not empty message}">
            <div class="alert alert-${message.get("alert")}">${message.get("message")}</div>
        </c:if>
        <div class="form-group px-2">
            <div class="row my-2">
                <label class="ml-5 mx-2" style="margin-right: 67px !important;">FullName:</label>
                <input type="text" value="" name="fullName" cssClass="form-control"/>
            </div>
            <div class="row my-2">
                <label class="ml-5 mx-2" style="margin-right: 60px !important;">UserName:</label>
                <input type="text" value="" name="userName" cssClass="form-control" id="userName"/>
            </div>
            <div class="row my-2">
                <label class="ml-5 mx-2" style="margin-right: 67px !important;">Password:</label>
                <input type="password" value="" name="password" cssClass="form-control" id="password"/>
            </div>
            <div class="row my-2">
                <label class="ml-5 mx-2">Confirm Password:</label>
                <input type="password" cssClass="form-control" id="confirmPassword"/>
            </div>
            <div class="row my-2">
                <label class="ml-5 mx-2" style="margin-right: 57px !important;">Select Role:</label>
                <select name="role" cssClass="form-control" id="role">
                    <option value="" label="---Select---"/>
                    <c:forEach var="item" items="${roleMaps}">
                        <option value="${item.key}" label="${item.value}"/>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="mx-auto" style="width: 466px">
            <button type="submit" style="width: 200px; margin-left: 133px" id="btnAddAcount" class="btn btn-success px-5 py-2">Add</button>
        </div>
    </form:form>
</c:if>
<!-- Modal -->
<c:if test="${not empty model.id}">
    <form:form class="p-3 mx-auto" style="border: 1px solid rgba(0,0,0,.125); width:500px !important"  id="formEdit" method="get">
        <c:if test="${not empty message}">
            <div class="alert alert-${message.get("alert")}">${message.get("message")}</div>
        </c:if>
        <div class="form-group px-2">
            <div class="row my-2">
                <label class="ml-5 mx-2" style="margin-right: 67px !important;">FullName:</label>
                <input type="text" value="${model.fullName}" name="fullName" cssClass="form-control"/>
            </div>
            <div class="row my-2">
                <label class="ml-5 mx-2" style="margin-right: 60px !important;">UserName:</label>
                <input value="${model.userName}" cssClass="form-control" disabled/>
            </div>
            <security:authorize access="hasRole('MANAGER')">
                <div class="row my-2">
                    <label class="ml-5 mx-2" style="margin-right: 66px !important;">Password:</label>
                    <input type="password" name="password" value="${model.password}" cssClass="form-control"/>
                </div>
            </security:authorize>
            <div class="row my-2">
                <label class="ml-5 mx-2" style="margin-right: 95px !important;">Email:</label>
                <input type="text" value="${model.email}" name="email" cssClass="form-control"/>
            </div>
            <div class="row my-2">
                <label class="ml-5 mx-2"  style="margin-right: 88px !important;">Phone:</label>
                <input type="text" name="phone" value="${model.phone}" cssClass="form-control"/>
            </div>
            <div class="row my-2">
                <label class="ml-5 mx-2"  style="margin-right: 91px !important;">Street:</label>
                <input type="text" name="address" value="${model.address}" cssClass="form-control"/>
            </div>
        </div>
        <input type="hidden" value="${model.id}" name="id" id="id">
        <input type="hidden" value="1" name="status">
        <input type="hidden" value="${model.userName}" name="userName"/>
        <div class="mx-auto" style="width: 466px">
            <button type="submit" style="width: 200px; margin-left: 133px" id="btnAddAcount" class="btn btn-success px-5 py-2">Add</button>
        </div>
    </form:form>
</c:if>



<script>
    $('#btnAddAcount').click(function (e) {
        e.preventDefault();
        var check = true;
        if($('#password').val() != $('#confirmPassword').val()){
            alert("Password didn't match, try again");
            $('#password').val('');
            $('#confirmPassword').val('');
            $('#password').focus();
            check = false;
        }
        if(check){
            var data = {};
            var formData = $('#formEdit').serializeArray();
            $.each(formData, function (index, v) {
                data[""+v.name+""] = v.value;
            });
            var id = $('#id').val();
            if(id == ""){
                addNew(data);
            }else{
                updateNew(data);
            }
        }
    });

    function addNew(data) {
        $.ajax({
            type:"POST",
            url:"${userAPI}",
            contentType: "application/json",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (response) {
                window.location.href="${userEdit}?message=insert_success";
            },
            error:function (response) {
                window.location.href="${userEdit}?message=error_system";
            }
        });
    }
    function updateNew(data) {
        $.ajax({
            type:"PUT",
            url:"${userAPI}",
            contentType: "application/json",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (response) {
                window.location.href="${userEdit}/"+data["id"]+"/?message=update_success";
            },
            error:function (response) {
                window.location.href="${userEdit}/"+data["id"]+"/?message=error_system";
            }
        });
    }
</script>
</body>
</html>

