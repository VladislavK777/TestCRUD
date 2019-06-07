function init() {
    $.ajax({
        url: "api/v1/all",
        cache: false,
        success: function(response) {
          $("table.resultTab").remove();
          $("h1").remove();
          $h1 = $('<h1>Products</h1>');
          $table = $('<table class="resultTab">');
          $head1 = $("<tr>").append(
            $('<th>').text("Name"),
            $('<th>').text("Brand"),
            $('<th>').text("Price"),
            $('<th>').text("Quantity"),
          );
          $table.append($head1);
          for (var i in response) {
            $content = $("<tr>").append(
                $('<td>').text(response[i].name),
                $('<td>').text(response[i].brand),
                $('<td>').text(response[i].price),
                $('<td>').text(response[i].quantity)
            );
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
        success: function(response) {
          $h1 = $('<h1>Leftovers</h1>');
          $table = $('<table class="resultTab">');
          $head1 = $("<tr>").append(
            $('<th>').text("Name"),
            $('<th>').text("Brand"),
            $('<th>').text("Price"),
            $('<th>').text("Quantity"),
          );
          $table.append($head1);
          for (var i in response) {
            $content = $("<tr>").append(
                $('<td>').text(response[i].name),
                $('<td>').text(response[i].brand),
                $('<td>').text(response[i].price),
                $('<td>').text(response[i].quantity)
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
        success: function(response) {
          $("table.resultTab").remove();
          $("h1").remove();
          $h1 = $('<h1>Result of search</h1>');
          $table = $('<table class="resultTab">');
          $head1 = $("<tr>").append(
            $('<th>').text("Name"),
            $('<th>').text("Brand"),
            $('<th>').text("Price"),
            $('<th>').text("Quantity"),
          );
          $table.append($head1);
          for (var i in response) {
            $content = $("<tr>").append(
                $('<td>').text(response[i].name),
                $('<td>').text(response[i].brand),
                $('<td>').text(response[i].price),
                $('<td>').text(response[i].quantity)
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
        success: function(response) {
          $("table.resultTab").remove();
          $("h1").remove();
          $h1 = $('<h1>Result of search</h1>');
          $table = $('<table class="resultTab">');
          $head1 = $("<tr>").append(
            $('<th>').text("Name"),
            $('<th>').text("Brand"),
            $('<th>').text("Price"),
            $('<th>').text("Quantity"),
          );
          $table.append($head1);
          for (var i in response) {
            $content = $("<tr>").append(
                $('<td>').text(response[i].name),
                $('<td>').text(response[i].brand),
                $('<td>').text(response[i].price),
                $('<td>').text(response[i].quantity)
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
    console.log(json);
    $.ajax({
        url: "api/v1/admin/insert",
        cache: false,
        type: "post",
        data: json,
        contentType: "application/json",
        success: function() {
          init();
        }
    });
}