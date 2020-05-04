<!-- DataTable Bootstrap Script -->
<script src="${js}/angular.js"></script>

<!-- DataTable Bootstrap Script -->
<script src="${js}/livroController.js"></script>

<div class="container" ng-app="elivraria">

	<script>
		window.usuarioId = '${usuario.id}';
	</script>
	
	<c:if test="${not empty message}">
		
		<div class="alert alert-info">
			<h3 class="text-center">${message}</h3>
		</div>		
	
	</c:if>
	
	<h2>Bem vindo,  ${usuario.nome}  </h2>
	<hr/>
	
	<div class="row">
    	<div class="col-md-12">
    		<div class="panel panel-default">
    			<div class="panel-heading">
    				<h3 class="panel-title"><strong>Meus Pedidos</strong></h3>
    				
    			</div>
    		
    			<div class='col-xs-12'>
				
					<br>
					<table id="pedidosCliente" class="table table-condensed table-bordered">
									
						<thead>					
							<tr>					
								<th>Data</th>
								<th>Nº Pedido</th>
								<th>Qtd Itens</th>
								<th>Valor Total</th>
								<th>Status</th>
								<th>Visualizar</th>
							</tr>					
						</thead>
						
						<tfoot>
							<tr>					
								<th>Data</th>
								<th>Nº Pedido</th>
								<th>Qtd Itens</th>
								<th>Valor Total</th>
								<th>Status</th>
								<th>Visualizar</th>
							</tr>									
						</tfoot>
						
									
					</table>
				
				
				</div>
    		</div>
    	</div>
    </div>
    
    <hr/>
    
    <div class="row">
    	<div class="col-md-12">
    		<div class="panel panel-default">
    			<div class="panel-heading">
    				<h3 class="panel-title"><strong>Meus Endereços</strong></h3>
    			</div>
    			<div class='col-xs-12'>
				
									<br>
				
					<table id="enderecosCliente" class="table table-condensed table-bordered">
									
						<thead>					
							<tr>					
								<th>Tipo</th>
								<th>Logradouro</th>
								<th>Numero</th>
								<th>CEP</th>
								<th>Bairro</th>
								<th>Cidade</th>
								<th>Estado</th>
								<th>País</th>
								<th>Editar</th>
							</tr>					
						</thead>
						
						<tfoot>
							<tr>					
								<th>Tipo</th>
								<th>Logradouro</th>
								<th>Numero</th>
								<th>CEP</th>
								<th>Bairro</th>
								<th>Cidade</th>
								<th>Estado</th>
								<th>País</th>
								<th>Editar</th>
							</tr>									
						</tfoot>
						
									
					</table>
				
				
				</div>
    		</div>
    	</div>
    </div>
    
    <hr/>
    
    <div class="row">
    	<div class="col-md-12">
    		<div class="panel panel-default">
    			<div class="panel-heading">
    				<h3 class="panel-title"><strong>Meus Cartões</strong></h3>
    			</div>
    			<div class='col-xs-12'>
				
									<br>
				
					<table id="cartoesCliente" class="table table-condensed table-bordered">
									
						<thead>					
							<tr>					
								<th>Bandeira</th>
								<th>Numero</th>
								<th>Nome</th>
								<th>Mes Venc</th>
								<th>Ano Venc</th>
								<th>Editar</th>
							</tr>					
						</thead>
						
						<tfoot>
							<tr>					
								<th>Bandeira</th>
								<th>Numero</th>
								<th>Nome</th>
								<th>Mes Venc</th>
								<th>Ano Venc</th>
								<th>Editar</th>
							</tr>									
						</tfoot>
						
									
					</table>
				
				
				</div>
    		</div>
    	</div>
    </div>
</div>
