package org.insecurelabs.api.contacts;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "contact")
public interface Contact {

	public String getId();
	public void setId(String id);
	public String getFirstName();
	public void setFirstName(String firstName);
	public String getLastName();
	public void setLastName(String lastName);
	public String getEmail();
	public void setEmail(String email);

}
