$(".click").mouseover(function() {
$(this).css("cursor","pointer");
});

function openVendaModal(id) {
	$.ajax({
		url: "/vendas/detalhe/" + id,
		success: function(data) {
			$("#vendaHolter").html(data);
			
		}
	});
};

$('#confirmacaoExclusaoModal').on('show.bs.modal',function(event) {
			var button = $(event.relatedTarget);

			var codigoEstabelecimento = button.data('id');
			var nomeEstabelecimeto = button.data('nome');

			var modal = $(this);
			var form = modal.find('form');
			var action = form.data('url-base');
			if (!action.endsWith('/')) {
				action += '/';
			}
			form.attr('action', action + codigoEstabelecimento);

			modal.find('.modal-body span').html('Tem certeza que deseja excluir o estabelecimento <strong>'+ nomeEstabelecimeto + '</strong>?')
		});

$('#confirmacaoExclusaoModalUsuario').on('show.bs.modal',function(event) {
	var button = $(event.relatedTarget);

	var codigoUsuario = button.data('id');
	var nomeUsuario = button.data('nome');

	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + codigoUsuario);

	modal.find('.modal-body span').html('Tem certeza que deseja excluir o usuario <strong>'+ nomeUsuario +'</strong>?')
});

$('#confirmacaoExclusaoModalProduto').on('show.bs.modal',function(event) {
	var button = $(event.relatedTarget);
	
	var codigoUsuario = button.data('id');
	var nomeUsuario = button.data('nome');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + codigoUsuario);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o produto <strong>'+ nomeUsuario +'</strong>?')
});

//quando carregar a pagina excuta essa função
$(function(){
	$('[rel="tooltip"]').tooltip();
	
	//Mascara do telefone
	$("input.js-mask-telefone")
    .mask("(99) 9999-9999?9")
    .focusout(function (event) {  
        var target, phone, element;  
        target = (event.currentTarget) ? event.currentTarget : event.srcElement;  
        phone = target.value.replace(/\D/g, '');
        element = $(target);  
        element.unmask();  
        if(phone.length > 10) {  
            element.mask("(99) 99999-999?9");  
        } else {  
            element.mask("(99) 9999-9999?9");  
        }  
    });
	
	$('[rel="tooltip"]').tooltip();
	
	$('.js-currency').maskNumber({decimal: ',', thousands: '.'});
	
	$('.closebtn').click(function(){
		$('.alerta').slideUp(1000);
	});
	
	
	
$('.js-atualizar-status').on('click', function(event){
		event.preventDefault();
		var botaoReceber = $(event.currentTarget);
		var urlReceber = botaoReceber.attr('href');
		
		var response = $.ajax({
			url: urlReceber,
			type: 'PUT'
		});
		
		response.done(function(e){
			var codigoEstabelecimento = botaoReceber.data('id');
			$('[data-role=' + codigoEstabelecimento + ']').html('<span class="label label-success">'+ e +'</span>');
			botaoReceber.hide();
		});
		
		response.fail(function(e){
			console.log(e);
			alert('Erro em receber a cobrança');
		});
	});
	
	//Mascara do telefone
	
	$("input.js-mask-telefone")
    .mask("(99) 9999-9999?9")
    .focusout(function (event) {  
        var target, phone, element;  
        target = (event.currentTarget) ? event.currentTarget : event.srcElement;  
        phone = target.value.replace(/\D/g, '');
        element = $(target);  
        element.unmask();  
        if(phone.length > 10) {  
            element.mask("(99) 99999-999?9");  
        } else {  
            element.mask("(99) 9999-9999?9");  
        }  
    });
	
	//Obter endereço pelo cep
	
	$('.js-toggle').bind('click', function (event) {
        $('.js-sidebar, .js-content').toggleClass('is-toggled');
        event.preventDefault();
    });
	
	geocoder = new google.maps.Geocoder();
});

$('#cep').mask('**.***-***');

// Restante do código para obter endereço pelo CEP
function limpa_formulário_cep() {
    //Limpa valores do formulário de cep.
    document.getElementById('logradouro').value = ("");
    document.getElementById('bairro').value = ("");
    document.getElementById('cidade').value = ("");
    document.getElementById('uf').value = ("");
}

function meu_callback(conteudo) {
    if (!("erro" in conteudo)) {
        //Atualiza os campos com os valores.
        document.getElementById('logradouro').value = (conteudo.logradouro);
        document.getElementById('bairro').value = (conteudo.bairro);
        document.getElementById('cidade').value = (conteudo.localidade);
        document.getElementById('uf').value = (conteudo.uf);
    } //end if.
    else {
        //CEP não Encontrado.
        limpa_formulário_cep();
        alert("CEP não encontrado.");
    }
}

function pesquisacep(valor) {
    
    //Nova variável "cep" somente com dígitos.
    var cep = valor.replace(/\D/g, '');

    //Verifica se campo cep possui valor informado.
    if (cep != "") {

        //Expressão regular para validar o CEP.
        var validacep = /^[0-9]{8}$/;

        //Valida o formato do CEP.
        if (validacep.test(cep)) {

            //Preenche os campos com "..." enquanto consulta webservice.
            document.getElementById('logradouro').value = "...";
            document.getElementById('bairro').value = "...";
            document.getElementById('cidade').value = "...";
            document.getElementById('uf').value = "...";

            //Cria um elemento javascript.
            var script = document.createElement('script');

            //Sincroniza com o callback.
            script.src = '//viacep.com.br/ws/' + cep + '/json/?callback=meu_callback';

            //Insere script no documento e carrega o conteúdo.
            document.body.appendChild(script);

        } //end if.
        else {
            //cep é inválido.
			limpa_formulário_cep();
            alert("Formato de CEP inválido.");
        }
    } //end if.
    else {
        //cep sem valor, limpa formulário.
        limpa_formulário_cep();
    }
}
;

function converteEndereco(endereco){
	
	geocoder.geocode( { 'address': endereco}, function(resultado, status) {
	    if (status == google.maps.GeocoderStatus.OK) {
	    	document.getElementById('latitude').value = resultado[0].geometry.location.lat();
	    	document.getElementById('longitude').value = resultado[0].geometry.location.lng();	
	    } else {
	      alert('Erro ao converter endereço: ' + status);
	    }
	  });
	
}

function converte(){
	var endereco = document.getElementById('logradouro').value +"," + 
	document.getElementById('bairro').value + 
	"," + document.getElementById('cidade').value+"-" + 
	document.getElementById('uf').value;
	
	converteEndereco(endereco);
}

// Carregar o Mapa

var map;        
var myCenter=new google.maps.LatLng(-1.2972219, -48.4651844);
//var marker=new google.maps.Marker({
//	position:myCenter,
//	});

function initialize() {
	
		var mapProp = {
		center:myCenter,
		zoom: 17,
		draggable: true,
		scrollwheel: true,
		mapTypeId:google.maps.MapTypeId.ROADMAP
	};
		
map=new google.maps.Map(document.getElementById("map-canvas"),mapProp);
//marker.setMap(map);

};
google.maps.event.addDomListener(window, 'load', initialize);

google.maps.event.addDomListener(window, "resize", resizingMap());

$('#mapaModal').on('show.bs.modal', function() {
//Must wait until the render of the modal appear, thats why we use the resizeMap and NOT resizingMap!! ;-)
resizeMap();
})

function resizeMap() {
if(typeof map =="undefined") return;
setTimeout( function(){resizingMap();} , 400);
}

function resizingMap() {
if(typeof map =="undefined") return;
var center = map.getCenter();
google.maps.event.trigger(map, "resize");
map.setCenter(center); 
}

function FazMarcacao(lat, long, nome) {
	   
	   myCenter = new google.maps.LatLng(lat, long);
	   
	  
	          //criando variavel tipo google.maps.LatLng e 
	         //passando como parametro a latitude e longitude
	        //na configuracao: latitude,longitude.
	   
	       //aproximando o mapa, aumentando o zoom
	   map.setZoom(17);
	   
	      //fazendo  a marcacao, usando o latitude e longitude da variavel criada acima
	    var marker = new google.maps.Marker({ position: myCenter, map: map ,title: nome}); 
	    //aqui a variavel e o comando que faz a marcação
	   
	     map.setCenter(myCenter);//leva o mapa para a posicao da marcacao
	  } 

/**
 * função para movimentação das etapas do formulario de pré-cadastro 
 **/
function alteraEtapa(etapa){
	  $('#tabEtapas a[href="#etapa'+etapa+'"]').tab('show');
	  
	}
$(".prev").click(function(event){
    event.preventDefault();
});
