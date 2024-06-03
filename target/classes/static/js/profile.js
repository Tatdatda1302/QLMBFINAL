function setupPasswordToggle(toggleId, inputId) {
    const toggle = document.getElementById(toggleId);
    const input = document.getElementById(inputId);
  
    toggle.addEventListener("click", function () {
      if (input.type === "password") {
        input.type = "text";
        toggle.querySelector("i").classList.remove("fa-eye");
        toggle.querySelector("i").classList.add("fa-eye-slash");
      } else {
        input.type = "password";
        toggle.querySelector("i").classList.remove("fa-eye-slash");
        toggle.querySelector("i").classList.add("fa-eye");
      }
    });
  }
  
  setupPasswordToggle("toggleCurrentPassword", "current-password");
  setupPasswordToggle("toggleNewPassword", "new-password");
  setupPasswordToggle("toggleConfirmPassword", "confirm-password");
  
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
  