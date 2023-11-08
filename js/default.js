const toggleMenu = document.querySelector(".menu");
const toggleMenu2 = document.querySelector(".menu2");

function menuToggle() {
  //   const toggleMenu = document.querySelector(".menu");
  toggleMenu.classList.toggle("active");
}

function menu2Toggle() {
  //   const toggleMenu = document.querySelector(".menu2");
  toggleMenu2.classList.toggle("active");
}

function isClickToggle(event) {
  if (!toggleMenu.parentElement.contains(event.target)) {
    if (toggleMenu.classList.contains("active")) {
      toggleMenu.classList.toggle("active");
    }
  }

  if (!toggleMenu2.parentElement.contains(event.target)) {
    if (toggleMenu2.classList.contains("active")) {
      toggleMenu2.classList.toggle("active");
    }
  }
}

document.addEventListener("click", function (event) {
  isClickToggle(event);
});
