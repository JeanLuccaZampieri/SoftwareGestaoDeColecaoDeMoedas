package coin.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import coin.entity.PessoaEntity;
import coin.repository.PessoaRepository;

public class PessoaService {
	private PessoaRepository pessoaRepository;

	public PessoaService() {
		pessoaRepository = new PessoaRepository();
	}

	public PessoaEntity salvar(PessoaEntity pessoaEntity) {
		if (pessoaEntity.getId() == null) {
				pessoaRepository.inserir(pessoaEntity);
		} else {
			pessoaRepository.atualizar(pessoaEntity);
		}
		return pessoaEntity;
	}
	
	public List<PessoaEntity> listar() {
		return pessoaRepository.listar();
	}

	public void remover(PessoaEntity pessoaEntity) {
		pessoaRepository.remover(pessoaEntity);
	}

	public void remover(Long id) {
		pessoaRepository.remover(id);
	}
	public PessoaEntity pesquisaNome(String nome) {
		PessoaEntity pessoa = pessoaRepository.pesquisaPeloNome(nome);
		return pessoa;
	}
	
	public PessoaEntity pesquisaId(Long id) {
		PessoaEntity pessoa = pessoaRepository.pesquisaPeloId(id);
		return pessoa;
	}
	
	public boolean validaCpf (PessoaEntity pessoaEntity) {
		PessoaEntity entity = new PessoaEntity();
		 entity = pessoaRepository.pesquisaPeloCpf(pessoaEntity.getCpf());
		if (entity == null) {
			return true;
		} else {
			return false;
		}
	}	
	public boolean validaEmail (PessoaEntity pessoaEntity) {
		PessoaEntity entity = new PessoaEntity();
		 entity = pessoaRepository.pesquisaPeloEmail(pessoaEntity.getEmail());
		if (entity == null) {
			return true;
		} else {
			return false;
		}
	}	
	public boolean validaTelefone (PessoaEntity pessoaEntity) {
		PessoaEntity entity = new PessoaEntity();
		 entity = pessoaRepository.pesquisaPeloTelefone(pessoaEntity.getTelefone());
		if (entity == null) {
			return true;
		} else {
			return false;
		}
	}
	public boolean validaNome (PessoaEntity pessoaEntity) {
		PessoaEntity entity = new PessoaEntity();
		 entity = pessoaRepository.pesquisaPeloNome(pessoaEntity.getNome());
		if (entity == null) {
			return true;
		} else {
			return false;
		}
	}
	public byte validacao(PessoaEntity pessoaEntity) {
		if(validaNome(pessoaEntity)== false) {
			return 0;
		}
		if(validaCpf(pessoaEntity) == false) {
			return 1;
		}
		else if (validaEmail(pessoaEntity)== false) {
			return 2;
		}
		else if(validaTelefone(pessoaEntity) == false) {
			return 3;
		}
		else {
			return 4;
		}
	}
	public PessoaEntity pesquisaEmail(String email) {
		PessoaEntity pessoa = pessoaRepository.pesquisaPeloEmail(email);
		return pessoa;
	}
	public List<PessoaEntity> pesquisaSenha(String senha) {
		List<PessoaEntity> pessoa = pessoaRepository.pesquisaPelaSenha(senha);
		return pessoa;
	}
	public boolean verificarFormatoEmail(String email) {
        String padrao = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(padrao);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
	public boolean verificarFormatoCPF(String cpf) {
        // Verificar se o CPF possui 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verificar se todos os dígitos são iguais
        boolean todosDigitosIguais = cpf.matches("(\\d)\\1{10}");
        if (todosDigitosIguais) {
            return false;
        }

        // Validar o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int resto = (soma * 10) % 11;
        if (resto == 10) {
            resto = 0;
        }
        if (resto != Character.getNumericValue(cpf.charAt(9))) {
        	JOptionPane.showMessageDialog(null, "O CPF digitado é inválido!");
            return false;
        }

        // Validar o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        resto = (soma * 10) % 11;
        if (resto == 10) {
            resto = 0;
        }
        if (resto != Character.getNumericValue(cpf.charAt(10))) {
            return false;
        }

        // CPF válido
        return true;
    }
}
