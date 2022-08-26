//get json from servlet using fetch api
fetch("http://localhost:8080/CRM-Project/api/task", {
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
    var table = $("#task-table").DataTable();
    //clear table before display new data, the last column is the action column edit,delete and get detail
    table.clear().draw();
    $.each(data, function (index, value) {
      table.row
        .add([
          index + 1,
          value.name,
          value.project.name,
          value.user.fullName,
          //parse date object to string and change format to dd-mm-yyyy
          convertStringDateToDDMMYY(convertDateObjectToString(value.startDate)),
          convertStringDateToDDMMYY(convertDateObjectToString(value.endDate)),
          value.status.name,
          '<button class="btn btn-primary btn-sm" onclick="editTask(' +
            value.project.id +
            ')">Sửa</button>' +
            '<button class="btn btn-danger btn-sm" onclick="deleteTask(' +
            value.project.id +
            ')">Xóa</button>' +
            '<button class="btn btn-info btn-sm" onclick="getDetail(' +
            value.project.id +
            ')">Xem </button>',
        ])
        .draw(false);
    });
  })
  .catch(function (error) {
    console.log(error);
  });

//delete project
function deleteTask(id, event) {
  var result = confirm("Bạn có chắc chắn muốn xóa công việc này không?");
  if (result) {
    fetch("http://localhost:8080/CRM-Project/api/task/delete", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      //body is json
      body: JSON.stringify({
        id: id,
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
        alert("Xóa không thành công");
      });
  }
}
//edit project
function editTask(id, event) {
  window.location.href = "/task-add.html?id=" + id;
}

//get detail project
function getDetail(id, event) {
  window.location.href = "/groupwork-details.html?id=" + id;
}

function toJson(form) {
  var array = $(form).serializeArray();
  var json = {};
  $.each(array, function () {
    json[this.name] = this.value || "";
  });
  return json;
}

//convert date object to string
function convertDate(date) {
  //put 0 to day and month if it is less than 10

  let dateString =
    (date.day < 10 ? "0" + date.day : date.day) +
    "-" +
    (date.month < 10 ? "0" + date.month : date.month) +
    "-" +
    date.year;
  return dateString;
}
