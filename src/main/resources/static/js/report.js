document.addEventListener("DOMContentLoaded", function () {
    var btnMonthlyReport = document.getElementById("btn_monthlyReport");
    var btnYearlyReport = document.getElementById("btn_yearlyReport");
  
    var tableMonthly = document.getElementById("table_monthly");
    var tableYearly = document.getElementById("table_yearly");
  
    var searchMonthly = document.getElementById("search_monthly");
    var searchYearly = document.getElementById("search_yearly");
  
    // Add click event listener to the button
    btnMonthlyReport.addEventListener("click", function () {
      // tableYearly.style.display = "none";
      tableMonthly.style.display = "table";
      searchYearly.style.display = "none";
      searchMonthly.style.display = "flex"; // Change from "search_monthly" to "block"
    });
  
    // Add click event listener to the button
    btnYearlyReport.addEventListener("click", function () {

      tableMonthly.style.display = "none";
      // tableYearly.style.display = "table";
      searchMonthly.style.display = "none";
      searchYearly.style.display = "flex"; // Change from "search_yearly" to "block"
    });
  });
  
  window.addEventListener("load", function () {
    const congratsAction = document.querySelector(".congratulation__action");
    const infoPersonal = document.querySelector(".info__personal");
  
    let isInfoPersonalVisible = false;
  
    congratsAction.addEventListener("click", function () {
      if (!isInfoPersonalVisible) {
        infoPersonal.style.display = "block";
        isInfoPersonalVisible = true;
      } else {
        infoPersonal.style.display = "none";
        isInfoPersonalVisible = false;
      }
    });
  });
  
