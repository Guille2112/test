package pe.edu.upc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.DetallePedido;
import pe.edu.upc.entity.Reservation;
import pe.edu.upc.service.IPersonaService;
import pe.edu.upc.service.IReservationService;
import pe.edu.upc.service.IRoomService;



@Controller
@RequestMapping("/reservation")
public class ReservationController {
	@Autowired
	private IReservationService reService;
	@Autowired
	private IRoomService roService;
	@Autowired
	private IPersonaService peService;
	
	
	@RequestMapping("/")
	public String irReservation(Map<String, Object> model) {
		model.put("listaReservations", reService.listar());
	
		return "listReservation";
	}
	@Secured(" hasRole('ROLE_ADMIN')or hasRole('ROLE_RECEPTIONIST')")
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaRooms", roService.listar());
		model.addAttribute("listaPersonas", peService.listar());
		model.addAttribute("reservation", new Reservation());
		return "reservation";
	}
	@Secured(" hasRole('ROLE_ADMIN')or hasRole('ROLE_RECEPTIONIST')")
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Reservation objReservation, BindingResult binRes, Model model)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaRooms", roService.listar());
			model.addAttribute("listaPersonas", peService.listar());
			return "reservation";
		} else {
			boolean flag = reService.insertar(objReservation);
			if (flag) {
				return "redirect:/reservation/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/reservation/irRegistrar";
			}
		}
	}
	@Secured(" hasRole('ROLE_ADMIN')or hasRole('ROLE_RECEPTIONIST')")
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Reservation objReservation, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/reservation/listar";
		} else {
			boolean flag = reService.modificar(objReservation);

			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
				return "redirect:/reservation/listar";

			} else {
				objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
				return "redirect:/reservation/listar";
			}
		}
	}

	// El update
	@Secured(" hasRole('ROLE_ADMIN')or hasRole('ROLE_RECEPTIONIST')")
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Reservation objReservation = reService.listarId(id);
		if (objReservation == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/reservation/listar";
		} else {
			model.addAttribute("listaRooms", roService.listar());
			model.addAttribute("listaPersonas", peService.listar());
			model.addAttribute("reservation", objReservation);
			
			return "reservation";

		}

	}

	@Secured(" hasRole('ROLE_ADMIN')or hasRole('ROLE_RECEPTIONIST')")
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				reService.eliminar(id);
				model.put("listaReservations", reService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar una Raza asignada");
			model.put("listaReservations", reService.listar());

		}
		return "listReservation";
	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		model.put("listaReservations", reService.listar());
		return "listReservation";

	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Reservation reservation) {

		reService.listarId(reservation.getIdReservation());
		return "listReservation";

	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Reservation objReservation) throws ParseException {

		List<Reservation> listaReservations;

		objReservation.setCodigoReservacion(objReservation.getCodigoReservacion());
		listaReservations = reService.buscarReservationxPersona(objReservation.getCodigoReservacion());


			

				if (listaReservations.isEmpty()) {
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						objReservation.setBirthDateReservation(sdf.parse(objReservation.getCodigoReservacion()));

						listaReservations = reService.findByBirthDateReservation(objReservation.getBirthDateReservation());
					} catch (Exception e)

					{
						model.put("mensaje", "Formato incorrecto");

					}

				}

			

			if (listaReservations.isEmpty()) {
				model.put("mensaje", "No se encontró");
			}

			model.put("listaReservations", listaReservations);
			return "buscarReservation";
		}
		
		
	
	@RequestMapping("/detalles/{id}")
	public String listarDetalles(@PathVariable int id, Map<String, Object> model, RedirectAttributes objRedir) {


		model.put("listaDetalles", reService.buscarDetallePedidoxid(id));
		
		double total = 0;
		for(DetallePedido dp : reService.buscarDetallePedidoxid(id))
		{
			total += dp.getRoom().getNumPrice() * dp.getCantidad();
		}
		model.put("total", total);
		return "listDetallePedido";


	}

	

	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {

		model.addAttribute("reservation", new Reservation());
		return "buscarReservation";

	}
	
	
	
	
	
	
	
	
	
	
	
	
}
