package Entidades;

/**
 *
 * @author Juan Diego Gonzalez Mena - Juan Pablo Lopera Estrada
 * @param <T>
 */
public class List <T>{

    private Nodo<T> first;
    private Nodo<T>  last;
    
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


    public void add(T dato){
        addLast(dato);
    }
    
    
    /**
     * Insertar al final de la lista
     * @param a dato a insertar
     */
    public void addLast(T a) {
        Nodo<T> insert = new Nodo(a);
        if (isEmpty()) {
            first = insert;
            last = insert;
        } else {
            last.setNext(insert);
            insert.setBack(last);
            last = insert;
        }
    }
    
    /**
     * Inserta al principio de la lista
     * @param a dato a insertar
     */
    public void addFirst(T a){
        Nodo<T> temp = new Nodo(a);
        if(isEmpty()){
            first = temp;
            last = temp;
        }else{
            temp.setNext(first);
            first.setBack(temp);
            first = temp;
        }
    }
    
    /**
     * Inserta el dato al principio de la lista, metodo especial para que la
     * lista se comporte como una cola
     * @param a dato a insertar
     */
    public void push(T a){
        addLast(a);
    }
    
    /**
     * Elimina el ultimo nodo y retorna el dato del nodo eliminado, metodo especial
     * para que la lista se comporte como una cola
     * @return retorna el dato del nodo
     */
    public T pop(){
        if(isEmpty()){
            return null;
        }else{
            T dato = first.getDato();
            removeFirst();
            return dato;
        }
    }
    
    /**
     * Metodo que retorna el dato de una posicion de la lista
     * @param i dato que se desea retornar
     * @return dato del nodo
     * @throws IndexOutOfBoundsException si i es igual o mayor al tamaño de la lista
     */
    public T get(int i) throws IndexOutOfBoundsException{
        if(i >= size()){
            throw new IndexOutOfBoundsException();
        }else{
            Nodo<T> temp = first;
            for(int j = 0; j < i; j++){
                temp = temp.getNext();
            }
            
            return temp.getDato();
        }
    }
    
    /**
     * Establece el dato de un nodo dado por su posición
     * @param dato dato a establecer en el nodo
     * @param posicion posición del nodo en la lista
     * @throws IndexOutOfBoundsException si posicion es mayor o igual al tamaño
     * de la lista
     */
    public void set(T dato, int posicion) throws IndexOutOfBoundsException{
        if(posicion >= size()){
            throw new IndexOutOfBoundsException();
        }else{
            Nodo<T> temp = first;
            for(int i = 0; i < posicion; i++){
                temp = temp.getNext();
            }
            
            temp.setDato(dato);
        }
    }
    
    public boolean remove(T search){
        boolean find = false;
        Nodo<T> aux = first;
        for(;aux != null;aux = aux.getNext()){
            if(aux.getDato().equals(search)){
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
     * Elimina un nodo de la lista en una posicion especifica
     * @param posicion numero del nodo a eliminar
     * @throws IndexOutOfBoundsException si posicion es mayor o igual al tamaño
     * de la lista
     */
    public void remove(int posicion) throws IndexOutOfBoundsException{
        if(posicion >= size()){
            throw new IndexOutOfBoundsException();
        }else{
            Nodo<T> temp = first;
            for(int i = 0; i < posicion; i++){
                temp = temp.getNext();
            }
            
            if(temp.getNext() == null){
                removeLast();
            }else if(temp.getBack() == null){
                removeFirst();
            }else{
                Nodo<T> sig = temp.getNext();
                Nodo<T> ant = temp.getBack();
                
                ant.setNext(sig);
                sig.setBack(ant);
                
                temp.setBack(null);
                temp.setNext(null);
            }
        }
    }
    
    /**
     * Elimina el ultimo nodo de la lista
     * @return true si pudo eliminar el nodo o false si la lista esta vacia
     */
    public boolean removeFirst(){
        if(isEmpty()){
            return false;
        }else{
            if(size() == 1){
                first = null;
                last = null;
            }else{
               Nodo<T> pen = first.getNext();
               pen.setBack(null);
               first.setNext(null);
               first = pen;
            }
            
            return true;
        }
    }
    
    public boolean removeLast(){
        if(isEmpty()){
            return false;
        }else{
            if(size() == 1){
                first = null;
                last = null;
            }else{
                Nodo<T> temp = last.getBack();
                temp.setNext(null);
                last.setBack(null);
                last = temp;
            }
            return true;
        }
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
