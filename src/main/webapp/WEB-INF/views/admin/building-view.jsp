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
                        <td>Product's name</td>
                        <td>${building.name}</td>
                    </tr>
                    <tr>
                        <td>In-charge Staff</td>
                        <td>${building.staffString}</td>
                    </tr>
                    <tr>
                        <td>District</td>
                        <td>${building.district}</td>
                    </tr>
                    <tr>
                        <td>Ward</td>
                        <td>${building.ward}</td>
                    </tr>
                    <tr>
                        <td>Street</td>
                        <td>${building.street}</td>
                    </tr>
                    <tr>
                        <td>Structure</td>
                        <td>${building.structure}</td>
                    </tr>
                    <tr>
                        <td>Number Of Basement</td>
                        <td>${building.numberOfBasement}</td>
                    </tr>
                    <tr>
                        <td>Floor Area</td>
                        <td>${building.floorArea}</td>
                    </tr>
                    <tr>
                        <td>Direction</td>
                        <td>${building.direction}</td>
                    </tr>
                    <tr>
                        <td>Rank</td>
                        <td>${building.rank}</td>
                    </tr>
                    <tr>
                        <td>Area Description</td>
                        <td>${building.rentAreaDescription}</td>
                    </tr>
                    <tr>
                        <td>Rental Price</td>
                        <td>${building.rentCost}</td>
                    </tr>
                    <tr>
                        <td>Service Fee</td>
                        <td>${building.serviceCost}</td>
                    </tr>
                    <tr>
                        <td>Car fees</td>
                        <td>${building.carCost}</td>
                    </tr>
                    <tr>
                        <td>Moto Fees</td>
                        <td>${building.motorCost}</td>
                    </tr>
                    <tr>
                        <td>Electric Bill</td>
                        <td>${building.electricBill}</td>
                    </tr>
                    <tr>
                        <td>Deposit</td>
                        <td>${building.deposit}</td>
                    </tr>
                    <tr>
                        <td>Payment Method</td>
                        <td>${building.payment}</td>
                    </tr>
                    <tr>
                        <td>Rental Period</td>
                        <td>${building.timeRent}</td>
                    </tr>
                    <tr>
                        <td>Decoration Time</td>
                        <td>${building.timeDecoration}</td>
                    </tr>
                    <tr>
                        <td>Manager's Name</td>
                        <td>${building.managerName}</td>
                    </tr>
                    <tr>
                        <td>Manager's Phone</td>
                        <td>${building.managerPhone}</td>
                    </tr>
<%--                        <td>BuildingTypes</td>--%>
<%--                        <td><div class="row">--%>
<%--                            <div class="form-check buildingTypeForm">--%>
<%--                                <form:checkboxes path="buildingTypes" items="${buildingTypeMaps}" cssClass="form-check-input" />--%>
<%--                            </div>--%>
<%--                        </div></td>--%>
<%--                        <td>Rental Area</td>--%>
<%--                        <td>${building.rentArea}</td>--%>
                </tbody>
            </table>
            <!-- table-end -->
        </div>
        <!-- image -->
        <c:if test="${not empty building.image}">
            <div class="col-4 pr-0 pl-5">
                <img class="img-thumbnail rounded mx-auto  img-product" src="<c:url value="/admin/assets/images/product/${building.image}"/>">
            </div>
        </c:if>
        <!-- image-end -->
    </div>
</div>
<!-- content-end -->


<script>
</script>
</body>
</html>
