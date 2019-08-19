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

import pe.edu.upc.entity.Country;
import pe.edu.upc.service.ICountryService;

@Controller
@RequestMapping("/country")
public class CountryController {

	
	@Autowired
	private ICountryService coService;
	
	@RequestMapping("/bienvenido")
	public String irCountryBienvenido() {
		return "bienvenido";
	}

	@RequestMapping("/")
	public String irCountry(Map<String, Object> model) {
		model.put("listaCountrys", coService.listar());
		return "listCountry";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {

		model.addAttribute("country", new Country());
		return "country";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Country objCountry, BindingResult binRes, Model model)
			throws ParseException {
		if (binRes.hasErrors()) {
			return "country";
		} else {
			boolean flag = coService.insertar(objCountry);
			if (flag) {
				return "redirect:/country/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/country/irRegistrar";
			}
		}
	}

	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Country objCountry, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/country/listar";
		} else {
			boolean flag = coService.modificar(objCountry);

			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
				return "redirect:/country/listar";

			} else {
				objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
				return "redirect:/country/listar";
			}
		}
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Country objCountry = coService.listarId(id);
		if (objCountry == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/country/listar";
		} else {
			model.addAttribute("country", objCountry);
			return "country";

		}

	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				coService.eliminar(id);
				model.put("listaCountrys", coService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar una Raza asignada");
			model.put("listaCountrys", coService.listar());

		}
		return "listCountry";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		model.put("listaCountrys", coService.listar());
		return "listCountry";

	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Country country) {

		coService.listarId(country.getIdCountry());
		return "listCountry";

	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Country country) throws ParseException {

		List<Country> listaCountrys;

		country.setNameCountry(country.getNameCountry());
		listaCountrys = coService.buscarNombre(country.getNameCountry());

		if (listaCountrys.isEmpty()) {

			model.put("mensaje", "No se encontró");
		}

		model.put("listaCountrys", listaCountrys);
		return "buscar";

	}

	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {

		model.addAttribute("country", new Country());
		return "buscar";

	}
}
