<%@ page import="com.megacity.model.User" %>
<header id="header">
    	<div class="header-container container">

           <a href="<%= request.getContextPath() %>">
               <img src="<%= request.getContextPath() %>/assets/images/logo2.png" alt="">
           </a>

           <nav id="nav">
               <i class="ri-close-line" id="close"></i>

               <div class="nav-links hover-underline-animation">
                   <div class="nav-link">
                       <a href="<%= request.getContextPath() %>">Home</a>
                   </div>
                   <div class="nav-link">
                       <a href="<%= request.getContextPath() %>/booking">Book</a>
                   </div>
                   <div class="nav-link">
                       <a href="<%= request.getContextPath() %>/my-bookings">My Bookings</a>
                   </div>
               </div>
           </nav>

           <div class="menu-container">
               <i class="ri-user-3-line" id="user"></i>
               <i class="ri-menu-line" id="menu"></i>
           </div>

           <div class="profile">
           	   <%
            	 if (session.getAttribute("user") != null) {
            		User user = (User) session.getAttribute("user");
        	   %>
	               <div>
	                   <p><%= user.getName() %></p>
	               </div>
        	   <%
                 }
        	   %>
               <div class="menu-buttons">
               <%
            	 if (session.getAttribute("user") != null) {
        	   %>
		            <form action="<%= request.getContextPath() %>/logout" method="POST" style="display:inline;">
                        <button type="submit" class="button btn2" onclick="return confirm('logout from this website?');">Logout</button>
                    </form>
		       <%
                 } else {
        	   %>
		            <a href="<%= request.getContextPath() %>/login" class="button btn1">Login</a>
		            <a href="<%= request.getContextPath() %>/register" class="button btn2">Register</a>
		       <%
		         }
		       %>
               </div>
           </div>

       </div>
   </header>