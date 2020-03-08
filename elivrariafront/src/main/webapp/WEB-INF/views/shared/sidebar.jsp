<p class="lead">Categorias</p>




<div class="list-group">


	<c:forEach items="${categoria}" var="category">
		<a href="${contextRoot}/show/category/${categoria.id}/products" class="list-group-item" id="a_${categoria.nome}">${categoria.nome}</a>
	</c:forEach>

	 
</div>