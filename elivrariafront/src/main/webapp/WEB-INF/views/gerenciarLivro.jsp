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

			<div class="panel panel-primary">

				<div class="panel-heading">

					<h4>Gerenciar Livros</h4>

				</div>

				<div class="panel-body">
					<sf:form class="form-horizontal" modelAttribute="livro" action="${contextRoot}/gerenciar/livro" method="POST" enctype="multipart/form-data">
						<div class="form-group">
							<label class="control-label col-md-4">Código de Barras</label>
							<div class="col-md-8">
								<sf:input type="text" path="codBarras" class="form-control"
									placeholder="Insira o Código de Barras" />
								<sf:errors path="codBarras" cssClass="help-block" element="em"/> 
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4">ISBN</label>
							<div class="col-md-8">
								<sf:input type="text" path="ISBN" class="form-control"
									placeholder="Insira o ISBN do Livro" />
								<sf:errors path="ISBN" cssClass="help-block" element="em"/> 
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4">Titulo</label>
							<div class="col-md-8">
								<sf:input type="text" path="titulo" class="form-control"
									placeholder="Titulo" />
								<sf:errors path="titulo" cssClass="help-block" element="em"/> 
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4">Autor</label>
							<div class="col-md-8">
								<sf:input type="text" path="autor" class="form-control"
									placeholder="Autor" /> 
								<sf:errors path="autor" cssClass="help-block" element="em"/>	
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4">Editora</label>
							<div class="col-md-8">
								<sf:input type="text" path="editora" class="form-control"
									placeholder="Insira a Editora do Livro" /> 
								<sf:errors path="editora" cssClass="help-block" element="em"/>	
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4">Ano</label>
							<div class="col-md-8">
								<sf:input type="number" path="ano" class="form-control"
									placeholder="Insira o ano do Livro" /> 
								<sf:errors path="ano" cssClass="help-block" element="em"/>	
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4">Edição</label>
							<div class="col-md-8">
								<sf:input type="text" path="edicao" class="form-control"
									placeholder="Insira a Edição do Livro" /> 
								<sf:errors path="edicao" cssClass="help-block" element="em"/>	
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4">Sinopse</label>
							<div class="col-md-8">
								<sf:textarea path="sinopse" class="form-control"
									placeholder="Insira a Sinopse do Livro" /> 
								<sf:errors path="sinopse" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4">Numero de Pags</label>
							<div class="col-md-8">
								<sf:input type="number" path="numPaginas" class="form-control"
									placeholder="Insira o números de páginas do livro" /> 
								<sf:errors path="numPaginas" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4">Altura</label>
							<div class="col-md-8">
								<sf:input type="number" step="0.01" path="altura" class="form-control"
									placeholder="Insira a altura do livro" /> 
								<sf:errors path="altura" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4">Largura</label>
							<div class="col-md-8">
								<sf:input type="number" step="0.01" path="largura" class="form-control"
									placeholder="Insira a largura do livro" /> 
								<sf:errors path="largura" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4">Profundidade</label>
							<div class="col-md-8">
								<sf:input type="number" step="0.01" path="profundidade" class="form-control"
									placeholder="Insira a profundidade do livro" /> 
								<sf:errors path="profundidade" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4">Peso</label>
							<div class="col-md-8">
								<sf:input type="number" step="0.01" path="peso" class="form-control"
									placeholder="Insira o números de páginas do livro" /> 
								<sf:errors path="peso" cssClass="help-block" element="em"/>
							</div>
						</div>
						
					<div class="form-group">
						<label class="control-label col-md-4">Grupo Precificação</label>
						<div class="col-md-8">
							<sf:select path="grupoPrecificacaoId" items="${grupoPrecificacao}" itemLabel="descricao" itemValue="id" class="form-control"/>
														
						</div>							
					</div>
						 <div class="form-group">
							<!-- <label class="control-label col-md-4">Preço Unitário</label> -->
							<div class="col-md-8">
								<sf:input type="hidden" path="precoUnit" class="form-control"
									placeholder="Insira o preço unitário" />
								<sf:errors path="precoUnit" value="0.0" cssClass="help-block" element="em"/>
							</div>
						</div>

						<div class="form-group">
							<!-- <label class="control-label col-md-4">Quantidade em Estoque</label>  -->
							<div class="col-md-8">
								<sf:input type="hidden" path="quantidade" class="form-control"
									placeholder="Insira quantidade" />
								<sf:errors path="quantidade" value="0" cssClass="help-block" element="em"/> 
							</div>
						</div>


						<div class="form-group">
							<label class="control-label col-md-4">Imagem do Livro</label>
							<div class="col-md-8">
								<sf:input type="file" path="file" class="form-control"/>
								<sf:errors path="file" cssClass="help-block" element="em"/> 
							</div>
						</div>


						<div class="form-group">
							<label class="control-label col-md-4">Categoria</label>
							<div class="col-md-8">
								<sf:select path="categoria.id" items="${categorias}" itemLabel="nome" itemValue="id" class="form-control"/>
							
								<div class="text-right">
									<br/>			
									<sf:hidden path="id"/>
									<sf:hidden path="categoria.id"/>
									<sf:hidden path="ativo"/>														
									<button type="button" class="btn btn-warning btn-xs" data-toggle="modal" data-target="#CategoriaModal">Adicionar Categoria</button>
								</div>							
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

	<!-- Modal -->
	<div class="modal fade" id="CategoriaModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">Nova Categoria</h4>
	      </div>
	      <div class="modal-body">
	        
	        <sf:form id="categoriaForm" class="form-horizontal" modelAttribute="categoria" action="${contextRoot}/gerenciar/categoria" method="POST">
	        	
       			<div class="form-group">
					<label class="control-label col-md-4">Nome</label>
					<div class="col-md-8 validate">
						<sf:input type="text" path="nome" class="form-control"
							placeholder="Nome da Categoria" /> 
					</div>
				</div>
       			
       			<div class="form-group">				
					<label class="control-label col-md-4">Descricao</label>
					<div class="col-md-8 validate">
						<sf:textarea path="descricao" class="form-control"
							placeholder="Descricao da Categoria" /> 
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
	
	<hr/>	
	<h1>Livros Cadastrados</h1>
	<hr/>
	
	<div class="row">
				
		
		<div class='col-xs-12'>
		
		
			<table id="livrosTable" class="table table-condensed table-bordered">
							
				<thead>					
					<tr>					
						<th>Id</th>
						<th>ISBN</th>
						<th>Titulo</th>
						<th>Quantidade Disponível</th>
						<th>Preço</th>
						<th>Ativar/Desativar</th>				
						<th>Editar</th>
					</tr>					
				</thead>
				
				<tfoot>
					<tr>					
						<th>Id</th>
						<th>ISBN</th>
						<th>Titulo</th>
						<th>Quantidade Disponível</th>
						<th>Preço</th>
						<th>Ativar/Desativar</th>				
						<th>Editar</th>
					</tr>									
				</tfoot>
				
							
			</table>
		
		
		</div>
	
	
	</div>

</div>