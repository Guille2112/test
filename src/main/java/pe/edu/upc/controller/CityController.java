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

import pe.edu.upc.entity.City;
import pe.edu.upc.service.ICityService;
import pe.edu.upc.service.ICountryService;




@Controller
@RequestMapping("/city")
public class CityController {

	
	
	@Autowired
	private ICityService ciService;
	
	@Autowired
	private ICountryService coService;
	
	@RequestMapping("/")
	public String irCity(Map<String, Object> model) {
		model.put("listaCitys", ciService.listar());
		return "listCity";
	}
	

	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {

		model.addAttribute("city", new City());
		model.addAttribute("listaCountrys", coService.listar());
		return "city";
	}
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid City objCity, BindingResult binRes, Model model)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaCountrys", coService.listar());
			return "city";
		} else {
			boolean flag = ciService.insertar(objCity);
			if (flag) {
				return "redirect:/city/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/city/irRegistrar";
			}
		}	
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid City objCity, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/city/listar";
		} else {
			boolean flag = ciService.modificar(objCity);

			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
				return "redirect:/city/listar";

			} else {
				objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
				return "redirect:/city/listar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		City objCity = ciService.listarId(id);
		if(objCity == null){
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/city/listar";
		}else{
			model.addAttribute("listaCountrys", coService.listar());
			model.addAttribute("city", objCity);
			return "city";
		}	
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		if (id != null && id > 0) {
			ciService.eliminar(id);
			model.put("listaCitys", ciService.listar());
		}
		return "listCity";
	}
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		model.put("listaCitys", ciService.listar());
		return "listCity";
	}
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute City city) {
		ciService.listarId(city.getIdCity());
		return "listCity";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute City city) throws ParseException {
		List<City> listaCitys;
		city.setNameCity(city.getNameCity());
		listaCitys = ciService.buscarCity(city.getNameCity());
		if (listaCitys.isEmpty()) {
			listaCitys = ciService.buscarCountry(city.getNameCity());
		}
	if (listaCitys.isEmpty())
		{
			model.put("mensaje", "No se encontró");
		}
		model.put("listaCitys", listaCitys);
		return "buscarCity";
	}
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("city", new City());
		return "buscarCity";
	}
}
