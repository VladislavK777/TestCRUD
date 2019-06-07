<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
  <title>TestCRUD|Home</title>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="resources/js/main.js"></script>
</head>

<body onload="init()">
    <div>
      <p>${user}</p>
      <c:url value="/logout" var="logoutUrl"/>
      <form action="${logoutUrl}" method="post">
        <button type="submit" class="bot1">Выход</button>
      </form>
    </div>
    <div>
        <p>Поиск по названию</p>
        <input name="searchName" type="text" value="" placeholder="Название..."/>
        <input type="button" value="Поиск" onclick="searchName()"/>
        <br/>
        <p>Поиск по бренду</p>
        <input name="searchBrand" type="text" value="" placeholder="Бренд..."/>
        <input type="button" value="Поиск" onclick="searchBrand()"/>
    </div>
    </br>
    <div id="result"></div>
    <form action="export" method="get" id="prod">
      <input type="submit" form="prod" value="Save to Excel"/>
    </form>
</body>

</html>