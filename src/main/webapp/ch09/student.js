document.addEventListener('DOMContentLoaded', function () {
  //arrayList json오로 파싱
  var studentsJson = ${studentsJson};
  console.log(studentsJson);	
  let detailId = document.querySelector('.detail input[name="id"]');
  let detailUsername = document.querySelector('.detail input[name="username"]');
  let detailUniv = document.querySelector('.detail input[name="univ"]');
  let detailBirth = document.querySelector('.detail input[name="birth"]');
  let detailEmail = document.querySelector('.detail input[name="email"]');
  let updateId = document.querySelector('.update input[name="id"]');
  let updateUsername = document.querySelector('.update input[name="username"]');
  let updateUniv = document.querySelector('.update input[name="univ"]');
  let updateBirth = document.querySelector('.update input[name="birth"]');
  let updateEmail = document.querySelector('.update input[name="email"]');
  	
  //추가,수정,삭제 버튼
  let menus = document.querySelectorAll('.menu-wrap .menu');
  let btns = document.querySelectorAll('.detail-wrap .btn-group button');

  btns.forEach(btn => {
    btn.addEventListener('click', function() {
      let btnType = this.getAttribute('data-type');
      let matchType = document.querySelector(`.menu[data-type=${ "${btnType}" }]`);

      menus.forEach(menu => {
        menu.classList.remove('active');
      });      

      updateId.value = "";
      updateUsername.value = "";
      updateUniv.value = "";
      updateBirth.value = "";
      updateEmail.value = "";

      matchType.classList.add('active');
    })
  })

  //취소버튼
  let menuBtns = document.querySelectorAll('.btn-group .menu-btn');

  menuBtns.forEach(menuBtn => {
    let menuDetail = document.querySelector('.menu.detail-wrap');

    menuBtn.addEventListener('click', (e) => {
      e.preventDefault();

      menus.forEach(menu => {
        menu.classList.remove('active');
      });

      detailId.value = "";
      detailUsername.value = "";
      detailUniv.value = "";
      detailBirth.value = "";
      detailEmail.value = "";
      updateId.value = "";
      updateUsername.value = "";
      updateUniv.value = "";
      updateBirth.value = "";
      updateEmail.value = ""; 
      				
      menuDetail.classList.add('active')
    })
  })

  //메인버튼
  let mainBtn = document.querySelector('.detail-wrap .btn-group .main-btn');

  mainBtn.addEventListener('click', () => {
    window.location.href = "http://localhost:8080/jwbook/ch09/student.html";
  })
  
  //테이블 row클릭
  let tableRows = document.querySelectorAll('table tr');
  
    
  tableRows.forEach((row,index) => {
    row.addEventListener('click', () => {
      if(index !== 0){
		console.log(index - 1);
        detailId.value = studentsJson[index - 1].id;
        detailUsername.value = studentsJson[index - 1].username;
        detailUniv.value = studentsJson[index - 1].univ;
        detailBirth.value = studentsJson[index - 1].birth;         
        detailEmail.value = studentsJson[index - 1].email;
        updateId.value = studentsJson[index - 1].id;
        updateUsername.value = studentsJson[index - 1].username;
        updateUniv.value = studentsJson[index - 1].univ;
        updateBirth.value = studentsJson[index - 1].birth;
        updateEmail.value = studentsJson[index - 1].email;
      }
    })
  }) 
})