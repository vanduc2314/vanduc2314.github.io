<%-- 
    Document   : orderlisthome
    Created on : Nov 9, 2021, 10:54:31 AM
    Author     : pvand
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:choose>
    <c:when test="${sessionScope.position=='inventory staff'}">
        <!DOCTYPE html>
        <head>
            <meta charset="UTF-8">
            <meta name="description" content="Ogani Template">
            <meta name="keywords" content="Ogani, unica, creative, html">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <meta http-equiv="X-UA-Compatible" content="ie=edge">
            <title>Electronic Store</title>

            <!-- Google Font -->
            <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;800&display=swap" rel="stylesheet">

            <!-- Css Styles -->
            <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
            <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
            <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
            <link rel="stylesheet" href="css/nice-select.css" type="text/css">
            <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
            <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
            <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
            <link rel="stylesheet" href="css/style.css" type="text/css">
            <link rel="stylesheet" href="css/orderlist.css" type="text/css">
        </head>

        <body>

            <%@include file="/layout/header.jsp" %>
            <div class="container">
                <div class="row my-4">
                    <div class="col-10">
                        <input type="text" placeholder="Search order by customer name" class="input-search">

                    </div>
                    <div class="col-2">
                        <button type="submit" class="search-order-btn">
                            <span class="iconify search_icon" data-icon="ri:search-line">
                            </span>
                        </button>
                    </div>
                </div>
                <div class="row pl-3">
                    <div class="filter-order mr-3"> <span class="iconify mr-1" data-icon="mdi:filter"
                                                          data-inline="false"></span>Filter</div>
                    <select class="mr-3 select-filter">
                        <option value="" selected disabled hidden>Order Status</option>
                        <option value="">Trading</option>
                        <option value="">Order</option>
                        <option value="">Delivered</option>
                        <option value="">Complete</option>
                    </select>

                    <select class="select-filter">
                        <option value="" selected disabled hidden>Creation Date</option>
                        <option value="">Newest</option>
                        <option value="">Oldest</option>

                    </select>
                </div>

            </div>
            <div class="container">
                <div class="row mt-4">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">Code Order</th>
                                <th scope="col">Creation Date</th>
                                <th scope="col">Customer Name</th>
                                <th scope="col">Order Status</th>
                                <th scope="col">Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listb}" var="i">
                                <tr>
                                    <th scope="row"> <a class="link-page" style="color: #7D5A50 !important;" href="DetailOrder?pid=${i.id}">MM00${i.id}</a></th>
                                    <td>${i.cdate}</td>
                                    <td>${i.c.getName()}</td>
                                    <td style="  font-weight: 500;text-transform: capitalize;<c:choose>
                                            <c:when test="${i.status=='trading'}">
                                                color:#9C5400
                                            </c:when>
                                            <c:when test="${i.status=='delivering'}">
                                                color:#E86136
                                            </c:when>
                                            <c:when test="${i.status=='complete'}">
                                                color:#387500
                                            </c:when>
                                            <c:when test="${i.status=='cancel'}">
                                               color:#9E9590
                                            </c:when>
                                            <c:otherwise>
                                                 color:#004b75
                                            </c:otherwise>
                                        </c:choose>">${i.status}</td>
                                    <td>${Math.round(i.amount*100)/100}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>



            </div>
            <!-- Js Plugins -->
            <script src="js/jquery-3.3.1.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/jquery.nice-select.min.js"></script>
            <script src="js/jquery-ui.min.js"></script>
            <script src="js/jquery.slicknav.js"></script>
            <script src="js/mixitup.min.js"></script>
            <script src="js/owl.carousel.min.js"></script>
            <script src="js/main.js"></script>
            <script src="https://code.iconify.design/1/1.0.7/iconify.min.js"></script>


        </body>

    </c:when>
    <c:otherwise>

        <h1>Access Denied</h1>
    </c:otherwise>
</c:choose>

