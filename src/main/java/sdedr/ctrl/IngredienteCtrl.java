package sdedr.ctrl;

import java.util.ArrayList;

import sdedr.dao.IngredienteDao;
import sdedr.model.Ingrediente;
import sdedr.model.Receita;

public class IngredienteCtrl {
    public boolean retornarIngredientesReceita(Receita receita, ArrayList<Ingrediente> ingredientesReceita) {
        IngredienteDao ingredienteDao = new IngredienteDao();
        try {
            return ingredienteDao.retornarIngredientesReceita(receita, ingredientesReceita);
        } catch (Exception e) {
            System.out.println("ERRO" + e.getMessage());
            return false;
        }
    }
    
    public boolean cadastrarIngrediente(Ingrediente ingrediente) {
        IngredienteDao ingredienteDao = new IngredienteDao();
        try {
            return ingredienteDao.inserir(ingrediente);
        } catch (Exception exception) {
            System.out.println("ERRO" + exception.getMessage());
            return false;
        }
    }    
}
