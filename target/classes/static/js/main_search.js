// Function to show destinations when clicking on "From" or "To"
function showDestinations(inputId) {
  var list = document.getElementById(inputId + "List");
  list.style.display = "block";
}

function selectDestination(inputId, destination) {
  var inputAbb = document.querySelector("." + inputId + "__search .abb-name");
  inputAbb.textContent = destination.toUpperCase(); // Thay đổi nội dung của phần tử "From" thành tên địa điểm viết tắt

  // Chuyển đổi tên địa điểm thành viết tắc
  var fullName = "";
  switch (destination) {
    case "HNA":
      fullName = "Ha Noi";
      break;
    case "VTA":
      fullName = "Vung Tau";
      break;
    case "HCM":
      fullName = "Ho Chi Minh";
      break;
    case "PAR":
      fullName = "Paris";
      break;
    case "TKO":
      fullName = "Tokyo";
      break;
    case "NYK":
      fullName = "NewYork";
      break;
    default:
      fullName = destination;
      break;
  }
  var inputFullName = document.querySelector(
    "." + inputId + "__search .full-name"
  );
  inputFullName.textContent = fullName; // Thay đổi nội dung của phần tử "departing from" thành tên địa điểm viết tắt

  var list = document.getElementById(inputId + "List");
  list.style.display = "none"; // Ẩn danh sách điểm du lịch
}

// Lấy danh sách các phần tử "From" và "To"
var fromElement = document.querySelector(".from__search");
var toElement = document.querySelector(".to__search");

// Lấy phần tử danh sách điểm du lịch
var fromList = document.getElementById("fromList");
var toList = document.getElementById("toList");

// Thêm sự kiện khi rời chuột khỏi phần "From"
fromElement.addEventListener("mouseleave", function () {
  fromList.style.display = "none";
});

// Thêm sự kiện khi rời chuột khỏi phần "To"
toElement.addEventListener("mouseleave", function () {
  toList.style.display = "none";
});

// Thêm sự kiện khi nhấp vào phần "From"
fromElement.addEventListener("click", function () {
  fromList.style.display = "block";
  toList.style.display = "none"; // Ẩn danh sách "To" khi click vào "From"
});

// Thêm sự kiện khi nhấp vào phần "To"
toElement.addEventListener("click", function () {
  toList.style.display = "block";
  fromList.style.display = "none"; // Ẩn danh sách "From" khi click vào "To"
});

document.querySelector(".btn__details").addEventListener("click", function () {
  var itinerary = document.querySelector(".itinerary");
  if (itinerary.style.display === "block") {
    itinerary.style.display = "none";
  } else {
    itinerary.style.display = "block";
  }
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
