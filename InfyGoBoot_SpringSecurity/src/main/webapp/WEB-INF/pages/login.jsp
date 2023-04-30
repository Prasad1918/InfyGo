
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>InfyGo Reservation System</title>

	<link rel="stylesheet" href="<c:url value='css/newstyle.css'/>"
	type="text/css" media="all">
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" 
	rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value='css/custom.css'/>"
	type="text/css" media="all">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<!-- HEADER -->
	<header id="header">


		<nav class="navbar st-navbar navbar-fixed-top">

			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#st-navbar-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="logo1" href="${pageContext.servletContext.contextPath}"><img
						src="<c:url value='images/infygonew.PNG'/>" alt=""></a>
				</div>
				</div>
				</nav>
	</header>
	<!-- /HEADER -->

 
	<!-- Login start -->
	
	
	
		<section id="slider" class="margintop-10">
		<div id="home-carousel" class="carousel slide" data-ride="carousel">
			<div class="carousel-inner">
			
			
			
				<div class="item active" style="background-image: url(<c:url value='imgs/382266112.jpg'/>)">
					<br><br>
					<div class="container">
						<div class="col-sm-6">
								<p class="head " style="color:white">Connecting flight</p>
								<p class="carousel-content  " style="color:white">Taking more than one flight to
									get to your final destination? At InfyGo, we make connecting
									flights much easier!</p>
						</div>
					</div>					
				</div> 
				<div class="item" style="background-image: url(<c:url value='imgs/586266182.jpg'/>)">
					<br><br>
					<div class="container">
						<div class="col-sm-6">
								<p class="head " style="color:white">InfyGo Meals onboard</p>
								<p class="carousel-content " style="color:white">Tickle your taste buds and enjoy
									sumptuous meals onboard!</p>
						</div>
					</div>					
				</div> 
			
			<div class="item" style="background-image: url(<c:url value='imgs/346909778.jpg'/>)">
					<br><br>
					<div class="container">
						<div class="col-sm-6">
								<p class="head " style="color:white">Hospitality</p>
								<p class="carousel-content " style="color:white">More convenience and comfort
									with our hospitality and services!</p>
						</div>
					</div>					
				</div> 
				

			</div>
			<ol class="carousel-indicators">
				<li data-target="#home-carousel" data-slide-to="0" class="active"></li>
				<li data-target="#home-carousel" data-slide-to="1"></li>
				<li data-target="#home-carousel" data-slide-to="2"></li>
			</ol>
		</div>
		<!--/-carousel-->

	</section>
	<!-- /SLIDER -->
		
          	<!-- ABOUT US -->
          	<div class="row marginbottom-150">
		<div class="container col-sm-offset-1 col-sm-5 col-left ">
					<h1 class="capital">
						<strong>About Us</strong>
					</h1>
					<span class="st-border"></span>
					<div class="about-us ">
						<br>
						<p>
							<b>Why InfyGo?<br></b> The leading player in online flight
							bookings in India, InfyGo provides great offers, some of the
							lowest airfares, exclusive discounts and a seamless online
							booking experience. Flight bookings through our website is a
							user friendly experience, and with just a few clicks you can
							complete your booking.
						</p>
						<br>

						<p>
							<b>Booking Flights with InfyGo<br></b> While booking flights
							with InfyGo, you can expect the ultimate online booking
							experience. With premium customer service, 24/7 dedicated
							helpline for support, and over 5 million delighted users,
							InfyGo takes great pride in enabling customer satisfaction.
						</p>
						<br>

						<p>
							<b>Domestic Flights with InfyGo<br></b> InfyGo is India's
							leading player for flight bookings, and have a dominant position
							in the domestic flights sector. With the cheapest fare guarantee,
							experience great value at the lowest price.
						</p>

					</div>
		</div>
	<!-- /ABOUT US -->
	
		<div class="container  col-sm-5 col-right " style="width:40%">
			<br> <br> <br>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="capital">Login</h4>
				</div>
				<div class="panel-body">
					<h3 class="red" id="msg2"></h3>
				
					
<form id="login" action="${pageContext.servletContext.contextPath}/login" method='POST'>
	<div class="form-group">
		<label for="user" class="control-label col-sm-offset-1 col-sm-3 text-right">User Id*</label>
		<div class="col-sm-7"><input type="text" class="form-control" id="user" name="username" required></div><br>
	</div>
	<div class="form-group">
	<label for="pwd" class="control-label col-sm-offset-1 col-sm-3 text-right">Password*</label>
	<div class="col-sm-7"><input type="password" class="form-control" id="pwd" name="password" required></div><br>
    </div>	
    <br>
	<div class="col-sm-offset-4 col-sm-10 text-left"> 
	<button type="submit" id="loginbtn" class="btn btn-primary">Login</button></div><br><br>
	 <input type="hidden"
            name="${_csrf.parameterName}"
            value="${_csrf.token}"/>
           

   <c:if test="${param.error ne null}">
            <div class="col-md-12 text-center">
            <div class="text-center text-danger">Invalid credentials!</div>  
            </div>
            </c:if>       
</form>
 

					
				
					
					</div>
					
				</div>
			</div>
	</div>
	<!-- Login end -->

	<jsp:include page="footer.jsp"></jsp:include>
</body>


</html>
