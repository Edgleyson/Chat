package esse.chat.controle;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ValidadorFone implements ConstraintValidator<ValidaFone, String> {
    
    String padrao = "\\([1-9]{2}?\\)[2-9][0-9]{3,4}?\\-[0-9]{4}?";
    
    private Pattern pattern = Pattern.compile(padrao);
    //Formato DDD entre parentesis + número do telefone de 8 ou 9 digitos com hífen
    //DDD: dois digítos entre 1 e 9 (não existe DDD iniciado por zero)
    //Primeiro número do telefone entre 2 e 9 (não há números iniciado por zero ou um)
    //Os outros sete ou oito dígitos podem ser entre zero e nove

    @Override
    public void initialize(ValidaFone constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        Matcher m = pattern.matcher(valor);
        return m.matches();
    }
    
}
