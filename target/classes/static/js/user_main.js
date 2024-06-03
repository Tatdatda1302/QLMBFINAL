window.addEventListener('load', function() {

    const congratsAction = document.querySelector('.congratulation__action');
    const infoPersonal = document.querySelector('.info__personal');

    let isInfoPersonalVisible = false;

    congratsAction.addEventListener('click', function() {

      if (!isInfoPersonalVisible) {
        infoPersonal.style.display = 'block';
        isInfoPersonalVisible = true;
      } else {
        infoPersonal.style.display = 'none';
        isInfoPersonalVisible = false;
      }
    });
  });