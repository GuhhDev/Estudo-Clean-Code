para vocês que gostam:

ANTES
@Override
public Boolean validarAntesDeGravar() {
// Variável de retorno
	Boolean retorno = true;
// Verifica
	if (this.modelo.getPadrao()) {
// Percorre todos os estabelecimentos definidos
		for (Object[] objeto : lista) {
// Valor
			Integer id = (Integer) objeto[getVisaoTipada()
			.getCjxtConsulta().getIndiceColuna("Id")];
// Principal
			Boolean principal = (Boolean) objeto[getVisaoTipada()
			.getCjxtConsulta().getIndiceColuna("Padrão")];
// Está inserindo ou alterando e não o próprio registro?
			if ((!(this.alterando))
				|| ((this.alterando) && (!(id.equals(this.modelo
					.getId()))))) {
// Verifica se é principal
				if (principal) {
// Mensagem de erro
					Mensagem.aviso("Já existe um endereço padrão definido! Por favor, verifique.");
// Erro na validação
					retorno = false;
// Parando laço
					break;
				}
			}
		}
	}
// Retornando
	return retorno;
}


DEPOIS

@Override
public Boolean validarAntesDeGravar() {
	Object[] objeto = null;
// Verifica
	if (this.modelo.getPadrao()) {
// Percorre todos os estabelecimentos definidos
		objeto = lista.stream().filter(padrao -> (padrao[3].equals("SIM"))).findAny().orElse(null);

		if (!Objects.isNull(objeto)) {
			Mensagem.aviso("Já existe um endereço padrão definido! Por favor, verifique.");
		}
	}

	return (Objects.isNull(objeto));
}