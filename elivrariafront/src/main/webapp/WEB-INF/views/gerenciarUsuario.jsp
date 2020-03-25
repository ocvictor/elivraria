<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>

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

			<div class="panel panel-primary">

				<div class="panel-heading">

					<h4>Gerenciar Usuários</h4>

				</div>

				<div class="panel-body">
					<h3> Informações Pessoais</h3>
					<sf:form class="form-horizontal" modelAttribute="usuario" action="${contextRoot}/gerenciar/usuario" method="POST" enctype="multipart/form-data">
						<div class="form-group">
							<label class="control-label col-md-4">Nome</label>
							<div class="col-md-8">
								<sf:input type="text" path="nome" class="form-control"
									placeholder="Nome" />
								<sf:errors path="nome" cssClass="help-block" element="em"/> 
							</div>
						</div>
						
						<div class="form-group">
 							<label class="control-label col-md-4">Sobrenome</label>
 							<div class="col-md-8">
								<sf:input type="text" path="sobrenome" class="form-control"
									placeholder="Sobrenome" /> 
								<sf:errors path="sobrenome" cssClass="help-block" element="em"/>	
 							</div>
 						</div>

 						<div class="form-group">
 							<label class="control-label col-md-4">Data de Nascimento</label>
 							<div class="col-md-8">
								<sf:input type="date" path="dtNascimento" class="form-control"
									placeholder="Data de Nascimento" /> 
								<sf:errors path="dtNascimento" cssClass="help-block" element="em"/>
 							</div>
						</div>

 						<div class="form-group">
 							<label class="control-label col-md-4">Gênero</label>
 							<div class="col-md-8">
 								<sf:input type="text" path="genero" class="form-control"
 									placeholder="Gênero" />
 								<sf:errors path="genero" cssClass="help-block" element="em"/>
 							</div>
 						</div>

 						<div class="form-group">
 							<label class="control-label col-md-4">Email</label>
 							<div class="col-md-8">
								<sf:input type="text" path="email" class="form-control"
									placeholder="Email" />
								<sf:errors path="email" cssClass="help-block" element="em"/> 
 							</div>
 						</div>


						<div class="form-group">
 							<label class="control-label col-md-4">DDD - Telefone</label>
 							<div class="col-lg-2">
 								<sf:input type="text" maxlength="2" placeholder="XX" pattern="[0-9]{2}" path="dddTelefone" class="form-control" />
 								<sf:errors path="dddTelefone" cssClass="help-block" element="em"/> 							
 							</div>
 							<div class="col-sm-6">
 								<sf:input type="tel" maxlength="9" path="telefone" class="form-control" />
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
						
						<div class="row">
		
			<div class="col-md-4">
				
				<h4>Todos os Usuarios</h4>
				<hr/>
				
				<div class="row">
				
		
		<div class='col-xs-12'>
		
		
			<table id="UsuariosTable" class="table table-condensed table-bordered">
							
				<thead>					
					<tr>					
						<th>Id</th>
						<th>&#160;</th>
						<th>Nome</th>
						<th>Sobrenome</th>
						<th>Genero</th>
						<th>Data Nascimento</th>
						<th>Email</th>
						<th>Ativo?</th>
					</tr>					
				</thead>
				
				<tfoot>
					<tr>					
						<th>Id</th>
						<th>&#160;</th>
						<th>Nome</th>
						<th>Sobrenome</th>
						<th>Genero</th>
						<th>Data Nascimento</th>
						<th>Email</th>
						<th>Ativo?</th>
					</tr>									
				</tfoot>
				
							
			</table>
		
		
		</div>
	
	
	</div>
	
	
			</div>		
			
				
								
			
			</div>
							
							

<!-- 						<div class="form-group"> -->
<!-- 							<label class="control-label col-md-4">Category</label> -->
<!-- 							<div class="col-md-8"> -->
<%-- 								<sf:select path="categoryId" items="${categories}" itemLabel="name" itemValue="id" class="form-control"/> --%>
							
<!-- 								<div class="text-right"> -->
<!-- 									<br/>			 -->
<%-- 									<sf:hidden path="id"/> --%>
<%-- 									<sf:hidden path="code"/> --%>
<%-- 									<sf:hidden path="supplierId"/> --%>
<%-- 									<sf:hidden path="active"/>														 --%>
<!-- 									<button type="button" class="btn btn-warning btn-xs" data-toggle="modal" data-target="#myCategoryModal">Add New Category</button> -->
<!-- 								</div>							 -->
<!-- 							</div> -->
							
<!-- 						</div> -->


					
<!-- 						<div class="form-group"> -->
							
<!-- 							<div class="col-md-offset-4 col-md-4"> -->
							
<!-- 								<input type="submit" name="submit" value="Save" class="btn btn-primary"/> -->
								
<!-- 							</div> -->
<!-- 						</div>						 -->
										
					</sf:form>

				</div>

			</div>

		</div>

	</div>

<!-- 	<!-- Modal --> -->
<!-- 	<div class="modal fade" id="myCategoryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"> -->
<!-- 	  <div class="modal-dialog" role="document"> -->
<!-- 	    <div class="modal-content"> -->
<!-- 	      <div class="modal-header"> -->
<!-- 	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> -->
<!-- 	        <h4 class="modal-title" id="myModalLabel">New Category</h4> -->
<!-- 	      </div> -->
<!-- 	      <div class="modal-body"> -->
	        
<%-- 	        <sf:form id="categoryForm" class="form-horizontal" modelAttribute="category" action="${contextRoot}/manage/category" method="POST"> --%>
	        	
<!--        			<div class="form-group"> -->
<!-- 					<label class="control-label col-md-4">Name</label> -->
<!-- 					<div class="col-md-8 validate"> -->
<%-- 						<sf:input type="text" path="name" class="form-control" --%>
<%-- 							placeholder="Category Name" />  --%>
<!-- 					</div> -->
<!-- 				</div> -->
       			
<!--        			<div class="form-group">				 -->
<!-- 					<label class="control-label col-md-4">Description</label> -->
<!-- 					<div class="col-md-8 validate"> -->
<%-- 						<sf:textarea path="description" class="form-control" --%>
<%-- 							placeholder="Enter category description here!" />  --%>
<!-- 					</div> -->
<!-- 				</div>	        	         -->
	        
	        
<!-- 				<div class="form-group">				 -->
<!-- 					<div class="col-md-offset-4 col-md-4">					 -->
<!-- 						<input type="submit" name="submit" value="Save" class="btn btn-primary"/>						 -->
<!-- 					</div> -->
<!-- 				</div>	         -->
<%-- 	        </sf:form> --%>
<!-- 	      </div> -->
<!-- 	    </div> -->
<!-- 	  </div> -->
<!-- 	</div> -->
	
<!-- 	<hr/>	 -->
<!-- 	<h1>Available Products</h1> -->
<!-- 	<hr/> -->
	
<!-- 	<div class="row"> -->
				
		
<!-- 		<div class='col-xs-12'> -->
		
		
<!-- 			<table id="productsTable" class="table table-condensed table-bordered"> -->
							
<!-- 				<thead>					 -->
<!-- 					<tr>					 -->
<!-- 						<th>Id</th> -->
<!-- 						<th>&#160;</th> -->
<!-- 						<th>Name</th> -->
<!-- 						<th>Brand</th> -->
<!-- 						<th>Qty. Avail</th> -->
<!-- 						<th>Unit Price</th> -->
<!-- 						<th>Activate</th>				 -->
<!-- 						<th>Edit</th> -->
<!-- 					</tr>					 -->
<!-- 				</thead> -->
				
<!-- 				<tfoot> -->
<!-- 					<tr>					 -->
<!-- 						<th>Id</th> -->
<!-- 						<th>&#160;</th> -->
<!-- 						<th>Name</th> -->
<!-- 						<th>Brand</th> -->
<!-- 						<th>Qty. Avail</th> -->
<!-- 						<th>Unit Price</th> -->
<!-- 						<th>Activate</th>				 -->
<!-- 						<th>Edit</th> -->
<!-- 					</tr>									 -->
<!-- 				</tfoot> -->
				
							
<!-- 			</table> -->
		
		
<!-- 		</div> -->
	
	
<!-- 	</div> -->

</div>