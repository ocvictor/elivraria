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

			<h1>Gerenciar Trocas e Cancelamentos</h1>
			<hr/>
			
			<h3>Trocas Solicitadas</h3>
			<hr/>
			
			<div class="row">
						
				
				<div class='col-xs-12'>
				
				
					<table id="trocasSolicitadasTable" class="table table-condensed table-bordered">
									
						<thead>					
							<tr>					
								<th>Data</th>
								<th>Cliente</th>
								<th>Pedido</th>
								<th>Livro</th>
								<th>Quantidade</th>
								<th>Visualizar</th>
							</tr>					
						</thead>
						
						<tfoot>
							<tr>					
								<th>Data</th>
								<th>Cliente</th>
								<th>Pedido</th>
								<th>Livro</th>
								<th>Quantidade</th>
								<th>Visualizar</th>
							</tr>									
						</tfoot>
						
									
					</table>
				
				
				</div>
				
				<hr/>			
			</div>
			
			<h3>Trocas Em Análise</h3>
			<hr/>
			
			<div class="row">
						
				
				<div class='col-xs-12'>
				
				
					<table id="trocasAnaliseTable" class="table table-condensed table-bordered">
									
						<thead>					
							<tr>					
								<th>Data</th>
								<th>Cliente</th>
								<th>Pedido</th>
								<th>Livro</th>
								<th>Quantidade</th>
								<th>Visualizar</th>
							</tr>					
						</thead>
						
						<tfoot>
							<tr>					
								<th>Data</th>
								<th>Cliente</th>
								<th>Pedido</th>
								<th>Livro</th>
								<th>Quantidade</th>
								<th>Visualizar</th>
							</tr>									
						</tfoot>
						
									
					</table>
				
				
				</div>
				
				<hr/>			
			</div>
			
			<h3>Trocas Confirmadas</h3>
			<hr/>
			
			<div class="row">
						
				
				<div class='col-xs-12'>
				
				
					<table id="trocasConfirmadasTable" class="table table-condensed table-bordered">
									
						<thead>					
							<tr>					
								<th>Data</th>
								<th>Cliente</th>
								<th>Pedido</th>
								<th>Livro</th>
								<th>Quantidade</th>
							</tr>					
						</thead>
						
						<tfoot>
							<tr>					
								<th>Data</th>
								<th>Cliente</th>
								<th>Pedido</th>
								<th>Livro</th>
								<th>Quantidade</th>
							</tr>									
						</tfoot>
						
									
					</table>
				
				
				</div>
				
				<hr/>			
			</div>	
 		</div>
 	</div>
</div>