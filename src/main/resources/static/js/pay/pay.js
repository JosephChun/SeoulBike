$(".pay").on("click", function (e) {
    e.preventDefault();
    alert("o");
    $.ajax({
        type:'post',
        url:"/pay",
        success: function () {
            alert("zzz");
        }
    });
});