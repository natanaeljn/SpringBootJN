package projeto.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import projeto.springboot.model.Pessoa;
import projeto.springboot.model.Telefone;
import projeto.springboot.repository.PessoaRepository;
import projeto.springboot.repository.TelefoneRepository;

@Controller
public class TelefoneController {

	@Autowired
	private  PessoaRepository pessoaRepository;
	@Autowired
	private  TelefoneRepository telefoneRepository;
	
	@PostMapping("**/addfonepessoa/{pessoaid}")
	public ModelAndView addFonePessoa(Telefone telefone,@PathVariable("pessoaid")Long pessoaid) {
		Pessoa pessoa = pessoaRepository.findById(pessoaid).get();
		telefone.setPessoa(pessoa);
	    telefoneRepository.save(telefone);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/telefones");
		modelAndView.addObject("pessoaobj",pessoa);
		modelAndView.addObject("telefones",telefoneRepository.getTelefones(pessoaid));
		return modelAndView;
	}
	@RequestMapping(method = RequestMethod.GET, value = "/excluirtelefone/{idtelefone}")
	public ModelAndView excluirPessoa(@PathVariable("idtelefone")Long idtelefone) {
		Pessoa pessoa = telefoneRepository.findById(idtelefone).get().getPessoa();
		telefoneRepository.deleteById(idtelefone);
		ModelAndView view = new ModelAndView("cadastro/telefones");
		view.addObject("pessoaobj",pessoa);
		view.addObject("telefones",telefoneRepository.getTelefones(pessoa.getId()));
		return view;
		
	}
	
	
}
