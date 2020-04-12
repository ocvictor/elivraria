<%@include file="../../flows-shared/header.jsp" %>
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
		<!--  To display all the goods -->
		<div class="col-md-6">
			
			<div class="row">
				<c:forEach items="${checkoutModelo.itemCarrinho}" var="itemCarrinho">
				<div class="col-xs-12">
					
					<div>
						<h3>${itemCarrinho.livro.titulo}</h3>
						<hr/>
						<h4>Quantidade - ${itemCarrinho.livroQtd}</h4>
						<h5>Preço - R$ ${itemCarrinho.precoCompra}</h5>							
					</div>						
					<hr/>
					<div class="text-right">
						<h3>Total - R$ ${itemCarrinho.total}</h3>
					</div>						
				</div>
				</c:forEach>
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
			
					<h4>Selecionar Cartão</h4>
					<hr/>
					
					<div class="row">
						<c:forEach items="${cartoes}" var="cartao">					
							<div class="cols-xs-12">
								<h5>Bandeira: ${cartao.bandeira.descricao}</h5>
								<h5>Número: ${cartao.numeroCartao}</h5>
								<h5>Nome: ${cartao.nomeCartao}</h5>
								<h5>Mes/Ano Venc: ${cartao.mesVencimento}/${cartao.anoVencimento}</h5>
								<h5>CCV: ${cartao.ccv}</h5>
								<hr/>
								<div class="text-center">
									<a href="${flowExecutionUrl}&_eventId_selecaoCartao&cartaoId=${cartao.id}" class="btn btn-primary">Selecionar</a>
									<hr/>						
									<br>
								</div>												
							</div>
						</c:forEach>			
					</div>			
				</div>
			</div>
			
			<hr/>
			<hr/>						
							
			<div class="panel-heading">
				<h4>Registrar - Cartão</h4>
				<hr/>						
				
			</div>
			
			<div class="panel-body">
								
				<sf:form
					method="POST"
					modelAttribute="cartaoNovo"
					class="form-horizontal"
					id="cartaoForm"
				>
				
					<div class="form-group">
						<label class="control-label col-md-4">Bandeira</label>
						<div class="col-md-8">
							<sf:select path="bandeiraId" items="${bandeiras}" itemLabel="descricao" itemValue="id" class="form-control"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4" for="numeroCartao">Número Cartão</label>
						<div class="col-md-8">
							<sf:input type="tel" maxlength="16" path="numeroCartao" class="form-control"
								placeholder="xxxx xxxx xxxx xxxx"/>
							<sf:errors path="numeroCartao" cssClass="help-block" element="em"/> 
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4">Nome no Cartão</label>
						<div class="col-md-8">
							<sf:input type="text" path="nomeCartao" class="form-control"
								placeholder="Nome no Cartão" />
							<sf:errors path="nomeCartao" cssClass="help-block" element="em"/> 
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4">Mês Venc.</label>
						<div class="col-md-8">
							<sf:input type="numeric" path="mesVencimento" class="form-control"
								placeholder="XX" maxlength="2" />
							<sf:errors path="mesVencimento" cssClass="help-block" element="em"/> 
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-4">Ano Venc.</label>
						<div class="col-md-8">
							<sf:input type="numeric" path="anoVencimento" class="form-control"
								placeholder="XXXX" maxlength="4" />
							<sf:errors path="anoVencimento" cssClass="help-block" element="em"/> 
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4">CCV</label>
						<div class="col-md-8">
							<sf:input type="numeric" path="ccv" class="form-control"
								placeholder="Código de Verificação" maxlength="3" />
							<sf:errors path="ccv" cssClass="help-block" element="em"/> 
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-md-offset-4 col-md-8">
														
							<button type="submit" name="_eventId_salvarCartao" class="btn btn-primary">
								<span class="glyphicon glyphicon-plus"></span> Adicionar Cartão
							</button>																	 
						</div>
					</div>				
				</sf:form>
			
			</div>
            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a href="#"><span class="badge pull-right"> R$ ${checkoutModelo.checkoutTotal}</span> Pagamento Finalizar</a></li>
            </ul>
            <br/>
<%--             <a href="${flowExecutionUrl}&_eventId_pagar" class="btn btn-success btn-lg btn-block" role="button">Pagamento</a> --%>
			
		</div>
	</div>
</div>
<%@include file="../../flows-shared/footer.jsp" %>