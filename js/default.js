function menuToggle(){
    const toggleMenu = document.querySelector('.menu');
    toggleMenu.classList.toggle('active')
}

function menu2Toggle(){
    const toggleMenu = document.querySelector('.menu2');
    toggleMenu.classList.toggle('active')
}

// 로딩 화면
$(document).ready(function(){
    $('.loader').delay(900).fadeOut();
});


//로딩바
var text = document.querySelector('.text');
var percent = document.querySelector('.percent');
var progress = document.querySelector('.progress');
var count = 4;
var per = 16;
var loading = setInterval(animate, 7);
function animate(){
  if(count == 100 && per == 400){
    text.style.fontSize = "70px";
    text.classList.add("add");
    clearInterval(loading);
  }else{
    per = per + 4;
    count = count + 1;
    progress.style.width = per + 'px';
    percent.textContent = count + '%';
  }
}

