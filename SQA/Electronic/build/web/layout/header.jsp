<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Humberger Begin -->
<div class="humberger__menu__overlay"></div>
<div class="humberger__menu__wrapper">
    <div class="humberger__menu__logo">
        <a href="#"><img src="img/logo.png" alt=""></a>
    </div>

    <nav class="humberger__menu__nav mobile-menu">
        <ul>
            <li class="active"><a href="Home">Home</a></li>
            <li><a href="">Shop</a></li>
            <li><a href="">Blog</a></li>
            <li><a href="">Cart</a></li>
        </ul>
    </nav>
    <div id="mobile-menu-wrap"></div>
    <div class="header__top__right__social">
        <a href="#"><i class="fa fa-facebook"></i></a>
        <a href="#"><i class="fa fa-twitter"></i></a>
        <a href="#"><i class="fa fa-linkedin"></i></a>
        <a href="#"><i class="fa fa-pinterest-p"></i></a>
    </div>
    <div class="humberger__menu__contact">
        <ul>
            <li>Free Shipping for all Order</li>
        </ul>
    </div>
</div>
<!-- Humberger End -->

<!-- Header Section Begin -->
<header class="header">
    <div class="container">
        <div class="row">
            <div class="col-lg-2">
                <div class="header__logo">
                    <a href="./index.html"><img src="img/logo.png" alt=""></a>
                </div>
            </div>
            <div class="col-lg-7">
                <nav class="header__menu">
                    <c:choose>
                        <c:when test="${sessionScope.position=='manager'}">
                            <ul>
                                <li class="active"><a href="Home">Home</a></li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Report
                                    </a>
                                    <div class="dropdown-menu p-4" aria-labelledby="navbarDropdown">
                                        <a class="dropdown-item" href="Productstat">Product Statistic</a>
                                        <a class="dropdown-item" href="Employeestat">Employee Statistic</a>
                                    </div>
                                </li>

                            </ul>
                        </c:when>  
                        <c:when test="${sessionScope.position=='inventory staff'}">
                            <ul>
                                <li class="active"><a href="Home">Home</a></li>
                                <li><a href="">Storage</a></li>
                                <li><a href="Order">Order</a></li>
                            </ul>
                        </c:when> 
                        <c:otherwise>
                            <ul>
                                <li class="active"><a href="Home">Home</a></li>
                                <li><a href="Home">Shop</a></li>
                                <li><a href="Home">Contact</a></li>    
                                <li><a href="Home">Cart</a></li> 
                                <li><a href="Home">Blog</a></li> 
                            </ul>
                        </c:otherwise>
                    </c:choose>

                </nav>
            </div>
            <div class="col-lg-3">
                <div class="account">
                    <div class="account_icon iconify" data-icon="mdi:account-circle-outline">
                    </div>
                    <div class="hero__search__phone__text">
                        <c:choose>
                            <c:when test="${sessionScope.name != null}">
                                <h5>${sessionScope.name}</h5>
                                <span style="text-transform: capitalize">${sessionScope.position}</span>
                            </c:when>
                            <c:otherwise>
                                <h5><a style="color: #5C4038 !important;"  href="Login">Sign In</a></h5>

                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
        <div class="humberger__open">
            <i class="fa fa-bars"></i>
        </div>
    </div>
</header>
<c:if test="${sessionScope.name != null}">
    <a href="Logout"><span class="iconify logout" data-icon="fa:power-off" data-inline="false"></span></a>
    </c:if>
<!-- Header Section End -->
