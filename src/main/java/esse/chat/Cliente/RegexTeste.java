package esse.chat.Cliente;

import esse.chat.modelo.Email;
import esse.chat.modelo.Endereco;
import esse.chat.modelo.Fone;
import esse.chat.modelo.Instituicao;
import esse.chat.persistencia.InstituicaoDAO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexTeste {

    public static void main(String[] args) throws Exception {

//        String padrao = "\\([1-9]{2}?\\)[2-9][0-9]{3,4}?\\-[0-9]{4}?";
//
//        Pattern pattern = Pattern.compile(padrao);
//        String str = "(81)3226-7718";
        String padrao = "[A-Z]{1}[a-z]{2,}[0-9]*";

        Pattern pattern = Pattern.compile(padrao);
        String str = "Ederm2345";

        Matcher m2 = pattern.matcher(str);

        if (m2.find()) {
            System.out.println("Validado");
        } else {
            System.out.println("Invalidado");
        }
    }

}
