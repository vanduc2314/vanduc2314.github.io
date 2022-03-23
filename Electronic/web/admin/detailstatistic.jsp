<%-- 
    Document   : orderlisthome
    Created on : Nov 9, 2021, 10:54:31 AM
    Author     : pvand
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:choose>
    <c:when test="${sessionScope.position=='manager'}">
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
            <div class="container my-4">
                <h2><%= request.getParameter("name")%></h2>
                <p class="mt-3"><%= request.getParameter("sdate")%> -> <%= request.getParameter("edate")%></p>
            </div>       
            <div class="container mt-4 bg-sec">
                <div id="chart_div"></div>
            </div>   

            <div class="container mt-4">

                <div class="row pl-3">
                    <div class="filter-order mr-3"> Sort</div>
                    <select class="mr-3 select-filter">
                        <option value="" selected disabled hidden>Quantity</option>
                        <option value="">Low to High </option>
                        <option value="">High to Low</option>

                    </select>

                    <select class="select-filter">
                        <option value="" selected disabled hidden>Revenue</option>
                        <option value="">Low to High </option>
                        <option value="">High to Low</option>

                    </select>
                </div>

            </div>
            <div class="container">
                <div class="row mt-4">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Create Date</th>
                                <th scope="col">Order Code</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Sale Price</th>
                                <th scope="col">Amount</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="amount" value="${0}"/>
                            <c:forEach items="${bill}" var="s" varStatus="l">
                                <tr>

                                    <th scope="row">${l.index+1}</th>
                                    <td>${s.getCdate()}</td>

                                    <td>${s.getId()}</td>
                                    <td>${s.getBproduct().get(0).getQuantity()}</td>
                                    <td>$${s.getBproduct().get(0).getPrice()}</td>
                                    <c:set var="amount" value="${amount+ s.getBproduct().get(0).getPrice() * s.getBproduct().get(0).getQuantity()}"/>
                                    <td>$${s.getBproduct().get(0).getPrice() * s.getBproduct().get(0).getQuantity()}</td>
                                </tr>  
                            </c:forEach>
                            <c:set var="total" value="${0}"/>
                            <c:set var="sale" value="${0}"/>
                            <c:forEach items="${bill}" var="s">
                                <c:set var="total" value="${total +s.getBproduct().get(0).getQuantity()}"/>
                                <c:set var="sale" value="${sale +s.getBproduct().get(0).getPrice()}"/>
                            </c:forEach>
                            <tr class="total-sec">
                                <th style="border: none"></th>
                                <th style="border: none"></th>
                                <td>Total</td>
                                <td>${total}</td>
                                <td>$${sale}</td>
                                <td>$${Math.round(amount*100)/100}</td>
                            </tr>  
                        </tbody>

                    </table>
                </div>



            </div>
            <!-- Js Plugins -->
            <script src="js/jquery-3.3.1.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
            <script src="js/bootstrap.min.js"></script>

            <script src="js/jquery.nice-select.min.js"></script>
            <script src="js/jquery-ui.min.js"></script>
            <script src="js/jquery.slicknav.js"></script>
            <script src="js/mixitup.min.js"></script>
            <script src="js/owl.carousel.min.js"></script>
            <script src="js/main.js"></script>
            <script src="https://code.iconify.design/1/1.0.7/iconify.min.js"></script>
            <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
            <script>
                google.charts.load('current', {packages: ['corechart', 'bar']});
                google.charts.setOnLoadCallback(drawBasic);

                function drawBasic() {

                    var data = new google.visualization.arrayToDataTable([
                    ['Product', '$', {role: 'style'}],
                <c:forEach items="${bill}" var="s" varStatus="l">
                    ['${s.getId()}',${s.getBproduct().get(0).getPrice() * s.getBproduct().get(0).getQuantity()}, 'color: #E86137'],
                </c:forEach>

                    ]);
                    var options = {
                        
                        hAxis: {
                            title: "Order Code",
                        },
                        vAxis: {
                            title: 'Revenue'
                        },
                        backgroundColor: '#E5B299',
                        fontSize: '18',
                        fontName: 'Poppins',
                        legend: 'none'

                    };

                    var chart = new google.visualization.ColumnChart(
                            document.getElementById('chart_div'));

                    chart.draw(data, options);
                }
            </script>
        </body>

    </c:when>
    <c:otherwise>

        <h1>Access Denied</h1>
    </c:otherwise>
</c:choose>

