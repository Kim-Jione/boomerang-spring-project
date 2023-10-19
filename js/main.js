// 반응형 슬라이드
const swiperEl = document.querySelector('.hot_swiper')
Object.assign(swiperEl, {
  slidesPerView: 1,
  spaceBetween: 10,
  
  breakpoints: {
    640: {
      slidesPerView: 2,
      spaceBetween: 30,
    },
    768: {
      slidesPerView: 3,
      spaceBetween: 30,
    },
    1024: {
      slidesPerView: 4,
      spaceBetween: 30,
    },
  },
});
swiperEl.initialize();

// 스와이퍼 번호
const swiperEl2 = document.querySelector(".main_swiper");

      const params = {
        injectStyles: [
          `
        .swiper-pagination-bullet {
          width: 20px;
          height: 20px;
          text-align: center;
          line-height: 20px;
          font-size: 12px;
          color: #000;
          opacity: 1;
          background: rgba(0, 0, 0, 0.2);
        }
  
        .swiper-pagination-bullet-active {
          color: #fff;
          background: #007aff;
        }
        `
        ],
        pagination: {
          clickable: true,
          renderBullet: function (index, className) {
            return '<span class="' + className + '">' + (index + 1) + "</span>";
          }
        }
      };

      Object.assign(swiperEl2, params);

      swiperEl2.initialize();

      // 좋아요 애니메이션
      $(document).ready(function(){
        $('.heart').click(function(){
          $('.heart').toggleClass("heart-active")
        });
      });





