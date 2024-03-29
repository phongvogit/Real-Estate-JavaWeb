
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="searchURL" value="${urlMapping}"/>
<c:url var="buildingEditURL" value="/admin/building-edit"/>
<c:url var="buildingViewURL" value="/admin/building-view/"/>
<c:url var="buildingAPI" value="/api/building"/>
<c:url var="assignmentAPI" value="/api/assignment/"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- <title>Trang chủ</title> -->
</head>
<body>
<div class="breadcrumb">
    <li class="breadcrumb-item"><i class="fa fa-home home-icon" aria-hidden="true"></i></li>
    <li class="breadcrumb-item"><a href="#">Product</a></li>
    <li class="breadcrumb-item active" aria-current="page">List & Search</li>
</div>
<div class="widget-box" style="background-image: none">
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
                <form:form commandName="modelSearch" action="" id="listForm" method="GET">
                    <div class="form-group">
                        <div class="row">
                            <div class="col-md-6">
                                <h5>Product's Name</h5>
                                <form:input path="name" cssClass="form-control" />
                            </div>
                            <div class="col-md-6">
                                <h5>Gross Floor Area(GFA)</h5>
                                <form:input path="floorArea" cssClass="form-control" />
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
                                <form:input path="ward" cssClass="form-control" />
                            </div>
                            <div class="col-md-4">
                                <h5>Street</h5>
                                <form:input path="street" cssClass="form-control" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-md-4">
                                <h5>Number of Basements</h5>
                                <form:input path="numberOfBasement" cssClass="form-control" />
                            </div>
                            <div class="col-md-4">
                                <h5>Direction</h5>
                                <form:input path="direction" cssClass="form-control" />
                            </div>

                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-md-3">
                                <h5>Area from</h5>
                                <form:input path="areaRentFrom" cssClass="form-control" />
                            </div>
                            <div class="col-md-3">
                                <h5>Area to</h5>
                                <form:input path="areaRentTo" cssClass="form-control" />
                            </div>
                            <div class="col-md-3">
                                <h5>Rent from</h5>
                                <form:input path="costRentFrom" cssClass="form-control" />
                            </div>
                            <div class="col-md-3">
                                <h5>Rent to</h5>
                                <form:input path="costRentTo" cssClass="form-control" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-md-4">
                                <h5>Manager's Name</h5>
                                <form:input path="managerName" cssClass="form-control" />
                            </div>
                            <div class="col-md-4">
                                <h5>Manager's Phone</h5>
                                <form:input path="managerPhone" cssClass="form-control" />
                            </div>
                            <security:authorize access="hasRole('MANAGER')">
                                <div class="col-md-4">
                                    <h5>Select Task for Staff</h5>
                                    <form:select path="staffId" cssClass="form-control">
                                        <form:option value="" label="---Select Task for Staff---"/>
                                        <form:options items="${staffmaps}"/>
                                    </form:select>
                                </div>
                            </security:authorize>
                        </div>
                    </div>
                    <div class="row">
                            <div class="form-check buildingTypeForm">
                                <form:checkboxes path="buildingTypes" items="${buildingTypeMaps}" cssClass="form-check-input" />
                            </div>
                    </div>
                    <input type="hidden" id="page" name="currentPage" value="1">
                    <input type="hidden" id="maxPageItem" name="limit" value="5">
                    <button type="submit" class="btn btn-success px-5 py-2" id="btnSearch">Search</button>
                </form:form>
            </div>
        </div>
    </div>
    <!-- Sub-Btn -->
    <security:authorize access="hasRole('MANAGER')">
    <div class="row sub-btn">
        <div class="col-12">
            <a type="buttonAdd" class="btn btn-outline-dark" href="${buildingEditURL}" data-toggle="tooltip" title='Add more'>
                <i class="fa fa-plus-circle" aria-hidden="true" style="color: black;"></i>
            </a>
            <button type="btnDelete" class="btn btn-outline-danger" data-toggle="tooltip" title='Delete' id="btnDelete">
                <i class="fa fa-trash" aria-hidden="true"></i>
            </button>
        </div>
    </div>
    </security:authorize>
    <!-- Table-->
    <div class="mx-auto">
        <div class="widget-table mb-0">
            <table class="table table-hover table-border text-center">
                <thead>
                <tr>
                    <th scope="col"><input type="checkbox" id="check-all"></th>
                    <th scope="col">Date</th>
                    <th scope="col">Product's name</th>
                    <th scope="col">Manager's name</th>
                    <th scope="col">Street</th>
                    <th scope="col">Service cost</th>
                    <th scope="col">Floor Area</th>
                    <th scope="col">Manipulation</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${buildingResponse.buildings}" >
                    <form action="${buildingEditURL}/${item.id}" commandName="modelSearch" method="get">
                        <tr >
                            <th class="check-box"><input type="checkbox" value="${item.id}"></th>
                            <td>${item.createdDateShowing}</td>
                            <td>${item.name}</td>
                            <td>${item.managerName}</td>
                            <td>${item.street}</td>
                            <td>${item.serviceCost}$</td>
                            <td>${item.floorArea}</td>
                            <input type="hidden" name="id" value="${item.id}">
                            <td>
                                <security:authorize access="hasRole('MANAGER')">
                                    <button type="button" class="btn btn-info" data-toggle="modal" data-target="#exampleModal" onclick="assignmentBuilding(${item.id})" >
                                        <i class="fa fa-newspaper-o" aria-hidden="true"></i>
                                    </button>
                                </security:authorize>
                                <a type="button" class="btn btn-info text-white"  data-toggle="tooltip" title='Update' id="btnEdit" href="<c:url value="${buildingEditURL}/${item.id}"/>">
                                    <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                </a>
                                <a type="button" class="btn btn-info" title='Read more' href="<c:url value="${buildingViewURL}${item.id}"/>">
                                    <i class="fa fa-eye" aria-hidden="true"></i>
                                </a>
                                <button type="button" class="btn btn-info" id="btnAddPriority" title='Add more'  onclick="addPriority(${item.id})" >
                                    <i class="fa fa-plus" aria-hidden="true"></i>
                                </button>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="bg-white text-center mx-auto" style="width: 250px">
        <nav aria-label="Page navigation example mx-auto ">
            <ul class="pagination" id="pagination">
            </ul>
        </nav>
    </div>

<!-- Modal -->
<div class="modal fade" id="assignmentBuildingModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
    var totalPages = ${buildingResponse.totalPage};
    var currentPage = ${buildingResponse.page};
    var limit = 5;
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 5,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if(currentPage != page){
                    $('#maxPageItem').val(limit);
                    $('#page').val(page);
                    $('#listForm').submit();
                }
            }
        }).on('page', function (event, page) {
            console.info(page + ' (from event listening)');
        });
    });
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
        $('#btnSearch').click(function (e) {
            e.preventDefault();
            $('#listForm').attr('action', '${searchURL}');
            $('#listForm').submit();
        });
        $(document).keypress(function(e) {
            if (e.which == 13) {
                $('#listForm').attr('action', '${searchURL}');
                $('#listForm').submit();
                return false;
            }
        });
    });

    function addPriority(buildingId){
        $.ajax({
            url: '${buildingAPI}/' + buildingId + '/priority',
            type: 'POST',
            success: function (res) {
                window.location.href = "${searchURL}";
            },
            error: function (res) {
                console.log(res);
            }
        });
    }
    $("#btnDelete").click(function() {
        var data = {};
        var ids = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['ids'] = ids;
        var result = confirm("Want to Delete");
        if(result){
            deleteBuilding(data);
        }
    });
    function deleteBuilding(data) {
        $.ajax({
            url: '${buildingAPI}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href="${searchURL}";
            },
            error: function (error) {
                console.log("failed");
            }
        });
    }

    function assignmentBuilding(id){
        openAssignmentBuilding();
        loadStaff(id);
    }

    function loadStaff(id) {
        $.ajax({
            type: "GET",
            url: "${assignmentAPI}"+id+"/staffs",
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
                    row +=              '<input type="checkbox" aria-label="Checkbox for following text input" value='+ item.id +' '+ item.check +'>';
                    row +=          '</div>';
                    row +=      '</div>';
                    row += '    <input class="form-control bg-white" value="'+ item.fullName +'" disabled>';
                    row += '    <input type="hidden" class="form-control bg-white" id="buildingId" value="'+id+'" disabled>';
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
        var buildingId = $('#staffList #buildingId').val();
        var data = {};
        var ids = $('#staffList input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffIds'] = ids;
        data['buildingId'] = buildingId;
        saveAssignment(data);
    });
    function saveAssignment(data) {
        $.ajax({
            url: '${assignmentAPI}building',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href="${searchURL}";
            },
            error: function (error) {
                console.log("failed");
            }
        });
    }
    function openAssignmentBuilding(){
        $("#assignmentBuildingModal").modal();
    }

    $('#btnSearch').click(function(e){
        e.preventDefault();
        $('#listForm').submit();
    });
</script>
</body>
</html>