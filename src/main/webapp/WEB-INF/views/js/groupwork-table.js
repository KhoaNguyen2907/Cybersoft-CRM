//get json from servlet using fetch api
fetch("http://localhost:8080/CRM-Project/api/project", {
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
    var table = $("#project-table").DataTable();
    //clear table before display new data, the last column is the action column edit,delete and get detail
    table.clear().draw();
    $.each(data, function (index, value) {
      table.row
        .add([
          index + 1,
          value.name,
          value.description,
          //parse date object to string and change format to dd-mm-yyyy
          convertStringDateToDDMMYY(convertDateObjectToString(value.startDate)),
          convertStringDateToDDMMYY(convertDateObjectToString(value.endDate)),
          '<button class="btn btn-primary btn-sm" onclick="editProject(' +
            value.id +
            ')">Sửa</button>' +
            '<button class="btn btn-danger btn-sm" onclick="deleteProject(' +
            value.id +
            ')">Xóa</button>' +
            '<button class="btn btn-info btn-sm" onclick="getDetail(' +
            value.id +
            ')">Xem </button>',
        ])
        .draw(false);
    });
  })
  .catch(function (error) {
    console.log(error);
  });

//delete project
function deleteProject(id, event) {
  var result = confirm("Bạn có chắc chắn muốn xóa dự án này không?");
  if (result) {
    fetch("http://localhost:8080/CRM-Project/api/project/delete", {
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
function editProject(id, event) {
  window.location.href = "/groupwork-add.html?id=" + id;
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
