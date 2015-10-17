package esse.chat.controle;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target( {ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidadorApelido.class)
@Documented
public @interface ValidaApelido {
    String message() default "{esse.chat.modelo.Usuario.apelido}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

