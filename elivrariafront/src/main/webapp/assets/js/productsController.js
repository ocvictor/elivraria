var app = angular.module('elivraria', []);

app.controller('LivroController', function($http) {
	
	var me = this;
		
	me.mvLivros= [];
	me.mcLivros = [];
	
	me.fetchProducts = function() {
		
		
		$http.get('/elivrariafront/json/data/mv/livros')
			.then(function(response) {
				me.mvLivros = response.data;
		});
			
			
		$http.get('/elivrariafront/json/data/mc/livros')
		.then(function(response) {
			me.mcLivros = response.data;
		});
				
	}
	
});