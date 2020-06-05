package dto;

public class Member {
	
	private String nickname;
	private String name;
	private String password;
	private String email;
	private String team;
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	
	@Override
	public String toString() {
		return "Member [nickname=" + nickname + ", name=" + name + ", password=" + password + ",  email=" + email +", team =" + team + "]";
	}

}
