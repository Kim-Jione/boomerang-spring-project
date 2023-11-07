function init() {
    var pwBtn = document.querySelector('.pw_button');
    var idBtn = document.querySelector('.id_button');

    var pwonoffBox = document.querySelector('.pwonoffBox');
    var idonoffBox = document.querySelector('.idbox');

    var pwEmailBtn = document.getElementById('pwemailBtn');
    var idEmailBtn = document.getElementById('idemailBtn');

    var pw_button_box = document.querySelector('.pw_button_box');
    var id_button_box = document.querySelector('.id_button_box');
   
    pwBtn.addEventListener('click', function() {
        pwonoffBox.style.display = 'flex';
        pw_button_box.style.display ='flex';
        idonoffBox.style.display = 'none';
        id_button_box.style.display ='none';
    });
  
    idBtn.addEventListener('click', function() {
        idonoffBox.style.display = 'flex';
        id_button_box.style.display ='flex';
        pw_button_box.style.display ='none';
        pwonoffBox.style.display = 'none';
    });

    pwEmailBtn.addEventListener('click', function(){
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

    idEmailBtn.addEventListener('click', function(){
        const idInput = document.getElementById("idInput"); // 수정: getElementById 사용
        const id = idInput.value;

        const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        console.log(id);
        if (pattern.test(id)) {
            // 이메일 형식이 올바른 경우
            alert('이메일이 발송되었습니다.');
            idlInput.value = ""; // 입력 필드 초기화
        } else {
            // 이메일 형식이 잘못된 경우
            alert('이메일 형식이 올바르지 않습니다. 다시 시도해주세요.');
        }
    });

  }
  
  window.addEventListener("load", init);