<!-- DataTable Bootstrap Script -->
<script src="${js}/angular.js"></script>

<!-- DataTable Bootstrap Script -->
<script src="${js}/livroController.js"></script>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>		
<div class="container" ng-app="elivraria">
	
	<c:if test="${not empty message}">
		
		<div class="alert alert-info">
			<h3 class="text-center">${message}</h3>
		</div>		
	
	</c:if>
	<h2 align="center">Gerenciar Relatórios</h2>
	<br>
	<br>	
	<div class="row">
    	<div class="col-md-12">
    		<div class="panel panel-default">
    			<div class="panel-heading">
    				<h3 class="panel-title"><strong>Relatorio Vendas por Categoria (Gênero/Periodo)</strong></h3>
    				
    			</div>
    		
    			<div class='col-xs-12'>
    				
    				<div class="panel-body">
					<sf:form class="form-horizontal" modelAttribute="relatorioModelo" method="POST" enctype="multipart/form-data">
						
						
						<div class="form-group">
							<label class="control-label col-md-4">Data Inicial</label>
							<div class="col-md-8">
								<sf:input type="date" path="dataInicial" class="form-control"
									placeholder="Insira a Data Inicial" />
								<sf:errors path="dataInicial" cssClass="help-block" element="em"/> 
							</div>
						</div>
							
						<div class="form-group">
							<label class="control-label col-md-4">Data Final</label>
							<div class="col-md-8">
								<sf:input type="date" path="dataFinal" class="form-control"
									placeholder="Insira a Data Final" />
								<sf:errors path="dataFinal" cssClass="help-block" element="em"/> 
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
								</div>
							</div>
						

					
						<div class="form-group">
							
							<div class="col-md-offset-4 col-md-4">
							
								<input type="submit" name="submit" value="Gerar Relatorio" class="btn btn-primary"/>
								
							</div>
						</div>						
										
					</sf:form>

				</div>
    				
						
				</div>
    		</div>
    	</div>
    </div>
    <hr>
    <div class="row">
    	<div class="col-md-12">
    		<div class="panel panel-default">
    			<div class="panel-heading">
    				<h3 class="panel-title"><strong>Relatório de Vendas por Livro (Gênero/Periodo)</strong></h3>
    				
    			</div>
    		
    			<div class='col-xs-12'>
						
				</div>
    		</div>
    	</div>
    </div>
    <hr>
    <div class="row">
    	<div class="col-md-12">
    		<div class="panel panel-default">
    			<div class="panel-heading">
    				<h3 class="panel-title"><strong>Relatório de Vendas Por Grupo de Precificação (Gênero/Periodo)</strong></h3>
    				
    			</div>
    		
    			<div class='col-xs-12'>
						
				</div>
    		</div>
    	</div>
    </div>
</div>
