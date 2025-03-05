<%@ include file="/WEB-INF/components/admin/check-admin-login.jsp" %>
<%@ include file="/WEB-INF/components/java-tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Vehicles</title>
    
    <jsp:include page="/WEB-INF/components/admin/admin-link-imports.jsp" />
</head>
<body>

	<jsp:include page="/WEB-INF/components/admin/admin-header.jsp" />
	<jsp:include page="/WEB-INF/components/message.jsp" />

	<section class="form-container" style="min-height: 0;">

	     <form action="<%= request.getContextPath() %>/admin/vehicles" method="POST">
	        <h3>create a vehicle</h3>
	        <input type="text" required minlength="3" name="vehicle_number" placeholder="Enter the vehicle number" maxlength="10" class="box username">
	        
	        <select name="vehicle_type" required class="box username">
			    <option value="" disabled selected>--Select vehicle type--</option>
			    <option value="Car">Car</option>
			    <option value="Van">Van</option>
			    <option value="SUV">SUV</option>
			</select>
			
	        <input type="number" required min="1" max="12" name="seats" placeholder="Enter the number of seats" class="box username">
	        <input type="submit" value="create" name="submit" class="btn">
	     </form>
   	</section>
   	
   	<section>
     	<div class="table-container">
            <h1>Registered Vehicles</h1>
            <div class="table">
                <table>
                    <thead>
                        <tr class="heading">
                            <th>#</th>
                            <th>Number</th>
                            <th>Type</th>
                            <th>Seats</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="vehicle" items="${vehicles}" varStatus="status">
		                    <tr>
		                        <td>${status.index + 1}</td>
		                        <td style="text-transform: uppercase;">${vehicle.vehicleNumber}</td>
		                        <td>${vehicle.vehicleType}</td>
		                        <td>${vehicle.noOfSeats}</td>
		                    </tr>
		                </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
     </section>

	
	<script src="<%= request.getContextPath() %>/assets/js/admin-script.js"></script>
</body>
</html>