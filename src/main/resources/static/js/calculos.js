$('.selectpicker').selectpicker({
  style: 'btn-info',
  size: 4
});

$('#undAcaiFS').on('change',function(){
	var qtd = $('#qtdAcaiFS').val();
	var und = this.value;

	document.getElementById("qtdAcaiForaSafra").value = calcularTotal(und, qtd).toFixed(2);
	
});

$('#undResFS').on('change',function(){
	var qtd = $('#qtdResFS').val();
	var und = this.value;
	
	document.getElementById("qtdResiduoForaSafra").value = calcularTotal(und, qtd).toFixed(2);
	
});

$('#undAcaiNS').on('change',function(){
	var qtd = $('#qtdAcaiNS').val();
	var und = this.value;

	document.getElementById("qtdAcaiNaSafra").value = calcularTotal(und, qtd).toFixed(2);
	
});

$('#undResNS').on('change',function(){
	var qtd = $('#qtdResNS').val();
	var und = this.value;
	
	document.getElementById("qtdResiduoNaSafra").value = calcularTotal(und, qtd).toFixed(2);
	
});

$('#horario').on('blur',function(){
	var hrI = $('#hrInicio').val();
	var hrF = $('#hrFim').val();
	setTimeout(horario(hrI, hrF), 2000);
	
});

function horario(hrI, hrF) {
	document.getElementById("horario").value = hrI + " as " + hrF;
}

function calcularTotal(und, qtd){
	var qtd_total;
	
	switch(und) {
	case "lata":
		qtd_total = qtd * 14;
		break;
	case "saca":
		qtd_total = qtd * 60;
		break;
	case "raza":
		qtd_total = qtd * 28;
		break;
	}
	
	return qtd_total;
}

$(".time").mask("99:99");