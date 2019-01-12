<!DOCTYPE HTML>
<html>
<head>

<meta charset="utf-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<title>Welcome</title>

</head>
<body>
	<div style="padding: 30px;" class="text-table">
	<h1>${msg}</h1>
		<h2>Copy "txt" file to /webapp/ and set name *.txt</h2>

	</div>

	<div style="padding: 30px;" class="text-table">
		
		<form role="form" style="width: 300px" action="readLines" method="POST">
			<div class="form-group">
				<label for="q"> File name:</label> 
				<input type="text" name="f" class="form-control" id="q">
			</div>
			<button type="submit" class="btn btn-secondary btn-lg">Send</button>
		</form>
	</div>

</body>
</html>
