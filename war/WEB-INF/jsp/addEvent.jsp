<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title></title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/form.css">
<script src="js/vendor/jquery-1.8.2.min.js">
	<\/script>
	<script src="js/vendor/modernizr-2.6.2.min.js">
</script>
<script>
	$('#top_menu').css("backgroundColor", "blue");
	$(function() {
		$("#datepicker").datepicker({
			minDate : new Date()
		});
	});
</script>
</head>
<body>
	<!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->
	<nav class="top_menu">
		<a href="#"> Zarządzaj wydarzeniami </a> <span>&nbsp | &nbsp <span>
				<a href="#"> Zarządzaj kategoriami</a> <span>&nbsp | &nbsp <span>
						<a href="#"> Zarządzaj uzytkownikami </a>
	</nav>

	<div class="outer">
		<div class="logo"></div>
		<div class="container">
			<nav class="menu">
				<a href="#"> Zapisz i dodaj kolejne </a><span> | </span> <a href="#">
					Zapisz </a>
			</nav>

			<form action="#" method="POST">
				<fieldset>
					<label> Tytuł <input type="text" id="title" name="title" />
					</label> <label> Opis <textarea name="descritpion" cols="15"
							rows="30"> </textarea></label>
				</fieldset>

				<p class="details_button">+ Details</p>
				<div class="details">
					<fieldset>
						<label> WWW <input type="text" id="webpage" name="webpage" />
						</label> <label> Telefon <input type="text" id="telephone"
							name="telephone" /><label> <label> Skype <input
									type="text" id="skype" name="skype" /><label>
					</fieldset>
				</div>

				<fieldset>
					<label> Miasto <input type="text" name="city" id="city" /></label>
					<label> Adres <input type="text" name="address"
						id="address" /></label> <label>Latitude<input type="number"
						name="lat" id="lat" /></label> <label>Longtitude<input
						type="number" name="long" id="long" /></label>
					<div class="map">
						<img src="img/map.png" alt="mapa" />
					</div>

				</fieldset>
			</form>

		</div>
	</div>


	<script src="js/plugins.js"></script>
	<script src="js/main.js"></script>
	<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
</body>
</html>
