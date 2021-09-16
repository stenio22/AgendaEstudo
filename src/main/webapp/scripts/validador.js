/**
 * VALIDACAO DE CAMPOS 
@author STENIOOLIVEIRA

 */

function validar() {
	let nome = frmContato.nome.value;
	let fone = frmContato.fone.value;
	let email = frmContato.email.value;
	if (nome === "") {
		alert('Preencha o campo corretamente')
		frmContato.nome.focus()
		return false
	} else if (fone === "") {
		alert('Preencha o campo corretamente')
		frmContato.fone.focus()
		return false
	} else if (email === "") {
		alert('Preencha o campo corretamente')
		frmContato.email.focus()
		return false
	}else{
		document.forms["frmContato"].submit();
	}
}