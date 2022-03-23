
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<c:choose>
    <c:when test="${sessionScope.position=='inventory staff'}">
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
            <link rel="stylesheet" href="cfgdss/font-awesome.min.css" type="text/css">
            <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
            <link rel="stylesheet" href="css/nice-select.css" type="text/css">
            <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
            <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
            <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
            <link rel="stylesheet" href="css/style.css" type="text/css">
            <link rel="stylesheet" href="css/orderdetail.css" type="text/css">
            <link rel="stylesheet" type="text/css" href="css/print.min.css">
        </head>

        <body>
            <%@include file="/layout/header.jsp" %>
            <!-- Header Section End -->
            <c:if test="${bill.getStatus()=='trading'}">
                <div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content bg-sec">
                            <div class="modal-header">
                                <h4> Shipper List</h4>
                            </div>
                            <div class="modal-body">
                                <div class="container">
                                    <div class="row my-4">
                                        <div class="col-10">
                                            <input type="text" placeholder="Search shipper's name"
                                                   class="input-search">

                                        </div>
                                        <div class="col-2">
                                            <button type="submit" class="search-order-btn">
                                                <span class="iconify search_icon"
                                                      data-icon="ri:search-line">
                                                </span>
                                            </button>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <table class="table table-bordered">
                                            <thead>
                                                <tr>
                                                    <th scope="col">#</th>
                                                    <th scope="col">EC</th>
                                                    <th scope="col">Shipper's name</th>
                                                    <th scope="col">Phone number</th>
                                                    <th scope="col"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${slist}" var="s" varStatus="l">
                                                    <tr>

                                                        <th scope="row">${l.index+1}</th>
                                                        <td>${s.getId()}</td>

                                                        <td>${s.getName()}</td>
                                                        <td>${s.getPhone()}</td>
                                                        <td><button name="${s.getId()}"
                                                                    class="btn select-shipper-btn">Select</button>
                                                        </td>
                                                    </tr>  
                                                </c:forEach>


                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> 
            </c:if>
            <div class="container" id ="printJS-form">
                <div class="container">
                    <div class="row">
                        <div class="col-9">
                            <div class="row my-4">
                                <h2 class="col-3 order-name mr-3">
                                    MM00${bill.getId()}
                                </h2>
                                <div class="col">
                                    <h4 class="row order-status"
                                        <c:choose>
                                            <c:when test="${bill.getStatus()=='trading'}">
                                                style="color:#9C5400"
                                            </c:when>
                                            <c:when test="${bill.getStatus()=='delivering'}">
                                                style="color:#E86137"
                                            </c:when>
                                            <c:when test="${bill.getStatus()=='complete'}">
                                                style="color:#387500"
                                            </c:when>
                                            <c:when test="${bill.getStatus()=='cancel'}">
                                                style="color:#9E9590"
                                            </c:when>
                                        </c:choose>

                                        >${bill.getStatus()}</h4>
                                    <h5 class="row order-date">${bill.getCdate()}</h5>
                                </div>

                            </div>

                        </div>
                        <div class="col my-4">
                            <c:choose>
                                <c:when test="${bill.getStatus()=='trading'}">
                                    <button class="btn status-btn" data-toggle="modal"
                                            data-target=".bd-example-modal-lg">Select Shipper</button>
                                </c:when>
                                <c:when test="${bill.getStatus()=='delivering' && bill.getDinv().get(0).getStatus()=='assigned'}">
                                    <button class="btn status-btn print-iv">Print Invoice</button>
                                </c:when>
                                <c:when test="${bill.getStatus()=='delivering' && bill.getDinv().get(0).getStatus()=='delivering'}">
                                    <button class="btn status-btn delivered">Delivered</button>
                                </c:when>
                                <c:when test="${bill.getStatus()=='complete'}">
                                    <button class="btn status-btn" disabled>Complete</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn status-btn" id="approve">Approve</button>
                                </c:otherwise>
                            </c:choose>


                        </div>

                    </div>

                </div>


                <c:if test="${bill.getDinv().get(0).getS().getName()!=null}">
                    <div class="container bg-sec mb-4">
                        <div class="row pl-4 pt-4">
                            <div class="col-1">
                                <p>#DE00${bill.getDinv().get(0).getId()}</p>
                            </div>
                            <div class="col">
                                <small
                                    style="color: #FCDEC0; border-radius:12px; padding: 4px 10px; text-transform: capitalize; <c:choose>
                                        <c:when test="${bill.getDinv().get(0).getStatus()=='delivering'}">
                                            background-color:#E86137;
                                        </c:when>
                                        <c:when test="${bill.getDinv().get(0).getStatus()=='delivered'}">
                                            background-color:#E86137;
                                        </c:when>
                                        <c:otherwise>
                                            background-color:#387500;
                                        </c:otherwise>
                                    </c:choose> ">
                                    ${bill.getDinv().get(0).getStatus()}</small>
                            </div>
                        </div>

                        <div class="row pl-4 pb-4">
                            <div class="col-9">
                                <h4>Delivery by: ${bill.getDinv().get(0).getS().getName()} - ${bill.getDinv().get(0).getS().getPhone()}</h4>
                            </div>
                        </div>
                    </div>

                </c:if>
                <div class="container">
                    <div class="row mb-4 justify-content-between">
                        <div class="col-7 customer-sec bg-sec">
                            <h4 class="mb-2">Customer information</h4>
                            <div class="row">
                                <div class="col-1">
                                    <img src="" alt="">
                                </div>
                                <div class="col">
                                    <p class="customer-name">${bill.getC().getName()}</p>
                                    <p class="customer-phone">${bill.getC().getPhone()}</p>
                                </div>
                            </div>
                            <div class="line mb-3"></div>
                            <div class="deli-infor">
                                <h5 class="mb-2">Delivery Information</h5>

                                <div class="pl-4">
                                    <p>${bill.getDinv().get(0).getName()}</p>
                                    <p>${bill.getDinv().get(0).getPhone()}</p>
                                    <p>${bill.getDinv().get(0).getAddress()}</p>
                                </div>  




                            </div>
                        </div>

                        <div class="col-4 note-sec bg-sec">
                            <h3>Note</h3>
                            <p class="note-content pt-3 pl-4">${bill.getNote()}</p>
                            <button class="btn add-note">Add note</button>

                        </div>
                    </div>

                </div>
                <div class="container bg-sec mb-4">
                    <div class="row p-4">
                        <h3>List of products</h3>
                    </div>
                    <div class="row">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">SKU</th>
                                    <th scope="col">Product's name</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Unit Price</th>
                                    <th scope="col">Sale Price</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${bill.getBproduct()}" var="i" varStatus="loop">
                                    <tr>
                                        <th scope="row">${loop.index+1}</th>
                                        <td >${i.getP().getId()}</td>
                                        <td>${i.getP().getName()}</td>
                                        <td>${i.getQuantity()}</td>
                                        <td>$${i.getP().getPrice()}</td>
                                        <td>$${i.getPrice()}</td>

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="row pb-4">
                        <div class="col-8"></div>
                        <div class="col money-cal">
                            <div class="row">
                                <div class="col-8">
                                    <div>Subtotal:</div>
                                </div>
                                <div class="col-4">
                                    <div>${Math.round(bill.getAmount()*100)/100}</div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-8">
                                    <div>Shipping fee</div>
                                </div>
                                <div class="col-4">
                                    <div>${bill.getDinv().get(0).getFee()}</div>

                                </div>
                            </div>
                            <div class="row">
                                <div class="col-8">
                                    <div>Discount</div>
                                </div>
                                <div class="col-4">
                                    <div>${bill.getDiscount()}</div>
                                </div>
                            </div>
                            <div class="row total">
                                <div class="col-8">
                                    <div>Total</div>
                                </div>
                                <div class="col-4">
                                    <div>${bill.getAmount()+bill.getDinv().get(0).getFee()-bill.getDiscount()}</div>


                                </div>
                            </div>


                        </div>
                    </div>
                </div>
                <div class="container bg-sec mb-4">
                    <div class="row pay-method">
                        <div class="col-10 payment"><h4>Payment Method</h4></div>
                        <div class="col-1"><select name="" id=""<c:if test="${bill.getPaymentype()!=null}">
                                                   disabled
                                </c:if> >
                                <option value="COD" <c:if test="${bill.getPaymentype()=='COD'}">
                                        selected="selected"
                                    </c:if>>COD</option>
                                <option value="Cash"<c:if test="${bill.getPaymentype()=='Cash'}">
                                        selected="selected"
                                    </c:if>>Cash</option>
                                <option value="Banking" <c:if test="${bill.getPaymentype()=='Banking'}">
                                        selected="selected"
                                    </c:if>>Banking</option>
                            </select></div>
                    </div>
                </div>
            </div>
            <!-- Header Section End -->



            <!-- Js Plugins -->

            <script src="js/jquery-3.3.1.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/jquery.nice-select.min.js"></script>
            <script src="js/jquery-ui.min.js"></script>
            <script src="js/jquery.slicknav.js"></script>
            <script src="js/mixitup.min.js"></script>
            <script src="js/owl.carousel.min.js"></script>
            <script src="js/print.min.js"></script>
            <script src="js/main.js"></script>

            <script src="https://code.iconify.design/1/1.0.7/iconify.min.js"></script>
            <script>
                $(document).ready(function () {
                    $(document).on("click", "#approve", function (e) {
                        $.ajax({
                            url: "DetailOrder",
                            method: "post",
                            data: {pid: '${param.pid}',
                                bstatus: 'trading',
                                button: 'approve'},
                            success: function (response) {
                                location.reload(true)
                            }
                        });
                    }
                    );
                    $(document).on("click", ".select-shipper-btn", function (e) {
                        var c_id = $(this).attr("name");
                        $.ajax({
                            url: "DetailOrder",
                            method: "post",
                            data: {pid: '${param.pid}',
                                did: '${bill.getDinv().get(0).getId()}',
                                shipper: c_id,
                                dstatus: 'assigned',
                                bstatus: 'delivering',
                            button:'select'},
                            success: function (response) {
                                location.reload(true)
                            }
                        });
                    }
                    );
                    $(document).on("click", ".print-iv", function (e) {
                        printJS({printable: 'printJS-form',
                            type: 'html',
                            onPrintDialogClose: function () {
                                $.ajax({
                                    url: "DetailOrder",
                                    method: "post",
                                    data: {pid: '${param.pid}',
                                        did: '${bill.getDinv().get(0).getId()}',
                                        dstatus: 'delivering',
                                        bstatus: 'delivering',
                                        button:'print'},
                                    success: function (response) {
                                        location.reload(true)
                                    }
                                });
                            }});

                    }
                    );
                    $(document).on("click", ".delivered", function (e) {
                        $.ajax({
                            url: "DetailOrder",
                            method: "post",
                            data: {pid: '${param.pid}',
                                did: '${bill.getDinv().get(0).getId()}',
                                dstatus: 'delivered',
                                bstatus: 'complete',
                                button:'complete'},
                            success: function (response) {
                                location.reload(true)
                            }
                        });
                    }
                    );
                }
                );
            </script>

        </body>

    </html>
</c:when>
<c:otherwise>

    <h1>Access Denied</h1>
</c:otherwise>
</c:choose>
