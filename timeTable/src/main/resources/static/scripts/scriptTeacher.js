$(document).ready(function() {
	$("body").on("click", ".delete", function() {
		deleteBook($(this));
	})

	function deleteBook(button) {
		let parentId = button.parent().parent().attr("id");
		console.log(parentId);
		$.ajax({
			type: "DELETE",
			contentType: "application/json",
			url: "/teachers/" + parentId,
			dataType: 'json',
			caches: false,
			timeout: 600000,
			success: function(data) {
				$("#" + data["id"]).remove();
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
		let surname = $("#" + parentId + " .surname").val();
		let name = $("#" + parentId + " .name").val();
		let patronymic = $("#" + parentId + " .patronymic").val();
		let phone = $("#" + parentId + " .tel").val();
		
		let req = {};
		req["surname"] = surname;
		req["name"] = name;
		req["patronymic"] = patronymic;
		req["phone"] = phone;	

		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "/api/teachers",
			data: JSON.stringify(req),
			dataType: 'json',
			caches: false,
			timeout: 600000,
			success: function(data) {
				let htmlString = '<tr id="' + data["id"] + '"><td>' + data["surname"] + '</td><td>' + data["name"] + '</td><td>' + data["patronymic"] + '</td><td>' + data["phone"] + '</td><td><button class="delete">Удалить</button></td></tr>';
				$("#" + parentId).before(htmlString);
			},
			error: function(e) {
			}
		})
	}
});