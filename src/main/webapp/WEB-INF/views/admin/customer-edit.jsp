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
<form:form class="p-3 m-5" style="border: 1px solid rgba(0,0,0,.125);"  id="formEdit" commandName="modelSearch"  method="GET">
    <c:if test="${not empty message}">
        <div class="alert alert-${message.get("alert")}">${message.get("message")}</div>
    </c:if>
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
    <button type="submit" id="btnAddCustomer" class="btn btn-success px-5 py-2">Add</button>
</form:form>
<c:if test="${not empty modelSearch.id}">
    <c:forEach var="item" items="${transactionResponse.transactionTypeMaps}">
        <div class="p-3 mx-5 transactionInf">
            <div class="title mb-0 col-3 p-0">
                <label><h5 class="text-primary mr-2">${item.value}</h5></label>
                <button type="button" class="btn btn-info" title='Add more' data-toggle="modal" data-target="#addTransactionModal" onclick="loadTransactionTypeModal('${item.key}', '${item.value}', ${transactionResponse.customerid})">
                    <i class="fa fa-plus" aria-hidden="true"></i>
                </button>
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
</c:if>
<!-- Modal -->
<div class="modal fade" id="addTransactionModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="titleTransactionModal"></h5>
            </div>
            <div class="modal-body" id="transaction">
                <div class="form-group">
                    <label for="note">Note</label>
                    <textarea class="form-control" id="note" rows="3" value=""></textarea>
                    <input type="hidden" id="transactionType" value="">
                    <input type="hidden" id="customerId" value="">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" id="saveTransactionBtn">Save</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>



<script>
    function loadTransactionTypeModal(key, value, customerId){
        openTransactionTypeModal();
        $('#addTransactionModal #titleTransactionModal').html(value);
        $('#addTransactionModal #transactionType').val(key);
        $('#addTransactionModal #customerId').val(customerId);
    }

    function openTransactionTypeModal(){
        $("#assignmentBuildingModal").modal();
    }
    $('#saveTransactionBtn').click(function (e) {
        e.preventDefault();
        var data = {};
        var transactionType = $('#transaction #transactionType').val();
        var customerId = $("#transaction #customerId").val();
        var note = $("#transaction #note").val();
        if(note != ""){
            data['note'] = note;
            data['customerId'] = customerId;
            data['type'] = transactionType;
            addNewTransaction(data);
        }
    });
    function addNewTransaction(data) {
        $.ajax({
            type:"POST",
            url:"${transactionAPI}",
            contentType: "application/json",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (response) {
                location.reload();
            },
            error:function (response) {
                console.log('failed');
            }
        });
    }

    $('#btnAddCustomer').click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#formEdit').serializeArray();
        $.each(formData, function (index, v) {
            data[""+v.name+""] = v.value;
        });
        var id = $('#id').val();
        data["id"] = id;
        if(id == ""){
            addNew(data);
        } else {
            updateNew(data);
        }
    });

    function addNew(data) {
        $.ajax({
            type:"POST",
            url:"${customerAPI}",
            contentType: "application/json",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (response) {
                window.location.href="${customerEditUrl}?message=insert_success";
            },
            error:function (response) {
                window.location.href="${customerEditUrl}?message=error_system";
            }
        });
    }
    function updateNew(data) {
        $.ajax({
            type:"PUT",
            url:"${customerAPI}",
            contentType: "application/json",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (response) {
                window.location.href="${customerEditUrl}/"+response.data.id+"?message=update_success";
            },
            error:function (response) {
                window.location.href="${customerEditUrl}/"+data["id"]+"?message=error_system";
            }
        });
    }
</script>
</body>
</html>
