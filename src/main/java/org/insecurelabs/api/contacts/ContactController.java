package org.insecurelabs.api.contacts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.*;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;
import org.xml.sax.*;


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

	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = " /{id}", method=RequestMethod.GET)
	public final ModelAndView create( @PathVariable int id ){
		Contact contact = contactRepository.get(id);
		return new ModelAndView("", "responseObject", contact);
	}

	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = " /{id}/html", method=RequestMethod.POST)
	@ResponseBody
	public final String format(@PathVariable int id, HttpEntity<String> xslt ){
		Contact contact = contactRepository.get(id);
		try {
    	String xml = Formatter.format(contact, xslt.getBody());
			return xml;
		} catch(Exception ex) {
			return ex.getMessage();
		}
	}



}
 