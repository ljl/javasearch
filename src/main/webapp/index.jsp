<%@ page import="service.SearchService" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hello, search!</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1>Hello, search!</h1>

        <form role="form">
            <div class="form-group">
                <label for="s">Search</label>
                <input type="text" class="form-control" id="s" placeholder="Search for stuff">
            </div>
        </form>
    </div>
</div>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>