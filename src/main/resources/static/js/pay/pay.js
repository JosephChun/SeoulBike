$(".pay").on("click", function (e) {
    e.preventDefault();

    $.ajax({
        type:'post',
        url:"/api/pay",
        data: "text",
        success: function (data) {
            window.open(data);
        }
    });

});
// js 어렵다아아아아
//백엔드 개발자도 js를 공부해야 좋다.