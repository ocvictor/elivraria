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

		<div class="col-md-offset-2 col-md-8">

			<h1>Solicitar Troca</h1>
			<hr/>
			
			<div class="panel-body">
				<sf:form class="form-horizontal" modelAttribute="troca" action="${contextRoot}/troca" method="POST" enctype="multipart/form-data">
					
					<div class="form-group">
						<div class="col-md-8">
							<sf:input type="hidden" value="${itemVenda.livro.id}" path="livroId" class="form-control"/>
							<sf:errors path="livroId" cssClass="help-block" element="em"/> 
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-md-8">
							<sf:input type="hidden" value="${itemVenda.id}" path="itemVendaId" class="form-control"/>
							<sf:errors path="itemVendaId" cssClass="help-block" element="em"/> 
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-md-8">
							<sf:input type="hidden" value="${vendaDetalhe.id}" path="vendaDetalheId" class="form-control"/>
							<sf:errors path="vendaDetalheId" cssClass="help-block" element="em"/> 
						</div>
					</div>
										
 					<div class="form-group">
						<label class="control-label col-md-4">Titulo</label>
 						<div class="col-md-8">
							<sf:input value="${itemVenda.livro.titulo}" disabled="true" path= "" class="form-control"/>
							<sf:errors path="" cssClass="help-block" element="em"/> 
 						</div>
 					</div> 					
					
					<div class="form-group">
						<label class="control-label col-md-4">Quantidade Comprada</label> 
						<div class="col-md-8">
							<sf:input value="${itemVenda.qtdLivro}" disabled="true" path= "" class="form-control"/>
							<sf:errors path="" cssClass="help-block" element="em"/> 
 						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4">Quantidade a ser Trocada</label> 
						<div class="col-md-8">
							<sf:input type="number" path="qtdTroca" class="form-control"
								placeholder="Insira quantidade a ser trocada" />
							<sf:errors path="qtdTroca" cssClass="help-block" element="em"/> 
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4">Motivo da Troca</label>
						<div class="col-md-8">
							<sf:textarea path="motivo" class="form-control"
								placeholder="Informe o motivo da troca" /> 
							<sf:errors path="motivo" cssClass="help-block" element="em"/>
						</div>
					</div>
				
					<div class="form-group">
						
						<div class="col-md-offset-4 col-md-4">
						
							<input type="submit" name="submit" value="Salvar" class="btn btn-primary"/>
							
						</div>
					</div>
				</sf:form>
			</div>
 		</div>
 	</div>
</div>