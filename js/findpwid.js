function init() {
    var pwBtn = document.querySelector('.pw_button');
    var idBtn = document.querySelector('.id_button');
    var pwBox = document.querySelector('.pwbox');
    var idBox = document.querySelector('.idbox');
    var emailBtn = document.querySelector('.emaillink');
  
    pwBtn.addEventListener('click', function() {
      pwBox.style.visibility = 'visible';
      idBox.style.visibility = 'hidden';
    });
  
    idBtn.addEventListener('click', function() {
      idBox.style.visibility = 'visible';
      pwBox.style.visibility = 'hidden';
    });

    emailBtn.addEventListener('click', function(){
        const emailInput = document.getElementById("emailInput"); // 수정: getElementById 사용
        const email = emailInput.value;

        const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        console.log(email);
        if (pattern.test(email)) {
            // 이메일 형식이 올바른 경우
            alert('이메일이 발송되었습니다.');
            emailInput.value = ""; // 입력 필드 초기화
        } else {
            // 이메일 형식이 잘못된 경우
            alert('이메일 형식이 올바르지 않습니다. 다시 시도해주세요.');
        }
    });

  }
  
  window.addEventListener("load", init);