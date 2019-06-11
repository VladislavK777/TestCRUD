<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
  <title>TestCRUD|Auth</title>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="resources/js/main.js"></script>
</head>

<body>
    <table id="loginTab">
      <tr>
        <td>
          <h2>Login to system</h2>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" autocomplete="off" id="username" value="" required />
          <span>Username</span>
        </td>
      </tr>
      <tr>
        <td>
          <input type="password" autocomplete="off" id="password" value="" required />
          <span>Password</span>
        </td>
      </tr>
      <tr>
        <td>
          <input type="button" onclick="login(this.parentNode.parentNode.parentNode.parentNode.id)" value="Login" />
        </td>
      </tr>
    </table>
</body>

</html>