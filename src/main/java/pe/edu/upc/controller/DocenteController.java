package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Docente;
import pe.edu.upc.service.IDocenteService;

@Controller
@RequestMapping("/docente")
public class DocenteController {
	@Autowired
	IDocenteService docenteService;
	@RequestMapping("/")
	public String irDocente(Map<String, Object> model)
	{
		model.put("listaDocentes", docenteService.listar());
		return "listDocente";
	}
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("docente", new Docente());
		return "docente";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Docente objDocente, BindingResult binRes, Model model,RedirectAttributes redirectAttributes)
			throws ParseException {
		if (binRes.hasErrors()) {
			return "docente";
		}
		else {List<Docente> listaDocentes2;
		listaDocentes2 = docenteService.buscarDni(objDocente.getDniDocente().toString());
		List<Docente>listaDocentes3;
		listaDocentes3 = docenteService.listar();
		List<Docente> listaDocentes4;
		listaDocentes4 = docenteService.findByDocente(objDocente.getDniDocente().toString());
		if(objDocente.getIdDocente()>0)
			{
		for (int i = 0; i<listaDocentes3.size(); i++) {
		if (listaDocentes4.isEmpty())
		{
			docenteService.modificar(objDocente);
			redirectAttributes.addFlashAttribute("info",true);
		 
			return "redirect:/docente/listar";
		}
		else {
			if(( objDocente.getIdDocente()==listaDocentes3.get(i).getIdDocente() && listaDocentes2.isEmpty() )||(objDocente.getIdDocente()==listaDocentes2.get(i).getIdDocente())) 
			
			{
				docenteService.modificar(objDocente);
				redirectAttributes.addFlashAttribute("info",true);
			 
				return "redirect:/docente/listar";
			 
			}
			
			 else
			 {
					redirectAttributes.addFlashAttribute("mensaje",true);//dni
					return "redirect:/docente/irRegistrar";
			 }
		}
		}
			}
		else {
		List<Docente> listaDocentes;
		listaDocentes = docenteService.buscarDni(objDocente.getDniDocente().toString());
		if (listaDocentes.isEmpty()) {
			
			
			boolean flag = docenteService.insertar(objDocente);
			
			if (flag) {
				redirectAttributes.addFlashAttribute("success",true);
				return "redirect:/docente/listar";
			} else {
				redirectAttributes.addFlashAttribute("danger",true);//errors
				return "redirect:/docente/irRegistrar";
			}
		}
			else {
				redirectAttributes.addFlashAttribute("mensaje",true);//dni
				return "redirect:/docente/irRegistrar";
			}
		
			}
		}
		redirectAttributes.addFlashAttribute("danger",true);//errors
		return "redirect:/docente/irRegistrar";

	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Docente objDocente, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/docente/listar";
		} else {
			boolean flag = docenteService.modificar(objDocente);

			if (flag) {
			objRedir.addFlashAttribute("info", true);//actualizar
				return "redirect:/docente/listar";

			} else {
				objRedir.addFlashAttribute("danger",true);//error
				return "redirect:/docente/listar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Docente objDocente = docenteService.listarId(id);
		if (objDocente == null) {
			objRedir.addFlashAttribute("danger",true);//error
			return "redirect:/docente/listar";
		} else {
			objRedir.addFlashAttribute("info",true);
			model.addAttribute("docente", objDocente);
			return "docente";

		}

	}
	
	
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id,RedirectAttributes objRedir) {
		try {
			if (id != null && id > 0) {
				docenteService.eliminar(id);
				model.put("warning",true);//eliminar
				model.put("listaDocentes", docenteService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("danger", true);//error
			model.put("listaDocentes", docenteService.listar());

		}
		return "listDocente";
	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		model.put("listaDocentes", docenteService.listar());
		return "listDocente";
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Docente docente) {
		docenteService.listarId(docente.getIdDocente());
		return "listDocente";
	}
	
	

}
