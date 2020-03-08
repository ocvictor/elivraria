<!-- DataTable Bootstrap Script -->
<script src="${js}/angular.js"></script>

<!-- DataTable Bootstrap Script -->
<script src="${js}/productsController.js"></script>
<div class="container" ng-app="elivraria" ng-controller="LivroController as lCtrl" >

	<div class="row" ng-init="lCtrl.fetchProducts()">

		<div class="col-md-3">
			<%@include file="./shared/sidebar.jsp"%>
		</div>

		<div class="col-md-9">

			<div class="row carousel-holder">

				<div class="col-md-12">
					<div id="carousel-example-generic" class="carousel slide"
						data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#carousel-example-generic" data-slide-to="0"
								class="active"></li>
							<li data-target="#carousel-example-generic" data-slide-to="1"></li>
							<li data-target="#carousel-example-generic" data-slide-to="2"></li>
							<li data-target="#carousel-example-generic" data-slide-to="3"></li>
						</ol>
						<div class="carousel-inner">
							
							<div class="item active">
								<img class="slide-image" src="${images}/banner5.jpg"
									alt="">
							</div>
							<div class="item">
								<img class="slide-image" src="${images}/banner6.jpg"
									alt="">
							</div>
							<div class="item">
								<img class="slide-image" src="${images}/banner7.jpg"
									alt="">
							</div>
							<div class="item">
								<img class="slide-image" src="${images}/banner8.jpg"
									alt="">
							</div>
						</div>
						<a class="left carousel-control" href="#carousel-example-generic"
							data-slide="prev"> <span
							class="glyphicon glyphicon-chevron-left"></span>
						</a> <a class="right carousel-control"
							href="#carousel-example-generic" data-slide="next"> <span
							class="glyphicon glyphicon-chevron-right"></span>
						</a>
					</div>
				</div>

			</div>

				<div class="row">
                	<div class="col-xs-12">
                		<h3>Mais Vistos</h3>
                		<hr/>
                	</div>
                </div>

                <div class="row is-table-row">
                	
                    <div class="col-sm-4" ng-repeat="Livros in lCtrl.mvLivros">                    	
                        <div class="thumbnail">
                            <img ng-src="${images}/{{livro.isbn}}.jpg" alt="{{livro.titulo}}" class="landingImg">
                            <h5>{{livro.titulo}}</h5>
                            <hr/>
                            <div class="caption">
                                <h4 class="pull-right">&#8377; {{livro.precoUnit}}</h4>
                                <p>{{livro.autor}}</p>
                                <a ng-href="${contextRoot}/mostrar/{{livro.id}}/livro" class="btn btn-primary pull-right">Ver</a>
                            </div>
                        </div>
                        
                    </div>

                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <h4>Confira Mais!</h4>
                        <hr/>
                        <a class="btn btn-primary" href="${contextRoot}/mostrar/todos/livros">Mais Livros</a>
                    </div>

                </div>
				
				<div class="row">
                	<div class="col-xs-12">
                		<h3>Mais Comprados</h3>
                		<hr/>
                	</div>
                </div>
               <div class="row is-table-row">
                	
                    <div class="col-sm-4" ng-repeat="livro in lCtrl.mcLivros">                    	
                        <div class="thumbnail">
                            <img ng-src="${images}/{{livro.isbn}}.jpg" alt="{{livro.titulo}}" class="landingImg">
                            <h5>{{livro.titulo}}</h5>
                            <hr/>
                            <div class="caption">
                                <h4 class="pull-right">&#8377; {{livro.precoUnit}}</h4>
                                <p>{{livro.autor}}</p>
                                <a ng-href="${contextRoot}/mostrar/{{livro.id}}/livro" class="btn btn-primary pull-right">Ver</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <h4>Confira outros livros!</h4>
                        <hr/>
                        <a class="btn btn-primary" href="${contextRoot}/mostrar/todos/livros">Mais Livros</a>
                    </div>

                </div>

		</div>

	</div>

</div>
<!-- /.container -->
