package Entidades;

public class List<T> {
    
    private Nodo<T> inicio;
    private Nodo<T> fin;
    
    public List() {
        inicio = null;
        fin = null;
    }
    
    public void add(T dato) {
        addLast(dato);
    }
    
    public void addLast(T dato) {
        Nodo<T> insertar = new Nodo<T>(dato);
        if (isEmpty()) {
            inicio = insertar;
            fin = insertar;
        } else {
            fin.setNext(insertar);
            insertar.setBack(fin);
            fin = insertar;
        }
    }
    
    public boolean remove(T dato) {
        Nodo<T> aux = new Nodo(dato);
        for (; aux != null; aux = aux.getNext()) {
            if (aux.equals(dato)) {
                break;
            }
        }
        
        if (aux != null) {
            aux.getBack().setNext(aux.getNext());
            aux.getNext().setBack(aux.getBack());
            return true;            
        } else {
            return false;            
        }
        
    }
    
    public boolean isEmpty() {
        return inicio == null;
    }
    
    public int size() {
        int cont = 0;
        if (isEmpty()) {
            cont = -1;
        } else {
            Nodo<T> temp = inicio;
            while (temp != null) {
                cont++;
                temp = temp.getNext();
            }
        }
        
        return cont;
    }
    
    public T getFirst() {
        return inicio.getDato();
    }
    
    public T getLast() {
        return fin.getDato();
    }
    
    @Override
    public String toString() {
        String re = "";
        if (!isEmpty()) {
            Nodo<T> temp = inicio;
            while (temp != null) {
                re += temp.getDato().toString();
                temp = temp.getNext();
            }
        }
        
        return re;
    }
    
    public static void main(String[] args) {
        List<ClienteE> clien = new List();
        List<EmpleadoE> empl = new List();
        
    }
}
