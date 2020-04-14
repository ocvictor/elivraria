<%@include file="../flows-shared/header.jsp" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>			
	<div class="container">
		<c:forEach var="topic" items="${model.selectedTopics}" varStatus="loop">
		  <c:forEach items="${flowRequestContext.messageContext.allMessages}" var="message">
		    <c:set var="msgSrc" value="selectedTopics[${loop.index}].room"></c:set>
		    <c:if test="${message.source eq msgSrc}">
		        <c:if test="${message.severity eq 'INFO'}">
		            <span class="infoText">${message.text}</span>
		        </c:if>
		     </c:if>
		  </c:forEach>
		</c:forEach>
		
		<div class="row">
			
			<div class="col-md-6 col-md-offset-3">
				
				<div class="panel panel-primary">
				
					<div class="panel-heading">
						<h4>Registrar - Informação Pessoal</h4>
					</div>
					
					<div class="panel-body">
										
						<sf:form
							method="POST"
							modelAttribute="usuario"
							class="form-horizontal"
							id="registerForm"
						>
						
							
							
							<div class="form-group">
								<label class="control-label col-md-4">Nome</label>
								<div class="col-md-8">
									<sf:input type="text" path="nome" class="form-control" 
										placeholder="Insira o Nome" />
									<sf:errors path="nome" cssClass="help-block" element="em"/> 
								</div>
							</div>


							<div class="form-group">
								<label class="control-label col-md-4">Sobrenome</label>
								<div class="col-md-8">
									<sf:input type="text" path="sobrenome" class="form-control"
										placeholder="Insira o Sobrenome" />
									<sf:errors path="sobrenome" cssClass="help-block" element="em"/> 
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4">CPF</label>
								<div class="col-md-8">
									<sf:input type="text" id="cpf" path="cpf" class="form-control" minlength="11" maxlength="11" 
									placeholder="Insira o CPF" />
									<sf:errors path="cpf" cssClass="help-block" element="em"/> 
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4">Data de Nascimento</label>
								<div class="col-md-8">
									<sf:input type="date" path="dtNascimento" class="form-control"
										placeholder="Insira a Data de Nascimento" />
									<sf:errors path="dtNascimento" cssClass="help-block" element="em"/> 
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4">Gênero</label>
								<div class="col-md-8">
									<sf:select path="genero" id="genero">
										<option value="Masculino">Masculino</option>
										<option value="Feminino">Feminino</option>
										<option value="Indefinido">Indefinido</option>
									</sf:select>
									<sf:errors path="genero" cssClass="help-block" element="em"/> 
									
<%-- 									<sf:input type="text" path="genero" class="form-control" --%>
<%-- 										placeholder="Insira o Gênero" /> --%>
<%-- 									<sf:errors path="genero" cssClass="help-block" element="em"/>  --%>
								</div>
							</div>
						
							<div class="form-group">
								<label class="control-label col-md-4">Email</label>
								<div class="col-md-8">
									<sf:input type="email" path="email" class="form-control"
										placeholder="abc@zyx.com" />
									<sf:errors path="email" cssClass="help-block" element="em"/> 									
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4">DDD</label>
								<div class="col-md-8">
									<sf:input type="text" path="dddTelefone" class="form-control"
										placeholder="XX" maxlength="2" />
									<sf:errors path="dddTelefone" cssClass="help-block" element="em"/> 
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-4">Telefone</label>
								<div class="col-md-8">
									<sf:input type="text" path="telefone" class="form-control"
										placeholder="XXXXXXXXXX" maxlength="9" />
									<sf:errors path="telefone" cssClass="help-block" element="em"/> 
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4">Senha</label>
								<div class="col-md-8">
									<sf:input type="password" path="senha" class="form-control"
										placeholder="Senha" minlength="8"/>
									<sf:errors path="senha" cssClass="help-block" element="em"/> 
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-4">Confirmar Senha</label>
								<div class="col-md-8">
									<sf:input type="password" path="confirmaSenha" class="form-control"
										placeholder="Confirmar Senha" />
									<sf:errors path="confirmaSenha" cssClass="help-block" element="em"/>										 
								</div>
							</div>
							
	<div class="form-group">
		<label class="control-label col-md-4">Selecionar Role</label>
		<div class="col-md-8">
			<label class="radio-inline">
				<sf:radiobutton path="role" value="USER" checked="checked"/> Usuário 				
			</label>
			<label class="radio-inline">
				<sf:radiobutton path="role" value="SUPPLIER"/> Fornecedor
			</label>
		</div>
	</div>							

							<div class="form-group">
								<div class="col-md-offset-4 col-md-8">
									<button type="submit" name="_eventId_endereco" class="btn btn-primary">
										Próximo - Endereço <span class="glyphicon glyphicon-chevron-right"></span>
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
