<div class="container">

		<div id="container"
			style="width: 550px; height: 400px; margin: 0 auto"></div>
	</div>

	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
	<script type="text/javascript">
    Highcharts.chart('container', {
        chart: {
            type: 'column'
        },
        title: {
            text: 'Relatório de Venda Por ${relatorioModelo.tipo} '
        },
        subtitle: {
            text: 'Período de ${relatorioModelo.dataInicial} até ${relatorioModelo.dataFinal} Gênero: ${relatorioModelo.genero}'
        },
        xAxis: {
            categories: ${categoriaMap.keySet()},
            crosshair: true
        },
        yAxis: {
            min: 0,
            max:100,
            title: {
                text: 'Quantidade Vendidas'
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
            name: '${relatorioModelo.tipo}',
            data: ${categoriaMap.values()}
        }]
    });
   
</script>