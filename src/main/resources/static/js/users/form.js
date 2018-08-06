
$(".user-join-form button[type=submit]").on("click",function (e) {
    e.preventDefault();

    // console.log("회원가입 클릭");

    var url = $(".user-join-form").attr("action");
    // console.log("url : " + url);

    //  Stringfy하면 userId+password+name+email이 전체가 string이 되잖니???
    // var queryString = $(".user-join-form").serialize();
    // // console.log("queryString: " + queryString);

    var join = {};

    join["userId"] = $("#userId").val();
    join["password"] = $("#password").val();
    join["name"] = $("#name").val();
    join["email"] = $("#email").val();


    $.ajax({
        type: 'post',
        url: url,
        contentType: "application/json",
        data: JSON.stringify(join),
        dataType: 'json',
        error: function () {
            // console.log("회원가입 error!");
        },
        success: function (data) {
            if (data.status == 'fail') {
                // console.log("실패");
                $(".errorMessage").text(data.message);
            } else {
                // console.log("성공");
                location.href= data.url;
            }
        }
    });
});