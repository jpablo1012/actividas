package Entidades;
public class Nodo<T> {
	private T dato;
	private Nodo<T> next;
	private Nodo<T> back;
	
	public Nodo(){
		this(null, null, null);
	}
	
	public Nodo(T dato){
		this(dato, null, null);
	}
	
	public Nodo(T dato, Nodo<T> siguiente, Nodo<T> anterior){
		this.dato = dato;
		this.next = siguiente;
		this.back = anterior;
	}
	
	public T getDato() {
		return dato;
	}
	public void setDato(T dato) {
		this.dato = dato;
	}
	public Nodo<T> getNext() {
		return next;
	}
	public void setNext(Nodo<T> siguiente) {
		this.next = siguiente;
	}
	public Nodo<T> getBack() {
		return next;
	}
	public void setBack(Nodo<T> anterior) {
		this.next = anterior;
	}
}