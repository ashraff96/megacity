<%@page import="java.util.List"%>
<div id="message_box"></div>
<script>
let message_box = document.getElementById("message_box");
let success_icon = "<i class='fas fa-check-circle'></i>";
let error_icon = "<i class='fas fa-xmark-circle'></i>";

function show_message(msg, type) {
    msg = msg.charAt(0).toUpperCase() + msg.slice(1);
    let message = document.createElement("div");

    switch(type) {
        case "success":
            message.innerHTML = success_icon + msg;
            break;
        case "error":
            message.innerHTML = error_icon + msg;
            break;
        default:
            message.innerHTML = msg;
    }

    message.classList.add("message");
    message.classList.add(type);

    message.addEventListener("click", function() {
        message.remove();
    });

    message_box.appendChild(message);

    setTimeout(()=>{
        message.remove();
    }, 5000);
}</script>

 <% 
 	List<String> messages = (List<String>) request.getAttribute("messages");
    String messageType = (String) request.getAttribute("messageType");
 %>

 <% if (messages != null && messageType != null) { %>
    <% for (String msg : messages) { %>
        <script>
        	show_message("<%= msg %>", "<%= messageType %>");
        </script>
    <% } %>
<% } %>