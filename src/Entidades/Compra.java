package Entidades;

import tads.linkedlist.MyLinkedListImpl;
import tads.linkedlist.MyList;

public class Compra {
    private long numeroComrpa;

    private MyList<Producto> listaDeProductos = new MyLinkedListImpl<>();

    public MyList<Producto> getListaDeProductos() {
        return listaDeProductos;
    }



    public long getNumeroComrpa() {
        return numeroComrpa;
    }

    public Compra(long numeroComrpa, MyList<Producto> listaDeProductos) {
        this.numeroComrpa = numeroComrpa;
        this.listaDeProductos = listaDeProductos;
    }
}
