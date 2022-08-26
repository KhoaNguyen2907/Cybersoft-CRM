//get id from url
var url = new URL(window.location.href);
var id = url.searchParams.get("id");
if (id != null) {
  // change button name
  $("#btn-update").text("Cập nhật");
  $(".page-title").text("Chỉnh sửa quyền");
  //change onclick event atribute
  $("#btn-update").attr("onclick", "updateRole(event)");
  // add input hidden
  $("#submit-form").append(
    "<input type='hidden' name='id' value='" + id + "'>"
  );
  //get role by id
  fetch("http://localhost:8080/CRM-Project/api/role/getRoleById?id=" + id, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then(function (response) {
      return response.json();
    })
    .then(function (data) {
      console.log(data);
      $("#name").val(data.name);
      $("#description").val(data.description);
    })
    .catch(function (error) {
      console.log(error);
    });
}
//add role
function addRole(event) {
  var formEl = $("#submit-form");
  if (checkEmpty(formEl) == false) {
    alert("Vui lòng nhập đầy đủ thông tin");
    return;
  }
  var json = toJson(formEl);
  console.log(JSON.stringify(json));
  fetch("http://localhost:8080/CRM-Project/api/role/add", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(json),
  })
    .then(function (response) {
      console.log(response);
      return response.json();
    })
    .then(function (data) {
      console.log(data);
      if (data.isSuccess == true) {
        alert("Thêm thành công");
        window.location.href = "role-table.html";
      } else {
        alert("Thêm không thành công");
      }
    })
    .catch(function (error) {
      console.log(error);
    });
}

//update role
function updateRole(event) {
  //get form data
  var formEl = $("#submit-form");
  if (checkEmpty(formEl) == false) {
    alert("Vui lòng nhập đầy đủ thông tin");
    return;
  }
  console.log(toJson(formEl));
  fetch("http://localhost:8080/CRM-Project/api/role/update", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    //body is json from form data
    body: JSON.stringify(toJson(formEl)),
  })
    .then(function (response) {
      console.log(response);
      return response.json();
    })
    .then(function (data) {
      console.log(data);
      if (data.isSuccess == true) {
        alert("Cập nhật thành công");
      } else {
        alert("Cập nhật không thành công");
      }
      //prevent refresh page
      event.preventDefault();
    })
    .catch(function (error) {
      console.log(error);
    }),
    function (error) {
      console.log(error);
    };
}

function toJson(form) {
  var array = $(form).serializeArray();
  var json = {};
  $.each(array, function () {
    json[this.name] = this.value || "";
  });
  return json;
}
