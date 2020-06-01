<!-- DataTable Bootstrap Script -->
<script src="${js}/angular.js"></script>

<!-- DataTable Bootstrap Script -->
<script src="${js}/livroController.js"></script>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>		
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
    				<h3 class="panel-title"><strong>Meus Cupons</strong></h3>
    				
    			</div>
    		
    			<div class='col-xs-12'>
				
					<br>
					<table id="cuponsCliente" class="table table-condensed table-bordered">
									
						<thead>					
							<tr>					
								<th>Numero</th>
								<th>Descricao</th>
								<th>Valor</th>
							</tr>					
						</thead>
						
						<tfoot>
							<tr>					
								<th>Numero</th>
								<th>Descricao</th>
								<th>Valor</th>
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
    <hr/>
    
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#AlterarSenhaModal">Alterar Senha</button>
    <br>
    <br>
    <!-- Modal -->
	<div class="modal fade" id="AlterarSenhaModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">Alterar Senha</h4>
	      </div>
	      <div class="modal-body">
	        
	        <sf:form id="senhaForm" class="form-horizontal" modelAttribute="usuario" action="${contextRoot}/gerenciar/usuario/senha" method="POST">
	        	
       			<div class="form-group">
					<label class="control-label col-md-4">Senha</label>
					<div class="col-md-8">
						<sf:input type="password" path="senha" class="form-control"	placeholder="Senha" value="" minlength="8"/>
						<sf:errors path="senha" cssClass="help-block" element="em"/> 
					</div>
				</div>
							
				<div class="form-group">
					<label class="control-label col-md-4">Confirmar Senha</label>
					<div class="col-md-8">
						<sf:input type="password" path="confirmaSenha" class="form-control"
							placeholder="Confirmar Senha" />
						<sf:errors path="confirmaSenha" cssClass="help-block" element="em"/>										 
					</div>
				</div> 	        
	        
	        
				<div class="form-group">				
					<div class="col-md-offset-4 col-md-4">					
						<input type="submit" name="submit" value="Salvar" class="btn btn-primary"/>						
					</div>
				</div>	        
	        </sf:form>
	      </div>
	    </div>
	  </div>
	</div>
</div>
