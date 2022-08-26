//get project list from server and display in select element
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
    $("#project").empty();
    $.each(data, function (index, value) {
      //clear select element before display new data
      $("#project").append(
        "<option value='" + value.id + "'>" + value.name + "</option>"
      );
    });
  })
  .catch(function (error) {
    console.log(error);
  });
//get user list
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
    $("#in-charge").empty();
    $.each(data, function (index, value) {
      //clear select element before display new data
      $("#in-charge").append(
        "<option value='" + value.code + "'>" + value.fullName + "</option>"
      );
    });
  })
  .catch(function (error) {
    console.log(error);
  });
//get status list and display to select el
fetch("http://localhost:8080/CRM-Project/api/status", {
  method: "GET",
  headers: {
    "Content-Type": "application/json",
  },
})
  .then(function (response) {
    return response.json();
  })
  .then(function (data) {
    console.log("status" + data);
    $("#status").empty();
    $.each(data, function (index, value) {
      //clear select element before display new data
      $("#status").append(
        "<option value='" + value.id + "'>" + value.name + "</option>"
      );
    });
  })
  .catch(function (error) {
    console.log(error);
  });

var url = new URL(window.location.href);
var id = url.searchParams.get("id");
if (id != null) {
  // change button name
  $("#btn-update").text("Cập nhật");
  $(".page-title").text("Chỉnh sửa công việc");
  //change onclick event atribute
  $("#btn-update").attr("onclick", "updateTask(event)");
  // add input hidden
  $("#submit-form").append(
    "<input type='hidden' name='id' value='" + id + "'>"
  );

  //get task from server and display in form when page is load
  fetch("http://localhost:8080/CRM-Project/api/task/getTaskById?id=" + id, {
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
      //setvalue for select element
      $("#project").val(data.project.id);
      $("#name").val(data.name);
      $("#description").val(data.description);
      $("#in-charge").val(data.user.code);
      $("#start-date").val(convertDateObjectToString(data.startDate));
      $("#end-date").val(convertDateObjectToString(data.endDate));
      $("#status").val(data.status.id);
      $("#created-by").val(data.createdBy);
    })
    .catch(function (error) {
      console.log(error);
    });
}

//add task
function addTask(event) {
  var formEl = $("#submit-form");
  //if any input is empty, alert and retype
  if (checkEmpty(formEl) == false) {
    alert("Vui lòng nhập đầy đủ thông tin");
    return;
  }

  var json = toJson(formEl);
  console.log(JSON.stringify(json));
  fetch("http://localhost:8080/CRM-Project/api/task/add", {
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
        window.location.href = "/task.html";
      } else {
        alert("Thêm không thành công");
      }
    })
    .catch(function (error) {
      console.log(error);
    });
}

//update task
function updateTask(event) {
  //get form data
  var formEl = $("#submit-form");
  if (checkEmpty(formEl) == false) {
    alert("Vui lòng nhập đầy đủ thông tin");
    return;
  }

  var json = toJson(formEl);
  console.log(JSON.stringify(json));
  fetch("http://localhost:8080/CRM-Project/api/task/update", {
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
      alert("Cập nhật không thành công");
      console.log(error);
    });
}

function toJson(form) {
  var array = $(form).serializeArray();
  var json = {};
  $.each(array, function () {
    json[this.name] = this.value || "";
  });
  json["user"] = { code: $("#in-charge").val() };
  json["project"] = { id: $("#project").val() };
  json["status"] = { id: $("#status").val() };
  json["startDate"] = convertStringDateToDDMMYY($("#start-date").val());
  json["endDate"] = convertStringDateToDDMMYY($("#end-date").val());

  return json;
}
