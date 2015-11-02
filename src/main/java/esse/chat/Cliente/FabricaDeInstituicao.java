package esse.chat.Cliente;

import esse.chat.modelo.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FabricaDeInstituicao {
    
    private int contador = 0; 
    
    public Instituicao criarInstituicao() {        
        Instituicao ins = new Instituicao();
        ins.setNome("Instituto Federal de Pernambuco");
        ins.setSigla("IFPE");
        ins.setCnpj("76.124.151/0001-13");
        return ins;
//        Instituicao ins = new Instituicao();
//        ins.setNome("UFPB");
//        ins.setCnpj("12.123.344./0001-96");
//        return ins;
    }

    public Endereco criarEndereco() {
        Endereco end = new Endereco();
        end.setLogradouro("Av. Prof. Luis Freire");
        end.setNumero("500");
        end.setComplemento("");
        end.setBairro("Cidade Universitária");
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
        fone.setFone("(81)3321-786"+contador);
        contador++;
        return fone;
//        Fone fone = new Fone();
//        fone.setFone("3322.088"+contador);
//        contador++;
//        return fone;
    }
    
    public Aluno criarAluno(){
        Aluno aluno = new Aluno();
        aluno.setPrimeiroNome("Edgleyson");
        aluno.setUltimoNome("Araujo");
        aluno.setApelido("Edde");
        aluno.setCpf("499.630.904-00");
        aluno.setMatricula("20132Y6-RC0162");
        DateFormat f = DateFormat.getDateInstance();
        try {
            Date data = f.parse("03/12/1968");
            aluno.setNascimento(data);
        } catch (ParseException ex) {
            Logger.getLogger(FabricaDeInstituicao.class.getName()).log(Level.SEVERE, null, ex);
        }
        aluno.setSenha("Ed@68");
        aluno.setSexo("M");
        return aluno;
        
    }
    
    public Curso criarCurso(){
        Curso curso = new Curso();
        curso.setNome("Tecnologia em Análise e Desenvolvimento de Sistemas");
        return curso;
    }
}
