package com.ituple.pingamedic.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ituple.pingamedic.entity.Person;;

@Controller
@RequestMapping("/employe/save")
public class EmployeeController {

	@Autowired
	private MongoTemplate mongoTemplate;
	public static final String COLLECTION_NAME = "person";
	
	@RequestMapping(value="/{name}", method = RequestMethod.GET)
	public String savePerson(@PathVariable String name, ModelMap model) {
		model.addAttribute("name", name);
		Person person=new Person();
		person.setId("sample id");
		person.setName("sample name");
		
			if (!mongoTemplate.collectionExists(Person.class)) {
				mongoTemplate.createCollection(Person.class);
			}		
			person.setId(UUID.randomUUID().toString());
			mongoTemplate.insert(person, COLLECTION_NAME);
		
		return "list";
	}
	
}