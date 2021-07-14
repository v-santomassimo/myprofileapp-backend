//BURGER MENU
document.addEventListener("DOMContentLoaded", function () {
  var elems = document.querySelectorAll(".sidenav");
  var instances = M.Sidenav.init(elems);
});

//TABS
var el = document.querySelector(".tabs");
var instance = M.Tabs.init(el, { swipeable: true });
