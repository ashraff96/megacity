<%@ include file="/WEB-INF/components/is-not-logged-in.jsp" %>
<%@ include file="/WEB-INF/components/java-tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>

    <jsp:include page="/WEB-INF/components/links-import.jsp" />
</head>
<body>

	<jsp:include page="/WEB-INF/components/header.jsp" />
	<jsp:include page="/WEB-INF/components/message.jsp" />
	
    <div class="outter-box">
        <div class="inner-box">
            <form action="<%= request.getContextPath() %>/register" method="POST">
                <h3>Register</h3>
                
                <div class="divider">
                    <div>
                        <div class="input-container">
                            <label>Name</label>
                            <input type="text" value="${name}" minlength="3" maxlength="55" required name="name">
                        </div>
                        <div class="input-container">
                            <label>Address</label>
                            <input type="text" value="${address}" maxlength="255" required name="address">
                        </div>
                        <div class="input-container">
                            <label>NIC</label>
                            <input type="number" value="${nic}" minlength="9" maxlength="12" onkeypress="if(this.value.length == 12) return false;" required name="nic">
                        </div>
                        <div class="input-container">
                            <label>Telephone</label>
                            <input type="number" value="${telephone}" minlength="9" maxlength="10" onkeypress="if(this.value.length == 10) return false;" required name="telephone">
                        </div>
                    </div>

                    <div class="credentials">
                        <div class="input-container">
                            <label>Username</label>
                            <input type="text" value="${username}" minlength="3" maxlength="25" onkeypress="if(this.value.length == 25) return false;" required name="username">
                        </div>
                        <div class="input-container">
                            <label>Password</label>
                            <input type="password" minlength="5" maxlength="255" required name="password">
                        </div>
                    </div>
                </div>

                <input type="submit" value="Register" class="button btn1">
            </form>

            <p>Already have an account? <a href="<%= request.getContextPath() %>/login">Login</a></p>
        </div>
    </div>

	<script src="<%= request.getContextPath() %>/assets/js/script.js"></script>
</body>

</html>