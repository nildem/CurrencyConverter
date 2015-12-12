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
<title>Currency Converter - Main</title>
<link rel="stylesheet" href="static/css/style.css">
<link rel="stylesheet" type="text/css"
	href="//cdn.datatables.net/1.10.10/css/jquery.dataTables.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">



</head>

<body>
	<section class="about">
		<p class="about-author">
			<a href="<c:url value="/logout" />">Logout</a>
	
	</section>
	<section class="container">
		<div class="login">
			<h1>Dashboard</h1>
			<form:form method="POST" modelAttribute="data" action="main">
			<p><label
				 for="currencyFrom">Base Currency: ${data.currencyFrom}</label>
				</p>
				<p>
					<label for="currentCurrency">Select Currency</label>
					<form:select path="currentCurrency" id="currentCurrency">
						<c:forEach var="ct" items="${currencies}">
							<form:option value="${ct}">${ct}</form:option>
						</c:forEach>
					</form:select>
				</p>
				<p><label
				 for="currentDate">Select Date</label>
				<form:input path="currentDate" id="currentDate" />
				<div class="has-error">
					<form:errors path="currentDate" />
				</div>
				</p>
				<p class="submit">
					<input type="submit" name="commit" value="Submit">
				</p>

			</form:form>
		</div>


	</section>
	<section class="container">
	<table id="currencyTable" class="display">
					<thead>
						<tr>
							<th>Date</th>
							<th>Base Currency</th>
							<th>Convertion Currency</th>
							<th>Rate</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="rate" items="${data.rates}">
							<tr>
								<td>${rate.convertionDate}</td>
								<td>${rate.currencyFrom}</td>
								<td>${rate.currencyTo}</td>
								<td>${rate.rate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
	</section>

	<section class="about">
		<p class="about-author">
			&copy; 2015 <a href="http://thibaut.me" target="_blank">Gul
				Nildem Ucok</a> Original PSD by <a
				href="http://www.premiumpixels.com/freebies/clean-simple-login-form-psd/"
				target="_blank">Orman Clark</a>
	
	</section>


<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="//cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
	<script>
		$(function() {
			$("#currentDate").datepicker();
		});
		$(document).ready(function() {
			$('#currencyTable').DataTable();
		});
	</script>
</body>
</html>
