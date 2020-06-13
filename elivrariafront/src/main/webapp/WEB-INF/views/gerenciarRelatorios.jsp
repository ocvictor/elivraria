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
								<label class="control-label col-md-4">Relatório</label>
								<div class="col-md-8">
									<sf:select path="tipo" id="tipo">
										<option value="Categoria">Vendas por Categoria</option>
										<option value="Livro">Vendas por Livro</option>
										<option value="Grupo de Precificação">Vendas por Grupo de Precificação</option>
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