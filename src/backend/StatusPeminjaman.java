package backend;

public enum StatusPeminjaman {
    DIPINJAM(0, "Belum Kembali"),
    DIKEMBALIKAN(1, "Sudah Kembali");

    private final int kode;
    private final String deskripsi;

    StatusPeminjaman(int kode, String deskripsi) {
        this.kode = kode;
        this.deskripsi = deskripsi;
    }

    public int getKode() {
        return kode;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    // Metode utilitas untuk mendapatkan enum dari kode integer
    public static StatusPeminjaman fromKode(int kode) {
        for (StatusPeminjaman status : StatusPeminjaman.values()) {
            if (status.getKode() == kode) {
                return status;
            }
        }
        throw new IllegalArgumentException("Kode status tidak valid: " + kode);
    }
}