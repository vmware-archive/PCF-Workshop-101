<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html class="no-js" lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Pivotal CF Workshop | All Attendees</title>
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
        <h3>All Attendees</h3>
        <hr/>
      </div>
    </div>
	
    <div class="row">
      <div class="large-12 columns">
        <table>
          <thead>
            <tr>
                <th> </th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Address</th>
              <th>City</th>
              <th>State</th>
              <th>Zip Code</th>
              <th>Phone</th>
              <th>Email Address</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${attendees}" var="attendee">
            <tr>
                <td><a href="delete?attendeeId=${attendee.id}"><img alt="Delete" src="resources/img/delete.jpeg" width="20" height="20"></a></td>
                <td><a href="sessions?attendeeId=${attendee.id}">${attendee.firstName}</a></td>
                <td>${attendee.lastName}</td>
                <td>${attendee.address}</td>
                <td>${attendee.city}</td>
                <td>${attendee.state}</td>
                <td>${attendee.zipCode}</td>
                <td>${attendee.phoneNumber}</td>
                <td>${attendee.emailAddress}</td>
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
