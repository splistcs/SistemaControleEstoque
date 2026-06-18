package sdedr.model.Enum;

public enum TipoMovimentacao {
	ERRO,
	ENTRADA,
	SAIDA,
	AJUSTE,
	PERDA;

	public static TipoMovimentacao tipoMovimentacaoInt(int tipo) {
		return switch (tipo) {
			case 1 -> ENTRADA;
			case 2 -> SAIDA;
			case 3 -> AJUSTE;
			case 4 -> PERDA;
			default -> ERRO;
		};
	}
}
