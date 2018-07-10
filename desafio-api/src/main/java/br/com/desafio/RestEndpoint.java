/**
 *
 */
package br.com.desafio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author brmsouza
 *
 */
@RestController
@RequestMapping("/rest")
public class RestEndpoint {

	@GetMapping("/bankslips")
	public String get() {
		return "abc";
	}

}
