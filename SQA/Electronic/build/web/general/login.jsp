<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="css/login.css">
        <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400&display=swap" rel="stylesheet">
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
              integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <div class="col-md-5 mx-auto">
                <div class="form">
                    <div class="logo mb-3">
                        <div class="col-md-12 text-center">
                            <h1><b>Sign In</b></h1>
                        </div>
                    </div>
                    <c:if test="${not empty message}">
                        <div class="alert alert-danger" role="alert">
                            ${message}
                        </div>
                    </c:if>
                    <form method="post" name="login">
                        <div class="form-group">
                            <input type="text" class="form-control" id="formGroupExampleInput"
                                   placeholder="Username" name="username">
                        </div>
                        <div class="form-group">

                            <input type="password" name="password" id="password"
                                   class="form-control" aria-describedby="emailHelp"
                                   placeholder="Password">
                        </div>

                        <button type="submit" class="btn btn-primary loginbtn">Sign
                            In</button>

                        <div class="text-center text_dont ">
                            Don't have an account
                        </div>

                    </form>
                    <a href="Registration"><button class=" btn btn-primary cabtn">Create
                            Account</button>  </a>
                </div>
            </div>
        </div>

    </body>

</html>