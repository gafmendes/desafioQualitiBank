<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">  
	<title>Qualiti Internet Bank</title>
</head>
<body>
	<center>
		<h1>Qualiti Internet Bank</h1>
        <h2>
        	<a href="${pageContext.request.contextPath}/client?action=new">Novo cliente</a>
        	&nbsp;&nbsp;&nbsp;
        </h2>
	</center>
    <div align="center">
        <table class="table table-striped table-bordered" style="width:100%">
            <tr class="thead-dark">
                <th scope="col">ID</th>
                <th scope="col">Nome</th>
                <th scope="col">Email</th>
                <th scope="col">Ações</th>
            </tr>
            <c:forEach var="customer" items="${client}">
                <tr>
                    <td scope="row"><c:out value="${client.id}" /></td>
                    <td><c:out value="${client.name}" /></td>
                    <td><c:out value="${client.email}" /></td>
                    <td>
                    <a href="${pageContext.request.contextPath}/client?action=edit&id=<c:out value='${client.id}'/>">Atualizar</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                   	<a href="${pageContext.request.contextPath}/client?action=delete&id=<c:out value='${client.id}'/>">Deletar</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                   	<a href="${pageContext.request.contextPath}/client?action=list&client_id=<c:out value='${client.id}'/>">Listar contas</a>
                    	                	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>  
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>  
</body>
</html>
