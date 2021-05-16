<%@ include file="common/header.jspf"%>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<div class="container" style="height: 60px;"> 
	</div>
	<article>

	<h1 class="text-center p-5">Medical Representatives Schedule</h1>

	<div class="row">
		<div class="col">
			<!-- Dummy tag for maintaining structure of cards -->
		</div>
		<div class="col card bg-light text-center" style="width: 15rem;">
			<img class="card-img-top"  style="margin-top: 15px;"
				src="https://images.unsplash.com/photo-1563371386-187140095137?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80"
				alt="img planner and calender">
			<div class="card-body">
				<h5 class="card-title">Select Date</h5>
				<p class="card-text">To view the schedule of the representatives
				</p>


				<form action="/user/createSchedule" method="post">

					<div style="color: red; margin: 20px">
						<c:choose>
							<c:when test="${errorMessage1}">
							Please Enter today's / upcoming date
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
					</div>

					<div style="color: red; margin: 20px">
						<c:choose>
							<c:when test="${errorMessage2}">
							Date out of service
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
					</div>


					<div style="color: red"></div>
					<div>
						<div>
							<i style="font-size: 24px; margin: 0 0 5px 0;" class="fa">&#xf073;
							</i> <span style="padding: 5px;">Date of meeting</span> <input type="date" name="scheduleStartDate"
								value="" required="true">
						</div>
					</div>
					<div>
						<div>
							<input type="submit" class="btn btn-info m-3" name="submit"
								value="View schedule">
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="col">
			<!-- Dummy tag for maintaining structure of cards -->
		</div>
	</div>

</article>

<%@ include file="common/footer.jspf"%>