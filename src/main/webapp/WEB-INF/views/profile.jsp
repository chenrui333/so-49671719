<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<html>
<head>
    <title>User</title>
</head>
<body>
<h1>Your Profile</h1>
<c:out value="${user.name}"/>
<c:out value="${user.surname}"/>
</body>
</html>