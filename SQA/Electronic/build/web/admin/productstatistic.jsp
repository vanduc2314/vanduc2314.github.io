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
                <h2>Statistic Product by Revenue</h2>
            </div>       
            <div class="container">

                <label for="birthday">Start Date:</label>
                <input class="date-input" type="date" id="sdate">

                <label for="birthday">End Date:</label>
                <input class="date-input" type="date"  id="edate">


                <button type="submit" class="sub-date mx-3">
                    Statistic
                </button>
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
                                <th scope="col">SKU</th>
                                <th scope="col">Product's name</th>
                                <th scope="col">Sold Quantity</th>
                                <th scope="col">Amount</th>
                            </tr>
                        </thead>
                        <tbody id="content">
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
                $(document).ready(function () {
                    $(document).on("click", ".sub-date", function (e) {
                        var sdate = $("#sdate").val();
                        console.log(sdate);
                        var edate = $("#edate").val();
                        $.ajax({
                            url: "Productstat",
                            method: "post",
                            data: {sdate: sdate,
                                edate: edate
                            },
                            success: function (response) {
                                $("#content").html(response);
                            }
                        });

                    }
                    );
                }
                );
            </script>
        </body>

    </c:when>
    <c:otherwise>

        <h1>Access Denied</h1>
    </c:otherwise>
</c:choose>

