// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily =
  '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = "#292b2c";

let jobPositionTitle = document.querySelectorAll(".jobPositionTitle");
let genreCount = document.querySelectorAll(".genreCount");

jobPositionTitle = Array.from(jobPositionTitle).map((element) => element.value);
genreCount = Array.from(genreCount).map((element) => element.value);

// Pie Chart Example
var ctx = document.getElementById("myPieChart");
var myPieChart = new Chart(ctx, {
  type: "pie",
  data: {
    labels: jobPositionTitle,
    datasets: [
      {
        data: genreCount,
        backgroundColor: ["#007bff", "#dc3545", "#ffc107", "#28a745"]
      }
    ]
  }
});
