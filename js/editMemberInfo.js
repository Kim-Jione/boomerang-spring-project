function openImageSelector() {
  let imageSelector = document.getElementById("imageSelector");
  imageSelector.style.display = "block";
}
function closeImageSelector() {
  let imageSelector = document.getElementById("imageSelector");
  imageSelector.style.display = "none";
}
function changeImage(newImageSrc) {
  document.getElementById("icon_img").src = newImageSrc;
  closeImageSelector();
}

document.querySelector(".icon").addEventListener("click", openImageSelector);
document.querySelector("#close").addEventListener("click", closeImageSelector);

var iconImages = document.querySelectorAll(".icon_imgs");
iconImages.forEach(function (iconImage) {
    iconImage.addEventListener("click", function() {
      changeImage(iconImage.src);
    });
  });