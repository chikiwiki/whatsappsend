package com.wiki.whatsapp.model;

public class Contact {

	String nameInAgenda; //Nombre del contacto en tu agenda
	String nickName; //Alias del contacto
	String numberMobile; //Numero de telefono del contacto
	boolean sended; //flag de enviado. Indica si se ha enviado el mensaje para este contacto o no
	
	public String getNameInAgenda() {
		return nameInAgenda;
	}
	public void setNameInAgenda(String nameInAgenda) {
		this.nameInAgenda = nameInAgenda;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getNumberMobile() {
		return numberMobile;
	}
	public void setNumberMobile(String numberMobile) {
		this.numberMobile = numberMobile;
	}
	public boolean isSended() {
		return sended;
	}
	public void setSended(boolean sended) {
		this.sended = sended;
	}
	
}
