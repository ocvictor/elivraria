$ajax({

	url: "/gerenciar/relatorios",
	success: function(result){
		/* line chart single starts here */
		var category = categoriaMap;
	}
});
Highcharts.chart('container', {
        chart: {
            type: 'column'
        },
        title: {
            text: 'Technology Popularity In India'
        },
        subtitle: {
            text: 'All Details is not true its a dummy Map'
        },
        xAxis: {
            categories: category.keySet(),
            crosshair: true
        },
        yAxis: {
            min: 0,
            max:100,
            title: {
                text: 'Technology rating [in %]'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
            '<td style="padding:0"><b>{point.y:.1f} K</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: 'Technology',
            data: category.values()
        }]
    });