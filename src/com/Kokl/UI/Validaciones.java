package com.Kokl.UI;

import javafx.scene.control.*;

public class Validaciones {

    public boolean validarTexto(TextField input){
        boolean err = false;

        if(input.getText().equals("")){
            input.getStyleClass().add("err");
            err = true;
        }else{
            input.getStyleClass().remove("err");
        }

        return err;
    }

    public boolean validarComboBox(ComboBox input){
        boolean err = false;

        if(input.getValue().equals("Seleccione")){
            input.getStyleClass().add("err");
            err = true;
        }else{
            input.getStyleClass().remove("err");
        }

        return err;
    }

    public boolean validarContrasena(TextField inputContrasena){
        boolean err = false;

        if(!inputContrasena.getText().matches("(?-i)(?=^.{8,12}$)((?!.*\\s)(?=.*[A-Z])(?=.*[a-z]))((?=(.*\\d){1,})|(?=(.*\\W){1,}))^.*$")){
            inputContrasena.getStyleClass().add("err");
            err = true;
        }else{
            inputContrasena.getStyleClass().remove("err");
        }

        return err;
    }

	public boolean validarFecha(DatePicker inputFecha) {
	    boolean err = false;

	        if(inputFecha.getValue() == null){
	            inputFecha.getStyleClass().add("err");
	            err = true;
            }else{
	            inputFecha.getStyleClass().remove("err");
            }
	    return err;
    }
}