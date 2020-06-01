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

			<h1>Confirmar Troca</h1>
			<hr/>
			
			<div class="panel-body">
				<sf:form class="form-horizontal" modelAttribute="troca" action="${contextRoot}/troca" enctype="multipart/form-data">
					
					<div class="form-group">
						<label class="control-label col-md-4">Data</label>
 						<div class="col-md-8">
							<sf:input value="${troca.dataSolicitacao}" disabled="true" path= "" class="form-control"/>
							<sf:errors path="" cssClass="help-block" element="em"/> 
 						</div>
 					</div> 	
 					
 					<div class="form-group">
						<label class="control-label col-md-4">Nº Pedido</label>
 						<div class="col-md-8">
							<sf:input value="${troca.vendaDetalhe.id}" disabled="true" path= "" class="form-control"/>
							<sf:errors path="" cssClass="help-block" element="em"/> 
 						</div>
 					</div> 	
 					
 					<div class="form-group">
						<label class="control-label col-md-4">Cliente Solicitante</label>
 						<div class="col-md-8">
							<sf:input value="${troca.vendaDetalhe.usuario.nome}${troca.vendaDetalhe.usuario.sobrenome}" disabled="true" path= "" class="form-control"/>
							<sf:errors path="" cssClass="help-block" element="em"/> 
 						</div>
 					</div> 						
 					<div class="form-group">
						<label class="control-label col-md-4">Titulo</label>
 						<div class="col-md-8">
							<sf:input value="${troca.itemVenda.livro.titulo}" disabled="true" path= "" class="form-control"/>
							<sf:errors path="" cssClass="help-block" element="em"/> 
 						</div>
 					</div> 					
					
					<div class="form-group">
						<label class="control-label col-md-4">Quantidade a ser trocada</label> 
						<div class="col-md-8">
							<sf:input value="${troca.qtdTroca}" disabled="true" path= "" class="form-control"/>
							<sf:errors path="" cssClass="help-block" element="em"/> 
 						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-4">Motivo da Troca</label> 
						<div class="col-md-8">
							<sf:input value="${troca.motivo}" disabled="true" path= "" class="form-control"/>
							<sf:errors path="" cssClass="help-block" element="em"/> 
 						</div>
					</div>

				
					<div class="form-group">
						
						<div class="col-md-offset-4 col-md-4">
						
							<a href="${contextRoot}/gerenciar/trocasCancelamentos" class="btn btn-md btn-info" >Voltar</a>
							<a href="${contextRoot}/gerenciar/trocas/confirmar/salvar" class="btn btn-md btn-info">Confirmar</a>
						</div>
					</div>
				</sf:form>
			</div>
 		</div>
 	</div>
</div>