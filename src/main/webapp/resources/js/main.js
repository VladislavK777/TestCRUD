function init() {
    $.ajax({
        url: "api/v1/all",
        cache: false,
        success: function(response) {
          $table = $('<table class="resultTab">');
          $head1 = $("<tr>").append(
            $('<th>').text("№"),
            $('<th>').text("Name"),
            $('<th>').text("Brand"),
            $('<th>').text("Price"),
            $('<th>').text("Quantity"),
          );
          $table.append($head1);
          for (var i in response) {
            $content = $("<tr>").append(
                $('<td>').text(response[i].id),
                $('<td>').text(response[i].name),
                $('<td>').text(response[i].brand),
                $('<td>').text(response[i].price),
                $('<td>').text(response[i].quantity)
            );
            $table.append($content);
          }
          $("#result").append($table);
        }
    });
}

function searchName() {
    var val = $('input[name="searchName"]').val();
    $.ajax({
        url: "api/v1/search/name?name=" + val,
        cache: false,
        type: "get",
        success: function(response) {
          $("table.resultTab").remove();
          $table = $('<table class="resultTab">');
          $head1 = $("<tr>").append(
            $('<th>').text("№"),
            $('<th>').text("Name"),
            $('<th>').text("Brand"),
            $('<th>').text("Price"),
            $('<th>').text("Quantity"),
          );
          $table.append($head1);
          for (var i in response) {
            $content = $("<tr>").append(
                $('<td>').text(response[i].id),
                $('<td>').text(response[i].name),
                $('<td>').text(response[i].brand),
                $('<td>').text(response[i].price),
                $('<td>').text(response[i].quantity)
            );
            $table.append($content);
          }
          $("#result").append($table);
        }
    });
}

function searchBrand() {
    var val = $('input[name="searchBrand"]').val();
    $.ajax({
        url: "api/v1/search/brand?brand=" + val,
        cache: false,
        type: "get",
        success: function(response) {
          $("table.resultTab").remove();
          $table = $('<table class="resultTab">');
          $head1 = $("<tr>").append(
            $('<th>').text("№"),
            $('<th>').text("Name"),
            $('<th>').text("Brand"),
            $('<th>').text("Price"),
            $('<th>').text("Quantity"),
          );
          $table.append($head1);
          for (var i in response) {
            $content = $("<tr>").append(
                $('<td>').text(response[i].id),
                $('<td>').text(response[i].name),
                $('<td>').text(response[i].brand),
                $('<td>').text(response[i].price),
                $('<td>').text(response[i].quantity)
            );
            $table.append($content);
          }
          $("#result").append($table);
        }
    });
}