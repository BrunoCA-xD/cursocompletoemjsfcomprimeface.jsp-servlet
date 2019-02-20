package beans;

public class ProductBean {

	private Long id;
	private String name;
	private Double amount;
	private Double value;
	
	public ProductBean() {
	}
	
	public ProductBean(String name) {
		super();
		this.name = name;
	}
	public ProductBean(Long id, String name, Double amount, Double value) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	

}
