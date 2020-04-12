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
<meta name="description" content="eLivraria">
<meta name="author" content="MV Consult">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<title>eLivraria - ${title}</title>

<script>
	window.menu = '${title}';
	
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
	
	<div class="se-pre-con"></div>
	<div class="wrapper">

		<!-- Navigation -->
		<%@include file="./shared/navbar.jsp"%>

		<!-- Page Content -->

		<div class="content">
			
			<c:if test="${ClickHome == true }">
				<%@include file="home.jsp"%>
			</c:if>

			<c:if test="${ClickSobre == true }">
				<%@include file="sobre.jsp"%>
			</c:if>

			<c:if test="${ClickContato == true }">
				<%@include file="contato.jsp"%>
			</c:if>
			
			<c:if test="${ClickTodosLivros == true or ClickCategoriaLivros == true }">
				<%@include file="listaLivros.jsp"%>
			</c:if>	
			
			
			<c:if test="${ClickMostrarLivro == true}">
				<%@include file="LivroUnico.jsp"%>
			</c:if>								

			<c:if test="${ClickGerenciarUsuario == true}">
				<%@include file="gerenciarUsuario.jsp"%>
			</c:if>
			
			<c:if test="${ClickGerenciarEstoque == true}">
				<%@include file="gerenciarEstoque.jsp"%>
			</c:if>
			
			<c:if test="${ClickGerenciarVendas == true}">
				<%@include file="gerenciarVendas.jsp"%>
			</c:if>
			
			<c:if test="${ClickGerenciarLivro == true}">
				<%@include file="gerenciarLivro.jsp"%>
			</c:if>

			<c:if test="${ClickMostrarCarrinho == true}">
				<%@include file="carrinho.jsp"%>
			</c:if>	

		</div>


		<!-- Footer comes here -->
		<%@include file="./shared/footer.jsp"%>

		<!-- jQuery -->
		<script src="${js}/jquery.js"></script>

		<script src="${js}/jquery.validate.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="${js}/bootstrap.min.js"></script>
		
		<!-- DataTable Plugin -->
		<script src="${js}/jquery.dataTables.js"></script>
		
		<!-- DataTable Bootstrap Script -->
		<script src="${js}/dataTables.bootstrap.js"></script>
		
		<!-- DataTable Bootstrap Script -->
		<script src="${js}/bootbox.min.js"></script>
		
		<!-- Self coded javascript -->
		<script src="${js}/myapp.js"></script>

	</div>

</body>

</html>