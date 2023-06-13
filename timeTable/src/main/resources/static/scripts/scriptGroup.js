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
			url: "/api/groups/" + parentId,
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
		let name = $("#" + parentId + " .name").val();
		
		let req = {};
		req["name"] = name;

		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "/api/groups",
			data: JSON.stringify(req),
			dataType: 'json',
			caches: false,
			timeout: 600000,
			success: function(data) {

				let htmlString = '<tr id="' + data["id"] + '"><td>' + data["name"] + '</td><td><button class="delete">Удалить</button></td></tr>';
				$("#" + parentId).before(htmlString);
			},
			error: function(e) {
			}
		})
	}
});