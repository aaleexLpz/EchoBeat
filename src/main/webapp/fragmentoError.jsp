<% String mensajeError = (String)request.getAttribute("error"); %>
<% if(mensajeError != null) { %>
<body onload = "alert('<%=mensajeError%>');">
<% } else { %>
<body>
<% } %>