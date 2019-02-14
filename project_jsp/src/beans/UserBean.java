package beans;

public class UserBean {

	private Long id;
	private String login;
	private String senha;

	public UserBean() {
	}
	public UserBean(Long id,String login, String senha) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
	}
	public UserBean(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
