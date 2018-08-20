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

//백엔드 개발자도 js를 공부해야 좋다.