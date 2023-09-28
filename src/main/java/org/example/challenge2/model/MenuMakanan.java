package org.example.challenge2.model;

public class MenuMakanan {
    long id; String nama; long harga;

    public MenuMakanan() {
    }

    public MenuMakanan(long id, String nama, long harga) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public long getHarga() {
        return harga;
    }

    public void setHarga(long harga) {
        this.harga = harga;
    }

    @Override
    public String toString() {
        return "MenuMakanan{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", harga=" + harga +
                '}';
    }
}
