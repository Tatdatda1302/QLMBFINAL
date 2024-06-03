document.addEventListener("DOMContentLoaded", function () {
    const ticketIssueDates = document.querySelectorAll("td:nth-child(9)");
    ticketIssueDates.forEach(function (date) {
      if (date.textContent.trim() !== "") {
        date.closest("tr").querySelector(".issue-ticket-btn").disabled = true;
      }
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
  