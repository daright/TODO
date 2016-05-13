package model;

public class LineItem {
	private int iditem;
	private String item;
	private boolean checked;
	private String login;
	private int numOfSubitems;
	private int numOfCheckedSubitems;
	private String date;

	public LineItem(int iditem, String item, boolean checked, String login) {
		this.iditem = iditem;
		this.item = item;
		this.checked = checked;
		this.login = login;
	}
	
	public LineItem(int iditem, String item, boolean checked, String login, String date) {
		this.iditem = iditem;
		this.item = item;
		this.checked = checked;
		this.login = login;
		this.date = date;
	}

	public int getIditem() {
		return iditem;
	}

	public void setIditem(int iditem) {
		this.iditem = iditem;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getNumOfSubitems() {
		return numOfSubitems;
	}

	public void setNumOfSubitems(int numOfSubitems) {
		this.numOfSubitems = numOfSubitems;
	}

	public int getNumOfCheckedSubitems() {
		return numOfCheckedSubitems;
	}

	public void setNumOfCheckedSubitems(int numOfCheckedSubitems) {
		this.numOfCheckedSubitems = numOfCheckedSubitems;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "LineItem [iditem=" + iditem + ", item=" + item + ", checked=" + checked + ", login=" + login + "]";
	}

}
