package responsi1;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class SistemLogistikBantuan {
    private static final String NAMA_FILE =
            "manifest_pengiriman.txt";
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<BarangBantuan> daftarBarang =
                new ArrayList<>();
        boolean programBerjalan = true;
        while (programBerjalan) {
            System.out.println("\n=== SISTEM LOGISTIK BANTUAN ===");
            System.out.println("1. Tambah Barang Bantuan");
            System.out.println("2. Selesai dan Cetak Manifes");
            System.out.print("Pilih menu: ");
            String pilihan = input.nextLine().trim();
            if (pilihan.equals("1")) {
                tambahBarang(input, daftarBarang);
            } else if (pilihan.equals("2")) {
                cetakManifes(daftarBarang);
                programBerjalan = false;
            } else {
                System.out.println(
                        "Pilihan tidak valid. Masukkan 1 atau 2."
                );
            }
        }
        input.close();
    }

    private static void tambahBarang(
            Scanner input,
            ArrayList<BarangBantuan> daftarBarang) {
        boolean berhasil = false;
        while (!berhasil) {
            try {
                System.out.print("Kode barang (EDX-): ");
                String kodeBarang = input.nextLine().trim();
                if (!kodeBarang.startsWith("EDX-")) {
                    throw new IllegalArgumentException("Kode barang harus diawali EDX-");
                }
                System.out.print("Nama barang: ");
                String namaBarang = input.nextLine().trim();
                System.out.print("Kategori barang: ");
                String kategori = input.nextLine().trim();
                System.out.print("Berat barang (kg): ");
                String teksBerat = input.nextLine().trim();
                double beratKg;
                try {
                    beratKg = Double.parseDouble(teksBerat);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Berat harus berupa angka, misalnya 12.5.");
                }
                if (Double.isNaN(beratKg)
                        || Double.isInfinite(beratKg)) {
                    throw new IllegalArgumentException("Berat harus berupa angka yang valid.");
                }
                if (beratKg <= 0) {
                    throw new BeratInvalidException("Berat barang harus lebih dari 0 kg.");
                }
                BarangBantuan barang = new BarangBantuan(kodeBarang,namaBarang,kategori,beratKg);
                daftarBarang.add(barang);
                System.out.println("Barang berhasil ditambahkan.");
                berhasil = true;
            } catch (BeratInvalidException
                     | IllegalArgumentException e) {
                System.out.println("Input tidak valid: " + e.getMessage());
                System.out.println("Silakan ulangi input barang.");
            } finally {
                System.out.println("Pemeriksaan input barang selesai.");
            }
        }
    }

    private static void cetakManifes(
            ArrayList<BarangBantuan> daftarBarang) {
        double totalBerat = 0.0;
        for (BarangBantuan barang : daftarBarang) {
            totalBerat += barang.getBeratKg();}
        String labelArmada;
        if (totalBerat < 50) {
            labelArmada = "Mobil Pickup";
        } else {
            labelArmada = "Truk Ekspedisi";
        }
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(NAMA_FILE))) {
            writer.write("MANIFES PENGIRIMAN BANTUAN");
            writer.newLine();
            writer.write("==============================================");
            writer.newLine();
            writer.write(String.format("%-4s | %-15s | %-25s | %-15s | %10s","No.", "Kode Barang", "Nama Barang","Kategori", "Berat (kg)"
            ));
            writer.newLine();
            writer.write("------------------------------------------------");
            writer.newLine();
            for (int i = 0; i < daftarBarang.size(); i++) {
                BarangBantuan barang = daftarBarang.get(i);
                writer.write(String.format(
                        "%-4d | %-15s | %-25s | %-15s | %10.2f",
                        i + 1,
                        barang.getKodeBarang(),
                        barang.getNamaBarang(),
                        barang.getKategori(),
                        barang.getBeratKg()
                ));
                writer.newLine();
            }
            writer.write("------------------------------------------------");
            writer.newLine();
            writer.write("Total barang: " + daftarBarang.size());
            writer.newLine();
            writer.write(String.format("Total berat: %.2f kg", totalBerat));
            writer.newLine();
            writer.write("Label armada: " + labelArmada);
            writer.newLine();
        } catch (IOException e) {
            System.out.println(
                    "Gagal mengekspor manifes: " + e.getMessage()
            );
            return;
        }

        System.out.println(
                "\nData manifes berhasil diekspor ke "
                + NAMA_FILE
        );
        System.out.println("Total barang: " + daftarBarang.size());
        System.out.printf("Total berat: %.2f kg%n", totalBerat);
        System.out.println("Label armada: " + labelArmada);
    }
}
