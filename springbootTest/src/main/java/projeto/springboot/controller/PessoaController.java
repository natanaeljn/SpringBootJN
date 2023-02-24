package projeto.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import projeto.springboot.model.Pessoa;
import projeto.springboot.repository.PessoaRepository;
import projeto.springboot.repository.TelefoneRepository;

@Controller
public class PessoaController {
	
	@Autowired
	private  PessoaRepository pessoaRepository;
	@Autowired
	private  TelefoneRepository telefoneRepository;
	

	@RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
	public ModelAndView inicio() {
		ModelAndView view = new ModelAndView("cadastro/cadastropessoa");
		view.addObject("pessoaobj",new Pessoa());
		Iterable<Pessoa>pessoasIt = pessoaRepository.findAll();
		view.addObject("pessoas", pessoasIt);
		
		return view;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "**/salvarpessoa")
	public ModelAndView salvar(@Valid Pessoa pessoa,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			ModelAndView view = new ModelAndView("cadastro/cadastropessoa");
			Iterable<Pessoa>pessoasIt = pessoaRepository.findAll();
			view.addObject("pessoas", pessoasIt);
			view.addObject("pessoaobj",pessoa);
			List<String>msg = new ArrayList<String>();
			for(ObjectError error : bindingResult.getAllErrors()) {
				msg.add(error.getDefaultMessage());
			}
			view.addObject("msg",msg);
			return view;
		}
		
		pessoaRepository.save(pessoa);
		
		ModelAndView view = new ModelAndView("cadastro/cadastropessoa");
		Iterable<Pessoa>pessoasIt = pessoaRepository.findAll();
		view.addObject("pessoas", pessoasIt);
		view.addObject("pessoaobj",new Pessoa());
		
	    return view;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listapessoas")
	public ModelAndView listaPessoas() {
		ModelAndView view = new ModelAndView("cadastro/cadastropessoa");
		Iterable<Pessoa>pessoasIt = pessoaRepository.findAll();
		view.addObject("pessoas", pessoasIt);
		view.addObject("pessoaobj",new Pessoa());
		return view;
		
	}
	@RequestMapping(method = RequestMethod.GET, value = "/editarpessoa/{idpessoa}")
	public ModelAndView editarPessoa(@PathVariable("idpessoa")Long idpessoa) {
		ModelAndView view = new ModelAndView("cadastro/cadastropessoa");
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
		view.addObject("pessoaobj" , pessoa.get());
		return view;
		
	}
	@RequestMapping(method = RequestMethod.GET, value = "/excluirpessoa/{idpessoa}")
	public ModelAndView excluirPessoa(@PathVariable("idpessoa")Long idpessoa) {
		pessoaRepository.deleteById(idpessoa);
		ModelAndView view = new ModelAndView("cadastro/cadastropessoa");
		view.addObject("pessoas",pessoaRepository.findAll());
		view.addObject("pessoaobj",new Pessoa());
		return view;
		
	}
	@PostMapping("**/pesquisarpessoa")
	public ModelAndView pesquisarPorNome(@RequestParam("nomepesquisa") String nomepesquisa) {
		
		ModelAndView view = new ModelAndView("cadastro/cadastropessoa");
		view.addObject("pessoas",pessoaRepository.buscarPessoaPorNome(nomepesquisa));
		view.addObject("pessoaobj",new Pessoa());
		return view;
		
	}
	@RequestMapping(method = RequestMethod.GET, value = "/telefones/{idpessoa}")
	public ModelAndView Telefones(@PathVariable("idpessoa")Long idpessoa) {
		ModelAndView view = new ModelAndView("cadastro/telefones");
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
		view.addObject("pessoaobj" , pessoa.get());
	    view.addObject("telefones",telefoneRepository.getTelefones(idpessoa));
		return view;
		
	}
	
}
