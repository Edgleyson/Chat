package esse.chat.Cliente;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexTeste {

    public static void main(String[] args) throws Exception {
        //Regex para telefone
//        String padrao = "\\([1-9]{2}?\\)[2-9][0-9]{3,4}?\\-[0-9]{4}?";
//        String str = "(81)3226-7718";

        String padrao = "\\p{Upper}{1}\\p{Lower}{2,}\\d*";
        String str = "Ede Maia";

        Pattern pattern = Pattern.compile(padrao);
        Matcher m2 = pattern.matcher(str);

        if (m2.find()) {
            System.out.println("Validado");
        } else {
            System.out.println("Invalidado");
        }
    }

}
