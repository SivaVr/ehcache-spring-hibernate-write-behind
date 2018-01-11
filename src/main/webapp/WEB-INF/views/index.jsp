<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<script type="text/javascript" src="resources/jquery-1.9.1.js"></script>
<script>
	$(document).ready(
			function() {
				hideAll();
				$("#add_hero").click(function() {
					hideAll();
					$(".add_hero").show();
				});
				$("#view_hero").click(
						function() {
							hideAll();
							$(".view_hero").show();
							$.ajax({
								url : 'viewHeros',
								type : 'POST',
								async : false,
								success : function(data) {
									//console.log(data);
									var result = JSON.parse(data);
									//console.log(result,result.length);
									$("#fill_content").html("");
									for (var i = 0; i < result.length; i++) {
										var heros = result[i];
										//console.log(heros);
										var html = [ "<tr>", "<td>", heros.id,
												"</td>", "<td>", heros.name,
												"</td>", "<td>", heros.movie,
												"</td>", "<td>", heros.rank,
												"</td>", "<td>",
												heros.description, "</td>",
												"<td>", heros.status, "</td>",
												"</tr>" ].join("");
										$("#fill_content").append(html);
									}

								}
							});
						});
				$("#view_hero_i").click(function() {
					hideAll();
					$(".view_hero").show();
					$(".view_hero_i").show();

				});
			});
	function hideAll() {
		$(".add_hero").hide();
		$(".view_hero").hide();
		$(".view_hero_i").hide();
	}
	function saveHero() {
		if ($.trim($("#name").val()) == "" || $.trim($("#movie").val()) == ""
				|| $.trim($("#rank").val()) == ""
				|| $.trim($("#description").val()) == "") {
			alert("Please Fill Required Fields(* mandatory fields).");

		} else {
			$.ajax({
				url : 'addNewHero',
				type : 'POST',
				async : false,
				data : {
					name : $.trim($("#name").val()),
					movie : $.trim($("#movie").val()),
					rank : $.trim($("#rank").val()),
					description : $.trim($("#description").val()),
					status : $("#status").val()
				},
				success : function(data) {
					alert(data);
				}
			});
		}
	}
	function getHeroById() {
		var id = parseInt($.trim($("#hero_id").val()));
		console.log(!isNaN(id));
		if (!isNaN(id)) {
			$.ajax({
				url : 'viewHerosById',
				type : 'POST',
				async : false,
				data : {
					id : id
				},
				success : function(data) {
					//console.log(data);
					var result = JSON.parse(data);
					
					$("#fill_content").html("");
					for (var i = 0; i < result.length; i++) {
						var heros = result[i];
						//console.log(heros);
						var html = [ "<tr>", "<td>", heros.id, "</td>", "<td>",
								heros.name, "</td>", "<td>", heros.movie,
								"</td>", "<td>", heros.rank, "</td>", "<td>",
								heros.description, "</td>", "<td>",
								heros.status, "</td>", "</tr>" ].join("");
						$("#fill_content").append(html);
					}

				}
			});
		}
	}
</script>
</head>
<body>
	<form action="#">
		<h2>Super Heros</h2>
		<p>
		<h4>Menu:</h4>
		</p>
		<ul>
			<li><input type="button" id="add_hero" value="Add Hero" /></li>
			<br />
			<li><input type="button" id="view_hero" value="View Heros" /></li>
			<br />
			<li><input type="button" id="view_hero_i"
				value="View Heros By Id" /></li>
		</ul>

		<br /> <br />
		<div class="add_hero">
			<table border="1" style="border-collapse: collapse;">
				<thead>
					<tr>
						<th colspan="2">Enter Hero Details</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><strong>Hero Name*:</strong></td>
						<td><input type="text" id="name" name="name" required /></td>
					</tr>
					<tr>
						<td><strong>Movie*:</strong></td>
						<td><input type="text" id="movie" name="movie" required /></td>
					</tr>
					<tr>
						<td><strong>Hero Rank*:</strong></td>
						<td><input type="number" id="rank" name="rank" required /></td>
					</tr>
					<tr>
						<td><strong>Hero Description*:</strong></td>
						<td><textarea id="description" name="description" required></textarea></td>
					</tr>
					<tr>
						<td><strong>Status</strong></td>
						<td><Select id="status" name="status" required>
								<option value="Active">Active</option>
								<option value="Deactive">Deactive</option>
						</Select></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: center;"><input
							type="button" value="Save" onclick="saveHero()" /> <input
							type="reset" value="Clear" /></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="view_hero">
			<table class="view_hero_i" border="1"
				style="border-collapse: collapse;">
				<tr>
					<td><strong>Hero Id:</strong></td>
					<td><input type="number" id="hero_id" /></td>
					<td><input type="button" value="View" onclick="getHeroById()" /></td>
				</tr>
			</table>
			<table border="1" style="border-collapse: collapse;" cellpadding="8"
				cellspacing="8">
				<thead>
					<tr>
						<th>Hero ID</th>
						<th>Hero Name</th>
						<th>Movie</th>
						<th>Hero Rank</th>
						<th>Hero Description</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody id="fill_content">
				</tbody>
			</table>

		</div>

	</form>
</body>
</html>