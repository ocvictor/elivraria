<c:set var="availableCount" value="${usuarioModelo.carrinho.itens}" />
<div class="container">


	<c:if test="${not empty message}">
		
		<div class="alert alert-info">
			<h3 class="text-center">${message}</h3>
		</div>		
	
	</c:if>
	
	<c:choose>
		<c:when test="${not empty itemCarrinho}">
			<table id="cart" class="table table-hover table-condensed">
			   	<thead>
					<tr>
						<th style="width:50%">Produto</th>
						<th style="width:10%">Preço</th>
						<th style="width:8%">Quantidade</th>
						<th style="width:22%" class="text-center">Subtotal</th>
						<th style="width:10%"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${itemCarrinho}" var="itemCarrinho">
					<c:if test="${itemCarrinho.disponivel == false}">
						<c:set var="availableCount" value="${availableCount - 1}"/>
					</c:if>
					
					<tr>
						<td data-th="Livro">
							<div class="row">
								<div class="col-sm-2 hidden-xs">
									<img src="${images}/${itemCarrinho.livro.ISBN}.jpg" alt="${itemCarrinho.livro.titulo}" class="img-responsive dataTableImg"/></div>
								<div class="col-sm-10">
									<h4 class="nomargin">${itemCarrinho.livro.titulo} 
										<c:if test="${itemCarrinho.disponivel == false}">
											<strong style="color:red">(Indisponível)</strong> 
										</c:if>
									</h4>
									<p>Editora : ${itemCarrinho.livro.editora}</p>
									<p>Autor : ${itemCarrinho.livro.autor}
								</div>
							</div>
						</td>
						<td data-th="Preço"> R$ ${itemCarrinho.precoCompra} </td>
						<td data-th="Quantidade">
							<input type="number" id="count_${itemCarrinho.id}" class="form-control text-center" value="${itemCarrinho.livroQtd}" min="1" max="3">
						</td>
						<td data-th="Subtotal" class="text-center">R$ ${itemCarrinho.total}</td>
						<td class="actions" data-th="">
							<c:if test="${itemCarrinho.disponivel == true}">
								<button type="button" name="refreshCart" class="btn btn-info btn-sm" value="${itemCarrinho.id}"><span class="glyphicon glyphicon-refresh"></span></button>
							</c:if>												
							<a href="${contextRoot}/carrinho/${itemCarrinho.id}/remover" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash"></span></a>								
						</td>
					</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr class="visible-xs">
						<td class="text-center"><strong>Total R$ ${usuarioModelo.carrinho.total}</strong></td>
					</tr>
					<tr>
						<td><a href="${contextRoot}/mostrar/todos/livros" class="btn btn-warning"><span class="glyphicon glyphicon-chevron-left"></span> Continuar Comprando</a></td>
						<td colspan="2" class="hidden-xs"></td>
						<td class="hidden-xs text-center"><strong>Total R$ ${usuarioModelo.carrinho.total}</strong></td>
						
						<c:choose>
							<c:when test="${qtdDisponivel != 0}">
								<td><a href="${contextRoot}/carrinho/validar" class="btn btn-success btn-block">Checkout <span class="glyphicon glyphicon-chevron-right"></span></a></td>
							</c:when>							
							<c:otherwise>
								<td><a href="javascript:void(0)" class="btn btn-success btn-block disabled"><strike>Checkout <span class="glyphicon glyphicon-chevron-right"></span></strike></a></td>
							</c:otherwise>
						</c:choose>						
					</tr>
				</tfoot>
			</table>
		
		</c:when>
		
		<c:otherwise>
			
			<div class="jumbotron">
				
				<h3 class="text-center">Carrinho Vazio!</h3>
			
			</div>
		
		</c:otherwise>
	</c:choose>




</div>
