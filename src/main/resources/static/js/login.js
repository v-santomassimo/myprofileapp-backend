//BURGER MENU
document.addEventListener("DOMContentLoaded", function () {
  var elems = document.querySelectorAll(".sidenav");
  var instances = M.Sidenav.init(elems);
});

//TABS
var el = document.querySelector(".tabs");
var instance = M.Tabs.init(el, { swipeable: true });

//ALERT CLOSE
$("#close-alert").click(function () {
  $("#card-panel").fadeOut("slow", function () {});
});
//var el = document.querySelectorAll("#alertSuccess");
//var instance = M.toast.init(el, { displayLength: 100 });

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
