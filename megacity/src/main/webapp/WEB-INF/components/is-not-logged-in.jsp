<%
    if (session != null && session.getAttribute("user") != null) {
        response.sendRedirect(request.getContextPath() + "/");
        return;
    }
%>