
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="../../lib/css/pintuer.css">
    <link rel="stylesheet" href="../../lib/css/admin.css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="../../lib/js/jquery.js"></script>
    <script src="../../lib/js/pintuer.js"></script>
    <script src="../../lib/js/userlist.js"></script>


</head>
<body>
<form method="post" action="" id="listform">
    <div class="panel admin-panel">
        <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong> <a href=""
                                                                               style="float:right; display:none;">添加字段</a>
        </div>
        <table class="table table-hover text-center">

            <thead>
            <tr>
                <th style="text-align:left; padding-left:20px;" width="90">全选</th>
                <th>工号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>学历</th>
                <th>部门</th>
                <th>职位</th>
            </tr>

            </thead>

            <tbody>
            <tr></tr>
            </tbody>
            <%--<tr>
                <td style="text-align:left; padding-left:20px;"><input type="checkbox" name="id[]" value="" />
                    1</td>
                <td><font color="#00CC99">A001</font></td>
                <td>李四</td>
                <td>女</td>
                <td>本科</td>
                <td>纪检监察审计部</td>
                <td>纪检员</td>
                <td><div class="button-group">
                    <a class="button border-main" href="editUserInfo.html" style="padding:2px 8px;"><span class="icon-edit"></span>修改</a>
                    <a class="button border-red" href="javascript:void(0)" style="padding:2px 8px;" onclick="return del(1,1,1)"><span class="icon-trash-o"></span> 删除</a>
                </div>
                </td>
            </tr>
            <tr>
                <td style="text-align:left; padding-left:20px;"><input type="checkbox" name="id[]" value="" />
                    2</td>
                <td><font color="#00CC99">A002</font></td>
                <td>张三</td>
                <td>女</td>
                <td>本科</td>
                <td>纪检监察审计部</td>
                <td>纪检员</td>
                <td><div class="button-group"> <a class="button border-main" style="padding:2px 8px;" href="editNotice.html"><span class="icon-edit"></span> 修改</a>
                    <a class="button border-red" href="javascript:void(0)" style="padding:2px 8px;" onclick="return del(1,1,1)"><span class="icon-trash-o"></span> 删除</a> </div></td>
            </tr>
            <tr>
                <td style="text-align:left; padding-left:20px;"><input type="checkbox" name="id[]" value="" />
                    3</td>
                <td><font color="#00CC99">A003</font></td>
                <td>由国芝</td>
                <td>女</td>
                <td>本科</td>
                <td>纪检监察审计部</td>
                <td>纪检员</td>
                <td><div class="button-group"> <a class="button border-main" style="padding:2px 8px;" href="editNotice.html"><span class="icon-edit"></span> 修改</a>
                    <a class="button border-red" href="javascript:void(0)" style="padding:2px 8px;" onclick="return del(1,1,1)"><span class="icon-trash-o"></span> 删除</a> </div></td>
            </tr>--%>
            <tfoot>
            <tr>

                <td style="text-align:left; padding:19px 0;padding-left:20px;"><input type="checkbox" id="checkall"/>
                    全选
                </td>
                <td style="text-align:left;"><a class="button border-main" href="addUser.html" style="padding:5px 8px;">+&nbsp;新增</a>
                    <a href="javascript:void(0)" class="button border-red" style="padding:5px 8px;"
                       onclick="DelSelect()"><span class="icon-trash-o"></span>&nbsp;批量删除</a>
                </td>
            </tr>

            <tr>
                <td colspan="8">
                    <div class="pagelist">
                        共<span id="count"></span>条记录&nbsp;&nbsp; <span id="currentPage"></span>/<span
                            id="totalPage"></span>页

                        <a href="javascript:void(0)" class="first">首页</a>
                        <a href="javascript:void(0)" class="prev">上一页</a>


                        <a href="javascript:void(0)" class="next">下一页</a>
                        <a href="javascript:void(0)" class="last">尾页</a>

                        &nbsp;&nbsp;
                        </ul>
                        <span class="page-go-form"><label>跳转至</label>
	     <input type="text" name="inputPage" id="inputPage" class="page-key"/>页
	     <button type="button" class="page-btn" onClick=''>GO</button>
		</span>
                    </div>
            </tr>

            </tfoot>
        </table>
    </div>
    </div>
    </div>
</form>
<%--<script src="../lib/js/jquery.js"></script>
<script src="../lib/js/pintuer.js"></script>--%>
<%--<script>

    $(function () {
        bindUser();
    })
    //默认当前页
    //var currentPage = 1;
    function bindUser() {
        $.ajax({
            type: "post",
            url: "userlist",
            /*data: {currentPage: currentPage},*/
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




</script>--%>
</body>
</html>