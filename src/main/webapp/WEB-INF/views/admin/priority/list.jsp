
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="myList" value="/admin/priority/list"/>
<c:url var="buildingAPI" value="/api/building"/>
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
    <div class="row sub-btn">
        <div class="col-12">
            <button type="btnDelete" class="btn btn-outline-danger" data-toggle="tooltip" title='Delete' id="btnDelete">
                <i class="fa fa-trash" aria-hidden="true"></i>
            </button>
        </div>
    </div>
    <!-- Table-->
    <div class="mx-auto">
        <div class="widget-table mb-0">
            <table class="table table-hover table-border text-center">
                <thead>
                <tr>
                    <th scope="col"><input type="checkbox" id="check-all"></th>
                    <th scope="col">Product's name</th>
                    <th scope="col">Manager's name</th>
                    <th scope="col">Street</th>
                    <th scope="col">Service cost</th>
                    <th scope="col">Floor Area</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${buildingResponse.buildings}" >
                        <tr >
                            <th class="check-box"><input type="checkbox" value="${item.id}"></th>
                            <td>${item.name}</td>
                            <td>${item.managerName}</td>
                            <td>${item.street}</td>
                            <td>${item.serviceCost}$</td>
                            <td>${item.floorArea}</td>
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
            </nav>
        </div>
        <form:form id="listForm" method="get" commandName="model">
            <input type="hidden" id="maxItems" name="maxItems" value="">
            <input type="hidden" id="page" name="page" value="">
        </form:form>
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
                            $('#maxItems').val(limit);
                            $('#page').val(page);
                            $('#listForm').submit();
                        }
                    }
                }).on('page', function (event, page) {
                    console.info(page + ' (from event listening)');
                });
            });

            $("#btnDelete").click(function() {
                var dataArrays = $('tbody input[type=checkbox]:checked').map(function () {
                    return $(this).val();
                }).get();
                var result = confirm("Want to Delete?");
                if(result){
                    deleteBuilding(dataArrays);
                }
            });
            function deleteBuilding(dataArrays) {
                $.ajax({
                    url: '${buildingAPI}/mylist',
                    type: 'DELETE',
                    contentType: 'application/json',
                    data: JSON.stringify(dataArrays),
                    success: function (result) {
                        window.location.href="${myList}";
                    },
                    error: function (error) {
                        console.log("failed");
                    }
                });
            }
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
        </script>
</body>
</html>
