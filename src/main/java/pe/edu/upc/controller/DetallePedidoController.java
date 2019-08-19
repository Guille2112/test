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

import pe.edu.upc.entity.DetallePedido;
import pe.edu.upc.service.IDetallePedidoService;
import pe.edu.upc.service.IReservationService;
import pe.edu.upc.service.IRoomService;

@Controller
@RequestMapping("/detallePedido")
public class DetallePedidoController{

	
	@Autowired
	private IDetallePedidoService rService;

	@Autowired
	private IReservationService reService;

	@Autowired
	private IRoomService roService;
	
	@RequestMapping("/bienvenido")
	public String irPedidoBienvenido() {
		return "bienvenido";
	}

	@RequestMapping("/")
	public String irPedido(Map<String, Object> model) {
		model.put("listaPedidos", rService.listar());
		return "listPedido";
	}

	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {

		model.addAttribute("detallePedido", new DetallePedido());
		model.addAttribute("listaReservations", reService.listar());
		model.addAttribute("listaRooms", roService.listar());
		return "detallePedido";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid DetallePedido objPedido, BindingResult binRes, Model model)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaReservations", reService.listar());
			model.addAttribute("listaRooms", roService.listar());
			return "detallePedido";
		} else {
			boolean flag = rService.insertar(objPedido);
			if (flag) {
				return "redirect:/reservation/detalles/" + objPedido.getReservation().getIdReservation();
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/detallePedido/irRegistrar";
			}
		}
	}

	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid DetallePedido objPedido, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaReservations", reService.listar());
			model.addAttribute("listaRooms", roService.listar());
			return "redirect:/detallePedido/listar";
		} else {
			boolean flag = rService.modificar(objPedido);

			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
				return "redirect:/detallePedido/listar";

			} else {
				objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
				return "redirect:/detallePedido/listar";
			}
		}
	}

	// El update
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		DetallePedido objPedido = rService.listarId(id);
		model.addAttribute("listaReservations", reService.listar());
		model.addAttribute("listaRooms", roService.listar());
		if (objPedido == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/reservation/listar";
		} else {
			model.addAttribute("detallePedido", objPedido);
			return "detallePedido";

		}

	}

	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				rService.eliminar(id);
				model.put("listaDetalles", rService.listarId(id));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar una Variable asignada");
			model.put("listaDetalles", rService.listarId(id));

		}
		return "redirect:/reservation/detalles/" + id;
	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		model.put("listaPedidos", rService.listar());
		return "listPedido";

	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute DetallePedido detallePedido) {

		rService.listarId(detallePedido.getIdDetallePedido());
		return "listPedido";

	}



	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {

		model.addAttribute("detallePedido", new DetallePedido());
		return "buscarReservation";

	}

}
