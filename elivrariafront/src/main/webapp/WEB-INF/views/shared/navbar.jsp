<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
	<script>
		window.userRole = '${usuarioModelo.role}';
	</script>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${contextRoot}/home">e-Livraria</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li id="about">
                        <a href="${contextRoot}/sobre">Sobre</a>
                    </li>

                    <li id="contact">
                        <a href="${contextRoot}/contato">Contato</a>
                    </li>
                    
                    <li id="listProducts">
                        <a href="${contextRoot}/mostrar/todos/livros">Livros</a>
                    </li>
					<security:authorize access="hasAuthority('ADMIN')">
	                    <li id="gerenciarLivro">
	                        <a href="${contextRoot}/gerenciar/livro">Gerenciar Livros</a>
	                    </li>					
					</security:authorize>
					<security:authorize access="hasAuthority('ADMIN')">
	                    <li id="gerenciarUsuario">
	                        <a href="${contextRoot}/gerenciar/usuario">Gerenciar Usuarios</a>
	                    </li>					
					</security:authorize>
                </ul>
			    
			    <ul class="nav navbar-nav navbar-right">
			    	<security:authorize access="isAnonymous()">
	                    <li id="signup">
	                        <a href="${contextRoot}/registro">Registrar-se</a>
	                    </li>
						<li id="login">
	                        <a href="${contextRoot}/login">Login</a>
	                    </li> 			    	
			    	</security:authorize>
			    	<security:authorize access="isAuthenticated()">
						<li class="dropdown" id="usuarioModelo">
						  <a class="btn btn-default dropdown-toggle" href="javascript:void(0)" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
						    ${usuarioModelo.nomeCompleto}
						    <span class="caret"></span>
						  </a>
						  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
		                    <security:authorize access="hasAuthority('USER')">
			                    <li id="carrinho">
			                        <a href="${contextRoot}/carrinho/mostrar">
			                        	<span class="glyphicon glyphicon-shopping-cart"></span>&#160;<span class="badge">${usuarioModelo.carrinho.itens}</span> - R$; ${usuarioModelo.carrinho.total} 
			                        </a>
			                    </li>		     
			                	<li role="separator" class="divider"></li>
			                	<li id="meuperfil">
			                        <a href="${contextRoot}/meuperfil/mostrar"> Meu Perfil 
			                        </a>
			                    </li>		     
			                	<li role="separator" class="divider"></li>	                                   
		                    </security:authorize>
							<li id="logout">
		                        <a href="${contextRoot}/logout">Sair</a>
		                    </li>                    			    	
						  </ul>		
						</li>    			    
			    	</security:authorize>                    
			    </ul>                
                
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

