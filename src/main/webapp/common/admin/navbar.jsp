<%@ page import="com.laptrinhjavaweb.security.utils.SecurityUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="URLBuildingPriorities" value="/admin/priority/list"/>
<c:url var="customerListURL" value="/admin/customer-list"/>
<c:url var="userListURL" value="/admin/user/list"/>
<c:url var="buildingListURL" value="/admin/building-list"/>
<c:url var="buildingAssignmentURL" value="/admin/building-assignment"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- Navbar start -->
<div class="navbar navbar-expand-lg navbar-light bg-light">
    <li class="navbar-brand" style="color: white;">
        <i class="fa fa-leaf"></i> <span>Admin</span>
    </li>
    <button class="navbar-toggler mr-2" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <i class="fa fa-bars" aria-hidden="true"></i>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown" id="#chevron-nav">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa fa-database" aria-hidden="true"></i>Product
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown1">
                    <security:authorize access="hasRole('MANAGER')">
                        <a class="dropdown-item"  href="${buildingListURL}">Product List</a>
                    </security:authorize>
                    <security:authorize access="hasRole('STAFF')">
                        <a class="dropdown-item"  href="${buildingAssignmentURL}">Product List</a>
                    </security:authorize>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa fa-user" aria-hidden="true"></i>Customer
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown2">
                    <a class="dropdown-item"  href="${customerListURL}">Customer List</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${URLBuildingPriorities}"><i class="fa fa-list-alt" aria-hidden="true"></i>My List</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa fa-users" aria-hidden="true"></i>Account
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown3">
                    <a class="dropdown-item"  href="${userListURL}">Account List</a>
                </div>
            </li>
            <li class="nav-item dropdown log-out-nav">
                <div class="col-12">
                    <a class="dropdown-item" href="#"><i class="ace-icon fa fa-power-off"></i>Logout</a>
                </div>
            </li>
        </ul>
    </div>
    <ul class="navbar-nav user-nav">
        <a class="btn btn-right rounded-0 border-0 mr-3" id="chevron-nav" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <div class="row" style="margin-left: -30px; margin-right: -40px;">
                <div class="col-4 ml-2 mr-0" style="padding-right: 0px;">
                    <img class="rounded-circle img-nav" src="<c:url value="/admin/assets/images/avatars/user.jpg" />" alt="Jason's Photo">
                </div>
                <div class="col-4 px-0 user-info">
							<span>
								<small>WellCome,</small>
								<%=SecurityUtils.getPrincipal().getFullName()%>
							</span>
                </div>
                <div class="col-2">
                    <i class="fa fa-chevron-circle-down" style="font-size: 0.9rem;"></i>
                </div>
            </div>
        </a>
        <div class="dropdown-menu dropdown-menu-right user-menu">
            <a class="dropdown-item" href="#"><i class="ace-icon fa fa-cog"></i>Settings</a>
            <a class="dropdown-item" href="#"><i class="ace-icon fa fa-user"></i>Profile</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#"><i class="ace-icon fa fa-power-off"></i>Logout</a>
        </div>
    </ul>
</div>

<!-- Navbar end -->