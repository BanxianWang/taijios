setInterval(seven(),3600000)

function seven(){
    $.ajax({
        url: "sevenday",
        type: "post",
        dataType: "json",
        data:{"path":"F:\\pcTest2.py"},

        success: function(data) {
            alert(1)
        }
    });

}