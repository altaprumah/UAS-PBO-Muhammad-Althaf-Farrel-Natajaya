package backend;

import java.util.ArrayList;
import java.sql.ResultSet;

public class Peminjaman {
    private int idPeminjaman;
    private Anggota anggota = new Anggota();
    private Buku buku = new Buku();
    private String tanggalPinjam;
    private String tanggalKembali; // Bisa null
    private int status;

    public Peminjaman() {
    }

    public Peminjaman(Anggota anggota, Buku buku, String tglPinjam, String tglKembali, int status) {
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalPinjam = tglPinjam;
        this.tanggalKembali = tglKembali;
        this.status = status;
    }
    
    public int getIdPeminjaman() {
        return idPeminjaman;
    }

    public void setIdPeminjaman(int idPeminjaman) {
        this.idPeminjaman = idPeminjaman;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public void setAnggota(Anggota anggota) {
        this.anggota = anggota;
    }

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public String getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(String tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public String getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(String tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Peminjaman getById(int id) {
        Peminjaman pj = new Peminjaman();
        ResultSet rs = DBHelper.selectQuery(
            "SELECT p.idpeminjaman, p.tanggalpinjam, p.tanggalkembali, p.status, " +
            "a.idanggota, a.nama AS namaAnggota, a.alamat AS alamatAnggota, a.telepon AS teleponAnggota, " +
            "b.idbuku, b.judul AS judulBuku, b.penerbit AS penerbitBuku, b.penulis AS penulisBuku " +
            "FROM peminjaman p " +
            "LEFT JOIN anggota a ON p.idanggota = a.idanggota " +
            "LEFT JOIN buku b ON p.idbuku = b.idbuku " +
            "WHERE p.idpeminjaman = " + id
        );

        try {
            while (rs.next()) {
                pj.setIdPeminjaman(rs.getInt("idpeminjaman"));
                pj.getAnggota().setIdAnggota(rs.getInt("idanggota"));
                pj.getAnggota().setNama(rs.getString("namaAnggota"));
                pj.getAnggota().setAlamat(rs.getString("alamatAnggota"));
                pj.getAnggota().setTelepon(rs.getString("teleponAnggota"));

                pj.getBuku().setIdBuku(rs.getInt("idbuku"));
                pj.getBuku().setJudul(rs.getString("judulBuku"));
                pj.getBuku().setPenulis(rs.getString("penulisBuku"));
                pj.getBuku().setPenerbit(rs.getString("penerbitBuku"));

                pj.setTanggalPinjam(rs.getString("tanggalpinjam"));
                // Saat mengambil, pastikan kolom bisa null
                pj.setTanggalKembali(rs.getString("tanggalkembali")); // Ini akan null jika di DB null
                pj.setStatus(rs.getInt("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pj;
    }

    public ArrayList<Peminjaman> getAll() {
        ArrayList<Peminjaman> ListPeminjaman = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery(
            "SELECT p.idpeminjaman, p.tanggalpinjam, p.tanggalkembali, p.status, " +
            "a.idanggota, a.nama AS namaAnggota, a.alamat AS alamatAnggota, a.telepon AS teleponAnggota, " +
            "b.idbuku, b.judul AS judulBuku, b.penerbit AS penerbitBuku, b.penulis AS penulisBuku " +
            "FROM peminjaman p " +
            "LEFT JOIN anggota a ON p.idanggota = a.idanggota " +
            "LEFT JOIN buku b ON p.idbuku = b.idbuku"
        );

        try {
            while (rs.next()) {
                Peminjaman pj = new Peminjaman();
                pj.setIdPeminjaman(rs.getInt("idpeminjaman"));
                
                pj.getAnggota().setIdAnggota(rs.getInt("idanggota"));
                pj.getAnggota().setNama(rs.getString("namaAnggota"));
                pj.getAnggota().setAlamat(rs.getString("alamatAnggota"));
                pj.getAnggota().setTelepon(rs.getString("teleponAnggota"));

                pj.getBuku().setIdBuku(rs.getInt("idbuku"));
                pj.getBuku().setJudul(rs.getString("judulBuku"));
                pj.getBuku().setPenulis(rs.getString("penulisBuku"));
                pj.getBuku().setPenerbit(rs.getString("penerbitBuku"));

                pj.setTanggalPinjam(rs.getString("tanggalpinjam"));
                pj.setTanggalKembali(rs.getString("tanggalkembali"));
                pj.setStatus(rs.getInt("status"));

                ListPeminjaman.add(pj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListPeminjaman;
    }
    

    public ArrayList<Peminjaman> getAllByAnggotaId(int idAnggota) {
        ArrayList<Peminjaman> listPeminjaman = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery(
            "SELECT p.idpeminjaman, p.tanggalpinjam, p.tanggalkembali, p.status, " +
            "a.idanggota, a.nama AS namaAnggota, a.alamat AS alamatAnggota, a.telepon AS teleponAnggota, " +
            "b.idbuku, b.judul AS judulBuku, b.penerbit AS penerbitBuku, b.penulis AS penulisBuku " +
            "FROM peminjaman p " +
            "LEFT JOIN anggota a ON p.idanggota = a.idanggota " +
            "LEFT JOIN buku b ON p.idbuku = b.idbuku " +
            "WHERE p.idanggota = " + idAnggota
    );

    try {
        while (rs.next()) {
            // Logika di dalam try-catch ini sama persis dengan method getAll() Anda
            Peminjaman pj = new Peminjaman();
            pj.setIdPeminjaman(rs.getInt("idpeminjaman"));
            
            pj.getAnggota().setIdAnggota(rs.getInt("idanggota"));
            pj.getAnggota().setNama(rs.getString("namaAnggota"));
            pj.getAnggota().setAlamat(rs.getString("alamatAnggota"));
            pj.getAnggota().setTelepon(rs.getString("teleponAnggota"));

            pj.getBuku().setIdBuku(rs.getInt("idbuku"));
            pj.getBuku().setJudul(rs.getString("judulBuku"));
            pj.getBuku().setPenulis(rs.getString("penulisBuku"));
            pj.getBuku().setPenerbit(rs.getString("penerbitBuku"));

            pj.setTanggalPinjam(rs.getString("tanggalpinjam"));
            pj.setTanggalKembali(rs.getString("tanggalkembali"));
            pj.setStatus(rs.getInt("status"));

            listPeminjaman.add(pj);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return listPeminjaman;
}

    public ArrayList<Peminjaman> search(String keyword) {
        ArrayList<Peminjaman> ListPeminjaman = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery(
            "SELECT p.idpeminjaman, p.tanggalpinjam, p.tanggalkembali, p.status, " +
            "a.idanggota, a.nama AS namaAnggota, a.alamat AS alamatAnggota, a.telepon AS teleponAnggota, " +
            "b.idbuku, b.judul AS judulBuku, b.penerbit AS penerbitBuku, b.penulis AS penulisBuku " +
            "FROM peminjaman p " +
            "LEFT JOIN anggota a ON p.idanggota = a.idanggota " +
            "LEFT JOIN buku b ON p.idbuku = b.idbuku " +
            "WHERE b.judul LIKE '%" + keyword + "%' " +
            "   OR a.nama LIKE '%" + keyword + "%' " +
            "   OR p.tanggalpinjam LIKE '%" + keyword + "%' " +
            "   OR p.tanggalkembali LIKE '%" + keyword + "%' " +
            "   OR p.status LIKE '%" + keyword + "%'"
        );

        try {
            while (rs.next()) {
                Peminjaman pj = new Peminjaman();
                pj.setIdPeminjaman(rs.getInt("idpeminjaman"));
                
                pj.getAnggota().setIdAnggota(rs.getInt("idanggota"));
                pj.getAnggota().setNama(rs.getString("namaAnggota"));
                pj.getAnggota().setAlamat(rs.getString("alamatAnggota"));
                pj.getAnggota().setTelepon(rs.getString("teleponAnggota"));

                pj.getBuku().setIdBuku(rs.getInt("idbuku"));
                pj.getBuku().setJudul(rs.getString("judulBuku"));
                pj.getBuku().setPenulis(rs.getString("penulisBuku"));
                pj.getBuku().setPenerbit(rs.getString("penerbitBuku"));

                pj.setTanggalPinjam(rs.getString("tanggalpinjam"));
                pj.setTanggalKembali(rs.getString("tanggalkembali"));
                pj.setStatus(rs.getInt("status"));

                ListPeminjaman.add(pj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListPeminjaman;
    }

    public void save() {
    // Logika untuk menangani nilai NULL pada tanggal kembali untuk SQL
    String tanggalKembaliUntukSQL;
    if (this.tanggalKembali == null || this.tanggalKembali.isEmpty()) {
        tanggalKembaliUntukSQL = "NULL";
    } else {
        tanggalKembaliUntukSQL = "'" + this.tanggalKembali + "'";
    }

    // Cek apakah ini data baru (INSERT) atau data lama (UPDATE)
    if (getById(idPeminjaman).getIdPeminjaman() == 0) {
    // Jika ID tidak ditemukan, lakukan INSERT
    String SQL = "INSERT INTO peminjaman (idanggota, idbuku, tanggalpinjam, tanggalkembali, status) VALUES("
            + this.getAnggota().getIdAnggota() + ", "
            + this.getBuku().getIdBuku() + ", "
            + "'" + this.tanggalPinjam + "', "
            + tanggalKembaliUntukSQL + ", "
            + "'" + this.status + "')";
    
    System.out.println("SQL INSERT yang akan dieksekusi: " + SQL);
    this.idPeminjaman = DBHelper.insertQueryGetId(SQL);

} else {
    // Jika ID sudah ada, lakukan UPDATE
    String SQL = "UPDATE peminjaman SET "
            + "idanggota = " + this.getAnggota().getIdAnggota() + ", "
            + "idbuku = " + this.getBuku().getIdBuku() + ", "
            + "tanggalpinjam = '" + this.tanggalPinjam + "', "
            + "tanggalkembali = " + tanggalKembaliUntukSQL + ", "
            + "status = '" + this.status + "' "
            + "WHERE idpeminjaman = " + this.idPeminjaman;

    System.out.println("SQL UPDATE yang akan dieksekusi: " + SQL);
    DBHelper.executeQuery(SQL);
    }
}

    public void delete() {
        String SQL = "DELETE FROM peminjaman WHERE idpeminjaman = " + this.idPeminjaman;
        System.out.println("SQL DELETE yang akan dieksekusi: " + SQL);
        DBHelper.executeQuery(SQL);
    }
}