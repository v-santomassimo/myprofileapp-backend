
//TABS
var el = document.querySelector(".tabs");
var instance = M.Tabs.init(el, { swipeable: true });

//ALERT CLOSE
$(".alert").delay(4000).fadeOut("slow");

//LOADER
//$("#registrationForm").submit(function () {
  	//$(".material-icons right").hide();
 	//$("#registerButton").html('<div id="loading-bar-spinner" class="spinner"><div class="spinner-icon"></div></div>');
  	//$("#registerButton").text("Attendere...");
//});

//OVERLAY CARICAMENTO
$("#registrationForm").submit(function(){
    $("#overlay").fadeIn(500);
    $("#boxLoader").fadeIn(500);
});  
$("#loginForm").submit(function(){
    $("#overlay").fadeIn(500);
    $("#boxLoader").fadeIn(500);
});
