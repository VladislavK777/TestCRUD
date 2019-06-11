function login(id) {
  var context = document.getElementById(id);
  var json = JSON.stringify({
    username: context.querySelector("#username").value,
    password: context.querySelector("#password").value
  });
  $.ajax({
    url: "get-token",
    cache: false,
    type: "post",
    contentType: "application/json",
    data: json,
    success: function(data) {
      localStorage.authToken = data.authToken;
      window.location = "/";
    },
    error: function() {
      alert("Login failed");
    }
  });
}

function init() {
  var role = window.sessionStorage.getItem("role");
  $.ajax({
    url: "api/v1/all",
    cache: false,
    beforeSend: function(checkToken) {
      if (localStorage.authToken) {
        checkToken.setRequestHeader('Authorization', 'Bearer ' + localStorage.authToken);
      }
    },
    success: function(response) {
      $("table.resultTabProd").remove();
      $("table.resultTab").remove();
      $("h1").remove();
      $h1 = $("<h1>Products</h1>");
      $table = $('<table id="tabProd" class="resultTabProd">');
      $head1 = $("<tr>").append(
        $("<th>").text("Name"),
        $("<th>").text("Brand"),
        $("<th>").text("Price"),
        $("<th>").text("Quantity"),
        $("<th>"),
        $("<th>")
      );
      $table.append($head1);
      for (var i in response) {
        if (role == "ROLE_ADMIN") {
          $content = $("<tr>").append(
            $("<td>").append(
              $(
                '<input type="text" id="name" value="' +
                  response[i].name +
                  '" required/>'
              )
            ),
            $("<td>").append(
              $(
                '<input type="text" id="brand" value="' +
                  response[i].brand +
                  '" required/>'
              )
            ),
            $("<td>").append(
              $(
                '<input type="text" id="price" value="' +
                  response[i].price +
                  '" required/>'
              )
            ),
            $("<td>").append(
              $(
                '<input type="text" id="quantity" value="' +
                  response[i].quantity +
                  '" required/>'
              )
            ),
            $("<td>").append(
              $(
                '<input type="button" value="Update" onClick="updateProd(' +
                  response[i].id +
                  ', this.parentNode.parentNode.parentNode.id)"/>'
              )
            ),
            $("<td>").append(
              $(
                '<input type="button" value="Delete" onClick="deleteProd(' +
                  response[i].id +
                  ')"/>'
              )
            )
          );
        } else {
          $content = $("<tr>").append(
            $("<td>").append(
              $(
                '<input type="text" value="' + response[i].name + '" readonly/>'
              )
            ),
            $("<td>").append(
              $(
                '<input type="text" value="' +
                  response[i].brand +
                  '" readonly/>'
              )
            ),
            $("<td>").append(
              $(
                '<input type="text" value="' +
                  response[i].price +
                  '" readonly/>'
              )
            ),
            $("<td>").append(
              $(
                '<input type="text" value="' +
                  response[i].quantity +
                  '" readonly/>'
              )
            )
          );
        }
        $table.append($content);
      }
      $("#result").append($h1);
      $("#result").append($table);
    }
  });
}

function leftovers() {
  $.ajax({
    url: "api/v1/leftovers",
    cache: false,
    beforeSend: function(checkToken) {
      if (localStorage.authToken) {
        checkToken.setRequestHeader('Authorization', 'Bearer ' + localStorage.authToken);
      }
    },
    success: function(response) {
      $("table.resultTab").remove();
      $("h1").remove();
      $h1 = $("<h1>Leftovers</h1>");
      $table = $('<table class="resultTab">');
      $head1 = $("<tr>").append(
        $("<th>").text("Name"),
        $("<th>").text("Brand"),
        $("<th>").text("Price"),
        $("<th>").text("Quantity")
      );
      $table.append($head1);
      for (var i in response) {
        $content = $("<tr>").append(
          $("<td>").append(
            $('<input type="text" value="' + response[i].name + '" readonly/>')
          ),
          $("<td>").append(
            $('<input type="text" value="' + response[i].brand + '" readonly/>')
          ),
          $("<td>").append(
            $('<input type="text" value="' + response[i].price + '" readonly/>')
          ),
          $("<td>").append(
            $(
              '<input type="text" value="' +
                response[i].quantity +
                '" readonly/>'
            )
          )
        );
        $table.append($content);
      }
      $("#leftovers").append($h1);
      $("#leftovers").append($table);
    }
  });
}

function searchName() {
  var val = $('input[name="searchName"]').val();
  $.ajax({
    url: "api/v1/search/name?name=" + val,
    cache: false,
    beforeSend: function(checkToken) {
      if (localStorage.authToken) {
        checkToken.setRequestHeader('Authorization', 'Bearer ' + localStorage.authToken);
      }
    },
    success: function(response) {
      $("table.resultTab").remove();
      $("table.resultTabProd").remove();
      $("h1").remove();
      $h1 = $("<h1>Result of search</h1>");
      $table = $('<table class="resultTabProd">');
      $head1 = $("<tr>").append(
        $("<th>").text("Name"),
        $("<th>").text("Brand"),
        $("<th>").text("Price"),
        $("<th>").text("Quantity")
      );
      $table.append($head1);
      for (var i in response) {
        $content = $("<tr>").append(
          $("<td>").append(
            $('<input type="text" value="' + response[i].name + '" readonly/>')
          ),
          $("<td>").append(
            $('<input type="text" value="' + response[i].brand + '" readonly/>')
          ),
          $("<td>").append(
            $('<input type="text" value="' + response[i].price + '" readonly/>')
          ),
          $("<td>").append(
            $(
              '<input type="text" value="' +
                response[i].quantity +
                '" readonly/>'
            )
          )
        );
        $table.append($content);
      }
      $("#result").append($h1);
      $("#result").append($table);
    }
  });
}

function searchBrand() {
  var val = $('input[name="searchBrand"]').val();
  $.ajax({
    url: "api/v1/search/brand?brand=" + val,
    cache: false,
    beforeSend: function(checkToken) {
      if (localStorage.authToken) {
        checkToken.setRequestHeader('Authorization', 'Bearer ' + localStorage.authToken);
      }
    },
    success: function(response) {
      $("table.resultTab").remove();
      $("table.resultTabProd").remove();
      $("h1").remove();
      $h1 = $("<h1>Result of search</h1>");
      $table = $('<table class="resultTabProd">');
      $head1 = $("<tr>").append(
        $("<th>").text("Name"),
        $("<th>").text("Brand"),
        $("<th>").text("Price"),
        $("<th>").text("Quantity")
      );
      $table.append($head1);
      for (var i in response) {
        $content = $("<tr>").append(
          $("<td>").append(
            $('<input type="text" value="' + response[i].name + '" readonly/>')
          ),
          $("<td>").append(
            $('<input type="text" value="' + response[i].brand + '" readonly/>')
          ),
          $("<td>").append(
            $('<input type="text" value="' + response[i].price + '" readonly/>')
          ),
          $("<td>").append(
            $(
              '<input type="text" value="' +
                response[i].quantity +
                '" readonly/>'
            )
          )
        );
        $table.append($content);
      }
      $("#result").append($h1);
      $("#result").append($table);
    }
  });
}

function newProduct(id) {
  var context = document.getElementById(id);
  var json = JSON.stringify({
    name: context.querySelector("#name").value,
    brand: context.querySelector("#brand").value,
    price: context.querySelector("#price").value,
    quantity: context.querySelector("#quantity").value
  });
  $.ajax({
    url: "api/v1/admin/insert",
    cache: false,
    type: "post",
    data: json,
    contentType: "application/json",
    beforeSend: function(checkToken) {
      if (localStorage.authToken) {
        checkToken.setRequestHeader('Authorization', 'Bearer ' + localStorage.authToken);
      }
    },
    success: function() {
      init();
      alert("The new product succeed add!");
    },
    error: function() {
      alert("Error! Check query!");
    }
  });
}

function deleteProd(id) {
  $.ajax({
    url: "api/v1/admin/delete/" + id,
    cache: false,
    type: "delete",
    beforeSend: function(checkToken) {
      if (localStorage.authToken) {
        checkToken.setRequestHeader('Authorization', 'Bearer ' + localStorage.authToken);
      }
    },
    success: function() {
      init();
    }
  });
}

function updateProd(idProd, id) {
  var context = document.getElementById(id);
  console.log(id);
  var json = JSON.stringify({
    name: context.querySelector("#name").value,
    brand: context.querySelector("#brand").value,
    price: context.querySelector("#price").value,
    quantity: context.querySelector("#quantity").value
  });
  $.ajax({
    url: "api/v1/admin/update/" + idProd,
    cache: false,
    type: "put",
    data: json,
    contentType: "application/json",
    beforeSend: function(checkToken) {
      if (localStorage.authToken) {
        checkToken.setRequestHeader('Authorization', 'Bearer ' + localStorage.authToken);
      }
    },
    success: function() {
      init();
      alert("The product is succeed update!");
    },
    error: function() {
      alert("Error! Check query!");
    }
  });
}

