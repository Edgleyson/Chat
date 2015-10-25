package esse.chat.Cliente;

import esse.chat.modelo.Aluno;
import esse.chat.modelo.Email;
import esse.chat.modelo.Endereco;
import esse.chat.modelo.Fone;
import esse.chat.modelo.Instituicao;
import esse.chat.modelo.Usuario;
import esse.chat.persistencia.InstituicaoDAO;
import esse.chat.persistencia.AlunoDAO;



public class Cliente {
    
    public static void main(String[] args) throws Exception {
        FabricaDeInstituicao fabrica = new FabricaDeInstituicao();
        Instituicao instituicao = fabrica.criarInstituicao();
        Endereco endereco = fabrica.criarEndereco();
        Email email = fabrica.criarEmail();
        Email email2 = fabrica.criarEmail2();
        Fone fone1 = fabrica.criarFone();
//        Aluno aluno1 = fabrica.criarAluno();
//        System.out.println(aluno1.getPrimeiroNome());
        Fone fone2 = fabrica.criarFone();
        
        instituicao.setEndereco(endereco);
        instituicao.adicionaEmail(email);
        instituicao.adicionaEmail(email2);
        instituicao.adicionaFone(fone1);
        instituicao.adicionaFone(fone2);
        instituicao.adicionaCurso(null);
        
        
//        Long id = InstituicaoDAO.inserirInstituicao(instituicao);
//        Long newId = AlunoDAO.inserirAluno(aluno1);
       
//        String a = "1";
//        Long id = Long.parseLong(a);
        /*
        Instituicao ins = InstituicaoDAO.pesquisarInstituicao(newId);
        System.out.println(ins.toString());
        
        ins.setNome("UFPE");
        InstituicaoDAO.atualizarInstituicao(ins);
        
        System.out.println(ins.toString());
        
        InstituicaoDAO.deletarInstituicao(ins);
        */
    }
    
    
}
