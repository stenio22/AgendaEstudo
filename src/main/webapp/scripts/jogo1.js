/**
 * JOGO DA DESCOBERTA
 */

var numeroAleatorio = getRandomInt(1, 100)

function getRandomInt(min, max) {
	min = Math.ceil(min);
	max = Math.floor(max);
	return Math.floor(Math.random() * (max - min)) + min;
}



function jogo() {


	var i = prompt('Digite um numero de 1 a 100')

	var tentativas = 0
	while (tentativas < 5) {
		if (i > 100) {
			i = prompt('Digite um numero de 1 a 100')
		}

		if (i > numeroAleatorio) {
			alert('numero maior do que o que eu quero, ' + ((tentativas - 5) * -1) + 'restantes')
			tentativas++
			i = prompt('Digite um numero de 1 a 100')

		} if (i < numeroAleatorio) {
			alert('numero menor do que o que eu quero, ' + ((tentativas - 5) * -1) + 'restantes')
			tentativas++
			i = prompt('Digite um numero de 1 a 100')
		} if (numeroAleatorio == i) {
			var acertou = 1;
			alert('parabéns você acertou o número')
			tentativas = 5;
			document.getElementById("demo").innerHTML = "parabéns vc acertou!!!", +i;
		} if (i === null) {
			tentativas = 5
			break
		}
		if (tentativas === 5 && acertou === 0) {
		alert('tente novamente :<()')
	}

	}

	


}

