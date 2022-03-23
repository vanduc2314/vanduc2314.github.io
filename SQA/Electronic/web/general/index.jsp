<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ogani Template">
    <meta name="keywords" content="Ogani, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Electronic Store</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="css/jqufgfgfgery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>

<body>
    <%@include file="/layout/header.jsp" %>
    <!-- Hero Section Begin -->
    <section class="hero">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="hero__categories">
                        <div class="hero__categories__all">
                            <i class="fa fa-bars"></i>
                            <span>All Categories</span>
                        </div>
                        <ul>
                            <li><a href="#">Laptop</a></li>
                            <li><a href="#">Smartphone</a></li>
                            <li><a href="#">Tablet</a></li>
                            <li><a href="#">TV</a></li>
                            <li><a href="#">Accessories</a></li>
                            <li><a href="#">Smart Watch</a></li>
                            <li><a href="#">Speaker</a></li>
                            <li><a href="#">Printer</a></li>
                            <li><a href="#">Camera</a></li>
                            <li><a href="#">Mainboard</a></li>
                            <li><a href="#">Tools</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="hero__search">
                        <div class="hero__search__form">
                            <form action="#">
                                <input type="text" placeholder="What do you need?">
                                <button type="submit" class="site-btn search-btn">
                                    <div class="iconify search_icon" data-icon="ri:search-line"></div>
                                </button>
                            </form>
                        </div>
                    </div>
                    <div class="hero__item set-bg" data-setbg="https://images.unsplash.com/photo-1634403665481-74948d815f03?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1887&q=80">
                        <div class="hero__text">
                            <span>New Release</span>
                            <h2>Apple<br />Iphone 13 Series</h2>
                            <p>Get 3 months Apple Music free</p>
                            <a href="#" class="primary-btn">SHOP NOW</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Hero Section End -->

    <!-- Categories Section Begin -->
    <section class="categories">
        <div class="container">
            <div class="row">
                <div class="categories__slider owl-carousel">
                    <div class="col-lg-3">
                        <div class="categories__item set-bg" data-setbg="img/featured/feature-5.jpg">

                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="categories__item set-bg" data-setbg="img/featured/feature-2.jpg">

                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="categories__item set-bg" data-setbg="img/featured/feature-3.jpg">

                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="categories__item set-bg" data-setbg="img/featured/feature-4.jpg">

                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="categories__item set-bg" data-setbg="img/featured/feature-7.jpg">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Categories Section End -->

    <!-- Featured Section Begin -->
    <section class="featured spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title">
                        <h2>Featured Product</h2>
                    </div>
                    <div class="featured__controls">
                        <ul>
                            <li class="active" data-filter="*">All</li>
                            <li data-filter=".tablet">Tablet</li>
                            <li data-filter=".tool">Tools</li>
                            <li data-filter=".sp">Smartphone</li>
                            <li data-filter=".pc">PC</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="row featured__filter">
                <div class="col-lg-3 col-md-4 col-sm-6 mix tablet fresh-meat">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="img/featured/feature-1.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Ipad Pro 2021</a></h6>
                            <h5>$1000.00</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix tool sp">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="img/featured/feature-2.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Ipad Pro 2021</a></h6>
                            <h5>$1000.00</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix pc tool">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="img/featured/feature-3.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Ipad Pro 2021</a></h6>
                            <h5>$1000.00</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix sp pc">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="img/featured/feature-4.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Ipad Pro 2021</a></h6>
                            <h5>$1000.00</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix fresh-meat vegetables">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="img/featured/feature-5.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Ipad Pro 2021</a></h6>
                            <h5>$1000.00</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix oranges fastfood">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="img/featured/feature-6.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Ipad Pro 2021</a></h6>
                            <h5>$1000.00</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix fresh-meat vegetables">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="img/featured/feature-7.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Ipad Pro 2021</a></h6>
                            <h5>$1000.00</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix fastfood vegetables">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="img/featured/feature-8.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Ipad Pro 2021</a></h6>
                            <h5>$1000.00</h5>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Featured Section End -->

    <!-- Banner Begin -->
    <div class="banner mb-5">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="banner__pic">
                        <img src="img/banner/banner-1.jpg" alt="">
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="banner__pic">
                        <img src="img/banner/banner-2.jpg" alt="">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Banner End -->
    <!-- Footer Section Begin -->
    <%@include file="/layout/footer.jsp" %>
    <!-- Footer Section End -->

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

</html>