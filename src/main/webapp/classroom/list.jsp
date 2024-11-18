<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11/19/2024
  Time: 12:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Class Listing</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
  <h1>List Class</h1>
  <a href="?action=new">Create a new class</a>
  <table class="table">
    <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th scope="col"></th>
      <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <% for(Classroom c : (List<Classroom>)request.getAttribute("classes")){ %>
    <tr>
      <th scope="row"><%= c.getId() %></th>
      <td><%= c.getName() %></td>
      <td><a href="?action=edit&id=<%= c.getId() %>">Edit</a></td>
      <td>
        <form action="?action=delete" method="post">
          <input type="hidden" value="<%=c.getId()%>" name="id"/>
          <button onclick="return confirm('Are you sure delete class <%= c.getName()%>')" type="submit" class="btn btn-danger">Delete</button>
        </form>
      </td>
    </tr>
    <% } %>
    </tbody>
  </table>
</div>
</body>
</html>