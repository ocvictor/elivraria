$(function() {
	
	$(window).load(function(){
		setTimeout(function() {
			$(".se-pre-con").fadeOut("slow");
		}, 500);			
	});	
	
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	
	if((token!=undefined && header !=undefined) && (token.length > 0 && header.length > 0)) {		
		$(document).ajaxSend(function(e, xhr, options) {			
			xhr.setRequestHeader(header,token);			
		});				
	}
	
	
	
	switch (menu) {

	case 'Sobre':
		$('#sobre').addClass('active');
		break;
	case 'Contato':
		$('#contato').addClass('active');
		break;
	case 'Livros':
		$('#listaLivros').addClass('active');
		break;
	case 'Gerenciar Livros':
		$('#gerenciarLivro').addClass('active');
		break;
	case 'Gerenciar Usuarios':
		$('#gerenciarUsuario').addClass('active');
		break;
	case 'Gerenciar Estoque':
		$('#gerenciarEstoque').addClass('active');
		break;
	case 'Gerenciar Vendas':
		$('#gerenciarVendas').addClass('active');
		break;
	case 'Gerenciar Trocas e Cancelamentos':
		$('#gerenciarTrocasCancelamentos').addClass('active');
		break;
	case 'Carrinho':
		$('#carrinho').addClass('active');
		break;		
	default:
		if (menu == "Home")
			break;
		$('#listaLivros').addClass('active');
		$('#listaUsuarios').addClass('active');
		$('#gerenciarEstoque').addClass('active');
		$('#gerenciarVendas').addClass('active');
		$('#gerenciarTrocasCancelamentos').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}

	var $table = $('#LivroListaTabela');

	if ($table.length) {

		var jsonUrl = '';
		if (window.categoriaId == '') {
			jsonUrl = window.contextRoot + '/json/data/todos/livros';
		} else {
			jsonUrl = window.contextRoot + '/json/data/categoria/'
					+ window.categoriaId + '/livros';
		}

		$table
				.DataTable({
					
					"language": {
						
						    "sEmptyTable": "Nenhum registro encontrado",
						    "sInfo": "Mostrando de _START_ ate _END_ de _TOTAL_ registros",
						    "sInfoEmpty": "Mostrando 0 ate 0 de 0 registros",
						    "sInfoFiltered": "(Filtrados de _MAX_ registros)",
						    "sInfoPostFix": "",
						    "sInfoThousands": ".",
						    "sLengthMenu": "_MENU_ resultados por pagina",
						    "sLoadingRecords": "Carregando...",
						    "sProcessing": "Processando...",
						    "sZeroRecords": "Nenhum registro encontrado",
						    "sSearch": "Pesquisar",
						    "oPaginate": {
						        "sNext": "Proximo",
						        "sPrevious": "Anterior",
						        "sFirst": "Primeiro",
						        "sLast": "Ultimo"
						    },
						    "oAria": {
						        "sSortAscending": ": Ordenar colunas de forma ascendente",
						        "sSortDescending": ": Ordenar colunas de forma descendente"
						    },
						    "select": {
						        "rows": {
						            "_": "Selecionado %d linhas",
						            "0": "Nenhuma linha selecionada",
						            "1": "Selecionado 1 linha"
						        }
						    }
						
			        },
				
					lengthMenu : [ [ 3, 5, 10, -1 ],
							[ '3 ', '5 ', '10 ', 'Todos' ] ],
					pageLength : 5,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'isbn',
								bSortable : false,
								mRender : function(data, type, row) {

									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="dataTableImg"/>';

								}
							},
							{
								data : 'titulo'
							},
							{
								data : 'editora'
							},
							{
								data : 'precoUnit',
								mRender : function(data, type, row) {
									return 'R$ ' + data
								}
							},
							{
								data : 'quantidade',
								mRender : function(data, type, row) {

									if (data < 1) {
										return '<span style="color:red">Fora de Estoque!</span>';
									}

									return data;

								}
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {

									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/mostrar/'
											+ data
											+ '/livro" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';

									
									if(userRole !== 'ADMIN') {
										if (row.quantity < 1) {
											str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
										} else {
	
											str += '<a href="'
													+ window.contextRoot
													+ '/carrinho/adicionar/'
													+ data
													+ '/livro" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
										}
									}
									else {
										str += '<a href="'
											+ window.contextRoot
											+ '/gerenciar/'
											+ data
											+ '/livro" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';
									}
									
									return str;

								}

							} ]
				});
	}

	
	
	// lista todos produtos
	var $LivrosTable = $('#livrosTable');
	
	
	if($LivrosTable.length) {
		
		var jsonUrl = window.contextRoot + '/json/data/admin/todos/livros';
		console.log(jsonUrl);
		
		$LivrosTable.DataTable({
			"language": {
				
			    "sEmptyTable": "Nenhum registro encontrado",
			    "sInfo": "Mostrando de _START_ ate _END_ de _TOTAL_ registros",
			    "sInfoEmpty": "Mostrando 0 ate 0 de 0 registros",
			    "sInfoFiltered": "(Filtrados de _MAX_ registros)",
			    "sInfoPostFix": "",
			    "sInfoThousands": ".",
			    "sLengthMenu": "_MENU_ resultados por pagina",
			    "sLoadingRecords": "Carregando...",
			    "sProcessing": "Processando...",
			    "sZeroRecords": "Nenhum registro encontrado",
			    "sSearch": "Pesquisar",
			    "oPaginate": {
			        "sNext": "Proximo",
			        "sPrevious": "Anterior",
			        "sFirst": "Primeiro",
			        "sLast": "Ultimo"
			    },
			    "oAria": {
			        "sSortAscending": ": Ordenar colunas de forma ascendente",
			        "sSortDescending": ": Ordenar colunas de forma descendente"
			    },
			    "select": {
			        "rows": {
			            "_": "Selecionado %d linhas",
			            "0": "Nenhuma linha selecionada",
			            "1": "Selecionado 1 linha"
			        }
			    }
			
        },
					lengthMenu : [ [ 10, 30, 50, -1 ], [ '10 ', '30 ', '50 ', 'Todos' ] ],
					pageLength : 30,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [		
					           	{data: 'id'},


					           	{data: 'isbn'},
					           	{
									data : 'titulo'
								},							
								{
									data : 'quantidade',
									mRender : function(data, type, row) {

										if (data < 1) {
											return '<span style="color:red">Fora de Estoque!</span>';
										}

										return data;

									}
								},
								{
									data : 'precoUnit',
									mRender : function(data, type, row) {
										return 'R$ ' + data
									}
								},
								{
									data : 'ativo',
									bSortable : false,
									mRender : function(data, type, row) {
										var str = '';
										if(data) {											
											str += '<label class="switch"> <input type="checkbox" value="'+row.id+'" checked="checked">  <div class="slider round"> </div></label>';
											
										}else {
											str += '<label class="switch"> <input type="checkbox" value="'+row.id+'">  <div class="slider round"> </div></label>';
										}
										
										return str;
									}
								},
								{
									data : 'id',
									bSortable : false,
									mRender : function(data, type, row) {

										var str = '';
										str += '<a href="'
												+ window.contextRoot
												+ '/gerenciar/'
												+ data
												+ '/livro" class="btn btn-primary"><span class="glyphicon glyphicon-pencil"></span></a> &#160;';

										return str;
									}
								}					           	
					],
					
					
					initComplete: function () {
						var api = this.api();
						api.$('.switch input[type="checkbox"]').on('change' , function() {							
							var dText = (this.checked)? 'Deseja ativar o livro?': 'Deseja desativar o livro?';
							var checked = this.checked;
							var checkbox = $(this);
							debugger;
						    bootbox.confirm({
						    	size: 'medium',
						    	title: 'Ativar/Desativar Livro',
						    	message: dText,
						    	callback: function (confirmed) {
							        if (confirmed) {
							            $.ajax({							            	
							            	type: 'GET',
							            	url: window.contextRoot + '/gerenciar/livro/'+checkbox.prop('value')+'/ativacao',
							        		timeout : 100000,
							        		success : function(data) {
							        			bootbox.alert(data);							        										        			
							        		},
							        		error : function(e) {
							        			bootbox.alert('ERROR: '+ e);
							        			//display(e);
							        		}						            	
							            });
							        }
							        else {							        	
							        	checkbox.prop('checked', !checked);
							        }
						    	}
						    });																											
						});
							
					}
				});
	}
	
	
	// lista todos produtos
	var $LivrosTableEstoque = $('#livrosTableEstoque');
	
	
	if($LivrosTableEstoque.length) {
		
		var jsonUrl = window.contextRoot + '/json/data/admin/todos/livros';
		console.log(jsonUrl);
		
		$LivrosTableEstoque.DataTable({
			"language": {
				
			    "sEmptyTable": "Nenhum registro encontrado",
			    "sInfo": "Mostrando de _START_ ate _END_ de _TOTAL_ registros",
			    "sInfoEmpty": "Mostrando 0 ate 0 de 0 registros",
			    "sInfoFiltered": "(Filtrados de _MAX_ registros)",
			    "sInfoPostFix": "",
			    "sInfoThousands": ".",
			    "sLengthMenu": "_MENU_ resultados por pagina",
			    "sLoadingRecords": "Carregando...",
			    "sProcessing": "Processando...",
			    "sZeroRecords": "Nenhum registro encontrado",
			    "sSearch": "Pesquisar",
			    "oPaginate": {
			        "sNext": "Proximo",
			        "sPrevious": "Anterior",
			        "sFirst": "Primeiro",
			        "sLast": "Ultimo"
			    },
			    "oAria": {
			        "sSortAscending": ": Ordenar colunas de forma ascendente",
			        "sSortDescending": ": Ordenar colunas de forma descendente"
			    },
			    "select": {
			        "rows": {
			            "_": "Selecionado %d linhas",
			            "0": "Nenhuma linha selecionada",
			            "1": "Selecionado 1 linha"
			        }
			    }
			
        },
					lengthMenu : [ [ 10, 30, 50, -1 ], [ '10 ', '30 ', '50 ', 'Todos' ] ],
					pageLength : 30,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [		
					           	{data: 'id'},


					           	{data: 'isbn'},
					           	{
									data : 'titulo'
								},							
								{
									data : 'quantidade',
									mRender : function(data, type, row) {

										if (data < 1) {
											return '<span style="color:red">Fora de Estoque!</span>';
										}

										return data;

									}
								},
								{
									data : 'precoUnit',
									mRender : function(data, type, row) {
										return 'R$ ' + data
									}
								},
								{
									data : 'id',
									bSortable : false,
									mRender : function(data, type, row) {

										var str = '';
										str += '<a href="'
												+ window.contextRoot
												+ '/gerenciar/'
												+ data
												+ '/livro'
												+'/estoque" class="btn btn-primary"><span class="glyphicon glyphicon-pencil"></span></a> &#160;';

										return str;
									}
								}					           	
					],
					
					
					
				});
	}
	
	
	// lista todos estoques
	var $EstoqueTable= $('#estoqueTable');
	
	
	if($EstoqueTable.length) {

		var jsonUrl = window.contextRoot + '/json/data/admin/todos/estoques';
		console.log(jsonUrl);
		
		$EstoqueTable.DataTable({
			"language": {
				
			    "sEmptyTable": "Nenhum registro encontrado",
			    "sInfo": "Mostrando de _START_ ate _END_ de _TOTAL_ registros",
			    "sInfoEmpty": "Mostrando 0 ate 0 de 0 registros",
			    "sInfoFiltered": "(Filtrados de _MAX_ registros)",
			    "sInfoPostFix": "",
			    "sInfoThousands": ".",
			    "sLengthMenu": "_MENU_ resultados por pagina",
			    "sLoadingRecords": "Carregando...",
			    "sProcessing": "Processando...",
			    "sZeroRecords": "Nenhum registro encontrado",
			    "sSearch": "Pesquisar",
			    "oPaginate": {
			        "sNext": "Proximo",
			        "sPrevious": "Anterior",
			        "sFirst": "Primeiro",
			        "sLast": "Ultimo"
			    },
			    "oAria": {
			        "sSortAscending": ": Ordenar colunas de forma ascendente",
			        "sSortDescending": ": Ordenar colunas de forma descendente"
			    },
			    "select": {
			        "rows": {
			            "_": "Selecionado %d linhas",
			            "0": "Nenhuma linha selecionada",
			            "1": "Selecionado 1 linha"
			        }
			    }
			
        },
					lengthMenu : [ [ 10, 30, 50, -1 ], [ '10 ', '30 ', '50 ', 'Todos' ] ],
					pageLength : 30,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [		
					           	{data: 'dataEntrada'
					           	},
					           	{data: 'tpoOperacao'
					           	},	

					           	{data: 'livroId'
					           	},							
								{
									data : 'quantidade'
								},
								{
									data : 'valorCusto',
									mRender : function(data, type, row) {
										return 'R$ ' + data
									}
								},
								{
									data : 'id',
									bSortable : false,
									mRender : function(data, type, row) {

										var str = '';
										str += '<a href="'
												+ window.contextRoot
												+ '/gerenciar/'
												+ data
												+'/estoque" class="btn btn-primary"><span class="glyphicon glyphicon-pencil"></span></a> &#160;';

										return str;
									}
								},
								{
									data : 'id',
									bSortable : false,
									mRender : function(data, type, row) {

										var str = '';
										str += '<a href="'
												+ window.contextRoot
												+ '/gerenciar/'
												+ data
												+'/estoque/excluir" class="btn btn-primary"><span class="glyphicon glyphicon-remove"></span></a> &#160;';

										return str;
									}
								}					           	
					],					
					
					
				});
	}
	
	var $vendasAprovadasTable = $('#vendasAprovadasTable');	
	
	if($vendasAprovadasTable.length) {
		
		var jsonUrl = window.contextRoot + '/json/data/admin/vendas/aprovadas';
		console.log(jsonUrl);
		
		$vendasAprovadasTable.DataTable({
			"language": {
				
			    "sEmptyTable": "Nenhum registro encontrado",
			    "sInfo": "Mostrando de _START_ ate _END_ de _TOTAL_ registros",
			    "sInfoEmpty": "Mostrando 0 ate 0 de 0 registros",
			    "sInfoFiltered": "(Filtrados de _MAX_ registros)",
			    "sInfoPostFix": "",
			    "sInfoThousands": ".",
			    "sLengthMenu": "_MENU_ resultados por pagina",
			    "sLoadingRecords": "Carregando...",
			    "sProcessing": "Processando...",
			    "sZeroRecords": "Nenhum registro encontrado",
			    "sSearch": "Pesquisar",
			    "oPaginate": {
			        "sNext": "Proximo",
			        "sPrevious": "Anterior",
			        "sFirst": "Primeiro",
			        "sLast": "Ultimo"
			    },
			    "oAria": {
			        "sSortAscending": ": Ordenar colunas de forma ascendente",
			        "sSortDescending": ": Ordenar colunas de forma descendente"
			    },
			    "select": {
			        "rows": {
			            "_": "Selecionado %d linhas",
			            "0": "Nenhuma linha selecionada",
			            "1": "Selecionado 1 linha"
			        }
			    }
			
        },
					lengthMenu : [ [ 10, 30, 50, -1 ], [ '10 ', '30 ', '50 ', 'Todos' ] ],
					pageLength : 30,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [		
					           	{
					           		data: 'dataVenda'
					           	},

					           	{
					           		data: 'usuarioNome'
					           	},
					           	{
									data : 'qtdVenda'
								},							
								{
									data : 'totalVenda'
								},
								{
									data : 'statusDescricao'
								},
								{
									data : 'id',
									bSortable : false,
									mRender : function(data, type, row) {

										var str = '';
										str += '<a href="'
												+ window.contextRoot
												+ '/gerenciar/'
												+ 'vendas/'
												+ data
												+ '/avancar" class="btn btn-primary"><span class="glyphicon glyphicon-forward"></span></a> &#160;';

									return str;
									}
								}					           	
					],
					
					
					
				});
	}
	
	// lista todos produtos
	var $vendasEntreguesTable = $('#vendasEntreguesTable');
	
	
	if($vendasEntreguesTable.length) {
		
		var jsonUrl = window.contextRoot + '/json/data/admin/vendas/entregues';
		console.log(jsonUrl);
		
		$vendasEntreguesTable.DataTable({
			"language": {
				
			    "sEmptyTable": "Nenhum registro encontrado",
			    "sInfo": "Mostrando de _START_ ate _END_ de _TOTAL_ registros",
			    "sInfoEmpty": "Mostrando 0 ate 0 de 0 registros",
			    "sInfoFiltered": "(Filtrados de _MAX_ registros)",
			    "sInfoPostFix": "",
			    "sInfoThousands": ".",
			    "sLengthMenu": "_MENU_ resultados por pagina",
			    "sLoadingRecords": "Carregando...",
			    "sProcessing": "Processando...",
			    "sZeroRecords": "Nenhum registro encontrado",
			    "sSearch": "Pesquisar",
			    "oPaginate": {
			        "sNext": "Proximo",
			        "sPrevious": "Anterior",
			        "sFirst": "Primeiro",
			        "sLast": "Ultimo"
			    },
			    "oAria": {
			        "sSortAscending": ": Ordenar colunas de forma ascendente",
			        "sSortDescending": ": Ordenar colunas de forma descendente"
			    },
			    "select": {
			        "rows": {
			            "_": "Selecionado %d linhas",
			            "0": "Nenhuma linha selecionada",
			            "1": "Selecionado 1 linha"
			        }
			    }
			
        },
					lengthMenu : [ [ 10, 30, 50, -1 ], [ '10 ', '30 ', '50 ', 'Todos' ] ],
					pageLength : 30,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [		
					           	{
					           		data: 'dataVenda'
					           	},

					           	{
					           		data: 'usuarioNome'
					           	},
					           	{
									data : 'qtdVenda'
								},							
								{
									data : 'totalVenda'
								},
								{
									data : 'statusDescricao'
								}					           	
					],
					
					
					
				});
	}
	
	// lista as vendas em transporte
	var $vendasTransporteTable = $('#vendasTransporteTable');
	
	
	if($vendasTransporteTable.length) {
		
		var jsonUrl = window.contextRoot + '/json/data/admin/vendas/transporte';
		console.log(jsonUrl);
		
		$vendasTransporteTable.DataTable({
			"language": {
				
			    "sEmptyTable": "Nenhum registro encontrado",
			    "sInfo": "Mostrando de _START_ ate _END_ de _TOTAL_ registros",
			    "sInfoEmpty": "Mostrando 0 ate 0 de 0 registros",
			    "sInfoFiltered": "(Filtrados de _MAX_ registros)",
			    "sInfoPostFix": "",
			    "sInfoThousands": ".",
			    "sLengthMenu": "_MENU_ resultados por pagina",
			    "sLoadingRecords": "Carregando...",
			    "sProcessing": "Processando...",
			    "sZeroRecords": "Nenhum registro encontrado",
			    "sSearch": "Pesquisar",
			    "oPaginate": {
			        "sNext": "Proximo",
			        "sPrevious": "Anterior",
			        "sFirst": "Primeiro",
			        "sLast": "Ultimo"
			    },
			    "oAria": {
			        "sSortAscending": ": Ordenar colunas de forma ascendente",
			        "sSortDescending": ": Ordenar colunas de forma descendente"
			    },
			    "select": {
			        "rows": {
			            "_": "Selecionado %d linhas",
			            "0": "Nenhuma linha selecionada",
			            "1": "Selecionado 1 linha"
			        }
			    }
			
        },
					lengthMenu : [ [ 10, 30, 50, -1 ], [ '10 ', '30 ', '50 ', 'Todos' ] ],
					pageLength : 30,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [		
					           	{
					           		data: 'dataVenda'
					           	},

					           	{
					           		data: 'usuarioNome'
					           	},
					           	{
									data : 'qtdVenda'
								},							
								{
									data : 'totalVenda'
								},
								{
									data : 'statusDescricao'
								},
								{
									data : 'id',
									bSortable : false,
									mRender : function(data, type, row) {

										var str = '';
										str += '<a href="'
												+ window.contextRoot
												+ '/gerenciar/'
												+ 'vendas/'
												+ data
												+ '/avancar" class="btn btn-primary"><span class="glyphicon glyphicon-forward"></span></a> &#160;';

									return str;
									}
								}					           	
					],
					
					
					
				});
	}
	
	
	// lista todos enderecos do cliente
	var UsuariosTable = $('#UsuariosTable');
	
	
	if(UsuariosTable.length) {
		
		var jsonUrl = window.contextRoot + '/json/data/admin/todos/usuarios';
		console.log(jsonUrl);
		
		UsuariosTable.DataTable({
					lengthMenu : [ [ 10, 30, 50, -1 ], [ '10 registros', '30 registros', '50 registros', 'Todos' ] ],
					pageLength : 30,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [		
					           	{data: 'id'},


					           	
					           	{
									data : 'nome'
								},
								{
									data : 'sobrenome'
								},
								
								{
									data : 'id',
									bSortable : false,
									mRender : function(data, type, row) {

										var str = '';
										str += '<a href="'
												+ window.contextRoot
												+ '/gerenciar/'
												+ data
												+ '/usuario" class="btn btn-primary"><span class="glyphicon glyphicon-pencil"></span></a> &#160;';

										return str;
									}
								}					           	
					],				
					
				});
	}
	
	
	var $table = $('#pedidosCliente');

	if ($table.length) {

		var jsonUrl = '';
		if (window.usuarioId == '') {
			
		} else {
			jsonUrl = window.contextRoot + '/json/data/meuperfil/'
					+ window.usuarioId + '/pedidos';
		}

		$table
				.DataTable({
					
					"language": {
						
						    "sEmptyTable": "Nenhum registro encontrado",
						    "sInfo": "Mostrando de _START_ ate _END_ de _TOTAL_ registros",
						    "sInfoEmpty": "Mostrando 0 ate 0 de 0 registros",
						    "sInfoFiltered": "(Filtrados de _MAX_ registros)",
						    "sInfoPostFix": "",
						    "sInfoThousands": ".",
						    "sLengthMenu": "_MENU_ resultados por pagina",
						    "sLoadingRecords": "Carregando...",
						    "sProcessing": "Processando...",
						    "sZeroRecords": "Nenhum registro encontrado",
						    "sSearch": "Pesquisar",
						    "oPaginate": {
						        "sNext": "Proximo",
						        "sPrevious": "Anterior",
						        "sFirst": "Primeiro",
						        "sLast": "Ultimo"
						    },
						    "oAria": {
						        "sSortAscending": ": Ordenar colunas de forma ascendente",
						        "sSortDescending": ": Ordenar colunas de forma descendente"
						    },
						    "select": {
						        "rows": {
						            "_": "Selecionado %d linhas",
						            "0": "Nenhuma linha selecionada",
						            "1": "Selecionado 1 linha"
						        }
						    }
						
			        },
				
					lengthMenu : [ [ 3, 5, 10, -1 ],
							[ '3 ', '5 ', '10 ', 'Todos' ] ],
					pageLength : 5,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'dataVenda'
							},
							{
								data : 'id'
							},
							{
								data : 'qtdVenda'
							},
							{
								data : 'totalVenda'
							},
							{
								data : 'statusDescricao'
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {

									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/mostrar/'
											+ data
											+ '/pedido" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
									
									return str;

								}

							} ]
				});
	}
	
	var $table = $('#cuponsCliente');

	if ($table.length) {

		var jsonUrl = '';
		if (window.usuarioId == '') {
			
		} else {
			jsonUrl = window.contextRoot + '/json/data/meuperfil/'
					+ window.usuarioId + '/cupons';
		}

		$table
				.DataTable({
					
					"language": {
						
						    "sEmptyTable": "Nenhum registro encontrado",
						    "sInfo": "Mostrando de _START_ ate _END_ de _TOTAL_ registros",
						    "sInfoEmpty": "Mostrando 0 ate 0 de 0 registros",
						    "sInfoFiltered": "(Filtrados de _MAX_ registros)",
						    "sInfoPostFix": "",
						    "sInfoThousands": ".",
						    "sLengthMenu": "_MENU_ resultados por pagina",
						    "sLoadingRecords": "Carregando...",
						    "sProcessing": "Processando...",
						    "sZeroRecords": "Nenhum registro encontrado",
						    "sSearch": "Pesquisar",
						    "oPaginate": {
						        "sNext": "Proximo",
						        "sPrevious": "Anterior",
						        "sFirst": "Primeiro",
						        "sLast": "Ultimo"
						    },
						    "oAria": {
						        "sSortAscending": ": Ordenar colunas de forma ascendente",
						        "sSortDescending": ": Ordenar colunas de forma descendente"
						    },
						    "select": {
						        "rows": {
						            "_": "Selecionado %d linhas",
						            "0": "Nenhuma linha selecionada",
						            "1": "Selecionado 1 linha"
						        }
						    }
						
			        },
				
					lengthMenu : [ [ 3, 5, 10, -1 ],
							[ '3 ', '5 ', '10 ', 'Todos' ] ],
					pageLength : 5,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'id'
							},
							{
								data : 'descricao'
							},
							{
								data : 'valorCupom'
							}]
				});
	}
	var $table = $('#enderecosCliente');

	if ($table.length) {

		var jsonUrl = '';
		if (window.usuarioId == '') {
			
		} else {
			jsonUrl = window.contextRoot + '/json/data/meuperfil/'
					+ window.usuarioId + '/enderecos';
		}

		$table
				.DataTable({
					
					"order" : [[1, "desc"]],
					
					"language": {
						
						    "sEmptyTable": "Nenhum registro encontrado",
						    "sInfo": "Mostrando de _START_ ate _END_ de _TOTAL_ registros",
						    "sInfoEmpty": "Mostrando 0 ate 0 de 0 registros",
						    "sInfoFiltered": "(Filtrados de _MAX_ registros)",
						    "sInfoPostFix": "",
						    "sInfoThousands": ".",
						    "sLengthMenu": "_MENU_ resultados por pagina",
						    "sLoadingRecords": "Carregando...",
						    "sProcessing": "Processando...",
						    "sZeroRecords": "Nenhum registro encontrado",
						    "sSearch": "Pesquisar",
						    "oPaginate": {
						        "sNext": "Proximo",
						        "sPrevious": "Anterior",
						        "sFirst": "Primeiro",
						        "sLast": "Ultimo"
						    },
						    "oAria": {
						        "sSortAscending": ": Ordenar colunas de forma ascendente",
						        "sSortDescending": ": Ordenar colunas de forma descendente"
						    },
						    "select": {
						        "rows": {
						            "_": "Selecionado %d linhas",
						            "0": "Nenhuma linha selecionada",
						            "1": "Selecionado 1 linha"
						        }
						    }
						
			        },
				
					lengthMenu : [ [ 3, 5, 10, -1 ],
							[ '3 ', '5 ', '10 ', 'Todos' ] ],
					pageLength : 5,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'tipoResidencia'
							},
							{
								data : 'logradouro'
							},
							
							{
								data : 'numero'
							},
							
							{
								data : 'cep'
							},
							{
								data : 'bairro'
							},
							{
								data : 'cidade'
							},
							{
								data : 'estado'
							},
							{
								data : 'pais'
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {

									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/editar/'
											+ data
											+ '/endereco" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
									
									return str;

								}

							} ]
				});
	}
	
	// lista todos produtos
	var $table = $('#trocasSolicitadasTable');
	
	
	if($table.length) {
		
		var jsonUrl = window.contextRoot + '/json/data/admin/trocas/solicitadas';
		console.log(jsonUrl);
		
		$table.DataTable({
			"language": {
				
			    "sEmptyTable": "Nenhum registro encontrado",
			    "sInfo": "Mostrando de _START_ ate _END_ de _TOTAL_ registros",
			    "sInfoEmpty": "Mostrando 0 ate 0 de 0 registros",
			    "sInfoFiltered": "(Filtrados de _MAX_ registros)",
			    "sInfoPostFix": "",
			    "sInfoThousands": ".",
			    "sLengthMenu": "_MENU_ resultados por pagina",
			    "sLoadingRecords": "Carregando...",
			    "sProcessing": "Processando...",
			    "sZeroRecords": "Nenhum registro encontrado",
			    "sSearch": "Pesquisar",
			    "oPaginate": {
			        "sNext": "Proximo",
			        "sPrevious": "Anterior",
			        "sFirst": "Primeiro",
			        "sLast": "Ultimo"
			    },
			    "oAria": {
			        "sSortAscending": ": Ordenar colunas de forma ascendente",
			        "sSortDescending": ": Ordenar colunas de forma descendente"
			    },
			    "select": {
			        "rows": {
			            "_": "Selecionado %d linhas",
			            "0": "Nenhuma linha selecionada",
			            "1": "Selecionado 1 linha"
			        }
			    }
			
        },
					lengthMenu : [ [ 10, 30, 50, -1 ], [ '10 ', '30 ', '50 ', 'Todos' ] ],
					pageLength : 30,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [		
					           	{
					           		data: 'dataSolicitacao'
					           	},

					           	{
					           		data: 'usuarioNome'
					           	},
					           	{
									data : 'vendaDetalheId'
								},
					           	{
									data : 'titulo'
								},							
								{
									data : 'qtdTroca'
								},
								{
									data : 'id',
									bSortable : false,
									mRender : function(data, type, row) {

										var str = '';
										str += '<a href="'
												+ window.contextRoot
												+ '/gerenciar/'
												+ 'troca/'
												+ data
												+ '/analisar" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';

									return str;
									}
								}					           	
					],
					
					
					
				});
	}
	
	
	// lista todos produtos
	var $trocasAnaliseTable = $('#trocasAnaliseTable');
	
	
	if($trocasAnaliseTable.length) {
		
		var jsonUrl = window.contextRoot + '/json/data/admin/trocas/analise';
		console.log(jsonUrl);
		
		$trocasAnaliseTable.DataTable({
			"language": {
				
			    "sEmptyTable": "Nenhum registro encontrado",
			    "sInfo": "Mostrando de _START_ ate _END_ de _TOTAL_ registros",
			    "sInfoEmpty": "Mostrando 0 ate 0 de 0 registros",
			    "sInfoFiltered": "(Filtrados de _MAX_ registros)",
			    "sInfoPostFix": "",
			    "sInfoThousands": ".",
			    "sLengthMenu": "_MENU_ resultados por pagina",
			    "sLoadingRecords": "Carregando...",
			    "sProcessing": "Processando...",
			    "sZeroRecords": "Nenhum registro encontrado",
			    "sSearch": "Pesquisar",
			    "oPaginate": {
			        "sNext": "Proximo",
			        "sPrevious": "Anterior",
			        "sFirst": "Primeiro",
			        "sLast": "Ultimo"
			    },
			    "oAria": {
			        "sSortAscending": ": Ordenar colunas de forma ascendente",
			        "sSortDescending": ": Ordenar colunas de forma descendente"
			    },
			    "select": {
			        "rows": {
			            "_": "Selecionado %d linhas",
			            "0": "Nenhuma linha selecionada",
			            "1": "Selecionado 1 linha"
			        }
			    }
			
        },
					lengthMenu : [ [ 10, 30, 50, -1 ], [ '10 ', '30 ', '50 ', 'Todos' ] ],
					pageLength : 30,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [		
					           	{
					           		data: 'dataSolicitacao'
					           	},

					           	{
					           		data: 'usuarioNome'
					           	},
					           	{
									data : 'vendaDetalheId'
								},
					           	{
									data : 'titulo'
								},							
								{
									data : 'qtdTroca'
								},
								{
									data : 'id',
									bSortable : false,
									mRender : function(data, type, row) {

										var str = '';
										str += '<a href="'
												+ window.contextRoot
												+ '/gerenciar/'
												+ 'troca/'
												+ data
												+ '/analisar" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';

									return str;
									}
								}					           	
					],
					
					
					
				});
	}
	var $table = $('#cartoesCliente');

	if ($table.length) {

		var jsonUrl = '';
		if (window.usuarioId == '') {
			
		} else {
			jsonUrl = window.contextRoot + '/json/data/meuperfil/'
					+ window.usuarioId + '/cartoes';
		}

		$table
				.DataTable({
					
					"language": {
						
						    "sEmptyTable": "Nenhum registro encontrado",
						    "sInfo": "Mostrando de _START_ ate _END_ de _TOTAL_ registros",
						    "sInfoEmpty": "Mostrando 0 ate 0 de 0 registros",
						    "sInfoFiltered": "(Filtrados de _MAX_ registros)",
						    "sInfoPostFix": "",
						    "sInfoThousands": ".",
						    "sLengthMenu": "_MENU_ resultados por pagina",
						    "sLoadingRecords": "Carregando...",
						    "sProcessing": "Processando...",
						    "sZeroRecords": "Nenhum registro encontrado",
						    "sSearch": "Pesquisar",
						    "oPaginate": {
						        "sNext": "Proximo",
						        "sPrevious": "Anterior",
						        "sFirst": "Primeiro",
						        "sLast": "Ultimo"
						    },
						    "oAria": {
						        "sSortAscending": ": Ordenar colunas de forma ascendente",
						        "sSortDescending": ": Ordenar colunas de forma descendente"
						    },
						    "select": {
						        "rows": {
						            "_": "Selecionado %d linhas",
						            "0": "Nenhuma linha selecionada",
						            "1": "Selecionado 1 linha"
						        }
						    }
						
			        },
				
					lengthMenu : [ [ 3, 5, 10, -1 ],
							[ '3 ', '5 ', '10 ', 'Todos' ] ],
					pageLength : 5,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'bandeiraDescricao'
							},
							{
								data : 'numeroCartao'
							},
							
							{
								data : 'nomeCartao'
							},
							
							{
								data : 'mesVencimento'
							},
							{
								data : 'anoVencimento'
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {

									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/editar/'
											+ data
											+ '/cartao" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
									
									return str;

								}

							} ]
				});
	}
	
	function errorPlacement(error, element) {
		error.addClass("help-block");
		
		error.insertAfter(element);
		
		
		element.parents(".validate").addClass("has-feedback");	

	}	
	
	
	
	$categoriaForm = $('#categoria');
	
	if($categoriaForm.length) {
		
		$categoriaForm.validate({			
				rules: {
					nome: {
						required: true,
						minlength: 3
					},
					descricao: {
						required: true,
						minlength: 3					
					}				
				},
				messages: {					
					nome: {
						required: 'Insira o Livro!',
						minlength: 'Minimo de 5 caracteres'
					},
					descricao: {
						required: 'Insira o Livro!',
						minlength: 'Minimo de 5 caracteres'
					}					
				},
				errorElement : "em",
				errorPlacement : function(error, element) {
					errorPlacement(error, element);
				}				
			}
		
		);
		
	}
	
	$loginForm = $('#loginForm');
	
	if($loginForm.length) {
		
		$loginForm.validate({			
				rules: {
					username: {
						required: true,
						email: true
						
					},
					password: {
						required: true
					}				
				},
				messages: {					
					username: {
						required: 'Entre com e-mail',
						email: 'Insira um e-mail válido!'
					},
					password: {
						required: 'Entre com a senha!'
					}					
				},
				errorElement : "em",
				errorPlacement : function(error, element) {
					error.addClass("help-block");
					
					error.insertAfter(element);
				}				
			}
		
		);
		
	}
		
	
	

	$alert = $('.alert');
	if($alert.length) {
		setTimeout(function() {
	    	$alert.fadeOut('slow');
		   }, 3000
		);		
	}
		
	
	$('button[name="refreshCart"]').click(function(){
		var itemCarrinhoId = $(this).attr('value');
		var quantidade = $('#count_' + itemCarrinhoId);
		var originalCount = quantidade.attr('value');
		if(quantidade.val() !== originalCount) {	
			if(quantidade.val() < 1 || quantidade.val() > 3) {
				quantidade.val(originalCount);
				bootbox.alert({
					size: 'medium',
			    	title: 'Error',
			    	message: 'Quantidade deve ser minimo 1 e maximo 3!'
				});
			}
			else {
				var updateUrl = window.contextRoot + '/carrinho/' + itemCarrinhoId + '/atualizar?count=' + quantidade.val();
				window.location.href = updateUrl;
			}
		}
	});			
});