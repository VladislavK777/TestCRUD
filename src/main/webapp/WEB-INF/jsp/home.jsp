<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>

<head>
  <title>TestCRUD|Home</title>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="resources/js/main.js"></script>
</head>

<sec:authorize access="hasRole('ROLE_ADMIN')">
    <script>
        window.sessionStorage.setItem("role", "ROLE_ADMIN");
    </script>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_USER')">
    <script>
        window.sessionStorage.setItem("role", "ROLE_USER");
    </script>
</sec:authorize>
<body onLoad="init()">
  <table>
    <tr>
      <td>
        <div>
          <p>
            <sec:authentication property="principal.username" />
          </p>
          <c:url value="/logout" var="logoutUrl" />
          <form action="${logoutUrl}" method="post">
            <button type="submit">Logout</button>
          </form>
        </div>
      </td>
      <td>
        <input type="button" onclick="leftovers()" value="Leftovers" />
      </td>
    </tr>
  </table>
  <table>
    <tr>
      <td>
        <div>
          <p>Search by Name</p>
          <input name="searchName" type="text" value="" placeholder="Name..." />
          <input type="button" value="Search" onclick="searchName()" />
        </div>
      </td>
      <td>
        </div>
        <p>Search by Brand</p>
        <input name="searchBrand" type="text" value="" placeholder="Brand..." />
        <input type="button" value="Search" onclick="searchBrand()" />
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
      <sec:authorize access="hasRole('ROLE_ADMIN')">
        <td id="newProd">
          <input type="text" id="name" placeholder="Name..." required/>
          <br/><br/>
          <input type="text" id="brand" placeholder="Brand..." required/>
          <br/><br/>
          <input type="text" id="price" placeholder="Price..." required/>
          <br/><br/>
          <input type="text" id="quantity" placeholder="Quantity..." required/>
          <br/><br/>
          <input type="button" onclick="newProduct(this.parentNode.id)" value="New product" />
        </td>
      </sec:authorize>
    </tr>
  </table>
  <form action="export" method="get" id="prod">
    <input type="submit" form="prod" value="Save to Excel" />
  </form>
</body>

</html>