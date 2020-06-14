<!-- DataTable Bootstrap Script -->
<script src="${js}/angular.js"></script>

<!-- DataTable Bootstrap Script -->
<script src="${js}/livroController.js"></script>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty relatorioVenda}">
	<div class="container">

		<h2 align="center">Relatório </h2>
		<script src="${js}/jquery.js"></script>		
		<script src="${js}/highcharts-custom.src.js"></script>
		<script src="${js}/grafico.js"></script>
	</div>
</c:if>
<c:if test="${empty relatorioVenda}">
<div class="container">
	
	<c:if test="${not empty message}">
		
		<div class="alert alert-info">
			<h3 class="text-center">${message}</h3>
		</div>		
	
	</c:if>
	<h2 align="center">Gerenciar Relatórios</h2>
	<br>	
	<div class="row">
    	<div class="col-md-12">
    		<div class="panel panel-default">
    		
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
								<label class="control-label col-md-4">Indicadores</label>
								<div class="col-md-8">
									<sf:select path="indicador" id="indicador">
										<option value="Categoria">Por Categoria</option>
										<option value="Livro">Por Livro</option>
										<option value="GrupoPrecificacao">Por Grupo de Precificação</option>
									</sf:select>
									<sf:errors path="indicador" cssClass="help-block" element="em"/> 
								</div>
							</div>
								
							<div class="form-group">
								<label class="control-label col-md-4">Tipo de Gráfico</label>
								<div class="col-md-8">
									<sf:select path="tipo" id="tipo">
										<option value="Barra">Barra</option>
										<option value="Linha">Linha</option>
									</sf:select>
									<sf:errors path="tipo" cssClass="help-block" element="em"/> 
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
</div>
</c:if>