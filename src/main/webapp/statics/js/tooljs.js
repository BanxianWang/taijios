//setInterval(seven(),3600000)

function seven(){
    $.ajax({
        url: "sevenday",
        type: "post",
        dataType: "json",
        data:{"path":"D:\\pcTest2.py"},
        success: function(data) {
        }
    });

}