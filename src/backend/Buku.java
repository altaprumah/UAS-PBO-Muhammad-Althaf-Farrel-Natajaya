package backend;

import java.sql.*;
import java.util.ArrayList;

public class Buku {
    private int idBuku;
    private Kategori kategori = new Kategori();
    private String judul;
    private String penerbit;
    private String penulis;

    public int getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(int idBuku) {
        this.idBuku = idBuku;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }
    
    public Buku() {}
    
    public Buku(Kategori kategori, String judul, String penerbit, String penulis) {
        this.kategori = kategori;
        this.judul = judul;
        this.penerbit = penerbit;
        this.penulis = penulis;
    }

   public Buku getById(int id) {
    Buku buku = new Buku();
    
    ResultSet rs = DBHelper.selectQuery(
        "SELECT " +
        "b.idbuku AS idbuku, " +
        "k.idkategori AS idkategori, " +
        "k.nama AS nama, " +
        "k.keterangan AS keterangan, " +
        "b.judul AS judul, " +
        "b.penerbit AS penerbit, " +
        "b.penulis AS penulis " +
        "FROM buku b " +
        "LEFT JOIN kategori k ON b.idkategori = k.idkategori " +
        "WHERE b.idbuku = " + id
    );

    try {
        if (rs != null) { 
            while (rs.next()) {
                Kategori kategori = new Kategori();
                kategori.setIdKategori(rs.getInt("idkategori"));
                kategori.setNama(rs.getString("nama"));
                kategori.setKeterangan(rs.getString("keterangan"));

                buku.setIdBuku(rs.getInt("idbuku"));
                buku.setKategori(kategori);
                buku.setJudul(rs.getString("judul"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setPenulis(rs.getString("penulis"));
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return buku;
}

    public ArrayList<Buku> getAll() {
        ArrayList<Buku> ListBuku = new ArrayList<>();
        
        ResultSet rs = DBHelper.selectQuery(
            "SELECT " +
            "b.idbuku AS idbuku, " +
            "b.judul AS judul, " +
            "b.penerbit AS penerbit, " +
            "b.penulis AS penulis, " +
            "k.idkategori AS idkategori, " +
            "k.nama AS nama, " +
            "k.keterangan AS keterangan " +
            "FROM buku b " +
            "LEFT JOIN kategori k ON b.idkategori = k.idkategori "
        );

        try {
            if (rs != null) {
                while (rs.next()) {
                    Kategori kategori = new Kategori();
                    kategori.setIdKategori(rs.getInt("idkategori"));
                    kategori.setNama(rs.getString("nama"));
                    kategori.setKeterangan(rs.getString("keterangan"));

                    Buku buku = new Buku();
                    buku.setIdBuku(rs.getInt("idbuku"));
                    buku.setKategori(kategori);
                    buku.setJudul(rs.getString("judul"));
                    buku.setPenerbit(rs.getString("penerbit"));
                    buku.setPenulis(rs.getString("penulis"));

                    ListBuku.add(buku);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListBuku;
    }

    public ArrayList<Buku> search(String keyword) {
        ArrayList<Buku> ListBuku = new ArrayList<>();
        
        ResultSet rs = DBHelper.selectQuery(
            "SELECT " +
            "b.idbuku AS idbuku, " +
            "b.judul AS judul, " +
            "b.penerbit AS penerbit, " +
            "b.penulis AS penulis, " +
            "k.idkategori AS idkategori, " +
            "k.nama AS nama, " +
            "k.keterangan AS keterangan " +
            "FROM buku b " +
            "LEFT JOIN kategori k ON b.idkategori = k.idkategori " +
            "WHERE b.judul LIKE '%" + keyword + "%' " +
            "   OR b.penerbit LIKE '%" + keyword + "%' " +
            "   OR b.penulis LIKE '%" + keyword + "%' "       
        );

        try {
            // Menambahkan pengecekan null untuk keamanan
            if (rs != null) {
                while (rs.next()) {
                    Kategori kategori = new Kategori();
                    kategori.setIdKategori(rs.getInt("idkategori"));
                    kategori.setNama(rs.getString("nama"));
                    kategori.setKeterangan(rs.getString("keterangan"));

                    Buku buku = new Buku();
                    buku.setIdBuku(rs.getInt("idbuku"));
                    buku.setKategori(kategori);
                    buku.setJudul(rs.getString("judul"));
                    buku.setPenerbit(rs.getString("penerbit"));
                    buku.setPenulis(rs.getString("penulis"));

                    ListBuku.add(buku);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListBuku;
    }

    public void save() {
        if (getById(idBuku).getIdBuku() == 0) {
            String SQL = "INSERT INTO buku (idkategori, judul, penerbit, penulis) VALUES(" +
                         this.kategori.getIdKategori() + ", " +
                         "'" + this.judul + "', " +
                         "'" + this.penerbit + "', " +
                         "'" + this.penulis + "')";
            this.idBuku = DBHelper.insertQueryGetId(SQL);
        } else {
            String SQL = "UPDATE buku SET " +
                         "idkategori = " + this.kategori.getIdKategori() + ", " +
                         "judul = '" + this.judul + "', " +
                         "penerbit = '" + this.penerbit + "', " +
                         "penulis = '" + this.penulis + "' " +
                         "WHERE idbuku = " + this.idBuku;
            DBHelper.executeQuery(SQL);
        }
    }

    public void delete() {
        String SQL = "DELETE FROM buku WHERE idbuku = " + this.idBuku;
        DBHelper.executeQuery(SQL);
    }
    
    @Override
    public String toString() {
    return this.judul;
    }

    // Di dalam class Buku

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Buku buku = (Buku) obj;
        return idBuku == buku.idBuku;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(idBuku);
    }
}
