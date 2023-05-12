package lv.venta.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Costumer {
	
	@Column(name = "Idc") // DB pusē būs kolonna "Id" un būs kā auto increment primary key
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idc;
	
	@Column(name = "Name") // DB pusē būs kolonna "Title"
	@NotNull
	@Pattern(regexp = "[A-ZĀŠĒĢŪĪĶĻŅŽ]{1}[a-zēīļķšāžņģ\\ ]+")
	@Size(min = 3, max = 30, message = "Jābūt vismaz 3 simbolus garam un nedrīkst pārsniegt 30 simbolus")
	private String name;
	
	@Column(name = "Surname") // DB pusē būs kolonna "Title"
	@NotNull
	@Pattern(regexp = "[A-ZĀŠĒĢŪĪĶĻŅŽ]{1}[a-zēīļķšāžņģ\\ ]+")
	@Size(min = 3, max = 30, message = "Jābūt vismaz 3 simbolus garam un nedrīkst pārsniegt 30 simbolus")
	private String surname;
	
	
	
}
