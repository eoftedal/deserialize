package org.insecurelabs.api.contacts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/contacts")
public class ContactController {
	@Autowired
	private ContactRepository contactRepository;

	@RequestMapping( method = RequestMethod.POST)
	@ResponseStatus( HttpStatus.CREATED )
	public final ModelAndView create( @RequestBody Contact contact ){
		System.out.println("Creating new contact: " + contact.getFirstName());
		contactRepository.save(contact);
		return new ModelAndView("", "responseObject", contact);
	}
}
 