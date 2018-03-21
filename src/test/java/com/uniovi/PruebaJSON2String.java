package com.uniovi;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;
import org.junit.Test;

import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.model.Property;

public class PruebaJSON2String {

	@Test
	public void test() {
		Incidence i = new Incidence();
		i.setIdentifier("AOU123");
		i.setLogin("Manuel");
		i.setPassword("123456");
		i.setKind("Person");
		i.setName("Incendio");
		i.setDescription("Incendio pequeño");
		i.setLocation("+10, -15");
		Set<String> tags = new HashSet<String>();
		tags.add("tag1");
		tags.add("tag2");
		tags.add("tag3");
		i.setTags(tags);
		Set<Property> properties = new HashSet<Property>();
		properties.add(new Property("temperatura", "100"));
		properties.add(new Property("humedad", "0"));
		i.setProperties(properties);
		i.setStatus("open");
		i.setOperatorIdentifier("AOU1234");
		i.setExpiration("11/02/2019");
		
		System.out.println(generarJSON(i));
	}

	private String generarJSON(Incidence incidence) {
		JSONObject json = new JSONObject();
		json.put("identifier", incidence.getIdentifier());
		json.put("login", incidence.getLogin());
		json.put("password", incidence.getPassword());
		json.put("kind", incidence.getKind());
		json.put("name", incidence.getName());
		json.put("description", incidence.getDescription());
		json.put("location", incidence.getLocation());
		json.put("tags", incidence.getTags());
		json.put("properties", incidence.getProperties());
		json.put("status", incidence.getStatus());
		json.put("operatorIdentifier", incidence.getOperatorIdentifier());
		json.put("expiration", incidence.getExpiration());
		
		return json.toString();
	}
}