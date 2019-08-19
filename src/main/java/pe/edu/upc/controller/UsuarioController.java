
package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.edu.upc.entity.Usuario;

import pe.edu.upc.service.IUsuarioService;

@Controller
@RequestMapping("/user")
public class UsuarioController {

	@Autowired
	private IUsuarioService uService;

	@RequestMapping("/")
	public String irUsuario(Map<String, Object> model) {
		model.put("listaUsuario", uService.listar());
		return "listUsuario";
	}

	@RequestMapping("/goRegister")
	public String irRegistrar(Model model) {

		model.addAttribute("usuario", new Usuario());
		return "usuario";
	}

	@RequestMapping("/register")
	public String registrar(Map<String, Object> model, @ModelAttribute @Valid Usuario usuario, BindingResult binRes)
			throws ParseException {
		if (binRes.hasErrors()) {
		
			return "usuario";
		} else {
			if (usuario.getId() > 0) {
				uService.modificar(usuario);
			} else {
				uService.insertar(usuario);
			}
			model.put("listaUsuario", uService.listar());
			model.put("Mensaje", "Error!!");
			return "listUsuario";
		}
	}



	@RequestMapping("/modify")
	public String modificar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		Usuario usuario = uService.listarId(id);

		model.put("usuario", usuario);
		return "usuario";
	}

	@RequestMapping("/remove")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				uService.eliminar(id);
				model.put("listaUsuario", uService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("Error", "asociada");
			model.put("listaUsuario", uService.listar());

		}
		return "listUsuario";
	}

	@RequestMapping("/list")
	public String listar(Map<String, Object> model) {

		model.put("listaUsuario", uService.listar());
		return "listUsuario";
	}

	@RequestMapping("/listId")
	public String listarId(Map<String, Object> model, @ModelAttribute Usuario usuario) {
		uService.listarId(usuario.getId());
		return "listUsuario";
	}
	
}
