<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2021/10/29
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- JSTL 函数 包含一系列标准函数，大部分是通用的字符串处理函数-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- JSTL 核心标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- JSTL 格式化标签 用来格式化并输出文本、日期、时间、数字 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- JSTL SQL标签库 提供了与关系型数据库进行交互的标签 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!-- JSTL XML标签库 提供了创建和操作XML文档的标签 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<html>
<head>
    <title>康小狮会一直一直爱着雯小喵</title>
</head>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="application/javascript">

</script>

<body>
    <form>
        <label for="line">需要多少行？</label><input type="number" id="line" value="0">
    </form>
    <form name="user" id="form1">
    <table id="file" style="border:1px">
        <tr>
            <th>学号</th>
            <th>分数</th>
        </tr>
        <tbody id="body">

        </tbody>
        </table>
        <div id="wrap">
            类型:
            <input type="radio" name="type" value="0">按序号正序排列
            <input type="radio" name="type" value="1">按分数倒序排列
        </div>
    </form>

    <button id="commit">提交</button>
<script type="application/javascript">
    function addUser(num){
        let lab = ` <tr >
            <td><input name="id"  type="number" ></td>
             <td><input name="score" type="number"></td>
 </tr>`;
        let lab1;
       for(let i=0; i<num; i++){
           lab1+=lab;
       }
        $("#body").html(lab1);
    }
    $("#line").change(function (){
      addUser( $("#line").val())
    })
    $("#commit").click(function () {

        var result = [];
        $("#body tr").each(function () {
            var thisItem = $(this);
            result.push({
                "id": thisItem.find("input:eq(0)").val(),
                "score": thisItem.find('input:eq(1)').val()
            })
        });
        var type1 = $('#wrap input[name="type"]:checked ').val();
        console.log(type1)
        $.ajax({
            url: "/get/createFile",
            type: "POST",
            data: JSON.stringify({"list":result,"type":type1}),
            //设置请求的contentType为"application/json"
            contentType: "application/json",
            responseType: 'arraybuffer',
            success: function (data) {
                let blob = new Blob([data]);
                let link = document.createElement('a');
                link.href = window.URL.createObjectURL(blob);
                link.download = "title.xlsx";
                link.click();
            }
        });
        }
    )

</script>
</body>
</html>
