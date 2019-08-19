package pe.edu.upc.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Checkin;
import pe.edu.upc.entity.Reservation;
import pe.edu.upc.service.ICheckinService;
import pe.edu.upc.service.IReservationService;

@Controller
@RequestMapping("/checkin")
public class CheckinController {
	@Autowired
	private ICheckinService chiService;
	@Autowired
	private IReservationService reService;
	
	@RequestMapping("/")
	public String irCheckin(Map<String, Object> model) {
		model.put("listaCheckins", chiService.listar());
		return "listCheckin";
	}

	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		Checkin checkin= new Checkin();
		model.addAttribute("checkin",checkin);
		model.addAttribute("listaReservations", reService.listar());
		return "checkin";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Checkin objCheckin, BindingResult binRes, Model model)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaReservations", reService.listar());
			return "checkin";
		} else {
			boolean flag = chiService.insertar(objCheckin);
			if (flag) {
				return "redirect:/checkin/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/checkin/irRegistrar";
			}
		}
	}

	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Checkin objCheckin, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/checkin/listar";
		} else {
			boolean flag = chiService.modificar(objCheckin);

			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
				return "redirect:/checkin/listar";

			} else {
				objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
				return "redirect:/checkin/listar";
			}
		}
	}

	// El update
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Checkin objCheckin = chiService.listarId(id);
		if (objCheckin == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/reservation/listar";
		} else {
			model.addAttribute("checkin", objCheckin);
			model.addAttribute("listaReservations", reService.listar());
			return "checkin";

		}

	}

	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				chiService.eliminar(id);
				model.put("listaCheckins", chiService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar una Raza asignada");
			model.put("listaCheckins", chiService.listar());

		}
		return "listCheckin";
	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		model.put("listaCheckins", chiService.listar());
		return "listCheckin";

	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Checkin checkin) {

		chiService.listarId(checkin.getIdCheckin());
		return "listCheckin";

	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Checkin checkin,Reservation reservation) throws ParseException {

		List<Checkin> listaCheckins;

		if (StringUtils.isNumeric(checkin.getNameCheckin())) {

			listaCheckins = chiService.findBynameCheckin(checkin.getNameCheckin());

		} else {
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

			reservation.setBirthDateReservation(sdf1.parse(checkin.getNameCheckin()));
			listaCheckins = chiService.buscarxfechaReserva(reservation.getBirthDateReservation());

			if (listaCheckins.isEmpty()) {
				try {

					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

					checkin.setDateCheckin(sdf.parse(checkin.getNameCheckin()));
					listaCheckins = chiService.findByDateCheckin(checkin.getDateCheckin());

				} catch (Exception e) {
					model.put("mensaje", "Formato incorrecto!!");

				}
			}

		}

		if (listaCheckins.isEmpty()) {

			model.put("mensaje", "No se encontró");
		}

		model.put("listaCheckins", listaCheckins);
		return "buscarCheckin";


	}

	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {

		model.addAttribute("checkin", new Checkin());
		return "buscarCheckin";

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
