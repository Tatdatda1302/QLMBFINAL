document.addEventListener("DOMContentLoaded", function() {
    // Lắng nghe sự kiện click trên tất cả các nút ".btn__details"
    document.querySelectorAll(".btn__details").forEach(function(btn) {
      btn.addEventListener("click", function () {
        // Tìm phần tử ".itinerary" trong phạm vi của "result" cụ thể
        var result = this.closest(".result");
        var itinerary = result.querySelector(".itinerary");
        
        // Kiểm tra xem phần tử ".itinerary" có tồn tại không
        if (itinerary) {
          // Nếu tồn tại, thực hiện toggle display
          if (itinerary.style.display === "block") {
            itinerary.style.display = "none";
          } else {
            itinerary.style.display = "block";
          }
        } else {
          console.error("Không tìm thấy phần tử với lớp 'itinerary' trong kết quả này");
        }
      });
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
  