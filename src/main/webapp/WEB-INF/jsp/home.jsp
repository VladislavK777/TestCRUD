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
    <table>
        <tr>
            <td>
                <div>
                  <p>${user}</p>
                  <c:url value="/logout" var="logoutUrl"/>
                  <form action="${logoutUrl}" method="post">
                    <button type="submit">Logout</button>
                  </form>
                </div>
            </td>
            <c:if test="${role == '[ROLE_ADMIN]'}">
                <td id="newProd">
                    <input type="text" id="name" placeholder="Name..." required/>
                    <input type="text" id="brand" placeholder="Brand..." required/>
                    <input type="text" id="price" placeholder="Price..." required/>
                    <input type="text" id="quantity" placeholder="Quantity..." required/>
                    <input type="button" onclick="newProduct(this.parentNode.id)" value="New product"/>
                </td>
            </c:if>
            <td>
                <input type="button" onclick="leftovers()" value="Leftovers"/>
            </td>
        </tr>
    </table>
    <table>
            <tr>
                <td>
                    <div>
                        <p>Search by Name</p>
                        <input name="searchName" type="text" value="" placeholder="Name..."/>
                        <input type="button" value="Search" onclick="searchName()"/>
                    </div>
                </td>
                <td>
                    </div>
                        <p>Search by Brand</p>
                        <input name="searchBrand" type="text" value="" placeholder="Brand..."/>
                        <input type="button" value="Search" onclick="searchBrand()"/>
                    </div>
                </td>
            </tr>
    </table>
    </br>
    <table>
        <tr>
            <td>
                <div id="result"></div>
            </td>
            <td>
                <div id="leftovers"></div>
            </td>
        </tr>
    </table>
    <form action="export" method="get" id="prod">
      <input type="submit" form="prod" value="Save to Excel"/>
    </form>
</body>

</html>