package esse.chat.controle;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidadorApelido implements ConstraintValidator<ValidaEstado, String> {

    private Pattern pattern = 
            Pattern.compile("[A-Z]{1}[a-z]{2,} || [A-Z]{1}[a-z]{2,}[0-9]*");
    //O apelido inicia com uma letra maiúscula seguida de duas ou mais letras minúsculas
    //Ex.: Ede - Edlas
    //mas também pode admitir zero ou mais digitos no final. Ex: Edlas0000
    
    @Override
    public void initialize(ValidaEstado constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        Matcher m = pattern.matcher(valor);
        return m.matches();
    }
    
}
