$(function() {

	var $selectedComment = null;

	var loadWeatherData = function(city) {
		var apiKey = "cfed679d59a400ce311452d1f70e6c4d";
		$.ajax({
			method : "GET",
			url : "http://api.openweathermap.org/data/2.5/weather",
			data : {
				q : city,
				appid : apiKey,
				units : "metric"
			}
		}).done(function(response) {
			$('#current-temp').text(response.main.temp);
		});
	}

	var loadInitialData = function() {
		var city = $('#select-city').val();
		loadWeatherData(city);
	}

	$("#publish").on("click", function() {

		var city = $('#select-city').val();
		var cityLabel = $("#select-city option:selected").text();
		var $comment = $('#comment');
		var comment = $comment.val();
		var temperature = $("#current-temp").text();
		postComment(cityLabel, comment, temperature);
		$comment.val('');
	})
	var postComment = function(comment, place, temp) {
		$.ajax({
			method : "POST",
			url : "createPost",
			data : {
				comment : comment,
				place : place,
				temp : temp
			}
		}).done(
				function(response) {
					renderComment(response.id, response.place,
							response.comment, response.temp);
				}).fail(function(response) {
			console.log(response);
		})
	}

	getUserPosts = function() {
		$.ajax({
			method : "GET",
			url : "getMyPosts"
		}).done(
				function(response) {

					for (var i = 0; i < response.length; i++) {
						var currentPost = response[i];
						renderComment(currentPost.id, currentPost.place,
								currentPost.comment, currentPost.temp);
					}

				}).fail(function(response) {
			console.log(response);
		})
	}

	var renderComment = function(id, cityLabel, comment, temperature) {

		var $template = $('#comment-template').html();
		$template = $($template);

		$template.find('.remove-item').attr('id', id);
		$template.find('span').text(temperature);
		$template.find('h4').text(cityLabel);
		$template.find('p').text(comment);

		var $commentsList = $("#comments-list");
		$commentsList.append($template);
	}
	$("#select-city").on("change", function() {
		var selectedCity = $(this).val();
		loadWeatherData(selectedCity);
	})
	$(document).on('click', '.remove-item', function() {
		$selectedComment = $(this).closest('.list-group-item');
	})

	$("#confirm-delete").on("click", function() {

		var postId = $selectedComment.find('.remove-item').attr('id');
		removePostById(postId);
	})

	removePostById = function(id) {
		$.ajax({
			method : "POST",
			url : "removeMyPost",
			data: {
				id: id
			}
		}).done(function(response) {
			$selectedComment.remove();
			$('#confirmDeleteModal').modal('hide');
			
		}).fail(function(response) {
			console.log(response);
		})

	}

	loadInitialData();
	getUserPosts();

})