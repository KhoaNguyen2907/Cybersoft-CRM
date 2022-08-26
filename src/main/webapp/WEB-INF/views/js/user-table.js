//get json from servlet using fetch api
fetch("http://localhost:8080/CRM-Project/api/user", {
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
    var table = $("#user-table").DataTable();
    //clear table before display new data, the last column is the action column edit,delete and get detail
    table.clear().draw();
    $.each(data, function (index, value) {
      table.row
        .add([
          index + 1,
          value.fullName,
          value.email,
          value.phone,
          value.role.name,
          '<button class="btn btn-primary btn-sm" onclick="editUser(' +
            value.code +
            ')">Sửa</button>' +
            '<button class="btn btn-danger btn-sm" onclick="deleteUser(' +
            value.code +
            ')">Xóa</button>' +
            '<button class="btn btn-info btn-sm" onclick="getDetail(' +
            value.code +
            ')">Xem</button>',
        ])
        .draw(false);
    });
  })
  .catch(function (error) {
    console.log(error);
  });

//delete user
function deleteUser(code, event) {
  var result = confirm("Bạn có chắc chắn muốn xóa người dùng này không?");
  if (result) {
    fetch("http://localhost:8080/CRM-Project/api/user/delete", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      //body is json
      body: JSON.stringify({
        code: code,
      }),
    })
      .then(function (response) {
        return response.json();
      })
      .then(function (data) {
        console.log(data);
        if (data.isSuccess == true) {
          alert("Xóa thành công");
          window.location.reload();
        } else {
          alert("Xóa không thành công");
        }
      })
      .catch(function (error) {
        console.log(error);
      });
  }
}

//get detail of user, send redirect to user-details.html
function getDetail(code) {
  window.location.href = "user-details.html?code=" + code;
}

//edit user
function editUser(code) {
  window.location.href = "user-add.html?code=" + code;
}

toJson = function (form) {
  var array = $(form).serializeArray();
  var json = {};
  $.each(array, function () {
    json[this.name] = this.value || "";
  });
  return json;
};
