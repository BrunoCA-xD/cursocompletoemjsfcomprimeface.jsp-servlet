package beans;

public class UserBean {

	private Long id;
	private String login;
	private String password;
	private String name;
	private String phone;
	private String zCode;
	private String street;
	private String number;
	private String district;
	private String city;
	private String state;
	private String ibge;

	public UserBean() {
	}


	public UserBean(Long id, String login, String password) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
	}
	

	
	
	public UserBean(String login, String password, String name, String phone, String zCode, String street,
			String number, String district, String city, String state, String ibge) {
		super();
		this.login = login;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.zCode = zCode;
		this.street = street;
		this.number = number;
		this.district = district;
		this.city = city;
		this.state = state;
		this.ibge = ibge;
	}


	public UserBean(Long id, String login, String password, String name, String phone, String zCode, String street,
			String number, String district, String city, String state, String ibge) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.zCode = zCode;
		this.street = street;
		this.number = number;
		this.district = district;
		this.city = city;
		this.state = state;
		this.ibge = ibge;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getzCode() {
		return zCode;
	}


	public void setzCode(String zCode) {
		this.zCode = zCode;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getDistrict() {
		return district;
	}


	public void setDistrict(String district) {
		this.district = district;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getIbge() {
		return ibge;
	}


	public void setIbge(String ibge) {
		this.ibge = ibge;
	}
}
