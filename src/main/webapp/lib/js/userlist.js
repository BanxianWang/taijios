//默认当前页
var currentPage = 1;
$(function () {
    bindUser(currentPage);
    bindEvent();
})

function bindUser(currentPage) {
    var name = $("#name").val();
    var userorman = $("#userorman").val();
    $.ajax({
        type: "post",
        url: "/jsp/user/userlist",
        data: {currentPage: currentPage, name: name, userorman: userorman},
        dataType: "json",
        success: function (result) {
            $("table tbody tr").remove();
            var str = "";
            $.each(result.list, function (index, item) {

                str += "<tr>"
                str += "<td>" + item.username + "</td>"
                str += "<td>" + item.gender + "</td>"
                str += "<td>" + item.phone + "</td>"
                str += "<td>" + item.email + "</td>"
                str += "<td>" + item.address + "</td>"
                str += "<td>"
                str += +item.userorman == 0 ? "管理员" : "普通用户"
                str += "</td>"
                str += "<td><div class='button-group'>"
                str += "<a class='button border-main' id='updateUser' href='javascript:void(0)' userid='" + item.id + "' style='padding:2px 8px;'><span class='icon-edit'></span>修改</a>"
                str += " <a class='button border-red' id='deleteUser' href='javascript:void(0)' userid='" + item.id + "' style='padding:2px 8px;'><span class='icon-trash-o'></span> 删除</a>"
                str += " </div>"
                str += " </td>"
                str += "/tr>"
            })
            $("table tbody tr:not(:first)").remove();
            $("table tbody").append(str);
            $("#count").html(result.count);
            $("#currentPage").html(currentPage);
            $("#totalPage").html(result.totalPage)

        }
    });
}

function bindEvent() {


    $("table").delegate("#deleteUser", "click", function () {
        var id = $(this).attr("userid");
        window.location = "/deleteUser?id="+id
    })

    $("table").delegate("#updateUser", "click", function () {
        var id = $(this).attr("userid");
        alert(id);
    })

    $("#searchbutton").click(function () {
        currentPage = 1;
        bindUser(currentPage);
    })


    $(".next").click(function () {
        if (currentPage == parseInt($("#totalPage").html())) {
            return;
        }

        currentPage++;
        bindUser(currentPage);
    })


    $(".prev").click(function () {
        if (currentPage == 1) {
            return;
        }
        currentPage--;
        bindUser(currentPage);
    })

    $(".first").click(function () {
        currentPage = 1;
        bindUser(currentPage);
    })


    $(".last").click(function () {
        currentPage = parseInt($("#totalPage").html())
        bindUser(currentPage);
    })

    $(".page-btn").click(function () {
        var inputPage = $("#inputPage").val();
        if (inputPage < 1) {
            alert("请正确输入页面数")
            return;
        }

        if (inputPage > parseInt($("#totalPage").html())) {
            alert("请正确输入页面数")
            return;
        }

        currentPage = inputPage;
        bindUser(currentPage);

    })

}

/*///搜索
function changesearch() {

}*/
//单个删除
/*  function del(id, mid, iscid) {
      if (confirm("您确定要删除吗?")) {

      }
  }*/


//全选
/*$("#checkall").click(function () {
    $("input[name='id[]']").each(function () {
        if (this.checked) {
            this.checked = false;
        }
        else {
            this.checked = true;
        }
    });
})*/

/*
//批量删除
function DelSelect() {
    var Checkbox = false;
    $("input[name='id[]']").each(function () {
        if (this.checked == true) {
            Checkbox = true;
        }
    });
    if (Checkbox) {
        var t = confirm("您确认要删除选中的内容吗？");
        if (t == false) return false;
        $("#listform").submit();
    }
    else {
        alert("请选择您要删除的内容!");
        return false;
    }
}*/
