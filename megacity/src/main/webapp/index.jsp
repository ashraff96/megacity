<%@ include file="/WEB-INF/components/java-tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    
    <jsp:include page="/WEB-INF/components/links-import.jsp" />
</head>
<body>

	<jsp:include page="/WEB-INF/components/header.jsp" />
	<jsp:include page="/WEB-INF/components/message.jsp" />
	<jsp:include page="/WEB-INF/components/homepage.jsp" />
	

	
	
	<script src="<%= request.getContextPath() %>/assets/js/script.js"></script>
</body>
</html>