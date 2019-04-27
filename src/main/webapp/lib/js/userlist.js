//默认当前页
var currentPage = 1;
$(function () {
    bindUser();
   // bindEvent();
})

function bindUser() {
    $.ajax({
        type: "post",
        url: "/jsp/user/userlist",
       /* data: {currentPage: currentPage},*/
        dataType: "json",
        success: function (result) {
            $("table tbody tr").remove();
            $.each(result, function (index, item) {
                var tr = "<tr>" +
                    " <td style='text-align:left; padding-left:20px;'><input type='checkbox'name='id[]' value=''/>\n" +
                    " </td>\n" +
                    "<td><font color='#00CC99'>" + item.id + "</font></td>" +
                    "<td>" + item.username + "</td>" +
                    "<td>" + item.password + "</td>" +
                    "<td>" + item.phone + "</td>" +
                    "<td>" + item.email + "</td>" +
                    "<td><div class='button-group'>\n" +
                    "<a class='button border-main' href='editUserInfo.html' userid='" + item.id + "' style='padding:2px 8px;'><span class='icon-edit'></span>修改</a>\n" +
                    " <a class='button border-red' href='javascript:void(0)' userid='" + item.id + "' style='padding:2px 8px;' onclick='return del(1,1,1)'><span class='icon-trash-o'></span> 删除</a>\n" +
                    " </div>\n" +
                    " </td>\n" +
                    " /tr>";
                $("table tbody").append(tr);
            })

        }
    });
}
function bindEvent() {
    
}

///搜索
function changesearch() {

}

//单个删除
function del(id, mid, iscid) {
    if (confirm("您确定要删除吗?")) {

    }
}

//全选
$("#checkall").click(function () {
    $("input[name='id[]']").each(function () {
        if (this.checked) {
            this.checked = false;
        }
        else {
            this.checked = true;
        }
    });
})

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
}