package pe.edu.upc.controller;

import java.text.ParseException;
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
import pe.edu.upc.entity.Matricula;
import pe.edu.upc.service.IAlumnoService;
import pe.edu.upc.service.IMatriculaService;
import pe.edu.upc.service.ISeccionService;

@Controller
@RequestMapping("/vermatricula")
public class VermatriculaController {
	@Autowired
	IMatriculaService matriculaService;
	@Autowired
	IAlumnoService alumnoService;
	@Autowired
	ISeccionService seccionService;
	@RequestMapping("/")
	public String irMatricula(Map<String, Object> model) {
		model.put("listaMatriculas", matriculaService.listar());
		return "listvermatricula";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("vermatricula", new Matricula());
		model.addAttribute("listaAlumnos", alumnoService.listar());
		model.addAttribute("listaSecciones", seccionService.listar());
		return "vermatricula";
	}
	
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Matricula objMatricula, BindingResult binRes, Model model,RedirectAttributes redirectAttributes)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaAlumnos", alumnoService.listar());
			model.addAttribute("listaSecciones", seccionService.listar());
			return "vermatricula";
		} else {
			if(objMatricula.getIdMatricula()>0) {matriculaService.modificar(objMatricula);}
			else {
			boolean flag = matriculaService.insertar(objMatricula);
			if (flag) {
				redirectAttributes.addFlashAttribute("success",true);
				return "redirect:/vermatricula/listar";
			} else {
				redirectAttributes.addFlashAttribute("danger",true);//error
				return "redirect:/vermatricula/irRegistrar";
			}
			}
			redirectAttributes.addFlashAttribute("info",true);
			return "redirect:/vermatricula/listar";
		}
		
	}
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Matricula objMatricula, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/vermatricula/listar";
		} 
		else {
			boolean flag = matriculaService.modificar(objMatricula);

			if (flag) {
				objRedir.addFlashAttribute("info", true);//actualizar
				return "redirect:/vermatricula/listar";

			} else {
				objRedir.addFlashAttribute("danger",true);//error
				return "redirect:/vermatricula/listar";
			}
		}
	}
	
	// El update

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Matricula objMatricula = matriculaService.listarId(id);
		if (objMatricula == null) {
			objRedir.addFlashAttribute("danger",true);//error
			return "redirect:/vermatricula/listar";
		} else {
			objRedir.addFlashAttribute("info",true);
			model.addAttribute("listaAlumnos", alumnoService.listar());
			model.addAttribute("listaSecciones", seccionService.listar());
			model.addAttribute("vermatricula", objMatricula);
			
			return "vermatricula";

		}

	}


	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id,RedirectAttributes objRedir) {
		try {
			if (id != null && id > 0) {
				matriculaService.eliminar(id);
				model.put("warning",true);//eliminar
				model.put("listaMatriculas", matriculaService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("danger", true);//error
			
			model.put("listaMatriculas", matriculaService.listar());

		}
		return "listvermatricula";
	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		model.put("listaMatriculas", matriculaService.listar());
		return "listvermatricula";

	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Matricula matricula) {

		matriculaService.listarId(matricula.getIdMatricula());
		return "listvermatricula";

	}
	
	
	
	

}
