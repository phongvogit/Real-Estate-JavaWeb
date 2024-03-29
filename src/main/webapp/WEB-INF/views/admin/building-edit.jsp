<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingEditUrl" value="/admin/building-edit"/>
<c:url var="buildingListUrl" value="/admin/building-list"/>
<c:url var="buildingAPI" value="/api/building"/>
<c:url var="fileAPI" value="/api/file"/>
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
<form:form class="p-3 m-5" style="border: 1px solid rgba(0,0,0,.125);" action="${buildingEditUrl}" commandName="modelSearch" id="formEdit" method="POST" >
    <c:if test="${not empty message}">
        <div class="alert alert-${message.get("alert")}">${message.get("message")}</div>
    </c:if>
    <div class="form-group">
        <div class="row">
            <div class="col-md-6">
                <h5>Product's Name</h5>
                <form:input path="name" cssClass="form-control" placeholder="Enter Product's Name" />
            </div>
            <div class="col-md-6">
                <h5>Gross Floor Area(GFA)</h5>
                <form:input path="floorArea" cssClass="form-control" placeholder="Enter GFA" />
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="row">
            <div class="col-md-4">
                <h5>Select District</h5>
                <form:select path="district" cssClass="form-control" id="district">
                    <form:option value="" label="---Select District---"/>
                    <form:options items="${districtmaps}"/>
                </form:select>
            </div>
            <div class="col-md-4">
                <h5>Ward</h5>
                <form:input path="ward" cssClass="form-control"  placeholder="Enter ward"/>
            </div>
            <div class="col-md-4">
                <h5>Street</h5>
                <form:input path="street" cssClass="form-control"  placeholder="Enter Street"/>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="row">
            <div class="col-md-4">
                <h5>Area</h5>
                <form:input path="rentArea" id="rentArea" cssClass="form-control" placeholder="100, 200, 300"/>
                <p id="errorRentArea" class="text-dange"><p>
            </div>
            <div class="col-md-4">
                <h5>Rental Price</h5>
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
                <h5>Number of Basements</h5>
                <form:input path="numberOfBasement" cssClass="form-control"  />
            </div>
            <div class="col-md-4">
                <h5>Deposit</h5>
                <form:input path="deposit" cssClass="form-control"  />
            </div>
            <div class="col-md-4">
                <h5>Payment</h5>
                <form:input path="payment" cssClass="form-control"  />
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="row">
            <div class="col-md-4">
                <h5>Car Cost</h5>
                <form:input path="carCost" cssClass="form-control"  />
            </div>
            <div class="col-md-4">
                <h5>Motor Cost</h5>
                <form:input path="motorCost" cssClass="form-control"  />
            </div>
            <div class="col-md-4">
                <h5>Overtime Cost</h5>
                <form:input path="overtimeCost" cssClass="form-control"  />
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="row">
            <div class="col-md-4">
                <h5>Service Cost</h5>
                <form:input path="serviceCost" cssClass="form-control"  />
            </div>
            <div class="col-md-4">
                <h5>Electric Bill</h5>
                <form:input path="electricBill" cssClass="form-control"  />
            </div>
            <div class="col-md-4">
                <h5>Direction</h5>
                <form:input path="direction" cssClass="form-control"  />
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="row">
            <div class="col-md-12">
                <h5>Cost Description</h5>
                <form:input path="costDescription" cssClass="form-control"  />
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="row">
            <div class="col-md-12">
                <h5>Rent Area Description</h5>
                <form:input path="rentAreaDescription" cssClass="form-control"  />
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="row">
            <div class="col-md-12">
                <h5>Structure</h5>
                <form:input path="structure" cssClass="form-control"  />
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="row">
            <div class="col-md-4">
                <h5>Time Rent</h5>
                <form:input path="timeRent" cssClass="form-control"  />
            </div>
            <div class="col-md-4">
                <h5>Level</h5>
                <form:input path="level" cssClass="form-control"  />
            </div>
            <div class="col-md-4">
                <h5>Time Decoration</h5>
                <form:input path="timeDecoration" cssClass="form-control"  />
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
    <h5>Upload Image:</h5>
    <div class="custom-file mb-3">
        <input type="file" class="custom-file-input" id="fileImage" name="image" value="" accept="image/png, image/jpeg">
        <input type="hidden" value="${modelSearch.image}" id="imageName">
        <label class="custom-file-label" for="fileImage">${modelSearch.image}</label>
    </div>
    <p id="showImage">
        <c:if test="${not empty modelSearch.image}">
            <img id="thumbnail" src="<c:url value="/admin/assets/images/product/${modelSearch.image}"/>" alt="imagePreview" style="width: 300px; heigth:400px">
        </c:if>
    </p>
    <form:input path="id" id="id" type="hidden"/>
    <button type="submit" id="btnAddBuilding" class="btn btn-success px-5 py-2">Add</button>
</form:form>


<script>
    var checkImageClicked = false;
    $("#fileImage").change(function () {
        var row = '<img id=\"thumbnail\" src=\"\" alt=\"imagePreview\" style=\"width: 300px; heigth:400px\">';
        $('#showImage').html(row);
        showImageThumbnail(this);
    });

    function showImageThumbnail(fileInput){
        file = fileInput.files[0];
        reader = new FileReader();

        reader.onload = function (e) {
            $('#thumbnail').attr('src', e.target.result);
        };

        reader.readAsDataURL(file);
        checkImageClicked = true;
    }

    $(".custom-file-input").on("change", function() {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
    $('#btnAddBuilding').click(function (e) {
            e.preventDefault()
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
        data["id"] = id;
        if(checkImageClicked){
            data["image"] = saveFile();
        }else {
            data["image"] = $('#imageName').val();
        }
        if(id == ""){
            addNew(data);
        } else {
            data['checkUpdate'] = true;
            updateNew(data);
        }
    });
    function saveFile() {
        var dataArray = {};
        var files = $('#fileImage')[0].files[0];
        if(files != undefined){
            var reader = new FileReader();
            reader.onload = function (e) {
                dataArray["base64"] = e.target.result;
                dataArray["name"] = files.name;
                uploadFile(dataArray);
            };
            reader.readAsDataURL(files);
            return files.name;
        }
        return null;
    };
    function uploadFile(data){
        $.ajax({
            type:"POST",
            url:"${fileAPI}",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (response) {
                console.log("success");
            },
            error:function (response) {
                console.log('failed');
            }
        });
    }
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
                window.location.href="${buildingEditUrl}?message=insert_success";
            },
            error:function (response) {
                console.log('failed');
                window.location.href="${buildingEditUrl}?message=error_system";
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
                window.location.href="${buildingEditUrl}/"+data["id"]+"?message=update_success";
            },
            error:function (response) {
                window.location.href="${buildingEditUrl}/"+data["id"]+"?message=error_system";
            }
        });
    }
</script>
</body>
</html>
