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

import pe.edu.upc.entity.Seccion;
import pe.edu.upc.service.IDocenteService;
import pe.edu.upc.service.ISeccionService;

@Controller
@RequestMapping("/curso")
public class SeccionController {
	
	
	@Autowired
	ISeccionService seccionService;
	@Autowired
	IDocenteService docenteService;
	@RequestMapping("/")
	public String irSeccion(Map<String, Object> model) {
		
		model.put("listaSecciones", seccionService.listar());
		return "listSeccion";
	}

	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaDocentes", docenteService.listar());
		model.addAttribute("seccion", new Seccion());
	
		return "seccion";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Seccion objSeccion, BindingResult binRes, Model model,RedirectAttributes redirectAttributes)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaDocentes", docenteService.listar());
			return "seccion";
		}
		else {		List<Seccion> listaSecciones2;
		listaSecciones2 = seccionService.buscarNombre(objSeccion.getNameSeccion().toString());
		List<Seccion>listaSecciones3;
		listaSecciones3 = seccionService.listar();
		List<Seccion> listaSecciones4;
		listaSecciones4 = seccionService.findBySeccion(objSeccion.getNameSeccion().toString());
	
		if(objSeccion.getIdSeccion()>0)
		{
	for (int i = 0; i<listaSecciones3.size(); i++) {
	if (listaSecciones4.isEmpty())
	{
		seccionService.modificar(objSeccion);
		redirectAttributes.addFlashAttribute("info",true);
	 
		return "redirect:/curso/listar";
	}
	else {
		if(( objSeccion.getIdSeccion()==listaSecciones3.get(i).getIdSeccion() && listaSecciones2.isEmpty() )||(objSeccion.getIdSeccion())==listaSecciones2.get(i).getIdSeccion()) 
		
		{
			seccionService.modificar(objSeccion);
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
		List<Seccion> listaSecciones;
		listaSecciones = seccionService.buscarNombre(objSeccion.getNameSeccion().toString());
	
	if (listaSecciones.isEmpty()) {
		
		
		boolean flag = seccionService.insertar(objSeccion);
		
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
	public String actualizar(@ModelAttribute @Valid Seccion objSeccion, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/curso/listar";
		} else {
			boolean flag = seccionService.modificar(objSeccion);

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
		Seccion objSeccion = seccionService.listarId(id);
		if (objSeccion == null) {
			objRedir.addFlashAttribute("danger",true);//error
			return "redirect:/curso/listar";
		} else {
			model.addAttribute("listaDocentes", docenteService.listar());
			objRedir.addFlashAttribute("info",true);
			model.addAttribute("seccion", objSeccion);
			return "seccion";

		}

	}
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				seccionService.eliminar(id);
				model.put("warning",true);//eliminar
				model.put("listaSecciones", seccionService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("danger", true);//error
			model.put("listaSecciones", seccionService.listar());
		
		}
		return "listSeccion";
	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		model.put("listaSecciones", seccionService.listar());
		return "listSeccion";
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Seccion seccion) {
		seccionService.listarId(seccion.getIdSeccion());
		return "listSeccion";
	}
	
	
	
	
	
	
}
