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
        private String cocoa;
        private String type;
        private String flavors;
        private Float units;
        private String shape;
        
        
        public Chocolate(Integer id, String name, String cocoa, String type, String flavors, Float units,
				String shape) {
			super();
			this.id = id;
			this.name = name;
			this.cocoa = cocoa;
			this.type = type;
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

		public String getCocoa() {
			return cocoa;
		}

		public void setCocoa(String cocoa) {
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
			result = prime * result + ((id == null) ? 0 : id.hashCode());
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
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}
		
		
}
