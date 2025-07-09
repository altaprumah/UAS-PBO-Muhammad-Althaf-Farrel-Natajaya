package backend;

import java.sql.*;
import java.util.ArrayList;

public class Anggota {
    private int idAnggota;
    private String nama;
    private String alamat;
    private String telepon;

    public Anggota() {
    }

    public Anggota(String nama, String alamat, String telepon) {
        this.nama = nama;
        this.alamat = alamat;
        this.telepon = telepon;
    }

    public int getIdAnggota() {
        return idAnggota;
    }

    public void setIdAnggota(int idAnggota) {
        this.idAnggota = idAnggota;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public static Anggota getById(int id) {
        Anggota ang = new Anggota();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM anggota WHERE idAnggota = '" + id + "'");

        try {
            while (rs.next()) {
                ang = new Anggota();
                ang.setIdAnggota(rs.getInt("idAnggota"));
                ang.setNama(rs.getString("nama"));
                ang.setAlamat(rs.getString("alamat"));
                ang.setTelepon(rs.getString("telepon"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ang;
    }

    public ArrayList<Anggota> getAll() {
        ArrayList<Anggota> listAnggota = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM anggota");

        try {
            while (rs.next()) {
                Anggota ang = new Anggota();
                ang.setIdAnggota(rs.getInt("idAnggota"));
                ang.setNama(rs.getString("nama"));
                ang.setAlamat(rs.getString("alamat"));
                ang.setTelepon(rs.getString("telepon"));

                listAnggota.add(ang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listAnggota;
    }

    public ArrayList<Anggota> search(String keyword) {
        ArrayList<Anggota> listAnggota = new ArrayList<>();
        String sql = "SELECT * FROM anggota WHERE nama LIKE '%" + keyword + "%' "
                   + "OR alamat LIKE '%" + keyword + "%' OR telepon LIKE '%" + keyword + "%'";
        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                Anggota ang = new Anggota();
                ang.setIdAnggota(rs.getInt("idAnggota"));
                ang.setNama(rs.getString("nama"));
                ang.setAlamat(rs.getString("alamat"));
                ang.setTelepon(rs.getString("telepon"));

                listAnggota.add(ang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listAnggota;
    }

    public void save() {
    if (getById(this.idAnggota).getIdAnggota() == 0) {
        String sql = String.format("INSERT INTO anggota (nama, alamat, telepon) VALUES ('%s', '%s', '%s')",
                this.nama.replace("'", "''"),
                this.alamat.replace("'", "''"),
                this.telepon.replace("'", "''"));
        this.idAnggota = DBHelper.insertQueryGetId(sql);
    } else {
        String sql = String.format("UPDATE anggota SET nama = '%s', alamat = '%s', telepon = '%s' WHERE idAnggota = %d",
                this.nama.replace("'", "''"),
                this.alamat.replace("'", "''"),
                this.telepon.replace("'", "''"),
                this.idAnggota);
        DBHelper.executeQuery(sql);
    }
}


    public void delete() {
        String sql = "DELETE FROM anggota WHERE idAnggota = '" + this.idAnggota + "'";
        DBHelper.executeQuery(sql);
    }
    
    @Override
    public String toString() {
    return this.nama;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Anggota anggota = (Anggota) obj;
        return idAnggota == anggota.idAnggota;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(idAnggota);
    }
}
