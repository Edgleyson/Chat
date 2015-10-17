package esse.chat.Cliente;

import esse.chat.modelo.Email;
import esse.chat.modelo.Endereco;
import esse.chat.modelo.Fone;
import esse.chat.modelo.Instituicao;


public class FabricaDeInstituicao {
    
    private int contador = 0; 
    
    public Instituicao criarInstituicao() {        
        Instituicao ins = new Instituicao();
        ins.setNome("IFPE");
        ins.setCnpj("12.345.678./0001-96");
        return ins;
//        Instituicao ins = new Instituicao();
//        ins.setNome("UFPB");
//        ins.setCnpj("12.123.344./0001-96");
//        return ins;
    }

    public Endereco criarEndereco() {
        Endereco end = new Endereco();
        end.setLogradouro("Av. Prof. LuÃ­s Freire");
        end.setNumero("500");
        end.setComplemento("");
        end.setBairro("Cidade UniversitÃ¡ria");
        end.setCep("50740-540");
        end.setCidade("Recife");
        end.setEstado("PE");
        return end;
//        Endereco end = new Endereco();
//        end.setLogradouro("Av. Prof. Morais Rego");
//        end.setNumero("1235");
//        end.setComplemento("");
//        end.setBairro("Cidade UniversitÃ¡ria");
//        end.setCep("50670-901");
//        end.setCidade("Recife");
//        end.setEstado("PE");
//        return end;
    }

    public Email criarEmail() {
        Email email = new Email();
        String str = "ifpe@edu.gov.br";
        email.setEmail(str);
        return email;
//        Email email = new Email();
//        String str = "ifpe@edu.gov.br";
//        email.setEmail(str);
//        return email;
    }
    public Email criarEmail2() {
        Email email = new Email();
        String str = "reitoriaifpe@edu.gov.br";
        email.setEmail(str);
        return email;
//        Email email = new Email();
//        String str = "ifpereitoria@edu.gov.br";
//        email.setEmail(str);
//        return email;
    }

    public Fone criarFone() {
        Fone fone = new Fone();
        fone.setFone("3321.786"+contador);
        contador++;
        return fone;
//        Fone fone = new Fone();
//        fone.setFone("3322.088"+contador);
//        contador++;
//        return fone;
    }
}
