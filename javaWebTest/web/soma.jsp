<%-- 
    Document   : soma.jsp
    Created on : 19/10/2023, 09:45:59
    Author     : rodri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <div class="container text-center">
            <div class = "row pt-5">
                <div class = "col">
                    <form action="/javaWebTest/api/soma" method="post">
                        <h4>Numero 1</h4>
                        <input type="number" name="num1" value="<%= request.getAttribute("num1")%>"/>
                        <h4>Numero 2</h4>
                        <input type="number" name="num2" value="<%= request.getAttribute("num2")%>"/>
                        <br/>
                        <br/>
                        <input type="submit" class="btn btn-dark" value="somar"/>
                        <br/>
                        <%
                            int soma = 0;
                            if(request.getAttribute("soma") != null){
                                 soma = (int) request.getAttribute("soma");
                            }
                         %>  
                        <br/> 
                        <% if (soma != 0) { %>
                            <h6><%= request.getAttribute("num1") %> + <%= request.getAttribute("num2") %> = <%= soma %></h6>
                        <% } %>                       
                    </form>
                </div>
                         
            </div>
        </div>              
    </body>
</html>
