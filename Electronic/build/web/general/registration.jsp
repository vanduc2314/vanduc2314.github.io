<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
    </head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="css/regis.css">
    <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400&display=swap" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

    <body>
        <div class="container">
            <c:if test="${not empty message}">
                <div class="alert alert-danger" role="alert">
                    ${message}
                </div>
            </c:if>
            <div class="col-md-8 mx-auto">
                <div class="form">
                    <div class="logo mb-3">
                        <div class="col-md-12 text-center">
                            <h1><b>Create Account</b></h1>
                        </div>
                    </div>
                    <form method="post" name="registration">
                        <div class="form-row">
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control" id="inputName" name="name"
                                       placeholder="Full Name">
                            </div>
                            <div class="form-group col-md-7">
                                <input type="email" class="form-control" id="inputEmail4" name="email"
                                       placeholder="Email">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-7">
                                <input type="text" class="form-control" id="inputAddress" name="address"
                                       placeholder="Address">
                            </div>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control" id="inputTel" name="tel"
                                       placeholder="Tel">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md">
                                <input type="text" class="form-control" id="inputUsername" name="username"
                                       placeholder="Username">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <input type="password" class="form-control" id="inputPassword4" name="password"
                                       placeholder="Password">
                            </div>
                            <div class="form-group col-md-6">
                                <input type="password" class="form-control" id="inputPassword4" name="cpass"
                                       placeholder="Comfirm Password">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-5">
                                <button type="submit" class="btn btn-primary loginbtn">Create
                                    Account</button>
                            </div>
                            <div class="form-group col-md-7" style="color:#B4846C ;">
                                By creating an account, you understand and agree to <b>our's Term
                                    of
                                    Service</b>, including the <b>Agreement</b>  and<b> Privacy Policy</b> 
                            </div>
                        </div>

                        <div style="color:#B4846C" class="form-group col-md">
                            Already have an account?<a style="color:#E86137" href="Login"> Login</a>
                        </div>




                    </form>

                </div>
            </div>
        </div>

    </body>

</html>