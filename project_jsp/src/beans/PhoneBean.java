package beans;

public class PhoneBean {
	private Long userId;
	private String number;
	private String type;

	public PhoneBean() {
	}

	public PhoneBean(String number, String type) {
		super();
		this.number = number;
		this.type = type;
	}

	public PhoneBean(Long userId, String number, String type) {
		super();
		this.userId = userId;
		this.number = number;
		this.type = type;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
