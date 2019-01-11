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
		<h2>Hello!</h2>

	</div>

	<div style="padding: 30px;" class="text-table">
		<h2>Get Json Object</h2>
		<form role="form" style="width: 300px" action="readLines" method="GET">
			<div class="form-group">
				<label for="q"> Text to search in file:</label> 
				<input type="text" name="q" class="form-control" id="q">
			</div>
			<div class="form-group">
				<label for="limit">Max number of chars:</label> 
				<input type="text" name="limit" class="form-control" id="limit">
			</div>
			<div class="form-group">
				<label for="length">Max string length:</label> 
				<input type="text" name="length" class="form-control" id="length">
			</div>
			<div class="form-group form-check">
   				<input type="checkbox" name="metaData" class="form-check-input" id="metaData" value="true">
    			<label class="form-check-label" for="metaData">Show meta data</label>
  			</div>
			<button type="submit" class="btn btn-secondary btn-lg">Send</button>
		</form>
	</div>

</body>
</html>
