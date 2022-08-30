//check authorization
function getCookie(name) {
  var value = "; " + document.cookie;
  var parts = value.split("; " + name + "=");
  if (parts.length == 2) return parts.pop().split(";").shift();
}
//get jwt token from cookie
var jwtToken = getCookie("jwtToken");
//check if jwt token is exist
if (jwtToken == null) {
  window.location.href = "login.html";
}
//if user is user, cannot access to user, project, role page and return to home page
fetch("http://localhost:8080/CRM-Project/get-current-user", {
  method: "GET",
  headers: {
    "Content-Type": "application/json",
    dataType: "json",
    Authorization: "Bearer " + jwtToken,
  },
})
  .then(function (response) {
    return response.json();
  })
  .then(function (data) {
    if (data.role.id == 3) {
      alert("Không có quyền truy cập");
      window.location.href = "index.html";
    }
  })
  .catch(function (error) {
    console.log(error);
  });
