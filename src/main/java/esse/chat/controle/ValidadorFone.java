package esse.chat.controle;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ValidadorFone implements ConstraintValidator<ValidaFone, String> {
    
    private Pattern pattern = 
            Pattern.compile("([1-9]{2}) [2-9][0-9]{3,4}-[0-9]{4}");
    //Formato DDD seguido de hifen + número do telefone com 8 ou 9 digitos
    //DDD: dois digítos entre 1 e 9 (não existe DDD iniciado por zero)
    //Primeiro número do telefone entre 2 e 9 (não há números iniciado por zero ou um)
    //Os outro sete ou oito dígitos podem ser entre zero e nove

    @Override
    public void initialize(ValidaFone constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        Matcher m = pattern.matcher(valor);
        return m.matches();
    }
    
}
