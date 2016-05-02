package model;

public class SublistItem {
	private int iditem;
	private int idparent;
	private String login;
	private String item;
	private boolean checked;

	public int getIditem() {
		return iditem;
	}

	public void setIditem(int iditem) {
		this.iditem = iditem;
	}

	public int getIdparent() {
		return idparent;
	}

	public void setIdparent(int idparent) {
		this.idparent = idparent;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public SublistItem(int iditem, int idparent, String login, String item, boolean checked) {
		this.iditem = iditem;
		this.idparent = idparent;
		this.login = login;
		this.item = item;
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "SublistItem [iditem=" + iditem + ", idparent=" + idparent + ", login=" + login + ", item=" + item + ", checked=" + checked + "]";
	}

}
