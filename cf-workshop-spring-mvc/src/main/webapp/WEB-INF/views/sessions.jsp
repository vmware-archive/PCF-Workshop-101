<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html class="no-js" lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Pivotal CF Workshop | Sessions</title>
    <link rel="stylesheet" href="resources/css/foundation.css" />
    <script src="resources/js/modernizr.js"></script>
  </head>
  <body>

    <nav class="top-bar" data-topbar>
      <ul class="title-area">
        <li class="name">
          <h1><a href="./">Pivotal CF</a></h1>
        </li>
      </ul>
      <section class="top-bar-section">
        <ul class="right">
          <li><a href="./">Home</a></li>
          <li><a href="attendees">Attendees</a></li>
        </ul>
      </section>
    </nav>

    <div class="row">
      <div class="large-12 columns">
        <h3>Sessions for '${attendee.firstName} ${attendee.lastName}'</h3>
        <hr/>
      </div>
    </div>

    <div class="row">
      <div class="large-12 columns">
        <table>
          <thead>
            <tr>
              <th>Session Name</th>
              <th>Date</th>
              <th>Completed?</th>
            </tr>
          </thead>
          <tbody>
          	<c:forEach items="${sessions}" var="session">
          	<tr>
          	  <td>${session.name}</td>
          	  <td><fmt:formatDate value="${session.date}" pattern="MM/dd/yyyy"/></td>
          	  <td>${session.completed ? '&#x2713' : ''}</td>
          	</tr>
          	</c:forEach>
           </tbody>
        </table>
      </div>
    </div>

    <script src="resources/js/jquery.js"></script>
    <script src="resources/js/foundation.min.js"></script>
    <script>
      $(document).foundation();
    </script>

    </body>
</html>
