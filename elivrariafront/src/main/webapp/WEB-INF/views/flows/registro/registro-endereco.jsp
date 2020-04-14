<%@include file="../flows-shared/header.jsp" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>			
	<div class="container">
		
		
		<div class="row">
			
			<div class="col-md-6 col-md-offset-3">
				
				<div class="panel panel-primary">
				
					<div class="panel-heading">
						<h4>Registrar - Endere�o</h4>
					</div>
					
					<div class="panel-body">
										
						<sf:form
							method="POST"
							modelAttribute="endereco"
							class="form-horizontal"
							id="enderecoForm"
						>
							<div class="form-group">
								<label class="control-label col-md-4">Tipo de Residencia</label>
								<div class="col-md-8">
									<sf:select path="tipoResidencia" id="tipoResidencia">
										<option value="Casa">Casa</option>
										<option value="Apartamento">Apartamento</option>
									</sf:select>
									<sf:errors path="tipoResidencia" cssClass="help-block" element="em"/> 
								</div>
							</div>
						
<!-- 							<div class="form-group"> -->
<!-- 								<label class="control-label col-md-4" for="tiporesidencia">Tipo de Residencia</label> -->
<!-- 								<div class="col-md-8"> -->
<%-- 									<sf:input type="text" path="tipoResidencia" class="form-control" --%>
<%-- 										placeholder="Insira o Tipo de Resid�ncia" /> --%>
<%-- 									<sf:errors path="tipoResidencia" cssClass="help-block" element="em"/>  --%>
<!-- 								</div> -->
<!-- 							</div> -->
							
														<div class="form-group">
								<label class="control-label col-md-4">Tipo de Logradouro</label>
								<div class="col-md-8">
									<sf:select path="tipoLogradouro" id="tipoLogradouro">
										<option value="Aeroporto">Aeroporto</option>
										<option value="Alameda">Alameda</option>
										<option value="�rea">�rea</option>
										<option value="Avenida">Avenida</option>
										<option value="Campo">Campo</option>
										<option value="Ch�cara">Ch�cara</option>
										<option value="Col�nia">Col�nia</option>
										<option value="Condom�nio">Condom�nio</option>
										<option value="Conjunto">Conjunto</option>
										<option value="Distrito">Distrito</option>
										<option value="Esplanada">Esplanada</option>
										<option value="Esta��o">Esta��o</option>
										<option value="Estrada">Estrada</option>
										<option value="Favela">Favela</option>
										<option value="Feira">Feira</option>
										<option value="Jardim">Jardim</option>
										<option value="Ladeira">Ladeira</option>
										<option value="Lago">Lago</option>
										<option value="Lagoa">Lagoa</option>
										<option value="Largo">Largo</option>
										<option value="Loteamento">Loteamento</option>
										<option value="Morro">Morro</option>
										<option value="N�cleo">N�cleo</option>
										<option value="Parque">Parque</option>
										<option value="Passarela">Passarela</option>
										<option value="P�tio">P�tio</option>
										<option value="Pra�a">Pra�a</option>
										<option value="Quadra">Quadra</option>
										<option value="Recanto">Recanto</option>
										<option value="Residencial">Residencial</option>
										<option value="Rodovia">Rodovia</option>
										<option value="Rua">Rua</option>
										<option value="Setor">Setor</option>
										<option value="S�tio">S�tio</option>
										<option value="Travessa">Travessa</option>
										<option value="Trecho">Trecho</option>
										<option value="Trevo">Trevo</option>
										<option value="Vale">Vale</option>
										<option value="Vereda">Vereda</option>
										<option value="Via">Via</option>
										<option value="Viaduto">Viaduto</option>
										<option value="Viela">Viela</option>
										<option value="Vila">Vila</option>
									</sf:select>
									<sf:errors path="tipoLogradouro" cssClass="help-block" element="em"/> 
								</div>
							</div>
							
<!-- 							<div class="form-group"> -->
<!-- 								<label class="control-label col-md-4" for="tipologradouro">Tipo de Logradouro</label> -->
<!-- 								<div class="col-md-8"> -->
<%-- 									<sf:input type="text" path="tipoLogradouro" class="form-control" --%>
<%-- 										placeholder="Insira o Tipo de Logradouro" /> --%>
<%-- 									<sf:errors path="tipoLogradouro" cssClass="help-block" element="em"/>  --%>
<!-- 								</div> -->
<!-- 							</div> -->
							
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
								<label class="control-label col-md-4">Estado</label>
								<div class="col-md-8">
									<sf:select path="estado" id="estado">
										<option value="Acre">Acre (AC)</option>
										<option value="Alagoas">Alagoas (AL)</option>
										<option value="Amap�">Amap� (AP)</option>
										<option value="Amazonas">Amazonas (AM)</option>
										<option value="Bahia">Bahia (BA)</option>
										<option value="Cear�">Cear� (CE)</option>
										<option value="Distrito Federal">Distrito Federal (DF)</option>
										<option value="Esp�rito Santo">Esp�rito Santo (ES)</option>
										<option value="Goi�s">Goi�s (GO)</option>
										<option value="Maranh�o">Maranh�o (MA)</option>
										<option value="Mato Grosso">Mato Grosso (MT)</option>
										<option value="Mato Grosso do Sul">Mato Grosso do Sul (MS)</option>
										<option value="Minas Gerais">Minas Gerais (MG)</option>
										<option value="Par�">Par� (PA)</option>
										<option value="Para�ba">Para�ba (PB)</option>
										<option value="Paran�">Paran� (PR)</option>
										<option value="Pernambuco">Pernambuco (PE)</option>
										<option value="Piau�">Piau� (PI)</option>
										<option value="Rio de Janeiro">Rio de Janeiro (RJ)</option>
										<option value="Rio Grande do Norte">Rio Grande do Norte (RN)</option>
										<option value="Rio Grande do Sul">Rio Grande do Sul (RS)</option>
										<option value="Rond�nia">Rond�nia (RO)</option>
										<option value="Roraima">Roraima (RR)</option>
										<option value="Santa Catarina">Santa Catarina (SC)</option>
										<option value="S�o Paulo">S�o Paulo (SP)</option>
										<option value="Sergipe">Sergipe (SE)</option>
										<option value="Tocantins">Tocantins (TO)</option>
									</sf:select>
									<sf:errors path="estado" cssClass="help-block" element="em"/> 
								</div>
							</div>						
						
<!-- 							<div class="form-group"> -->
<!-- 								<label class="control-label col-md-4" for="estado">Estado</label> -->
<!-- 								<div class="col-md-8"> -->
<%-- 									<sf:input type="text" path="estado" class="form-control" --%>
<%-- 										placeholder="Insira o Estado" /> --%>
<%-- 									<sf:errors path="estado" cssClass="help-block" element="em"/>  --%>
<!-- 								</div> -->
<!-- 							</div> -->

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
										<span class="glyphicon glyphicon-chevron-left"></span> Voltar - Informa��es Pessoais
									</button>								
									<button type="submit" name="_eventId_cartao" class="btn btn-primary">
										Proximo - Cart�o<span class="glyphicon glyphicon-chevron-right"></span>
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
