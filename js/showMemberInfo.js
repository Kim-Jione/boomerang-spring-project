
//데이터 출력 : user_** 값들은 서버로부터 받아온다.
function printInfo() {
    gender.value = '남자';
    age.value = '20대';
    position.value = '연출';
    career.value = '1~3년';
}

//변수 연결
let gender = document.querySelector('#user_gender');
let age = document.querySelector('#user_age');
let position = document.querySelector('#user_position');
let career = document.querySelector('#user_career');

window.addEventListener("load", printInfo);