$(document).ready(function() {
	$("body").on("click", ".delete", function() {
		deleteBook($(this));
	})

	function deleteBook(button) {
		let parentId = button.parent().parent().attr("id");
		$.ajax({
			type: "DELETE",
			contentType: "application/json",
			url: "/api/lessons/" + parentId,
			dataType: 'json',
			caches: false,
			timeout: 600000,
			success: function(data) {
				$("#" + data["id"]).empty();
				$("#" + data["id"]).append('<div><button class="create">Create</button></div>');
				let newId = $("#" + data["id"]).attr("class");
				$("#" + data["id"]).attr("id", newId);
			},
			error: function(e) {
			}
		})
	}


	$("td").on("click", ".create", function() {
		addBook($(this));
	})

	function addBook(button) {

		let parentId = button.parent().parent().attr("id");
		$("#" + parentId).empty();
		let htmlString = '<div><input type="text" class="name" placeholder="Lesson name"></input></div>';
		$("#" + parentId).append(htmlString);
		
		let adress;
		let adressOfLesson = $("#" + parentId).attr("class");
		let classOfSelect;
		
		if($("body").attr("class").includes("teacher")) {
			classOfSelect = "groups"
			adress = "/api/lessons/getFreeGroup/" + adressOfLesson;
		} else {
			classOfSelect = "teacher"
			adress = "/api/lessons/getFreeTeacher/" + adressOfLesson;
		}

		$.ajax({
			type: "GET",
			contentType: "application/json",
			url: adress,
			dataType: 'json',
			caches: false,
			timeout: 600000,
			success: function(data) {
				htmlString = "<div><select class='" + classOfSelect + "'>";
				$.each(data, function() {
					htmlString += ('<option value="' + $(this)[0]["id"] + '">' + $(this)[0]["name"] + '</option>');
				});
				htmlString += ("</select></div>");
				$("#" + parentId).append(htmlString);
				$("#" + parentId).append('<button class="submit">Add</button>');
			},
			error: function(e) {
			}
		})

	}

	$("td").on("click", ".submit", function() {
		create($(this));
	})

	function create(button) {
		let parentId = button.parent().attr("id");
		let numberOfLesson = parentId.split("-")[0];
		let dayOfWeek = parentId.split("-")[1];
		let name = $("#" + parentId + " .name").val();
		let teacherId;
		let groupId;
		
		if($("body").attr("class").includes("teacher")) {
			groupId = $("#" + parentId + " .groups").val();
			teacherId = $(location).attr('pathname').split("/").at(-1);
		} else {
			groupId = $(location).attr('pathname').split("/").at(-1);
			teacherId = $("#" + parentId + " .teacher").val();
		}

		let req = {};
		req["numberOfLesson"] = numberOfLesson;
		req["name"] = name;
		req["dayOfWeek"] = dayOfWeek;
		req["teacher_id"] = teacherId;
		req["student_group"] = groupId;

		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "/api/lessons",
			data: JSON.stringify(req),
			dataType: 'json',
			caches: false,
			timeout: 600000,
			success: function(data) {
				let teacherName;
				let groupName;

				$.ajax({
					type: "GET",
					contentType: "application/json",
					url: '/api/teachers/' + teacherId,
					dataType: 'json',
					caches: false,
					timeout: 600000,
					success: function(reqData) {
						teacherName = reqData["name"];

						$.ajax({
							type: "GET",
							contentType: "application/json",
							url: '/api/groups/' + groupId,
							dataType: 'json',
							caches: false,
							timeout: 600000,
							success: function(reqData) {
								groupName = reqData["name"];
								
								$("#" + parentId).empty();
								let htmlString = '<div><p>' + data["name"] + '</p><p>' + teacherName + '</p><p>' + groupName + '</p><button class="delete">Удалить</button></div>';
								$("#" + parentId).append(htmlString);
								console.log($("#" + parentId).attr("id", data["id"]));
								$("#" + parentId).attr("id", data["id"]);
							},
							error: function(e) {
							}
						})
					},
					error: function(e) {
					}


				})


			},
			error: function(e) {
			}
		})

	}
});