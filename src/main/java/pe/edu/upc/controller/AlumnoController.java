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

import pe.edu.upc.entity.Alumno;
import pe.edu.upc.service.IAlumnoService;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {
	@Autowired
	IAlumnoService alumnoService;
	@RequestMapping("/bienvenido")
	public String irAlumnoBienvenido() {
		return "bienvenido";
	}	
	
	@RequestMapping("/")
	public String irAlumno(Map<String, Object> model) {
		model.put("listaAlumnos", alumnoService.listar());
		return "listAlumno";
	}
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("alumno", new Alumno());
		return "alumno";
	}
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Alumno objAlumno, BindingResult binRes, Model model,RedirectAttributes redirectAttributes)
			throws ParseException {
		if (binRes.hasErrors()) {
			return "alumno";
		} 
		else {List<Alumno> listaAlumnos2;
		listaAlumnos2 = alumnoService.buscarDni(objAlumno.getDniAlumno().toString());
		List<Alumno>listaAlumnos3;
		listaAlumnos3 = alumnoService.listar();
		List<Alumno> listaAlumnos4;
		listaAlumnos4 = alumnoService.findBydniAlumno(objAlumno.getDniAlumno().toString());
		if(objAlumno.getIdAlumno()>0)
			{
		for (int i = 0; i<listaAlumnos3.size(); i++) {
		if (listaAlumnos4.isEmpty())
		{
			alumnoService.modificar(objAlumno);
			redirectAttributes.addFlashAttribute("info",true);
		 
			return "redirect:/alumno/listar";
		}
		else {
			if(( objAlumno.getIdAlumno()==listaAlumnos3.get(i).getIdAlumno() && listaAlumnos2.isEmpty() )||(objAlumno.getIdAlumno()==listaAlumnos2.get(i).getIdAlumno())) 
			
			{
				alumnoService.modificar(objAlumno);
				redirectAttributes.addFlashAttribute("info",true);
			 
				return "redirect:/alumno/listar";
			 
			}
			
			 else
			 {
					redirectAttributes.addFlashAttribute("mensaje",true);//dni
					return "redirect:/alumno/irRegistrar";
			 }
		}
		}
			}
		else {
		List<Alumno> listaAlumnos;
		listaAlumnos = alumnoService.buscarDni(objAlumno.getDniAlumno().toString());
		if (listaAlumnos.isEmpty()) {
			
			
			boolean flag = alumnoService.insertar(objAlumno);
			
			if (flag) {
				redirectAttributes.addFlashAttribute("success",true);
				return "redirect:/alumno/listar";
			} else {
				redirectAttributes.addFlashAttribute("danger",true);//errors
				return "redirect:/alumno/irRegistrar";
			}
		}
			else {
				redirectAttributes.addFlashAttribute("mensaje",true);//dni
				return "redirect:/alumno/irRegistrar";
			}
		
			}
		}
		redirectAttributes.addFlashAttribute("danger",true);//errors
		return "redirect:/alumno/irRegistrar";

	
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Alumno objAlumno, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/alumno/listar";
		} else {
			boolean flag = alumnoService.modificar(objAlumno);

			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
				return "redirect:/alumno/listar";

			} else {
				objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
				return "redirect:/alumno/listar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Alumno objAlumno = alumnoService.listarId(id);
		if (objAlumno == null) {
			objRedir.addFlashAttribute("danger",true);//error
			return "redirect:/alumno/listar";
		} else {
			objRedir.addFlashAttribute("info",true);
			model.addAttribute("alumno", objAlumno);
			return "alumno";

		}

	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				alumnoService.eliminar(id);
				model.put("warning",true);//eliminar
				model.put("listaAlumnos", alumnoService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("danger", true);//error
			model.put("listaAlumnos", alumnoService.listar());

		}
		return "listAlumno";
	}
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		model.put("listaAlumnos", alumnoService.listar());
		return "listAlumno";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Alumno alumno) {
		alumnoService.listarId(alumno.getIdAlumno());
		return "listAlumno";
	}
	
	
	
	

}
