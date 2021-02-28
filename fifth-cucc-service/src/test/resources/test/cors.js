$(document).ready(function() {
  $.ajax({
    url: "http://localhost:9094/product/1"
  }).then(function(data, status, jqxhr) {
    $('.greeting-id').append(data.id);
    $('.greeting-content').append(data.name);
    console.log(jqxhr);
  });
});
