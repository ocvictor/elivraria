<p class="lead">Categorias</p>




<div class="list-group">


	<c:forEach items="${categorias}" var="categoria">
		<a href="${contextRoot}/mostrar/categoria/${categoria.id}/livros" class="list-group-item" id="a_${categoria.nome}">${categoria.nome}</a>
	</c:forEach>

	 
</div>