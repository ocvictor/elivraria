<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="container">

	<c:if test="${not empty message}">	
		<div class="row">			
			<div class="col-xs-12 col-md-offset-2 col-md-8">			
				<div class="alert alert-info fade in">${message}</div>				
			</div>
		</div>
	</c:if>

	<div class="row">

		<div class="col-md-offset-1 col-md-10">

			<h1>Gerenciar Vendas</h1>
			<hr/>
			
			<h3>Vendas Aprovadas</h3>
			<hr/>
			
			<div class="row">
						
				
				<div class='col-xs-12'>
				
				
					<table id="vendasAprovadasTable" class="table table-condensed table-bordered">
									
						<thead>					
							<tr>					
								<th>Data</th>
								<th>Cliente</th>
								<th>Quantidade Itens</th>
								<th>Valor Total</th>
								<th>Status</th>
								<th>Avançar Status</th>
							</tr>					
						</thead>
						
						<tfoot>
							<tr>					
								<th>Data</th>
								<th>Cliente</th>
								<th>Quantidade Itens</th>
								<th>Valor Total</th>
								<th>Status</th>
								<th>Avançar Status</th>
							</tr>									
						</tfoot>
						
									
					</table>
				
				
				</div>
				
				<hr/>			
			</div>
			
			<h3>Vendas Em Transporte</h3>
			<hr/>
			
			<div class="row">
						
				
				<div class='col-xs-12'>
				
				
					<table id="vendasTransporteTable" class="table table-condensed table-bordered">
									
						<thead>					
							<tr>					
								<th>Data</th>
								<th>Cliente</th>
								<th>Quantidade Itens</th>
								<th>Valor Total</th>
								<th>Status</th>
								<th>Avançar Status></th>
							</tr>					
						</thead>
						
						<tfoot>
							<tr>					
								<th>Data</th>
								<th>Cliente</th>
								<th>Quantidade Itens</th>
								<th>Valor Total</th>
								<th>Status</th>
								<th>Avançar Status></th>
							</tr>									
						</tfoot>
						
									
					</table>
				
				
				</div>
				
				<hr/>			
			</div>
			
			<h3>Vendas Entregues</h3>
			<hr/>
			
			<div class="row">
						
				
				<div class='col-xs-12'>
				
				
					<table id="vendasEntreguesTable" class="table table-condensed table-bordered">
									
						<thead>					
							<tr>					
								<th>Data</th>
								<th>Cliente</th>
								<th>Quantidade Itens</th>
								<th>Valor Total</th>
								<th>Status</th>
							</tr>					
						</thead>
						
						<tfoot>
							<tr>					
								<th>Data</th>
								<th>Cliente</th>
								<th>Quantidade Itens</th>
								<th>Valor Total</th>
								<th>Status</th>
							</tr>									
						</tfoot>									
					</table>				
				</div>			
				<hr/>			
			</div>
			
			
 		</div>
 	</div>
</div>