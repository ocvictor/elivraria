<!-- DataTable Bootstrap Script -->
<script src="${js}/angular.js"></script>

<!-- DataTable Bootstrap Script -->
<script src="${js}/livroController.js"></script>

<div class="container" ng-app="elivraria" ng-controller="LivroController as lCtrl">

	<div class="row" ng-init="lCtrl.fetchProducts()">


		<!-- Would be to display sidebar -->
		<div class="col-md-3">

			<%@include file="./shared/sidebar.jsp"%>

		</div>

		<!-- to display the actual products -->
		<div class="col-md-9">

			<!-- Added breadcrumb component -->
			<div class="row">

				<div class="col-lg-12">

					<c:if test="${ClickTodosLivros == true}">
					
						<script>
							window.categoriaId = '';
						</script>
					
						<ol class="breadcrumb">


							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">Todos os Livros</li>


						</ol>
					</c:if>
					
					
					<c:if test="${ClickCategoriaLivros == true}">
						<script>
							window.categoriaId = '${categoria.id}';
						</script>
					
						<ol class="breadcrumb">


							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">Categoria</li>
							<li class="active">${categoria.nome}</li>


						</ol>
					</c:if>
					

				</div>


			</div>

			
			<div class="row">
			
				<div class="col-xs-12">
				
					
					<table id="LivroListaTabela" class="table table-striped table-borderd">
					
					
						<thead>
						
							<tr>
								<th></th>
								<th>Título</th>
								<th>Editora</th>
								<th>Preço</th>
								<th>Qtd. Disponível</th>
								<th></th>
							
							</tr>
						
						</thead>
					

						<tfoot>
						
							<tr>
								<th></th>
								<th>Título</th>
								<th>Editora</th>
								<th>Preço</th>
								<th>Qtd. Disponível</th>
								<th></th>
							
							</tr>
						
						</tfoot>
					</table>
				
				</div>
			
			</div>

		</div>

	</div>

</div>