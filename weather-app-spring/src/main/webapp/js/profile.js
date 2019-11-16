$(function(){

    var loadProfileData = function(){
        $.ajax({
            method: "GET",
            url: "getCurrentUser"
        })
        .done(function(response) {
           if(!response){
        	   window.location = "index.html";
        	   return;
           }
           var $email = $("#email");
           $email.val(response.email);
           var $user = $("#user");
           $user.val(response.username)
        });
    }
    loadProfileData();
})