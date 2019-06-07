<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
  <title>TestCRUD|Auth</title>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
</head>

<body>
  <c:url value="/login" var="loginUrl" />
  <form action="${loginUrl}" method="post">
    <table>
      <tr>
        <td>
          <c:choose>
            <c:when test="${param.error != null}">
              <h2>Wrong login/password</h2>
            </c:when>
            <c:otherwise>
              <h2>Login to system</h2>
            </c:otherwise>
          </c:choose>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" autocomplete="off" name="username" value="" required />
          <span>Username</span>
        </td>
      </tr>
      <tr>
        <td>
          <input type="password" autocomplete="off" name="password" value="" required />
          <span>Password</span>
        </td>
      </tr>
      <tr>
        <td>
          <button type="submit">Login</button>
        </td>
      </tr>
    </table>
  </form>
</body>

</html>