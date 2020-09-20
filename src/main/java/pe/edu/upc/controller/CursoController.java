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

import pe.edu.upc.entity.Curso;
import pe.edu.upc.service.ICursoService;

@Controller
@RequestMapping("/cursooo")
public class CursoController {
	@Autowired
	ICursoService cursoService;
	@RequestMapping("/")
	public String irCurso(Map<String, Object> model) {
		model.put("listaCursos", cursoService.listar());
		return "listCurso";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("curso", new Curso());
		return "curso";
	}
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Curso objCurso, BindingResult binRes, Model model,	RedirectAttributes redirectAttributes)
			throws ParseException {
		if (binRes.hasErrors()) {
			return "curso";
		} else {List<Curso> listaCursos2;
		listaCursos2 = cursoService.buscarNombre(objCurso.getNameCurso().toString());
		List<Curso>listaCursos3;
		listaCursos3 = cursoService.listar();
		List<Curso>listaCursos4;
		listaCursos4 = cursoService.findByCurso(objCurso.getNameCurso().toString());
		if(objCurso.getIdCurso()>0)
			{
		for (int i = 0; i<listaCursos3.size(); i++) {
		if (listaCursos4.isEmpty())
		{
			cursoService.modificar(objCurso);
			redirectAttributes.addFlashAttribute("info",true);
		 
			return "redirect:/curso/listar";
		}
		else {
			if(( objCurso.getIdCurso()==listaCursos3.get(i).getIdCurso() && listaCursos2.isEmpty() )||(objCurso.getIdCurso()==listaCursos2.get(i).getIdCurso())) 
			
			{
				cursoService.modificar(objCurso);
				redirectAttributes.addFlashAttribute("info",true);
			 
				return "redirect:/curso/listar";
			 
			}
			
			 else
			 {
					redirectAttributes.addFlashAttribute("mensaje",true);//dni
					return "redirect:/curso/irRegistrar";
			 }
		}
		}
			}
		else {
		List<Curso> listaCursos;
		listaCursos = cursoService.buscarNombre(objCurso.getNameCurso().toString());
		if (listaCursos.isEmpty()) {
			
			
			boolean flag = cursoService.insertar(objCurso);
			
			if (flag) {
				redirectAttributes.addFlashAttribute("success",true);
				return "redirect:/curso/listar";
			} else {
				redirectAttributes.addFlashAttribute("danger",true);//errors
				return "redirect:/curso/irRegistrar";
			}
		}
			else {
				redirectAttributes.addFlashAttribute("mensaje",true);//dni
				return "redirect:/curso/irRegistrar";
			}
		
			}
		}
		redirectAttributes.addFlashAttribute("danger",true);//errors
		return "redirect:/curso/irRegistrar";

	
	}
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Curso objCurso, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/curso/listar";
		} else {
			boolean flag = cursoService.modificar(objCurso);

			if (flag) {
				objRedir.addFlashAttribute("info", true);//actualizar
				return "redirect:/curso/listar";

			} else {
				objRedir.addFlashAttribute("danger",true);//error
				return "redirect:/curso/listar";
			}
		}
	}
	
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Curso objCurso = cursoService.listarId(id);
		if (objCurso == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/curso/listar";
		} else {
			objRedir.addFlashAttribute("info",true);
			model.addAttribute("curso", objCurso);
			return "curso";

		}

	}
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id, RedirectAttributes objRedir) {
		try {
			if (id != null && id > 0) {
				cursoService.eliminar(id);
				model.put("warning",true);//eliminar
				model.put("listaCursos", cursoService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("danger", true);//error
			model.put("listaCursos", cursoService.listar());

		}
		return "listCurso";
	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		model.put("listaCursos", cursoService.listar());
		return "listCurso";
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Curso curso) {
		cursoService.listarId(curso.getIdCurso());
		return "listCurso";
	}
	

}
