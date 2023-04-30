<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <h3>You have successfully logged out</h3>
        <br/><%session.invalidate(); %>
        <h5> Click <a href="/home">here</a> to login </h5>
    </center>

</body>
</html>
