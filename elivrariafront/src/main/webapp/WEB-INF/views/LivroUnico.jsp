<div class="container">

	<!-- Breadcrumb -->
	<div class="row">
		
		<div class="col-xs-12">
		
			
			<ol class="breadcrumb">
			
				<li><a href="${contextRoot}/home">Home</a></li>
				<li><a href="${contextRoot}/mostrar/todos/livros">Livros</a></li>
				<li class="active">${livro.titulo}</li>
			
			</ol>
		
		
		</div>
	
	
	</div>
	
	
	<div class="row">
	
		<!-- Display the product image -->
		<div class="col-xs-12 col-sm-4">
		
			<div class="thumbnail">
							
				<img src="${images}/${livro.ISBN}.jpg" class="img img-responsive"/>
						
			</div>
		
		</div>
	
		
		<!-- Display the product description -->	
		<div class="col-xs-12 col-sm-8">
		
			<h3>${livro.titulo}</h3>
			<hr/>
			
			<p>${livro.sinopse}</p>
			<hr/>
			
			<h4>Preço: <strong> R$ ${livro.precoUnit} </strong></h4>
			<hr/>
			
			
			
			<c:choose>
				
				<c:when test="${livro.quantidade < 1}">
				
					<h6>Qtd. Disponível: <span style="color:red">Sem Estoque!</span></h6>
					
				</c:when>
				<c:otherwise>				
					
					<h6>Qtd. Disponível: ${livro.quantidade}</h6>
						
				</c:otherwise>
			
			</c:choose>
			
			
			<security:authorize access="isAnonymous() or hasAuthority('USER')">	

			<c:choose>
				
				<c:when test="${livro.quantidade < 1}">
				
					<a href="javascript:void(0)" class="btn btn-success disabled"><strike>
					<span class="glyphicon glyphicon-shopping-cart"></span> Adicionar ao Carrinho</strike></a>
					
				</c:when>
				<c:otherwise>				
				
				<a href="${contextRoot}/carrinho/adicionar/${livro.id}/livro" class="btn btn-success">
				<span class="glyphicon glyphicon-shopping-cart"></span> Adicionar ao Carrinho</a>
				
				
				
						
				</c:otherwise>
			
			</c:choose>
			</security:authorize>
			
			
			<security:authorize access="hasAuthority('ADMIN')">
				<a href="${contextRoot}/gerenciar/${livro.id}/livro" class="btn btn-success">
				<span class="glyphicon glyphicon-pencil"></span> Editar</a>
			</security:authorize>	
						
			

			<a href="${contextRoot}/mostrar/todos/livros" class="btn btn-warning">
				Continuar Comprando</a>
					
		</div>

	
	</div>

</div>