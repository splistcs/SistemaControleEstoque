package sdedr.model.Enum;

public enum Cardapio {
	ERRO,
	DOMINGO,
	SEGUNDA,
	TERCA,
	QUARTA,
	QUINTA,
	SEXTA,
	SABADO,
	FIXO,
	ESPECIAL,
	TMP;

	public static Cardapio tipoCardapioInt(int tipo) {
		return switch (tipo) {
			case 1 -> DOMINGO;
			case 2 -> SEGUNDA;
			case 3 -> TERCA;
			case 4 -> QUARTA;
			case 5 -> QUINTA;
			case 6 -> SEXTA;
			case 7 -> SABADO;
			case 8 -> FIXO;
			case 9 -> ESPECIAL;
			case 10 -> TMP;
			default -> ERRO;
		};
	}
}
