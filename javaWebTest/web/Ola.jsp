<%-- 
    Document   : Ola.jsp
    Created on : 19/10/2023, 09:24:23
    Author     : rodri
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="Testes.Teste"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            ArrayList<String> list = new ArrayList<>();
            for(int i = 0; i<10; i++){
                list.add("ola");
            }
            for(String s: list){
                out.print(Teste.mudaLinha(s));
            }
        %>
    </body>
</html>
