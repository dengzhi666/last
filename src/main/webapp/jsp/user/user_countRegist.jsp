<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<script src="${pageContext.request.contextPath}/echarts/echarts.min.js"></script>
<script type="text/javascript" src="https://cdn-hangzhou.goeasy.io/goeasy.js"></script>
<script type="text/javascript">
    $(function () {
        var myChart = echarts.init(document.getElementById('echarts_map'));
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '每月用户注册数统计'
            },
            tooltip: {},
            legend: {
                data:['用户注册数']
            },
            xAxis: {
                data: []
            },
            yAxis: {},
            series: [{
                name: '用户注册数',
                type: 'bar',
                data: []
            }]
        };
        $.get("${pageContext.request.contextPath}/user/countRegist",function (data) {
            option.xAxis.data = data.months;
            option.series[0].data=data.counts;
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        })
        var goEasy = new GoEasy({
            appkey: 'BC-6c8fedfaac52425286c22b50d4ba6770'
        });
        goEasy.subscribe({
            channel:'user_regist',
            onMessage: function(message){
                if (message.content == "addOne") {
                    $.get("${pageContext.request.contextPath}/user/countRegist",function (data) {
                        option.xAxis.data = data.months;
                        option.series[0].data=data.counts;
                        // 使用刚指定的配置项和数据显示图表。
                        myChart.setOption(option);
                    })
                }
            }
        });
    })
</script>
<div class="page-header">
    <h1>每月注册用户</h1>
</div>
<div id="echarts_map" style="width: 600px;height:400px;">

</div>
<div>
    <br>
    <br>
</div>