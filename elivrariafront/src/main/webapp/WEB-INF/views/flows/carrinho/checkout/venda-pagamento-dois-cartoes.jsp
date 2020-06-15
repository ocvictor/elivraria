<%@include file="../../flows-shared/header.jsp" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>			
<%@taglib prefix="sf2" uri="http://www.springframework.org/tags/form" %>		
<div class="container">
	
	<c:if test="${not empty message}">	
		<div class="row">			
			<div class="col-xs-12 col-md-offset-2 col-md-8">			
				<div class="alert alert-info fade in">${message}</div>				
			</div>
		</div>
	</c:if>
	<script type="text/javascript">
		function updateInput(ish){
			var rest = ${checkoutModelo.checkoutTotal} - ish
			document.getElementById("valorSegundoCartao").value = rest.toFixed(2)
		}
	</script>
	
	<div class="row">
		<!--  To display all the goods -->
		<div class="col-md-6">
			
			<div class="row">
				<c:forEach items="${checkoutModelo.itemCarrinho}" var="itemCarrinho">
				<div class="col-xs-12">
					
					<div>
						<h3>${itemCarrinho.livro.titulo}</h3>
						<hr/>
						<h5>Quantidade - ${itemCarrinho.livroQtd}</h4>
						<h5>Preço - R$ ${itemCarrinho.precoCompra}</h5>							
					</div>						
					<hr/>
					<div class="text-right">
						<h4>Total Item - R$ ${itemCarrinho.total}</h3>
						<hr/>				
					</div>						
				</div>

				</c:forEach>
				<div class="text-right">
					<div>												
						<h2> Total Pedido - R$ ${checkoutModelo.checkoutTotal}</h2>				
					</div>				
				</div>
				
			</div>			
		</div>
			
		<div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        Detalhes Pagamento
                    </h3>
                </div>
                
                <div class="col-md-12">
			
					<h4>Selecionar Cartões</h4>
					<hr/>
					
					<div class="panel-body">
								
				<sf:form
					method="POST"
					modelAttribute="doisCartoes"
					class="form-horizontal"
					id="cartaoForm"
				>
				
					<div class="form-group">
						<label class="control-label col-md-4">Primeiro Cartão</label>
						<div class="col-md-8">
							<sf:select id="primeiroCartao" path="primeiroCartao.id" items="${cartao}" itemLabel="descricao" itemValue="cartaoId" class="form-control"/>
							<sf:errors path="primeiroCartao.id" cssClass="help-block" element="em"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4">Valor</label>
						<div class="col-md-8">
							<sf:input type="number" path="valorPrimeiroCartao"
								placeholder="Insira o Valor a ser pago neste cartão" value="0.0" step="0.01" onblur="updateInput(this.value)" class="form-control" />
							<sf:errors path="valorPrimeiroCartao" cssClass="help-block" element="em"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4">Segundo Cartão</label>
						<div class="col-md-8">
							<sf:select id="segundoCartao" path="segundoCartao.id" value="" items="${cartao}" itemLabel="descricao" itemValue="cartaoId" class="form-control" />
							<sf:errors path="segundoCartao.id" cssClass="help-block" element="em"/>
							
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4">Valor</label>
						<div class="col-md-8">
							<sf:input type="number" id="valorSegundoCartao" value="0.0" readonly="true" path="valorSegundoCartao" step="0.01" class="form-control"
								placeholder="Insira o Valor a ser pago neste cartão"/>
							<sf:errors path="valorSegundoCartao" cssClass="help-block" element="em"/>
													
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-md-offset-4 col-md-8">
														
							<button type="submit" name="_eventId_processarDoisCartoes" class="btn btn-primary"> Efetuar Pagamento
							</button>																	 
						</div>
					</div>				
				</sf:form>
			
			</div>		
				</div>
			</div>			
			
		</div>
	</div>
</div>
<%@include file="../../flows-shared/footer.jsp" %>