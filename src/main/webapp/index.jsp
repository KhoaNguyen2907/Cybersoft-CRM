<%@ page import="cybersoft.java18.backend.CRM_Project.utils.UrlUtil" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<c:redirect url="<%=request.getContextPath() + UrlUtil.HOME%>"></c:redirect>
</body>
</html>