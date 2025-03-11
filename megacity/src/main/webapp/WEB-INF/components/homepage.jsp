<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>mega city cab</title>
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" integrity="sha384-k6RqeWeci5ZR/Lv4MR0sA0FfDOM1cR3z1eY5l5NZ/zS7j/5+InqX/c1Fg" crossorigin="anonymous">
    <script defer src="script.js"></script>
    <style>
        /* General styles */
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            line-height: 1.6;
        }

        /* Navbar styles */
        .navbar {
            background-color: #068b0d;
            padding: 15px;
            color: white;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .navbar h1 {
            margin: 0;
        }
        .navbar nav ul {
            list-style: none;
            margin: 0;
            padding: 0;
            display: flex;
        }
        .navbar nav ul li {
            margin-left: 20px;
        }
        .navbar nav ul li a {
            color: white;
            text-decoration: none;
        }
        .btn {
            background-color: #28a745;
            padding: 10px 15px;
            border-radius: 5px;
            color: white;
            text-decoration: none;
        }

        /* Hero section styles */
        .hero {
            background: linear-gradient(to right, #ade6b2, #a2f797);
            padding: 100px 20px;
            text-align: center;
            color: #333;
            position: relative;
        }
        .hero h1 {
            font-size: 2.5rem;
            margin: 0;
            text-align: center;
        }
        .hero p {
            font-size: 1.2rem;
            margin: 20px 0;
            text-align: center;
        }
        .hero-buttons {
            margin-top: 20px;
        }

        /* Laptop frame */
        .laptop-frame {
            width: 80%;
            max-width: 1200px;
            margin: 0 auto;
            border: 10px solid #333;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
            position: relative;
        }
        .laptop-screen {
            padding: 30px;
            background: linear-gradient(to right, #ade6b2, #a2f797);
            border-radius: 5px;
        }

        /* Service cards styles */
        .why-choose {
            padding: 50px 20px;
            text-align: center;
        }
        .service-cards {
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
            margin: 0 auto;
            max-width: 1200px;
        }
        .card {
            width: 250px;
            margin: 15px;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 8px;
            text-align: center;
            transition: transform 0.2s;
        }
        .card:hover {
            transform: scale(1.05);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .card i {
            color: #0eb61c;
            margin-bottom: 10px;
        }

        /* Footer styles */
        footer {
            background-color: #333;
            color: white;
            padding: 20px 0;
            margin-top: 20px;
        }
        .footer-content {
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
            max-width: 1200px;
            margin: 0 auto;
        }
        .footer-brand, .footer-links, .footer-contact {
            flex: 1;
            min-width: 200px;
            padding: 10px;
        }
        .footer-links ul {
            list-style: none;
            padding: 0;
        }
        .footer-links li {
            margin: 5px 0;
        }
        .footer-links a {
            color: white;
            text-decoration: none;
        }
        .footer-links a:hover {
            text-decoration: underline;
        }
        .social-icons a {
            margin: 0 5px;
            display: inline-block;
        }
        .footer-bottom {
            text-align: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>
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

    <main>
        <section class="hero">
            <div class="laptop-frame">
                <div class="laptop-screen">
                    <h1>providing safe, comfortable, and <br><span class="highlight">professional transportation.</span></h1>
                    <p>Our mission is to help our clients make distinctive, lasting, and substantial improvements in their performance and to build a great firm that attracts, develops, excites, and retains exceptional people.</p>
                    <div class="hero-buttons">
                        <a href="booking.html" class="btn">Book a CAB</a>
                        <a href="services.html" class="btn">View Services</a>
                    </div>
                </div>
            </div>
        </section>

        <section class="why-choose">
            <h2>Why Choose MegaCityCAB?</h2>
            <p>We leading financial services provider in SriLanka</p>
            <div class="service-cards">
                <div class="card">
                    <i class="fas fa-user-tie fa-3x"></i>
                    <h3>Taxi Services</h3>
                    <p>Traditional taxi services where users can book a ride either through a website or an app. This might involve contacting the taxi company directly or using an aggregator service.</p>
                </div>
                <div class="card">
                    <i class="fas fa-calendar-alt fa-3x"></i>
                    <h3>Luxury and Executive Car Services
                    </h3>
                    <p>A premium service for business clients or those looking for high-end transportation, such as luxury cars, limousines, or executive vehicles.</p>
                </div>
                <div class="card">
                    <i class="fas fa-cogs fa-3x"></i>
                    <h3>Scheduled Ride Services</h3>
                    <p>This service allows users to book a cab for a future time, offering more convenience for trips that need advanced planning.</p>
                </div>
            </div>
        </section>
    </main>

    <footer>
        <div class="footer-content">
            <div class="footer-brand">
                <h2>MegaCity</h2>
                <p>To be the most trusted and innovative ride-hailing service, enhancing daily commutes with cutting-edge technology, excellent customer service, and a commitment to sustainability.</p>
                <div class="social-icons">
                    <a href="#"><i class="fab fa-facebook-f"></i></a>
                    <a href="#"><i class="fab fa-twitter"></i></a>
                    <a href="#"><i class="fab fa-linkedin-in"></i></a>
                </div>
            </div>
            <div class="footer-links">
                <h3>Quick Links</h3>
            </div>
            <div class="footer-contact">
                <h3>Contact</h3>
                <p>Email: contact@megacity.com</p>
                <p>Phone: (555) 123-4567</p>
                <p>Address: 123 Plaza city, SriLanka</p>
            </div>
        </div>
        <p class="footer-bottom">&copy; 2025 MegaCity. All Rights Reserved.</p>
    </footer>
    
</body>
</html>
