<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>e-Livraria - Membros</title>

<script>

	window.contextRoot = '${contextRoot}'
	
</script>

<!-- Bootstrap Core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Readable Theme -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">


<!-- Bootstrap DataTables -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">


<!-- Custom CSS -->
<link href="${css}/myapp.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div class="wrapper">

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
           <div class="navbar-header">
               <a class="navbar-brand" href="${contextRoot}/home">E-Livraria</a>
           </div>
		</div>
	</nav>


	<!-- Page Content -->
	
	<div class="content">
		<div class="container">
			<div class="alert alert-success">
				<h3 class="text-center">Seu pedido foi confirmado!</h3>
			</div>
		    <div class="row">
		        <div class="col-xs-12">
		    		<div class="invoice-title">
		    			<h2>Fatura</h2><h3 class="pull-right">Pedido # ${vendaDetalhe.id}</h3>
		    		</div>
		    		<hr>
		    		<div class="row">
		    			<div class="col-xs-6">
		    				<address>
		    				<strong>Faturado à:</strong><br>
		    					${vendaDetalhe.usuario.nome} ${vendaDetalhe.usuario.sobrenome}<br>
		    					${vendaDetalhe.enderecoCobranca.tipoLogradouro}<br>
		    					${vendaDetalhe.enderecoCobranca.logradouro}, ${vendaDetalhe.enderecoCobranca.numero}<br>
		    					${vendaDetalhe.enderecoCobranca.cidade} - ${vendaDetalhe.enderecoCobranca.cep}<br>
		    					${vendaDetalhe.enderecoCobranca.estado} - ${vendaDetalhe.enderecoCobranca.pais}
		    				</address>
		    			</div>
		    			<div class="col-xs-6 text-right">
		    				<address>
		        			<strong>Enviado Para:</strong><br>
		    					${vendaDetalhe.usuario.nome} ${vendaDetalhe.usuario.sobrenome}<br>
		    					${vendaDetalhe.enderecoEntrega.tipoLogradouro}<br>
		    					${vendaDetalhe.enderecoEntrega.logradouro}, ${vendaDetalhe.enderecoEntrega.numero}<br>
		    					${vendaDetalhe.enderecoEntrega.cidade} - ${vendaDetalhe.enderecoEntrega.cep}<br>
		    					${vendaDetalhe.enderecoEntrega.estado} - ${vendaDetalhe.enderecoEntrega.pais}
		    				</address>
		    			</div>
		    		</div>
		    		<div class="row">
		    			<div class="col-xs-6">
		    				<address>
		    					<strong>Método de Pagamento </strong><br>
		    					Pagamento com Cartão <br>
		    					${vendaDetalhe.usuario.email}
		    				</address>
		    			</div>
		    			<div class="col-xs-6 text-right">
		    				<address>
		    					<strong>Data Pedido:</strong><br>
		    					${vendaDetalhe.dataVenda}<br><br>
		    				</address>
		    			</div>
		    		</div>
		    	</div>
		    </div>
		    
		    <div class="row">
		    	<div class="col-md-12">
		    		<div class="panel panel-default">
		    			<div class="panel-heading">
		    				<h3 class="panel-title"><strong>Resumo Pedido</strong></h3>
		    			</div>
		    			<div class="panel-body">
		    				<div class="table-responsive">
		    					<table class="table table-condensed">
		    						<thead>
		                                <tr>
		        							<td><strong>Item</strong></td>
		        							<td class="text-center"><strong>Preço</strong></td>
		        							<td class="text-center"><strong>Quantidade</strong></td>
		        							<td class="text-right"><strong>Total</strong></td>
		                                </tr>
		    						</thead>
		    						<tbody>
		    							<!-- foreach ($order->lineItems as $line) or some such thing here -->
		    							<c:forEach items="${vendaDetalhe.itemVenda}" var="itemVenda">
			    							<tr>
			    								<td>${itemVenda.livro.titulo}</td>
			    								<td class="text-center">R$ ${itemVenda.compraPreco}</td>
			    								<td class="text-center">${itemVenda.qtdLivro}</td>
			    								<td class="text-right">R$ ${itemVenda.total}</td>
			    							</tr>
		    							</c:forEach>
		    						</tbody>
		    					</table>
		    				</div>
		    			</div>
		    		</div>
		    	</div>
		    </div>
		    <div class="text-center">
		    	<a href="${contextRoot}/mostrar/todos/livros" class="btn btn-lg btn-warning">Continuar Comprando</a>
		    </div>
		</div>
<%@include file="../../flows-shared/footer.jsp" %>	