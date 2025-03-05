<%@ include file="/WEB-INF/components/admin/check-admin-login.jsp" %>
<%@ include file="/WEB-INF/components/java-tags.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - ${status} Bookings</title>
    
    <jsp:include page="/WEB-INF/components/admin/admin-link-imports.jsp" />
</head>
<body>
	<jsp:include page="/WEB-INF/components/admin/admin-header.jsp" />
	<jsp:include page="/WEB-INF/components/message.jsp" />

   	<section>
   		<h1 class="heading">${status} Bookings</h1>
   	
        <div class="booking-container">
            <c:forEach var="booking" items="${bookings}">
                <div class="booking">
                    <p>Booking Number: <span>${booking.id}</span></p>
                    <p>Customer Name: <span>${booking.user != null ? booking.user.name : 'N/A'}</span></p>
                    
                    <c:if test="${status eq 'accepted'}">
                        <p>Driver Name: <span style="text-transform: capitalize">${booking.driver != null ? booking.driver.name : 'N/A'}</span></p>
                        <p>Vehicle Number: <span style="text-transform: uppercase;">${booking.vehicle != null ? booking.vehicle.vehicleNumber : 'N/A'}</span></p>
                    </c:if>
                    
                    <p>Pickup Location: <span>${booking.pickupLocation}</span></p>
                    <p>Destination: <span>${booking.destination}</span></p>
                    <p>Booked Time: 
                        <span>${fn:substringBefore(booking.bookingDateTime, 'T')}  
                              ${fn:substringAfter(booking.bookingDateTime, 'T')}</span>
                    </p>
                    <p>Pickup Time: <span>${booking.pickupDateTime}</span></p>
                    <p>Seats: <span>${booking.noOfSeats}</span></p>
                    <p>Message: <span>${booking.message}</span></p>
                    
                    <c:if test="${status eq 'accepted'}">
                    	<p>Fare: Rs <span>${booking.fare}</span></p>
                    </c:if>
                    
                    <c:if test="${booking.bookingStatus == 'pending'}">
	                    <div class="flex-btn" style="justify-content: center">
	                        <form action="<%= request.getContextPath() %>/admin/bookings" method="POST">
	                            <input type="hidden" name="id" value="${booking.id}" />
	                            <button type="submit" class="delete-btn" onclick="return confirm('Are you sure to decline booking number ${booking.id}?');">Decline</button>
	                        </form>
	
	                        <a href="<%= request.getContextPath() %>/admin/bookings/assign?id=${booking.id}" class="btn">Assign</a>
	                    </div>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </section>
	
	<script src="<%= request.getContextPath() %>/assets/js/admin-script.js"></script>
</body>
</html>