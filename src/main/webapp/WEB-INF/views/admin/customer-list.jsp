
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="customerListURL" value="/admin/customer-list"/>
<c:url var="customerEditURL" value="/admin/customer-edit"/>
<c:url var="customerAPI" value="/api/customer" />

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
<div class="widget-box">
    <!-- Card -->
    <div class="card">
        <div class="card-header px-3 py-1">
            <div class="row">
                <div class="col-10">
                    <h5>Search</h5>
                </div>
                <div class="col-2">
                    <a class="widget-toolbar-right"data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                        <i class="fa fa-chevron-down" id="chevron"></i>
                    </a>
                </div>
            </div>
        </div>
        <div class="collapse" id="collapseExample">
            <div class="card card-body">
                <%--@elvariable id="modelSearch" type=""--%>
                <form:form commandName="modelSearch" action="${customerListURL}" id="listForm" method="get">
                    <div class="form-group">
                        <div class="row">
                            <div class="col-md-6">
                                <h5>Customer's Name</h5>
                                <form:input path="fullName" cssClass="form-control" placeholder="Enter name"/>
                            </div>
                            <div class="col-md-6">
                                <h5>Phone</h5>
                                <form:input path="phone" cssClass="form-control" placeholder="Enter phone"/>
                            </div>
                            <div class="col-md-6">
                                <h5>Email</h5>
                                <form:input path="email" cssClass="form-control" placeholder="Enter email"/>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-success px-5 py-2" id="btnSearch">Search</button>
                </form:form>
            </div>
        </div>
    </div>
    <!-- Sub-Btn -->
    <div class="row sub-btn">
        <div class="col-12">
            <a type="button" class="btn btn-outline-dark" href="<c:url value='${customerEditURL}'/>" data-toggle="tooltip" title='Add more'>
                <i class="fa fa-plus-circle" aria-hidden="true" style="color: black;"></i>
            </a>
            <button type="button" class="btn btn-outline-danger" data-toggle="tooltip" title='Delete' id="btnDelete">
                <i class="fa fa-trash" aria-hidden="true"></i>
            </button>
        </div>
    </div>
    <!-- Table-->
    <div class="widget-table">
        <table class="table table-hover table-border">
            <thead>
            <tr>
                <th scope="col"><input type="checkbox" id="check-all"></th>
                <th scope="col">Name</th>
                <th scope="col">Demand</th>
                <th scope="col">CreatedBy</th>
                <th scope="col">CreatedDate</th>
                <th scope="col">Manipulation</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${customers}">
                 <form:form action="${customerEditURL}" commandName="modelSearch" method="GET">
                     <tr>
                         <th class="check-box"><input type="checkbox" id="" value="${item.id}"></th>
                         <td>${item.fullName}</td>
                         <td>${item.demand}</td>
                         <td>${item.createdBy}</td>
                         <td>${item.createdDate}</td>
                         <input type="hidden" value="${item.id}" name="id">
                         <td>
                             <button type="button" class="btn btn-info" data-toggle="modal" onclick="assignmentCustomer(${item.id})" data-target="#exampleModal">
                                 <i class="fa fa-eye" aria-hidden="true"></i>
                             </button>
                             <button type="submit" class="btn btn-info" data-toggle="modal" title='Update'>
                                 <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                             </button>
                                 <%--                        <button type="button" class="btn btn-info" data-toggle="modal" title='Add more'>--%>
                                 <%--                            <i class="fa fa-newspaper-o" aria-hidden="true"></i>--%>
                                 <%--                        </button>--%>
                                 <%--                        <button type="button" class="btn btn-info" data-toggle="modal" title='Add more'>--%>
                                 <%--                            <i class="fa fa-plus" aria-hidden="true"></i>--%>
                                 <%--                        </button>--%>
                         </td>
                     </tr>
                 </form:form>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="assignmentCustomerModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Building Assigments</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="staffList">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" id="saveAssignmentBtn">Save</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<!-- Widget-box End -->
<!-- Sub-script-->
<!-- Sub-script-->
<script>
    $(document).ready(function(){
        $("#chevron").click(function(){
            $("#chevron").toggleClass("fa-chevron-up fa-chevron-down");
        });
    });

    $(document).ready(function(){
        $("#chevron-nav").click(function(){
            $("#chevron-nav i").toggleClass("fa-chevron-circle-down fa-chevron-circle-up");
        });
    });

    $(document).ready(function(){
        $("#check-all").click(function(){
            if($(this). is(":checked")){
                $(".check-box input").attr("checked","");
            }
            else if($(this). is(":not(:checked)")){
                $(".check-box input").removeAttr("checked");
            }
        });
    });

    $("#btnDelete").click(function() {
        var data = {};
        var ids = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['ids'] = ids;
        deleteNew(data);
    });

    function deleteNew(data) {
        $.ajax({
            url: '${customerAPI}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href="${customerListURL}";
            },
            error: function (error) {
                console.log("failed");
            }
        });
    }

    function assignmentCustomer(id){
        openAssignmentCustomer();
        loadStaff(id);
    }

    function loadStaff(id) {
        $.ajax({
            type: "GET",
            url: "${customerAPI}/"+id+"/staffs",
            //contentType: 'application/json',
            //data: JSON.stringify(data),
            dataType:'json',
            success: function (response) {
                console.log("success");
                var row = '';
                $.each(response.data, function (index,item) {
                    row += '<div class="input-group mb-3">';
                    row +=      '<div class="input-group-prepend">';
                    row +=          '<div class="input-group-text">';
                    row +=              '<input type="checkbox" aria-label="Checkbox for following text input" name="staff" value='+ item.staffId +' '+ item.check +'>';
                    row +=          '</div>';
                    row +=      '</div>';
                    row += '    <input class="form-control bg-white" value="'+ item.fullName +'" disabled>';
                    row += '    <input type="hidden" class="form-control bg-white" id="customerId" value="'+id+'" disabled>';
                    row += '</div>';
                });
                $('#staffList').html(row);
            },
            error: function (response) {
                console.log("failed");
            }
        });
    }

    $("#saveAssignmentBtn").click(function() {
        var customerId = $('#staffList #customerId').val();
        var data = {};
        var ids = $('#staffList input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffIds'] = ids;
        data['id'] = customerId;
        saveAssignment(data);
    });
    function saveAssignment(data) {
        $.ajax({
            url: '${customerAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                console.log("success");
            },
            error: function (error) {
                console.log("failed");
            }
        });
    }
    function openAssignmentCustomer(){
        $("#assignmentCustomerModal").modal();
    }

    $('#btnSearch').click(function(e){
        e.preventDefault();
        $('#listForm').submit();
    });
</script>
</body>
</html>