package org.insecurelabs.api.contacts;

import org.springframework.stereotype.Service;
import java.util.Hashtable;

@Service
public class ContactRepository {
	static Hashtable<Integer, Contact> store = new Hashtable<Integer, Contact>();
	static int counter = 0;

	public void save(Contact contact) {
		int id = ++counter;
		contact.setId(id);
		store.put(id, contact);
	}
	public Contact get(int id) {
		return store.get(id);
	}
}
