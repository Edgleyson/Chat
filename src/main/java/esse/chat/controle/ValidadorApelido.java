package esse.chat.controle;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidadorApelido implements ConstraintValidator<ValidaApelido, String> {

    String padrao = "[A-Z]{1}[a-z]{2,}[0-9]*";

    private Pattern pattern = Pattern.compile(padrao);
    //O apelido inicia com uma letra maiúscula seguida de duas ou mais letras minúsculas
    //Ex.: Ede - Edlas
    //mas também pode admitir digito(s) no final. Ex: Edlas0000
    
    @Override
    public void initialize(ValidaApelido constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        Matcher m = pattern.matcher(valor);
        return m.matches();
    }
    
}
