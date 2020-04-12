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

		<div class="col-md-offset-2 col-md-8">

			<h1>Gerenciar Estoque</h1>
			<hr/>
			
			<h3>Livros Cadastrados</h3>
			<hr/>
			
			<div class="row">
						
				
				<div class='col-xs-12'>
				
				
					<table id="livrosTableEstoque" class="table table-condensed table-bordered">
									
						<thead>					
							<tr>					
								<th>Id</th>
								<th>ISBN</th>
								<th>Titulo</th>
								<th>Quantidade Disponível</th>
								<th>Preço Venda</th>
								<th>Dar Entrada Estoque</th>
							</tr>					
						</thead>
						
						<tfoot>
							<tr>					
								<th>Id</th>
								<th>ISBN</th>
								<th>Titulo</th>
								<th>Quantidade Disponível</th>
								<th>Preço Venda</th>
								<th>Dar Entrada Estoque</th>
							</tr>									
						</tfoot>
						
									
					</table>
				
				
				</div>
				
				<hr/>			
			</div>
			
			<div class="panel-heading">
					
				<h3>Estoque</h3>

			</div>
			
			<div class="panel-body">
				<sf:form class="form-horizontal" modelAttribute="estoque" action="${contextRoot}/gerenciar/estoque" method="POST" enctype="multipart/form-data">
					
					<div class="form-group">
						<div class="col-md-8">
							<sf:input type="hidden" value="${livro.id}" path="livroId" class="form-control"/>
							<sf:errors path="livroId" cssClass="help-block" element="em"/> 
						</div>
					</div>
						
					<div class="form-group">					
						<label class="control-label col-md-4">ISBN</label>
						<div class="col-md-8">
							<sf:input value="${livro.ISBN}" disabled="true" path= "" class="form-control"/>
							<sf:errors cssClass="help-block" element="em"/> 
						</div>
					</div>
				
 					<div class="form-group">
 						<label class="control-label col-md-4">Código de Barras</label>
 						<div class="col-md-8">
							<sf:input value="${livro.codBarras}" disabled="true" path= "" class="form-control" />
							<sf:errors path="" cssClass="help-block" element="em"/> 
 						</div>
 					</div>

					
 					<div class="form-group">
						<label class="control-label col-md-4">Titulo</label>
 						<div class="col-md-8">
							<sf:input value="${livro.titulo}" disabled="true" path= "" class="form-control"/>
							<sf:errors path="" cssClass="help-block" element="em"/> 
 						</div>
 					</div>
					
 					<div class="form-group">
 						<label class="control-label col-md-4">Autor</label>
 						<div class="col-md-8">
							<sf:input value="${livro.autor}" disabled="true" path= "" class="form-control"/> 
							<sf:errors path="" cssClass="help-block" element="em"/>	
 						</div>
 					</div>
					
 					<div class="form-group">
 						<label class="control-label col-md-4">Editora</label>
 						<div class="col-md-8">
							<sf:input value="${livro.editora}" disabled="true" path= "" class="form-control"
								  /> 
							<sf:errors path="" cssClass="help-block" element="em"/>	
 						</div>
 					</div>
 					
 					<div class="form-group">
						<label class="control-label col-md-4">Fornecedor</label>
						<div class="col-md-8">
							<sf:select path="fornecedorId" items="${fornecedores}" itemLabel="nome" itemValue="id" class="form-control"/>														
						</div>
					</div>
					
					 <div class="form-group">
						<label class="control-label col-md-4">Valor de Custo</label>
						<div class="col-md-8">
							<sf:input type="number" path="valorCusto" class="form-control"
								placeholder="Insira o Valor de Custo" />
							<sf:errors path="valorCusto" cssClass="help-block" element="em"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4">Quantidade Comprada</label> 
						<div class="col-md-8">
							<sf:input type="number" path="quantidade" class="form-control"
								placeholder="Insira quantidade" />
							<sf:errors path="quantidade" cssClass="help-block" element="em"/> 
						</div>
					</div>
				
					<div class="form-group">
						
						<div class="col-md-offset-4 col-md-4">
						
							<input type="submit" name="submit" value="Salvar" class="btn btn-primary"/>
							
						</div>
					</div>
				</sf:form>
			</div>		
			<hr/>	
			<h1>Ultimos Estoques</h1>
			<hr/>
	
			<div class="row">				
		
				<div class='col-xs-12'>			
				
					<table id="estoqueTable" class="table table-condensed table-bordered">
									
						<thead>					
							<tr>					
								<th>Data Entrada</th>
								<th>Livro</th>
								<th>Quantidade Comprada</th>
								<th>Preço Custo</th>
								<th>Editar</th>
								<th>Excluir</th>		
																
							</tr>					
						</thead>
						
						<tfoot>
							<tr>					
								<th>Data Entrada</th>
								<th>Livro</th>
								<th>Quantidade Comprada</th>
								<th>Preço Custo</th>
								<th>Editar</th>
								<th>Excluir</th>							
							</tr>									
						</tfoot>				
									
					</table>				
				</div>	
			</div>
 		</div>
 	</div>
</div>