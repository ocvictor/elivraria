<%@include file="../flows-shared/header.jsp" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>			
	<div class="container">
		
		
		<div class="row">
			
			<div class="col-md-6 col-md-offset-3">
				
				<div class="panel panel-primary">
				
					<div class="panel-heading">
						<h4>Registrar - Cartão</h4>
					</div>
					
					<div class="panel-body">
										
						<sf:form
							method="POST"
							modelAttribute="cartao"
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
									<button type="submit" name="_eventId_endereco" class="btn btn-primary">
										<span class="glyphicon glyphicon-chevron-left"></span> Voltar - Endereço
									</button>								
									<button type="submit" name="_eventId_confirmar" class="btn btn-primary">
										Proximo - Confirmação<span class="glyphicon glyphicon-chevron-right"></span>
									</button>																	 
								</div>
							</div>
							
						
						
						</sf:form>					
					
					
					</div>
				
				
				</div>
			
			
			</div>
		
		
		</div>
		
		
	</div>

<%@include file="../flows-shared/footer.jsp" %>			
