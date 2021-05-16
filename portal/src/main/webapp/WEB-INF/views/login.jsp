<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="/fonts/icomoon/style.css">

    <link rel="stylesheet" href="/css/owl.carousel.min.css">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    
    <!-- Style -->
    <link rel="stylesheet" href="/css/style.css">

    <title>Login</title>
    <style>
      .error{
        color : rgb(204, 24, 60) !important;
      }
    </style>
  </head>
  <body>
  

  
  <div class="content">
    <div class="container">
      <div class="row">
        <div class="col-md-6">
          <img src="/images/undraw_remotely_2j6y.svg" alt="Image" class="img-fluid">
        </div>
        <div class="col-md-6 contents">
          <div class="row justify-content-center">
            <div class="col-md-8">
              <div class="mb-4">
              <h3>Sign In</h3>
              <p class="mb-4"></p>
            </div>
			<div class="text-center d-flex justify-content-between mt-4 error">
				<span>${errormsg}
        </span>
			</div>
			<form:errors path="usercredentials.*" />

            <form action="/user/homepage" modelAttribute="usercredentials" method="post">
              <div class="form-group first">
                <label for="username">Username</label>
                <input type="text" name="userid" class="form-control" minlength="4" maxlength="8" id="username">

              </div>
              <div class="form-group last mb-4">
                <label for="password">Password</label>
                <input type="password" name="password" class="form-control" minlength="4" maxlength="8" id="password">
                
              </div>
              
              <input type="submit" value="Log In" class="btn btn-block btn-primary">

              
            </form>
            </div>
          </div>
          
        </div>
        
      </div>
    </div>
  </div>

  
    <script src="/js/jquery-3.3.1.min.js"></script>
    <script src="/js/popper.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/main.js"></script>
  </body>
</html>