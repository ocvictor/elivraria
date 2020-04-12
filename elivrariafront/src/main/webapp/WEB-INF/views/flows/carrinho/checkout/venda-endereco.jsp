<%@include file="../../flows-shared/header.jsp" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>			
<div class="container">

	<div class="row">
		
			<div class="col-md-4">
				
				<h4>Selecionar Endereço Entrega</h4>
				<hr/>
				
				<div class="row">
					<c:forEach items="${enderecos}" var="endereco">					
						<div class="cols-xs-12">
							<h3>${endereco.tipoResidencia}</h3>
							<h4>${endereco.tipoLogradouro} ${endereco.logradouro}, ${endereco.numero}</h4>
							<h4>${endereco.bairro}, ${endereco.cidade} - ${endereco.cep}</h4>
							<h4>${endereco.estado} - ${endereco.pais}</h4>
							<hr/>
							<div class="text-center">
								<a href="${flowExecutionUrl}&_eventId_selecaoEndereco&enderecoId=${endereco.id}" class="btn btn-primary">Selecionar</a>
							</div>												
						</div>
					</c:forEach>
				</div>
							
			</div>
					
			<div class="col-md-8">
			
				
				<div class="panel panel-primary">
				
					<div class="panel-heading">
						<h4>Adicionar - Endereço</h4>
					</div>
					
					<div class="panel-body">
										
						<sf:form
							method="POST"
							modelAttribute="entrega"
							class="form-horizontal"
							id="billingForm"
						>
						
							
							<div class="form-group">
								<label class="control-label col-md-4" for="tipoResidencia">Tipo de Residencia</label>
								<div class="col-md-8">
									<sf:input type="text" path="tipoResidencia" class="form-control"
										placeholder="Insira o Tipo de Residência" />
									<sf:errors path="tipoResidencia" cssClass="help-block" element="em"/> 
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4" for="tipologradouro">Tipo de Logradouro</label>
								<div class="col-md-8">
									<sf:input type="text" path="tipoLogradouro" class="form-control"
										placeholder="Insira o Tipo de Logradouro" />
									<sf:errors path="tipoLogradouro" cssClass="help-block" element="em"/> 
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4" for="logradouro">Logradouro</label>
								<div class="col-md-8">
									<sf:input type="text" path="logradouro" class="form-control"
										placeholder="Insira o logradouro" />
									<sf:errors path="logradouro" cssClass="help-block" element="em"/> 
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4" for="numero">Numero</label>
								<div class="col-md-8">
									<sf:input type="text" path="numero" class="form-control"
										placeholder="Insira o numero do Logradouro" />
									<sf:errors path="numero" cssClass="help-block" element="em"/> 
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4" for="complemento">Complemento</label>
								<div class="col-md-8">
									<sf:input type="text" path="complemento" class="form-control"
										placeholder="Insira o numero do complemento" />
									<sf:errors path="complemento" cssClass="help-block" element="em"/> 
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-4" for="bairro">Bairro</label>
								<div class="col-md-8">
									<sf:input type="text" path="bairro" class="form-control"
										placeholder="Insira o bairro" />
									<sf:errors path="bairro" cssClass="help-block" element="em"/> 
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-4" for="cidade">Cidade</label>
								<div class="col-md-8">
									<sf:input type="text" path="cidade" class="form-control"
										placeholder="Insira a cidade" />
									<sf:errors path="cidade" cssClass="help-block" element="em"/> 
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4" for="cep">CEP</label>
								<div class="col-md-8">
									<sf:input type="text" path="cep" class="form-control"
										placeholder="XXXXX-XXX" />
									<sf:errors path="cep" cssClass="help-block" element="em"/> 
								</div>
							</div>							
						
							<div class="form-group">
								<label class="control-label col-md-4" for="estado">Estado</label>
								<div class="col-md-8">
									<sf:input type="text" path="estado" class="form-control"
										placeholder="Insira o Estado" />
									<sf:errors path="estado" cssClass="help-block" element="em"/> 
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-4" for="pais">Pais</label>
								<div class="col-md-8">
									<sf:input type="text" path="pais" class="form-control"
										placeholder="Insira o Pais" />
									<sf:errors path="pais" cssClass="help-block" element="em"/> 
								</div>
							</div>
							
							
							<div class="form-group">
								<div class="col-md-offset-4 col-md-8">
									<button type="submit" name="_eventId_salvarEndereco" class="btn btn-primary">
										<span class="glyphicon glyphicon-plus"></span> Adicionar Endereço
									</button>																	 
								</div>
							</div>					
						
						</sf:form>
					</div>			
								
				</div>
	
	
			</div>		
			
		
	</div>	

</div>	
<%@include file="../../flows-shared/footer.jsp" %>	