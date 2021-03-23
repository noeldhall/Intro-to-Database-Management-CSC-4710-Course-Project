<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<title>Account Page</title>
</head>
<body>
	<div align="center">
		<form action="community" method="post"><input type="submit" value="Community Page"/></form>
		<form action="logout" method="post"><input type="submit" value="logout" /></form>
		<h1>Welcome ${username}</h1>
		
		<button onclick="postForm()">post an image</button>
		<div id="postImageForm">
		<br>
			<form id="postimage" method="post" action="postForm">
				<label for="urlTxt">image URL: </label>
				<input type="text" id="urlTxt" name="url" placeholder="URL">
				<label for="descTxt">Description: </label>
				<input type="text" id="descTxt" name="description" placeholder="description">
				<label for="tagTxt">Tags: </label>
				<input type="text" id="tagTxt" name="tags" placeholder="tags">
				<input type="submit" value="Post Image">
			</form>
		</div>
		<script>
		function postForm(){
			var x = document.getElementById("postImageForm");
			if(x.style.display === "none"){
				x.style.display = "block";
			}
			else{
				x.style.display = "none";
			}
		}
		</script>
		
		<c:forEach items="${listImages}" var="Image">
			<h4>posted by ${Image.postUser} at <time>${Image.timeStamp}</time></h4>
			<img src=${Image.url} alt="image">
			<p>Likes: ${Image.likeCount}, Description: ${Image.description}</p>
			<form action="like" method="post"><input type="hidden" name="imageId" value="${Image.id}"><input type="submit" value="Like"/></form>
			<form action="dislike" method="post"><input type="hidden" name="imageId" value="${Image.id}"><input type="submit" value="Unlike"/></form>
			<c:if test="${Image.postUser == username }">
				<form action="deleteImage" method="post"><input type="hidden" name="imageId" value="${Image.id}"><input type="submit" value="Delete Image"/></form>
				<form action="updateImage" method="post"><input type="hidden" name="imageId" value="${Image.id}"><input type="text" name="description" size="45"><input type="submit" value="Update Image"/></form> 
			</c:if>
			
		</c:forEach>
	</div>
</body>
</html>