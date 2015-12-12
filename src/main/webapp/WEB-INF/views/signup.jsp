<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Currency Converter - SignUp</title>
<link rel="stylesheet" href="static/css/style.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

</head>

<body>
	<section class="container">
		<div class="login">
			<h1>Login</h1>
			<form:form method="POST" modelAttribute="user">
				<label for="email">E-mail *</label>
				<form:input path="email" id="email" />
				<div class="has-error">
					<form:errors path="email" />
				</div>
				<label for="email">Password *</label>
				<form:password path="password" id="password" />
				<div class="has-error">
					<form:errors path="password" />
				</div>
				<label for="email">Password Repeat *</label>
				<form:password path="passwordrepeat" id="passwordrepeat" />
				<div class="has-error">
					<form:errors path="passwordrepeat" />
				</div>
				<label for="email">Name *</label>
				<form:input path="name" id="name" />
				<div class="has-error">
					<form:errors path="name" />
				</div>
				<label for="email">Surname *</label>
				<form:input path="surname" id="surname" />
				<div class="has-error">
					<form:errors path="surname" />
				</div>
				<label for="birthday">Birthday *</label>
				<form:input path="birthday" id="birthday" />
				<div class="has-error">
					<form:errors path="birthday" />
				</div>
				<label for="email">Address</label>
				<form:input path="addressLine1" id="addressLine1" />
				<div class="has-error">
					<form:errors path="addressLine1" />
				</div>
				<label for="email">Address 2</label>
				<form:input path="addressLine2" id="addressLine2" />
				<div class="has-error">
					<form:errors path="addressLine2" />
				</div>
				<label for="city">City *</label>
				<form:input path="city" id="city" />
				<div class="has-error">
					<form:errors path="city" />
				</div>
				<label for="postalCode">Postal Code *</label>
				<form:input path="postalCode" id="postalCode" />
				<div class="has-error">
					<form:errors path="postalCode" />
				</div>
				<label for="country">Country</label>
				<form:select path="country" id="country">
					<c:forEach var="ct" items="${countries}">
						<form:option value="${ct.code}">${ct.name}</form:option>
					</c:forEach>
				</form:select>
				<p>
					<input type="submit" value="SignUp">
			</form:form>
		</div>
	</section>

	<section class="about">
		<p class="about-author">
			&copy; 2015 <a href="http://thibaut.me" target="_blank">Gul
				Nildem Ucok</a> Original PSD by <a
				href="http://www.premiumpixels.com/freebies/clean-simple-login-form-psd/"
				target="_blank">Orman Clark</a>
	</section>
	<script>
		$(function() {
			$("#birthday").datepicker();
		});
	</script>
</body>
</html>
