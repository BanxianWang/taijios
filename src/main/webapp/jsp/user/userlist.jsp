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
        <div class="search" style="text-align: center">
            <span>用户名：</span>
            <input id="name" class="input-text" type="text" value="">
            <span>用户角色：</span>
            <select id="userorman">
                <option value="2">--请选择--</option>
                <option value="1">--普通用户--</option>
                <option value="0">--管理员--</option>

            </select>
            <input type="hidden" name="pageIndex" value="1"/>
            <input value="查 询" type="submit" id="searchbutton" >
            <a class='button border-red' href='javascript:void(0)'  style='padding:2px 8px;' onclick='return del(1,1,1)'><span class='icon-plus-square-o'></span> 添加用户</a>

        </div>
        <table class="table table-hover text-center">
            <thead>
            <tr>
                <th>姓名</th>
                <th>性别</th>
                <th>手机号</th>
                <th>邮箱</th>
                <th>地址</th>
                <th>用户角色</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr></tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="7">
                    共<span id="count"></span>条记录&nbsp;&nbsp; <span id="currentPage"></span>/<span id="totalPage"></span>页</li>

                    <a href="javascript:void(0)" class="first">首页</a>
                    <a href="javascript:void(0)" class="prev">上一页</a>


                    <a href="javascript:void(0)" class="next">下一页</a>
                    <a href="javascript:void(0)" class="last">最后一页</a>

                    &nbsp;&nbsp;

                    <span class="page-go-form"><label>跳转至</label>
	     <input type="text" name="inputPage" id="inputPage" class="page-key"/>页
	     <button type="button" class="page-btn" onClick=''>GO</button>
		</span>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
    </div>
    </div>
</form>

</body>
</html>