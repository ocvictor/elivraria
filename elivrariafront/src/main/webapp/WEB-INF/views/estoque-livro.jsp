<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

	<div class="container">
	
		<c:if test="${not empty message}">	
			<div class="row">			
				<div class="col-xs-12 col-md-offset-2 col-md-8">			
					<div class="alert alert-info fade in">${message}</div>				
				</div>
			</div>
		</c:if>
		
		<hr/>	
		<h1>Livros Cadastrados</h1>
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
		
		
		</div>
	
	</div>
