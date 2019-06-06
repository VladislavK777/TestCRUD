<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
  <title>TestCRUD|Auth</title>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
</head>

<body>
      <form action="${loginUrl}" method="post">
              <c:choose>
                <c:when test="${param.error != null}">
                  <h2 style="color: #364274">Неверный логин или пароль</h2>
                </c:when>
                <c:otherwise>
                  <h2 style="color: #364274">Вход в систему</h2>
                </c:otherwise>
              </c:choose>
        <input type="text" autocomplete="off" name="username" value="" required />
        <span class="label">Пользователь</span>
        <span class="border" />

        <input type="password" autocomplete="off" name="password" value="" required />
        <span class="label">Пароль</span>
        <span class="border" />

        <button type="submit" class="bot1">Войти</button>

      </form>

</body>

</html>