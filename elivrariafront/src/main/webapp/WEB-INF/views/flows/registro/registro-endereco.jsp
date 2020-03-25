<%@include file="../flows-shared/header.jsp" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>			
	<div class="container">
		
		
		<div class="row">
			
			<div class="col-md-6 col-md-offset-3">
				
				<div class="panel panel-primary">
				
					<div class="panel-heading">
						<h4>Registrar - Endereço</h4>
					</div>
					
					<div class="panel-body">
										
						<sf:form
							method="POST"
							modelAttribute="endereco"
							class="form-horizontal"
							id="enderecoForm"
						>
						
							<div class="form-group">
								<label class="control-label col-md-4" for="tiporesidencia">Tipo de Residencia</label>
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
									<button type="submit" name="_eventId_infopessoal" class="btn btn-primary">
										<span class="glyphicon glyphicon-chevron-left"></span> Voltar - Informações Pessoais
									</button>								
									<button type="submit" name="_eventId_cartao" class="btn btn-primary">
										Proximo - Cartão<span class="glyphicon glyphicon-chevron-right"></span>
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
