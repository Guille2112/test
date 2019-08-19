package pe.edu.upc.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Persona;

import pe.edu.upc.service.ICityService;
import pe.edu.upc.service.IPersonaService;
import pe.edu.upc.service.IUploadFileService;

@Controller
@RequestMapping("/persona")

public class PersonaController {

	@Autowired
	private IPersonaService pService;
	@Autowired
	private ICityService psService;
	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	// METODO PARA VER EL DETALLE PERSONA
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		// Obtenemos las mascota por el ID
		Persona persona = pService.listarId(id);

		// Validamos el perro
		if (persona == null) {
			flash.addFlashAttribute("error", "La persona no existe en la base de datos");
			return "redirect:/listPersona";
		}

		model.put("persona", persona);
		model.put("titulo", "Detalle de la persona: " + persona.getNamePersona());

		return "persona/ver";
	}

	@RequestMapping("/")
	public String irPersona(Map<String, Object> model) {
		model.put("listaPersonas", pService.listar());
		return "listPersona";
	}
	@Secured(" hasRole('ROLE_ADMIN')or hasRole('ROLE_RECEPTIONIST')")
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("persona", new Persona());
		model.addAttribute("listaCitys", psService.listar());
		
		return "persona";

	}

	@Secured(" hasRole('ROLE_ADMIN')or hasRole('ROLE_RECEPTIONIST')")
	@RequestMapping("/registrar")
	public String registrar( @Valid Persona objPersona, BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaCitys", psService.listar());
			return "persona";
		} 
			// si el campo foto No esta vacio
			if (!foto.isEmpty()) {

				if (objPersona.getIdPersona() > 0 && objPersona.getFoto() != null
						&& objPersona.getFoto().length() > 0) {

					uploadFileService.delete(objPersona.getFoto());
				}

				////
				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				// pasar el nombre de la Foto al Empleado
				objPersona.setFoto(uniqueFilename);
			}
		

		boolean flag = pService.insertar(objPersona);
		if (flag) {
			return "redirect:/persona/listar";
		} else {
			model.addAttribute("mensaje", "Ocurrió un error");
			return "redirect:/persona/irRegistrar";
		}

	}

	@RequestMapping(value = "/verPersona/{id}")
	public String ver(@PathVariable int id, Map<String, Object> model, RedirectAttributes flash) {

		// Obtenemos el Empleado por el ID
		Persona persona = pService.buscarPorId(id);

		// Validamos el pet
		if (persona == null) {
			flash.addFlashAttribute("error", "La persona no existe en la base de datos");
			return "redirect:/persona/listar";
		}

		model.put("persona", persona);
		model.put("titulo", "Detalle de persona: " + persona.getNamePersona());

		return "/verPersona";
	}
	@Secured(" hasRole('ROLE_ADMIN')or hasRole('ROLE_RECEPTIONIST')")
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Persona objPersona, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/persona/listar";
		} else {
			boolean flag = pService.modificar(objPersona);

			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
				return "redirect:/persona/listar";

			} else {
				objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
				return "redirect:/persona/listar";
			}
		}
	}
	@Secured(" hasRole('ROLE_ADMIN')or hasRole('ROLE_RECEPTIONIST')")
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Persona objPersona = pService.buscarPorId(id);
		if (objPersona == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/persona/listar";
		} else {
			model.addAttribute("listaCitys", psService.listar());
			model.addAttribute("persona", objPersona);
			return "persona";

		}
	}
	@Secured(" hasRole('ROLE_ADMIN')or hasRole('ROLE_RECEPTIONIST')")
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id,
			RedirectAttributes flash) {
		try {
			Persona persona = pService.listarId(id);
			if (uploadFileService.delete(persona.getFoto())) {
				pService.eliminar(id);
				{
					flash.addFlashAttribute("info", "Foto" + persona.getFoto() + "eliminada con exito");
				}
				model.put("listaPersonas", pService.listar());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar una Persona si está asignada");
			model.put("listaPersonas", pService.listar());
		}
		return "listPersona";
	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		model.put("listaPersonas", pService.listar());
		return "listPersona";

	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Persona persona) {

		pService.listarId(persona.getIdPersona());
		return "listPersona";

	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Persona persona) throws ParseException {

		List<Persona> listaPersonas;

		if (StringUtils.isNumeric(persona.getDniPersona())) {

			listaPersonas = pService.findByDniPersona(persona.getDniPersona());

		} else {

			persona.setNamePersona(persona.getDniPersona());
			listaPersonas = pService.buscarNombre(persona.getNamePersona());

			if (listaPersonas.isEmpty()) {

				persona.setEmailPersona(persona.getDniPersona());
				listaPersonas = pService.buscarEmail(persona.getEmailPersona());

			}

			if (listaPersonas.isEmpty()) {
				try {

					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

					persona.setBirthDatePersona(sdf.parse(persona.getDniPersona()));
					listaPersonas = pService.findByBirthDatePersona(persona.getBirthDatePersona());

				} catch (Exception e) {
					model.put("mensaje", "Formato incorrecto!!");

				}
			}

		}

		if (listaPersonas.isEmpty()) {

			model.put("mensaje", "No se encontró");
		}

		model.put("listaPersonas", listaPersonas);
		return "buscarPersona";

	}

	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {

		model.addAttribute("persona", new Persona());
		return "buscarPersona";

	}

}
