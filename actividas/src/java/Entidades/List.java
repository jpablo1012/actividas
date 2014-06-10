package Entidades;

/**
 *
 * @author Juan Diego Gonzalez Mena
 * @param <T>
 */
public class List <T>{

    Nodo<T> first;
    Nodo<T>  last;
    
    public List(){
        first = null;
        last = null;
    }

    public void setFirst(Nodo<T> first) {
        this.first = first;
    }

    public void setLast(Nodo<T> last) {
        this.last = last;
    }

    public Nodo<T> getFirst() {
        return first;
    }

    public Nodo<T> getLast() {
        return last;
    }

    //Pre-constructor de addLast
    public void add(T dato){
        addLast(dato);
    }
    
    
    /**
     * Insertar al final de la lista
     * @param a dato a insertar
     */
    public void addLast(T a) {
        Nodo insert = new Nodo(a);
        if (isEmpty()) {
            first = insert;
            last = insert;
        } else {
            last.setNext(insert);
            insert.setBack(last);
            last = insert;
        }
    }
    
    public boolean remove(Nodo search){
        boolean find = false;
        Nodo aux = first;
        for(;aux != null;aux = aux.getNext()){
            if(aux.getDato().equals(search.getDato())){
                aux.getBack().setNext(aux.getNext());
                aux.getNext().setBack(aux.getBack());
                find = true;
                aux = null;
                break;
            }
        }   
        return find;
    }

    /**
     * Verifica si la lista esta vacia
     * @return  true si esta vacia o false si no lo esta
     */
    public boolean isEmpty() {
        return first == null;
    }

    
    /**
     * Nos da a conocer el tamaño de la lista
     * @return tamaño de la lista
     */
    public int size() {
        int cont = 0;
        if (isEmpty()) {
            cont = -1;
        } else {
            Nodo<T> temp = first;
            while (temp != null) {
                cont++;
                temp = temp.getNext();
            }
        }
        return cont;
    }

}
