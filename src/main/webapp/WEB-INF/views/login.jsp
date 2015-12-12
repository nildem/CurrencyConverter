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
<title>Currency Converter - Login</title>
<link rel="stylesheet" href="static/css/style.css">

</head>

<body>
	<section class="container">
		<div class="login">
			<h1>Login</h1>
			<div class="has-error">
					${error}
				</div>
			<form method="post" action="login" id="formLogin">
				<p>
					<input type="text" name="username" value="" placeholder="Email">
				</p>
				<p>
					<input type="password" name="password" value=""
						placeholder="Password">
				</p>
				<p class="submit">
					<input type="submit" name="commit" value="Login">
				</p>
			</form>
		</div>

		<div class="login-help">
			<p>
				New to CurrencyConverter? <a href="signup">Click here to sign up</a>.
			</p>
		</div>
	</section>

	<section class="about">
		<p class="about-author">
			&copy; 2015 <a href="http://thibaut.me" target="_blank">Gul
				Nildem Ucok</a> Original PSD by <a
				href="http://www.premiumpixels.com/freebies/clean-simple-login-form-psd/"
				target="_blank">Orman Clark</a>
	</section>
</body>
</html>
