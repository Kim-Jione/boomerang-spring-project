// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily =
  '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = "#292b2c";

let pfGenre = document.querySelectorAll(".pfGenre");
let pfGenreCount = document.querySelectorAll(".pfGenreCount");

pfGenre = Array.from(pfGenre).map((element) => element.value);
pfGenreCount = Array.from(pfGenreCount).map((element) => element.value);

alert(pfGenreCount);
alert(pfGenre);

// Pie Chart Example
var ctx = document.getElementById("myPieChart");
var myPieChart = new Chart(ctx, {
  type: "pie",
  data: {
    labels: pfGenre,
    datasets: [
      {
        data: pfGenreCount,
        backgroundColor: ["#007bff", "#dc3545", "#ffc107", "#28a745"]
      }
    ]
  }
});
