const dl_add = document.getElementById("dialog_add");
const btnAdd = document.getElementById("btnAdd");
const btnConAdd = document.getElementById("confirmAdd");
const btnCanAdd = document.getElementById("cancelAdd");
const dl_edit = document.getElementById("dialog_edit");
const btnEdit = document.getElementById("btnEdit");
const btnConEdit = document.getElementById("confirmEdit");
const btnCanEdit = document.getElementById("cancelEdit");

btnAdd.addEventListener("click", function() {
    dl_add.showModal();
});
btnConAdd.addEventListener("click", function() {
    document.getElementById("add_form").submit();
    dl_add.close();
});
btnCanAdd.addEventListener("click", function() {
    dl_add.close();
});

btnEdit.addEventListener("click", function() {
    dl_edit.showModal();
});
btnConEdit.addEventListener("click", function() {
    document.getElementById("edit_form").submit();
    dl_edit.close();
});
btnCanEdit.addEventListener("click", function() {
    dl_edit.close();
});



// window.addEventListener("load", function() {
//     const congratsAction = document.querySelector(".congratulation__action");
//     const infoPersonal = document.querySelector(".info__personal");

//     let isInfoPersonalVisible = false;

//     congratsAction.addEventListener("click", function() {
//         if (!isInfoPersonalVisible) {
//             infoPersonal.style.display = "block";
//             isInfoPersonalVisible = true;
//         } else {
//             infoPersonal.style.display = "none";
//             isInfoPersonalVisible = false;
//         }
//     });
// });