<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html class="no-js" lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Pivotal CF Workshop | Welcome</title>
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
        <h3>Welcome to Pivotal CF</h3>
        <hr/>
      </div>

    <div class="row">
      <div class="large-12 columns">
      	<div class="panel">
          <h4>Environment Information</h4><br/>
          <p>The current server time is <em>${serverTime}</em></p>
          <p>The application port is <em>${port}</em></p>
          <p>The instance ID is <em>${vcapApplication['instance_id']}</em></p>
          <p>The instance index is <em>${vcapApplication['instance_index']}</em></p>
          <p>The instance was started at <em>${vcapApplication['started_at']}</em></p>
          <p>The bound services are <em>${vcapServices}</em></p>
	    </div>
        <hr/>
      </div>
    </div>
    
    <div class="row">
      <div class="large-12 columns">
        <!-- This simply refreshes the page to demonstrate changed (or unchanged) ports -->
        <a href="javascript:window.location.reload();" class="medium button">Refresh</a>
        <!-- This link should be changed to the application's kill action -->
        <a href="kill" class="medium alert button">Kill</a>
      </div>
    </div>

    <script src="resources/js/jquery.js"></script>
    <script src="resources/js/foundation.min.js"></script>
    <script>
      $(document).foundation();
    </script>

    </body>
</html>
