package sdedr.model.Enum;

public enum TipoCadastro {
	ERRO,
  ADMIN,
	CHEF,
	ALMOXARIFADO; 
	
	public static TipoCadastro tipoCadastroInt(int tipo) {
		return switch (tipo) {
			case 1 -> ADMIN;
			case 2 -> CHEF;
			case 3 -> ALMOXARIFADO;
			default -> ERRO;
		};
	}
}
