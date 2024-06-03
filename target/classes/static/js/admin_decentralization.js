//  nois chung phan nay thi sao chep het vao
const dl_add_role = document.getElementById("dialog_add_role");
const btnAddRole = document.getElementById("btnAdd");
const btnConAddRole = document.getElementById("confirmAdd");
const btnCanAddRole = document.getElementById("cancelAdd");
btnAddRole.addEventListener("click", function () {
  dl_add_role.showModal();
});
btnConAddRole.addEventListener("click", function () {});
btnCanAddRole.addEventListener("click", function () {
  dl_add_role.close();
});

const dl_update_role = document.getElementById("dialog_update_role");
const btnUpdateRole = document.getElementById("btnUpdate");
const btnConUpdateRole = document.getElementById("confirmUpdate");
const btnCanUpdateRole = document.getElementById("cancelUpdate");

btnUpdateRole.addEventListener("click", function () {
  dl_update_role.showModal();
});
btnCanUpdateRole.addEventListener("click", function (e) {
  dl_update_role.close();
});
btnConUpdateRole.addEventListener("click", function () {});

const dl_remove_role = document.getElementById("dialog_remove_role");
const btnRemoveRole = document.getElementById("btnRemove");
const btnConRemoveRole = document.getElementById("confirmRemove");
const btnCanRemoveRole = document.getElementById("cancelRemove");

btnRemoveRole.addEventListener("click", function () {
  dl_remove_role.showModal();
});
btnCanRemoveRole.addEventListener("click", function (e) {
  dl_remove_role.close();
});
btnConRemoveRole.addEventListener("click", function () {});
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
