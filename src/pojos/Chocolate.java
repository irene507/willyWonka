package pojos;

import java.io.Serializable;

public class Chocolate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2290544292024275090L;
		//no tenemos ningun dato relacionado con dato 
   // podriamos poner un dato que fuera la fecha de creacion de ese chocolate 
	//Date: siempre import java.sql
        private Integer id;
        private String name;
        private String type;
        private Float cocoa;
        private String flavors;
        private Float units;
        private String shape;
        
        
        public Chocolate(Integer id, String name, String type, Float cocoa, String flavors, Float units,
				String shape) {
			super();
			this.id = id;
			this.name = name;
			this.type = type;
			this.cocoa = cocoa;
			this.flavors = flavors;
			this.units = units;
			this.shape = shape;
		}
        
        public Chocolate(String name, String type, Float cocoa, String flavors, Float units, String shape) {
			super();
			this.name = name;
			this.type = type;
			this.cocoa = cocoa;
			this.flavors = flavors;
			this.units = units;
			this.shape = shape;
		}





		//CREACION DEL CONSTRUCTOR 
		public Chocolate() {
			super();
		}
		//GETTERS AND SETTERS

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Float getCocoa() {
			return cocoa;
		}

		public void setCocoa(Float cocoa) {
			this.cocoa = cocoa;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getFlavors() {
			return flavors;
		}

		public void setFlavors(String flavors) {
			this.flavors = flavors;
		}

		public Float getUnits() {
			return units;
		}

		public void setUnits(Float units) {
			this.units = units;
		}

		public String getShape() {
			return shape;
		}

		public void setShape(String shape) {
			this.shape = shape;
		}
		
        //MÉTODO TOSTRING()
		@Override
		public String toString() {
			return "Chocolate [id=" + id + ", name=" + name + ", cocoa=" + cocoa + ", type=" + type + ", flavors="
					+ flavors + ", units=" + units + ", shape=" + shape + "]";
		}




		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((cocoa == null) ? 0 : cocoa.hashCode());
			result = prime * result + ((flavors == null) ? 0 : flavors.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((shape == null) ? 0 : shape.hashCode());
			result = prime * result + ((type == null) ? 0 : type.hashCode());
			result = prime * result + ((units == null) ? 0 : units.hashCode());
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
			Chocolate other = (Chocolate) obj;
			if (cocoa == null) {
				if (other.cocoa != null)
					return false;
			} else if (!cocoa.equals(other.cocoa))
				return false;
			if (flavors == null) {
				if (other.flavors != null)
					return false;
			} else if (!flavors.equals(other.flavors))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (shape == null) {
				if (other.shape != null)
					return false;
			} else if (!shape.equals(other.shape))
				return false;
			if (type == null) {
				if (other.type != null)
					return false;
			} else if (!type.equals(other.type))
				return false;
			if (units == null) {
				if (other.units != null)
					return false;
			} else if (!units.equals(other.units))
				return false;
			return true;
		}

	
		}

	
