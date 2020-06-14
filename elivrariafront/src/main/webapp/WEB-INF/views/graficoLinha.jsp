<div class="container">

		<div id="container"
			style="width: 800px; height: 450px; margin: 0 auto"></div>
	</div>

	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
	<script type="text/javascript">
	

   Highcharts.chart('container', {

	    title: {
	        text: 'Relatório de Vendas Por ${relatorioModelo.indicador}'
	    },

	    subtitle: {
	        text: 'Período de ${relatorioModelo.dataInicial} até ${relatorioModelo.dataFinal}'
	    },
	    
	    xAxis: {
	    	categories: ${anoMeses}
	    },

	    yAxis: {
	        title: {
	            text: 'Quantidade de Vendas'
	        }
	    },
	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'middle'
	    },



	    series: [${series}],

	    responsive: {
	        rules: [{
	            condition: {
	                maxWidth: 500
	            },
	            chartOptions: {
	                legend: {
	                    layout: 'horizontal',
	                    align: 'center',
	                    verticalAlign: 'bottom'
	                }
	            }
	        }]
	    }

	});
</script>