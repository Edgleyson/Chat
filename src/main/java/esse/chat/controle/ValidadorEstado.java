package esse.chat.controle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidadorEstado implements ConstraintValidator<ValidaEstado, String> {
    private List<String> estados;
    private String estadosBrasileiros[] = {"AC", "AL", "AM", "AP", "BA", "CE", "DF",
                                           "ES", "GO", "MA", "MT", "MS", "MG", "PA", 
                                           "PB", "PR", "PE", "PI", "RJ", "RN", "RO", 
                                           "RS", "RR", "SC", "SE", "SP", "TO"};
    
    @Override
    public void initialize(ValidaEstado validaEstado) {
        this.estados = new ArrayList<>();
        this.estados.addAll(Arrays.asList(estadosBrasileiros));
    }

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        return valor == null ? false : estados.contains(valor);
    }
    
}
