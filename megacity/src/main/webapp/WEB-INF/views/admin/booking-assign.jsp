<%@page import="com.megacity.model.Vehicle"%>
<%@page import="com.megacity.model.Driver"%>
<%@page import="java.util.List"%>
<%@ include file="/WEB-INF/components/admin/check-admin-login.jsp" %>
<%@ include file="/WEB-INF/components/java-tags.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Assign Booking</title>
    
    <jsp:include page="/WEB-INF/components/admin/admin-link-imports.jsp" />
</head>
<body>
	<jsp:include page="/WEB-INF/components/admin/admin-header.jsp" />
	<jsp:include page="/WEB-INF/components/message.jsp" />

   	<section class="form-container" style="min-height: 0;">
        <form action="<%= request.getContextPath() %>/admin/bookings/assign" method="POST">
            <h3>Assign Booking</h3>
            
            <input type="hidden" name="booking_id" value="${bookingId}" />
            <input type="hidden" name="required_seats" value="${requiredSeats}" />
            
            <select name="vehicle_id" required class="box username">
			    <option value="" disabled selected>--Assign Vehicle--</option>
			    <% 
			        List<Vehicle> availableVehicles = (List<Vehicle>) request.getAttribute("availableVehicles");
			        if (availableVehicles != null) {
			            for (Vehicle vehicle : availableVehicles) { 
			    %>
			                <option value="<%= vehicle.getId() %>">
			                    <%= vehicle.getVehicleNumber().toUpperCase() %> | 
			                    <%= vehicle.getVehicleType() %> | 
			                    <%= vehicle.getNoOfSeats() %> seats
			                </option>
			    <% 
			            } 
			        } 
			    %>
			</select>
			
			<select name="driver_id" required class="box username">
			    <option value="" disabled selected>--Assign Driver--</option>
			    <% 
			        List<Driver> availableDrivers = (List<Driver>) request.getAttribute("availableDrivers");
			        if (availableDrivers != null) {
			            for (Driver driver : availableDrivers) { 
			    %>
			                <option style="text-transform: capitalize" value="<%= driver.getId() %>">
			                    <%= driver.getName() %> | <%= driver.getLicenseNumber() %> 
			                </option>
			    <% 
			            } 
			        } 
			    %>
			</select>

			
			<input type="number" min="1" name="distance" id="distance-input" required class="box" placeholder="Enter Total Distance (KM)" />
			
			<input type="number" min="10" name="rate_per_km" id="rate-input" style="margin-bottom: 4rem;" required class="box" placeholder="Enter Rate per KM (LKR)" />
			
			<div class="label-container">
				<label>Base Fare</label>
			</div>
            <input type="number" name="base-fare" class="box" min="50" id="base-fare" required />
			
			<div class="label-container">
            	<label>Tax Rate(%)</label>
            </div>
            <input type="number" name="tax-rate" class="box" min="1" max="99" id="tax-rate" required />
            
			<div class="label-container">
				<label>Discount Rate(%)</label>
			</div>
            <input type="number" name="discount-rate" class="box" min="1" max="99" id="discount-rate" required />
            
            <div class="fare-calculated">
			    <p><strong>Gross Fare:</strong> Rs <span id="gross-fare">0.0</span></p>
			    <p><strong>Tax Amount:</strong> Rs <span id="tax-amount">0.0</span></p>
			    <p><strong>Discount Amount:</strong> Rs <span id="discount-amount">0.0</span></p>
			    <p><strong>Net Fare:</strong> Rs <span id="net-fare">0.0</span></p>
			</div>
			
            <input type="submit" value="Assign Booking" name="submit" class="btn">
        </form>
    </section>
    
   <script>
		document.addEventListener("DOMContentLoaded", function () {
		    const distanceInput = document.getElementById("distance-input");
		    const rateInput = document.getElementById("rate-input");
		    const baseFareInput = document.getElementById("base-fare");
		    const taxRateInput = document.getElementById("tax-rate");
		    const discountRateInput = document.getElementById("discount-rate");
		    
		    const grossFareDisplay = document.getElementById("gross-fare");
		    const taxAmountDisplay = document.getElementById("tax-amount");
		    const discountAmountDisplay = document.getElementById("discount-amount");
		    const netFareDisplay = document.getElementById("net-fare");
		
		    function roundToNearest10Floor(value) {
		        return Math.floor(value / 10) * 10;
		    }
		
		    function calculateFare() {
		        const distance = parseFloat(distanceInput.value);
		        const ratePerKm = parseFloat(rateInput.value);
		        const baseFare = parseFloat(baseFareInput.value) || 0;
		        const taxRate = parseFloat(taxRateInput.value) || 0;
		        const discountRate = parseFloat(discountRateInput.value) || 0;
		
		        if (!isNaN(distance) && distance > 0 && !isNaN(ratePerKm) && ratePerKm > 0) {
		            const subtotal = distance * ratePerKm;
		            const grossFare = baseFare + subtotal;
		
		            const taxAmount = (grossFare * taxRate) / 100;
		            const discountAmount = (grossFare * discountRate) / 100;
		
		            let netFare = (grossFare + taxAmount) - discountAmount;
		            netFare = roundToNearest10Floor(netFare);
		
		            grossFareDisplay.textContent = grossFare.toFixed(2);
		            taxAmountDisplay.textContent = taxAmount.toFixed(2);
		            discountAmountDisplay.textContent = discountAmount.toFixed(2);
		            netFareDisplay.textContent = netFare.toFixed(2);
		        }
		    }
		
		    distanceInput.addEventListener("input", calculateFare);
		    rateInput.addEventListener("input", calculateFare);
		    baseFareInput.addEventListener("input", calculateFare);
		    taxRateInput.addEventListener("input", calculateFare);
		    discountRateInput.addEventListener("input", calculateFare);
		});
	</script>

	
	<script src="<%= request.getContextPath() %>/assets/js/admin-script.js"></script>
</body>
</html>