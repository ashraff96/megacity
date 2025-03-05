<%@ include file="/WEB-INF/components/admin/check-admin-login.jsp" %>
<%@ include file="/WEB-INF/components/java-tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Dashboard</title>
    
    <jsp:include page="/WEB-INF/components/admin/admin-link-imports.jsp" />
</head>
<body>

	<jsp:include page="/WEB-INF/components/admin/admin-header.jsp" />
	<jsp:include page="/WEB-INF/components/message.jsp" />

	

	
	<script src="<%= request.getContextPath() %>/assets/js/admin-script.js"></script>
</body>
</html>