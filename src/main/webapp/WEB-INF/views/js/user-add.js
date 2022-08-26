//get id from url
var url = new URL(window.location.href);
var code = url.searchParams.get("code");
if (code != null) {
  // change button name
  $("#btn-update").text("Cập nhật");
  $(".page-title").text("Chỉnh sửa thành viên");
  //change onclick event atribute
  $("#btn-update").attr("onclick", "updateUser(event)");
  // add input hidden
  $("#submit-form").append(
    "<input type='hidden' name='code' value='" + code + "'>"
  );
  //get user by id
  fetch("http://localhost:8080/CRM-Project/api/user/getUserById?code=" + code, {
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
      $("#full-name").val(data.fullName);
      $("#email").val(data.email);
      $("#password").val(data.password);
      $("#phone").val(data.phone);
      $("#address").val(data.address);
      $("#roleId").val(data.role.id);
    })
    .catch(function (error) {
      console.log(error);
    });
}

//add user
function addUser(event) {
  var formEl = $("#submit-form");
  if (checkEmpty(formEl) == false) {
    alert("Vui lòng nhập đầy đủ thông tin");
    return;
  }
  var json = toJson(formEl);
  console.log(JSON.stringify(json));
  fetch("http://localhost:8080/CRM-Project/api/user/add", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    //body is json from form data
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
        window.location.href = "user-table.html";
      } else {
        alert("Thêm không thành công");
      }
    })
    .catch(function (error) {
      console.log(error);
    });
}
//update user
function updateUser(event) {
  //get form data
  var formEl = $("#submit-form");
  if (checkEmpty(formEl) == false) {
    alert("Vui lòng nhập đầy đủ thông tin");
    return;
  }
  console.log(JSON.stringify(toJson(formEl)));
  fetch("http://localhost:8080/CRM-Project/api/user/update", {
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
    })
    .catch(function (error) {
      console.log(error);
    });
}

function toJson(form) {
  var array = $(form).serializeArray();
  var json = {};
  $.each(array, function () {
    json[this.name] = this.value || "";
    json["role"] = { id: $("#roleId").val() };
  });
  return json;
}
