/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author juanx96506
 */
public class List {

    ClienteE<String> first;
    ClienteE<String> last;

    public void addLast(String id, String address, String mail, String name, String lastName, String phone, String city) {
        ClienteE<String> insert = new ClienteE<String>(id, address, mail, name, lastName, phone, city);
        if (isEmpty()) {
            first = insert;
            last = insert;
        } else {
            last.setNext(insert);
            insert.setBack(last);
            last = insert;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        int cont = 0;
        if (isEmpty()) {
            cont = -1;
        } else {
            ClienteE<String> temp = first;
            while (temp != null) {
                cont++;
                temp = temp.getNext();
            }
        }
        return cont;
    }

}
