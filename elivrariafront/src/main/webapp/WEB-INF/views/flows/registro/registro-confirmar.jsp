<%@include file="../flows-shared/header.jsp" %>
<div class="container">
	
	<div class="row">
	
		<div class="col-sm-6">
	
			<div class="panel panel-primary">
				
				<div class="panel-heading">
					<h4>Informação Pessoais</h4>
				</div>
			
				<div class="panel-body">
					<div class="text-center">
						<h3>Nome : <strong>${registroModelo.usuario.nome} ${registroModelo.usuario.sobrenome}</strong></h3>
						<h4>Email : <strong>${registroModelo.usuario.email}</strong></h4>
						<h4>Telefone : <strong>${registroModelo.usuario.telefone}</strong></h4>
						<h4>Role : <strong>${registroModelo.usuario.role}</strong></h4>
						<p>
							<a href="${flowExecutionUrl}&_eventId_infopessoal" class="btn btn-primary">Editar</a>
						</p>
					</div>
				</div>
			
			</div>
					
		
		</div>
		
		<div class="col-sm-6">
		
			<div class="panel panel-primary">
				
				<div class="panel-heading">
					<h4>Endereço</h4>
				</div>
			
				<div class="panel-body">
					<div class="text-center">
						<p>${registroModelo.endereco.logradouro}, ${registroModelo.endereco.numero} </p>
						<p>${registroModelo.endereco.bairro}, </p>
						<p>${registroModelo.endereco.cidade} -  ${registroModelo.endereco.cep}, </p>
						<p>${registroModelo.endereco.estado}</p>
						<p>${registroModelo.endereco.pais}</p>
						<p>
							<a href="${flowExecutionUrl}&_eventId_endereco" class="btn btn-primary">Editar</a>
						</p>
					</div>
				</div>
			
			</div>
		
		</div>
		
		<div class="col-sm-6">
		
			<div class="panel panel-primary">
				
				<div class="panel-heading">
					<h4>Cartão</h4>
				</div>
		
				<div class="panel-body">
					<div class="text-center">
						<p>${registroModelo.cartao.bandeira.descricao}, </p>
						<p>${registroModelo.cartao.numeroCartao}, </p>
						<p>${registroModelo.cartao.nomeCartao}, </p>
						<p>${registroModelo.cartao.mesVencimento}/${registroModelo.cartao.anoVencimento},</p>
						<p>${registroModelo.cartao.ccv}</p>
						<p>
							<a href="${flowExecutionUrl}&_eventId_cartao" class="btn btn-primary">Editar</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	
	</div>
	
	<div class="row">
		
		<div class="col-sm-4 col-sm-offset-4">
			
			<div class="text-center">
				
				<a href="${flowExecutionUrl}&_eventId_submeter" class="btn btn-lg btn-primary">Confirmar</a>
				
			</div>
			
		</div>
		
	</div>

</div>
<%@include file="../flows-shared/footer.jsp" %>