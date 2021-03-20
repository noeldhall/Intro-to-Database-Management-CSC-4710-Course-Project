<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head><title>Account Page</title></head>
<body>
	<div align="center">
	<form action="feed" method="post"><input type="submit" value="return to feed"/></form>
	<form action="logout" method="post"><input type="submit" value="logout"></form>
	<table>
		<tr>
			<th>Name </th> <th> Email </th> <th>follow/unfollow</th>
		</tr>
		<c:forEach items="${userList}" var="Account" varStatus="i">
			<tr>
				<td>${Account.firstName} ${Account.lastName}</td>
				<td>${Account.email}</td>	
				<!-- TODO link the follow/unfollow table here relative to current user -->
				<td> <form action="follow?email=${Account.email}&status=${followList[i.index]}" method="post"><button> 
				<c:choose> 
					<c:when test="${followList[i.index]}" > 
						<c:out default="unfollow" value="unfollow"/> 
					</c:when> 
					<c:otherwise>
						<c:out default="follow" value="follow"/>
					</c:otherwise> 
				</c:choose>
				</button>
				</form></td>
			</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>