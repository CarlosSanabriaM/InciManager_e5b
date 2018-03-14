package uo.asw.dbManagement.model;

import java.util.Arrays;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Incidence {
	
	/*	TODO - Quitar
	 *	Cada incidencia puede contener los siguientes campos: 
	 *	nombre de usuario y password, 
	 *	nombre de la incidencia, descripción, localización (se obtendrá automáticamente del dispositivo si es posible), 
	 *	etiquetas (lista de palabras separadas por comas que permitirán categorizar las incidencias), 
	 *	información adicional (fotos, vídeos, etc.). 
	 *	Algunas incidencias podrán también contener una lista de campos con la forma "propiedad/valor", 
	 *	donde el campo propiedad indica un nombre de propiedad, y el campo valor, indica el valor de dicha propiedad.
	 *
	 *	Las incidencias adquirirán un estado (abierta, en proceso, cerrada, anulada) 
	 *	así como otra información generada por el sistema (persona/entidad asignada para resolver la incidencia), 
	 *	comentarios sobre la incidencia realizados por los operarios, etc. 
	 *	Las incidencias también pueden tener una caducidad, por ejemplo, en el caso de las incidencias 
	 *	que pueda enviar un sensor de temperatura, si se envían cada hora, tendrán una hora de caducidad).
	 *
	 * 	Si hay valores de alguna propiedad que los filtros consideran peligroso, se marcará dicha incidencia como peligrosa.
	 */
	
	@Id
	@GeneratedValue
	private long id;
	@Column(unique=true) 
	private String identifier;
	
	@ManyToOne
	@JoinColumn(name="agent_id")
	private Agent agent;
	
	@ManyToOne
	@JoinColumn(name="operator_id")
	private Operator operator;
	
	private String name;
	private String description;
	private String location;
	private String[] tags;
	//private Map<String, Object> additional;
	@OneToMany(mappedBy="incidence")
	private Set<Property> properties;
	
	private String status; //open, in process, closed, canceled
	private String operatorComments;
	private String expiration;
	private boolean dangerous;
	
	public Incidence() {}
	
	public Incidence(String identifier) {
		this.identifier = identifier;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Agent getAgent() {
		return agent;
	}
	public Incidence setAgent(Agent agent) {
		this.agent = agent;
		return this;
	}
	public Operator getOperator() {
		return operator;
	}
	public Incidence setOperator(Operator operator) {
		this.operator = operator;
		return this;
	}
	public String getName() {
		return name;
	}
	public Incidence setName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public Incidence setDescription(String description) {
		this.description = description;
		return this;
	}
	public String getLocation() {
		return location;
	}
	public Incidence setLocation(String location) {
		this.location = location;
		return this;
	}

	public String[] getTags() {
		return tags;
	}

	public Incidence setTags(String[] tags) {
		this.tags = tags;
		return this;
	}
	
	public String getStatus() {
		return status;
	}
	public Incidence setStatus(String status) {
		this.status = status;
		return this;
	}
	public String getOperatorComments() {
		return operatorComments;
	}
	public Incidence setOperatorComments(String operatorComments) {
		this.operatorComments = operatorComments;
		return this;
	}
	public String getExpiration() {
		return expiration;
	}
	public Incidence setExpiration(String expiration) {
		this.expiration = expiration;
		return this;
	}
	public boolean isDangerous() {
		return dangerous;
	}
	public Incidence setDangerous(boolean dangerous) {
		this.dangerous = dangerous;
		return this;
	}

	public Set<Property> getProperties() {
		return properties;
	}

	public Incidence setProperties(Set<Property> properties) {
		this.properties = properties;
		return this;
	}

	@Override
	public String toString() {
		return "Incidence [id=" + id + ", identifier=" + identifier + ", agent=" + agent + ", operator=" + operator
				+ ", name=" + name + ", description=" + description + ", location=" + location + ", tags="
				+ Arrays.toString(tags) + ", properties=" + properties + ", status=" + status + ", operatorComments="
				+ operatorComments + ", expiration=" + expiration + ", dangerous=" + dangerous + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Incidence other = (Incidence) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;
	}
	
	/**
	 * Permite comparar si esta incidencia y la que se pasa como parametro
	 * tienen todos sus campos iguales.
	 * Es distinto del equals, ya que el equals solo compara si las dos incidencias 
	 * tienen el mismo "identifier", pero este metodo permite realizar pruebas más
	 * exhaustivas
	 * 
	 * @param i
	 * @return
	 */
	public boolean equalFields(Incidence i) {
		if (this == i)
			return true;
		if (i == null)
			return false;
		if (getClass() != i.getClass())
			return false;

		if (agent == null) {
			if (i.agent != null)
				return false;
		} else if (!agent.equals(i.agent))
			return false;
		if (dangerous != i.dangerous)
			return false;
		if (description == null) {
			if (i.description != null)
				return false;
		} else if (!description.equals(i.description))
			return false;
		if (expiration == null) {
			if (i.expiration != null)
				return false;
		} else if (!expiration.equals(i.expiration))
			return false;
		if (identifier == null) {
			if (i.identifier != null)
				return false;
		} else if (!identifier.equals(i.identifier))
			return false;
		if (location == null) {
			if (i.location != null)
				return false;
		} else if (!location.equals(i.location))
			return false;
		if (name == null) {
			if (i.name != null)
				return false;
		} else if (!name.equals(i.name))
			return false;
		if (operator == null) {
			if (i.operator != null)
				return false;
		} else if (!operator.equals(i.operator))
			return false;
		if (operatorComments == null) {
			if (i.operatorComments != null)
				return false;
		} else if (!operatorComments.equals(i.operatorComments))
			return false;
		if (properties == null) {
			if (i.properties != null)
				return false;
		} else if (!properties.equals(i.properties))
			return false;
		if (status == null) {
			if (i.status != null)
				return false;
		} else if (!status.equals(i.status))
			return false;
		if (!Arrays.equals(tags, i.tags))
			return false;
		
		return true;
	}

	
	
	

}