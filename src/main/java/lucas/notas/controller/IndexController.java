package lucas.notas.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class IndexController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
