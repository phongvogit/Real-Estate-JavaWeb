<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingEditUrl" value="/admin/building-edit"/>
<c:url var="buildingListUrl" value="/admin/building-list"/>
<c:url var="buildingAPI" value="/api/building"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
</head>
<body>
</nav>
<div class="breadcrumb">
    <li class="breadcrumb-item"><i class="fa fa-home home-icon" aria-hidden="true"></i></li>
    <li class="breadcrumb-item"><a href="#">Product</a></li>
    <li class="breadcrumb-item active" aria-current="page">Edit</li>
</div>
<!-- Form -->
<form:form class="p-3 m-5" style="border: 1px solid rgba(0,0,0,.125);" action="${buildingListUrl}" commandName="modelSearch" id="formEdit" method="POST">
    <div class="form-group">
        <div class="row">
            <div class="col-md-6">
                <h5>Product's Name</h5>
<%--                <input  value="" name="name" class="form-control" aria-describedby="emailHelp" placeholder="Enter Product's Name">--%>
                <form:input path="name" cssClass="form-control" placeholder="Enter Product's Name" />
            </div>
            <div class="col-md-6">
                <h5>Gross Floor Area(GFA)</h5>
<%--                <input  class="form-control" aria-describedby="emailHelp" placeholder="Enter GFA">--%>
                <form:input path="floorArea" cssClass="form-control" placeholder="Enter GFA" />
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="row">
            <div class="col-md-4">
                <h5>Select District</h5>
                <form:select path="district" cssClass="form-control">
                    <form:option value="" label="---Select District---"/>
                    <form:options items="${districtmaps}"/>
                </form:select>
            </div>
            <div class="col-md-4">
                <h5>Ward</h5>
<%--                <input  class="form-control"  aria-describedby="emailHelp" placeholder="Enter Ward">--%>
                <form:input path="ward" cssClass="form-control"  placeholder="Enter ward"/>
            </div>
            <div class="col-md-4">
                <h5>Street</h5>
<%--                <input  class="form-control" aria-describedby="emailHelp" placeholder="Enter Street">--%>
                <form:input path="street" cssClass="form-control"  placeholder="Enter Street"/>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="row">
            <div class="col-md-5">
                <h5>Number of Basements</h5>
<%--                <input  class="form-control" aria-describedby="emailHelp" placeholder="Enter Basement Quantity">--%>
                <form:input path="numberOfBasement" cssClass="form-control"  />
            </div>
<%--            <div class="col-md-5">--%>
<%--                <h5>Direction</h5>--%>
<%--&lt;%&ndash;                <input  class="form-control" aria-describedby="emailHelp" placeholder="Enter Direction">&ndash;%&gt;--%>
<%--                <form:input path="direction" cssClass="form-control" placeholder="Enter Direction"/>--%>
<%--            </div>--%>
        </div>
    </div>
    <div class="form-group">
        <div class="row">
            <div class="col-md-4">
                <h5>Area</h5>
<%--                <input  class="form-control" aria-describedby="emailHelp" placeholder="50" value="" name="area">--%>
                <form:input path="rentArea" id="rentArea" cssClass="form-control" placeholder="100, 200, 300"/>
                <p id="errorRentArea" class="text-dange"><p>
            </div>
            <div class="col-md-4">
                <h5>Rental Price</h5>
<%--                <input  class="form-control" aria-describedby="emailHelp" placeholder="250.000 euros">--%>
                <form:input path="rentCost" cssClass="form-control" placeholder="250.000 euros"/>
            </div>
            <div class="col-md-4">
                <h5>Service Fee</h5>
                <form:input path="serviceCost" cssClass="form-control" />
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="row">
            <div class="col-md-4">
                <h5>Manager's Name</h5>
<%--                <input  class="form-control" aria-describedby="emailHelp" value="" name="manager" placeholder="Enter Manager's Name"  >--%>
                <form:input path="managerName" cssClass="form-control" placeholder="Enter Manager's Name" />
            </div>
            <div class="col-md-4">
                <h5>Manager's Phone</h5>
<%--                <input  class="form-control" aria-describedby="emailHelp" placeholder="Enter Manager's Phone">--%>
                <form:input path="managerPhone" cssClass="form-control" placeholder="Enter Manager's Phone" />
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-check buildingTypeForm">
            <form:checkboxes path="buildingTypes" items="${buildingTypeMaps}" cssClass="form-check-input" />
        </div>
    </div>
    <form:input path="id" id="id" type="hidden"/>

    <button type="submit" id="btnAddBuilding" class="btn btn-success px-5 py-2">Add</button>
</form:form>


<script>
    $('#btnAddBuilding').click(function (e) {
            e.preventDefault();
            var buildingTypes = [];
            var data = {};
            var formData = $('#formEdit').serializeArray();
            $.each(formData, function (index, v) {
                if(v.name != 'buildingTypes'){
                    data[""+v.name+""] = v.value;
                }else{
                    buildingTypes.push(v.value);
                }
            });
        data["buildingTypes"] = buildingTypes;
        var id = $('#id').val();
        if(id == ""){
            addNew(data);
        } else {
            data['checkUpdate'] = true;
            updateNew(data);
        }
    });
    function addNew(data) {
        $.ajax({
            cache: false,
            type:"POST",
            dataType: "json",
            url:"${buildingAPI}",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function (response) {
                console.log("success");
                window.location.href="${buildingListUrl}";
            },
            error:function (response) {
                console.log('failed');
                console.log(response);
            }
        });
    }
    function updateNew(data) {
        $.ajax({
            type:"PUT",
            url:"${buildingAPI}",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(data),
            success: function (response) {
                console.log("success");
                window.location.href="${buildingListUrl}";
            },
            error:function (response) {
                console.log('failed');
                console.log(response);
            }
        });
    }
</script>
</body>
</html>