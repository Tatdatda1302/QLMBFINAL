document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.querySelector(".form.Sign.in form");
    const passwordInput = document.querySelector(".password");
  
    loginForm.addEventListener("submit", function (event) {
      const usernameInput = document.querySelector(".input"); // Sử dụng querySelector để lấy input user name
  
      const username = usernameInput.value.trim();
      const password = passwordInput.value.trim();
  
      if (!isValidUsername(username)) {
        alert("Please enter a valid username.");
        event.preventDefault();
        return;
      }
  
      if (!isValidPassword(password)) {
        alert(
          "Password must be at least 4 numbers."
        );
        event.preventDefault();
        return;
      }
    });
  
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
  
  const passwordField = document.getElementById("passwordInput");
  const togglePassword = document.querySelector(".password-toggle-icon i");
  
  togglePassword.addEventListener("click", function () {
    if (passwordField.type === "password") {
      passwordField.type = "text";
      togglePassword.classList.remove("fa-eye");
      togglePassword.classList.add("fa-eye-slash");
    } else {
      passwordField.type = "password";
      togglePassword.classList.remove("fa-eye-slash");
      togglePassword.classList.add("fa-eye");
    }
  });
  