package pe.edu.upc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import pe.edu.upc.entity.Checkout;
import pe.edu.upc.service.ICheckinService;
import pe.edu.upc.service.ICheckoutService;
import pe.edu.upc.service.IReservationService;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
	
	@Autowired
	private ICheckoutService choService;
	@Autowired
	private ICheckinService chinService;
	@Autowired
	private IReservationService reService;
	@RequestMapping("/")
	public String irCheckout(Map<String, Object> model) {
		model.put("listaCheckouts", choService.listar());
		return "listCheckout";
	}

	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {

		model.addAttribute("checkout", new Checkout());
		model.addAttribute("listaCheckins", chinService.listar());
		model.addAttribute("listaReservations", reService.listar());
		return "checkout";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Checkout objCheckout, BindingResult binRes, Model model)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaCheckins", chinService.listar());
			model.addAttribute("listaReservations", reService.listar());
			return "checkout";
		} else {
			boolean flag = choService.insertar(objCheckout);
			if (flag) {
				return "redirect:/checkout/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/checkout/irRegistrar";
			}
		}
	}

	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Checkout objCheckout, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/checkout/listar";
		} else {
			boolean flag = choService.modificar(objCheckout);

			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
				return "redirect:/checkout/listar";

			} else {
				objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
				return "redirect:/checkout/listar";
			}
		}
	}

	// El update
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Checkout objCheckout = choService.listarId(id);
		if (objCheckout == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/reservation/listar";
		} else {
			model.addAttribute("checkout", objCheckout);
			model.addAttribute("listaCheckins", chinService.listar());
			model.addAttribute("listaReservations", reService.listar());
			return "checkout";

		}

	}

	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				choService.eliminar(id);
				model.put("listaCheckouts", choService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar una Raza asignada");
			model.put("listaCheckouts", choService.listar());

		}
		return "listCheckout";
	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		model.put("listaCheckouts", choService.listar());
		return "listCheckout";

	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Checkout checkout) {

		choService.listarId(checkout.getIdCheckout());
		return "listCheckout";

	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Checkout checkout) throws ParseException {

		List<Checkout> listaCheckouts;

		checkout.setNameCheckout(checkout.getNameCheckout());
		listaCheckouts = choService.findBynameCheckout(checkout.getNameCheckout());
		if (listaCheckouts.isEmpty()) {
			try {

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

				checkout.setDateCheckout(sdf.parse(checkout.getNameCheckout()));
				listaCheckouts = choService.findByDateCheckout(checkout.getDateCheckout());

			} catch (Exception e) {
				model.put("mensaje", "Formato incorrecto!!");

			}
		}

		if (listaCheckouts.isEmpty()) {

			model.put("mensaje", "No se encontró");
		}

		model.put("listaCheckouts", listaCheckouts);
		return "buscarCheckout";

	}

	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {

		model.addAttribute("checkout", new Checkout());
		return "buscarCheckout";

	}
	  
	
	
	
	
}
