<div class="container">

		<div id="container"
			style="width: 550px; height: 400px; margin: 0 auto"></div>
	</div>

	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
	<script type="text/javascript">
	
	var formatteddata = [];
	var result = "${graficoMap}";
	for(var key in result){
		var singleObject = {
				name: '',
				data: []
			}
		singleObject.name = key.toUpperCase();
		for(var i = 0; i < result[key].length; i++){
			singleObject.data.push(parseInt(result[key][i].quantidade));
		}
		formatteddata.push(singleObject);
	}
   
   Highcharts.chart('container', {

	    title: {
	        text: 'Solar Employment Growth by Sector, 2010-2019'
	    },

	    subtitle: {
	        text: 'Source: thesolarfoundation.com'
	    },
	    
	    xAxis: {
	    	categories: ${graficoXMap.values()}
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

	    plotOptions: {
	        series: {
	            label: {
	                connectorAllowed: false
	            },
	            pointStart: 2019
	        }
	    },

	    series: ${graficoYMap.values()},

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