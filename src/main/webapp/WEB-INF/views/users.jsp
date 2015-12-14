<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<table>
    <tbody>
    <tr>
        <th>Name</th>
        <th>Surname</th>
    </tr>
    <c:forEach items="${userList}" var="user">
        <tr>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.surname}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>