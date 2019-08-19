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

import pe.edu.upc.entity.Room;
import pe.edu.upc.service.IRoomService;



@Controller
@RequestMapping("/room")
public class RoomController {


	@Autowired
	private IRoomService rService;
	
	
	@RequestMapping("/")
	public String irRoom(Map<String, Object> model) {
		model.put("listaRooms", rService.listar());
		return "listRoom";
	}

	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {

		model.addAttribute("room", new Room());
		return "room";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Room objRoom, BindingResult binRes, Model model)
			throws ParseException {
		if (binRes.hasErrors()) {
			return "room";
		} else {
			boolean flag = rService.insertar(objRoom);
			if (flag) {
				return "redirect:/room/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/room/irRegistrar";
			}
		}
	}

	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Room objRoom, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/room/listar";
		} else {
			boolean flag = rService.modificar(objRoom);

			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
				return "redirect:/room/listar";

			} else {
				objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
				return "redirect:/room/listar";
			}
		}
	}

	// El update
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Room objRoom = rService.listarId(id);
		if (objRoom == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/reservation/listar";
		} else {
			model.addAttribute("room", objRoom);
			return "room";

		}

	}

	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				rService.eliminar(id);
				model.put("listaRooms", rService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar una Raza asignada");
			model.put("listaRooms", rService.listar());

		}
		return "listRoom";
	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		model.put("listaRooms", rService.listar());
		return "listRoom";

	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Room room) {

		rService.listarId(room.getIdRoom());
		return "listRoom";

	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Room room) throws ParseException {

		List<Room> listaRooms;

		room.setNameRoom(room.getNameRoom());
		listaRooms = rService.buscarNombre(room.getNameRoom());

		if (listaRooms.isEmpty()) {

			model.put("mensaje", "No se encontró");
		}

		model.put("listaRooms", listaRooms);
		return "buscarRoom";

	}

	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {

		model.addAttribute("room", new Room());
		return "buscarRoom";

	}
}
