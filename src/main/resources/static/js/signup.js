document.addEventListener("DOMContentLoaded", function () {
    const signupForm = document.querySelector(".form.Sign.up form");
    const fullnameInput = document.querySelector(
      'input[placeholder="Full name"]'
    );
    const userIDInput = document.querySelector('input[placeholder="User ID"]');
    const phoneNumberInput = document.querySelector(
      'input[placeholder="Phone number"]'
    );
    const emailInput = document.querySelector(
      'input[placeholder="Email address"]'
    );
    const usernameInput = document.querySelector(
      'input[placeholder="User Name"]'
    );
    const createPasswordInput = document.querySelector(
      'input[placeholder="Create password"]'
    );
    const confirmPasswordInput = document.querySelector(
      'input[placeholder="Confirm password"]'
    );
  
    signupForm.addEventListener("submit", function (event) {
      if (!isValidFullName(fullnameInput.value.trim())) {
        alert("Full name must start with a capital letter for each word.");
        event.preventDefault();
        return;
      }
  
      if (!isValidUserID(userIDInput.value.trim())) {
        alert("User ID must be 12 digits long.");
        event.preventDefault();
        return;
      }
  
      if (!isValidPhoneNumber(phoneNumberInput.value.trim())) {
        alert("Please enter a valid Vietnamese phone number.");
        event.preventDefault();
        return;
      }
  
      if (!isValidEmail(emailInput.value.trim())) {
        alert("Please enter a valid email address.");
        event.preventDefault();
        return;
      }
  
      if (!isValidUsername(usernameInput.value.trim())) {
        alert(
          "Username must be at least 6 characters long and contain both letters and numbers."
        );
        event.preventDefault();
        return;
      }
  
      if (!isValidPassword(createPasswordInput.value.trim())) {
        alert(
          "Password must be at least 4 numbers."
        );
        event.preventDefault();
        return;
      }
  
    });
  
    function isValidFullName(fullname) {
      const nameWords = fullname.split(" ");
      for (let word of nameWords) {
        if (!/^[A-Z]/.test(word)) {
          return false;
        }
      }
      return true;
    }
  
    function isValidUserID(userID) {
      return /^\d{12}$/.test(userID);
    }
  
    function isValidPhoneNumber(phoneNumber) {
      return /^(0\d{9,10})$/.test(phoneNumber);
    }
  
    function isValidEmail(email) {
      return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
    }
  
    function isValidUsername(username) {
      return (
        username.length >= 6 && /\d/.test(username) && /[a-zA-Z]/.test(username)
      );
    }
  
    function isValidPassword(password) {
      return (
        password.length >= 4 && /\d/.test(password)
      );
    }
  });
  
  const createPasswordField = document.getElementById("createPasswordInput");
  const confirmPasswordField = document.getElementById("confirmPasswordInput");
  
  const toggleCreatePassword = document.getElementById("toggleCreatePassword");
  const toggleConfirmPassword = document.getElementById("toggleConfirmPassword");
  
  toggleCreatePassword.addEventListener("click", function () {
    if (createPasswordField.type === "password") {
      createPasswordField.type = "text";
      toggleCreatePassword.querySelector("i").classList.remove("fa-eye");
      toggleCreatePassword.querySelector("i").classList.add("fa-eye-slash");
    } else {
          toggleCreatePassword.querySelector("i").classList.remove("fa-eye-slash");
      toggleCreatePassword.querySelector("i").classList.add("fa-eye");
    }
  });
  
  toggleConfirmPassword.addEventListener("click", function () {
    if (confirmPasswordField.type === "password") {
      confirmPasswordField.type = "text";
      toggleConfirmPassword.querySelector("i").classList.remove("fa-eye");
      toggleConfirmPassword.querySelector("i").classList.add("fa-eye-slash");
    } else {
      confirmPasswordField.type = "password";
      toggleConfirmPassword.querySelector("i").classList.remove("fa-eye-slash");
   createPasswordField.type = "password";
     toggleConfirmPassword.querySelector("i").classList.add("fa-eye");
    }
  });
  