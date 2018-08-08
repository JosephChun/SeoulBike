$(".pay").on("click", function (e) {
    e.preventDefault();
    alert("o");
    $.ajax({
        type:'post',
        url:"/api/pay",
        data: "text",
        success: function (data) {
            window.open(data);
        }
    });
});