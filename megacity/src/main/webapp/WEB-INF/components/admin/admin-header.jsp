<%@ page import="com.megacity.model.Admin" %>
<header class="header">

   <section class="flex">

      <a href="<%= request.getContextPath() %>/admin" class="logo">Admin<span>Panel</span></a>

      <nav class="navbar">
         <a href="<%= request.getContextPath() %>/admin">home</a>
         <a href="<%= request.getContextPath() %>/admin/bookings?status=pending">bookings</a>
         <a href="<%= request.getContextPath() %>/admin/vehicles">vehicles</a>
         <a href="<%= request.getContextPath() %>/admin/drivers">drivers</a>
      </nav>

      <div class="icons">
         <div id="user-btn" class="fas fa-user"></div>
         <div id="menu-btn" class="fas fa-bars"></div>
      </div>
      
      <div class="profile">
          <form action="<%= request.getContextPath() %>/admin/logout" method="POST" style="display:inline;">
              <Button type="submit" class="delete-btn" onclick="return confirm('Logout as Admin?');">Logout</Button>
          </form>
       </div>

   </section>

</header>