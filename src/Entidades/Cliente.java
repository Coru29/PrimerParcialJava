package Entidades;

public class Cliente {

    private String cedula;
    private long numero;
    private Compra compraRealizada = null;

    public Compra getCompraRealizada() {
        return compraRealizada;
    }

    public void setCompraRealizada(Compra compraRealizada) {
        this.compraRealizada = compraRealizada;
    }

    public long getNumero() {
        return numero;
    }


    public void setNumero(long numero) {
        this.numero = numero;
    }

    private String nombre;

    public Cliente(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return cedula.equals(cliente.cedula);
    }

}
