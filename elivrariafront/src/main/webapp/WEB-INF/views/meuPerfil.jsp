<div class="container">


	<c:if test="${not empty message}">
		
		<div class="alert alert-info">
			<h3 class="text-center">${message}</h3>
		</div>		
	
	</c:if>
	
	<div class="row">
    	<div class="col-md-12">
    		<div class="panel panel-default">
    			<div class="panel-heading">
    				<h3 class="panel-title"><strong>Meus Pedidos</strong></h3>
    			</div>
    			<div class="panel-body">
    				<div class="table-responsive">
    					<table class="table table-condensed">
    						<thead>
                                <tr>
        							<td><strong>Item</strong></td>
        							<td class="text-center"><strong>Preço</strong></td>
        							<td class="text-center"><strong>Quantidade</strong></td>
        							<td class="text-right"><strong>Total</strong></td>
                                </tr>
    						</thead>
    						<tbody>
    							<!-- foreach ($order->lineItems as $line) or some such thing here -->
    							<c:forEach items="${vendaDetalhe.itemVenda}" var="itemVenda">
	    							<tr>
	    								<td>${itemVenda.livro.titulo}</td>
	    								<td class="text-center">R$ ${itemVenda.compraPreco}</td>
	    								<td class="text-center">${itemVenda.qtdLivro}</td>
	    								<td class="text-right">R$ ${itemVenda.total}</td>
	    							</tr>
    							</c:forEach>
    						</tbody>
    					</table>
    				</div>
    			</div>
    		</div>
    	</div>
    </div>
    
    <div class="row">
    	<div class="col-md-12">
    		<div class="panel panel-default">
    			<div class="panel-heading">
    				<h3 class="panel-title"><strong>Meus Endereços</strong></h3>
    			</div>
    			<div class="panel-body">
    				<div class="table-responsive">
    					<table class="table table-condensed">
    						<thead>
                                <tr>
        							<td><strong>Item</strong></td>
        							<td class="text-center"><strong>Preço</strong></td>
        							<td class="text-center"><strong>Quantidade</strong></td>
        							<td class="text-right"><strong>Total</strong></td>
                                </tr>
    						</thead>
    						<tbody>
    							<!-- foreach ($order->lineItems as $line) or some such thing here -->
    							<c:forEach items="${vendaDetalhe.itemVenda}" var="itemVenda">
	    							<tr>
	    								<td>${itemVenda.livro.titulo}</td>
	    								<td class="text-center">R$ ${itemVenda.compraPreco}</td>
	    								<td class="text-center">${itemVenda.qtdLivro}</td>
	    								<td class="text-right">R$ ${itemVenda.total}</td>
	    							</tr>
    							</c:forEach>
    						</tbody>
    					</table>
    				</div>
    			</div>
    		</div>
    	</div>
    </div>
    
    <div class="row">
    	<div class="col-md-12">
    		<div class="panel panel-default">
    			<div class="panel-heading">
    				<h3 class="panel-title"><strong>Meus Cartões</strong></h3>
    			</div>
    			<div class="panel-body">
    				<div class="table-responsive">
    					<table class="table table-condensed">
    						<thead>
                                <tr>
        							<td><strong>Item</strong></td>
        							<td class="text-center"><strong>Preço</strong></td>
        							<td class="text-center"><strong>Quantidade</strong></td>
        							<td class="text-right"><strong>Total</strong></td>
                                </tr>
    						</thead>
    						<tbody>
    							<!-- foreach ($order->lineItems as $line) or some such thing here -->
    							<c:forEach items="${vendaDetalhe.itemVenda}" var="itemVenda">
	    							<tr>
	    								<td>${itemVenda.livro.titulo}</td>
	    								<td class="text-center">R$ ${itemVenda.compraPreco}</td>
	    								<td class="text-center">${itemVenda.qtdLivro}</td>
	    								<td class="text-right">R$ ${itemVenda.total}</td>
	    							</tr>
    							</c:forEach>
    						</tbody>
    					</table>
    				</div>
    			</div>
    		</div>
    	</div>
    </div>
</div>
