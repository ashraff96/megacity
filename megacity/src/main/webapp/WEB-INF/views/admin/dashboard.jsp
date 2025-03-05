<%@ page import="com.megacity.model.Admin" %>
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

	<section>
		<div class="box-container">
	
	      <div class="box">
	      <% Admin admin = (Admin) session.getAttribute("admin"); %>
	         <h3>Welcome!</h3>
	         <p style="text-transform: capitalize;">${admin.username}</p>
	         <div>
	            <form action="<%= request.getContextPath() %>/admin/logout" method="POST" style="display:inline;">
	              <Button type="submit" class="btn" onclick="return confirm('Logout as Admin?');">Logout</Button>
	            </form>
	         </div>
	      </div>
	      
		  <div class="box">
	         <h3>${acceptedCount}</h3>
	         <p>Accepted Bookings</p>
	         <a href="<%= request.getContextPath() %>/admin/bookings?status=accepted" class="btn">see accepted bookings</a>
	      </div>
	      
	      <div class="box">
	         <h3>${pendingCount}</h3>
	         <p>Pending Bookings</p>
	         <a href="<%= request.getContextPath() %>/admin/bookings?status=pending" class="btn">see pending bookings</a>
	      </div>
	      
	      <div class="box">
	         <h3>${declinedCount}</h3>
	         <p>Declined Bookings</p>
	         <a href="<%= request.getContextPath() %>/admin/bookings?status=declined" class="btn">see declined bookings</a>
	      </div>
	      
	      <div class="box">
	         <h3>${driverCount}</h3>
	         <p>Drivers Registered</p>
	         <a href="<%= request.getContextPath() %>/admin/drivers" class="btn">see drivers</a>
	      </div>
	      
	      <div class="box">
	         <h3>${vehicleCount}</h3>
	         <p>Vehicles Registered</p>
	         <a href="<%= request.getContextPath() %>/admin/vehicles" class="btn">see vehicles</a>
	      </div>
	      
		</div>
	</section>
	
	<script src="<%= request.getContextPath() %>/assets/js/admin-script.js"></script>
</body>
</html>