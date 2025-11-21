//12S25026-Andere Bonaran Situngkir
//12S25029-Theressia Olivia Simanjuntak
import java.util.*;
import java.lang.Math;

public class TLAST {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        String[] deskripsi = new String[10], kodeMK = new String[10], matkul = new String[10], dosen = new String[10], deadline = new String[10], keterangan = new String[10], status = new String[10];
        int[] kesulitan = new int[10], hari = new int[10];
        double[] prioritas = new double[10];
        int jumlahTugas;
        String perintah;

        perintah = "";
        jumlahTugas = 0;
        do {
            perintah = input.nextLine();
            if (perintah.equals("---")) {
            } else {
                if (perintah.equals("Add task")) {
                    jumlahTugas = tambahTugas(jumlahTugas, deskripsi, kodeMK, matkul, dosen, deadline, keterangan, status, kesulitan, hari, prioritas);
                } else {
                    if (perintah.equals("Update task status")) {
                        updateStatus(jumlahTugas, kodeMK, status);
                    } else {
                        if (perintah.equals("Show assigment")) {
                            tampilkanTugas(jumlahTugas, deskripsi, kodeMK, matkul, dosen, deadline, keterangan, kesulitan, hari, status, prioritas);
                        }
                    }
                }
            }
        } while (!perintah.equals("---"));
    }
    
    public static String dapatkanRekomendasi(double skor) {
        String rekomendasi;

        if (skor > 3) {
            rekomendasi = "Penting! Anda harus mengerjakan tugas ini segera";
        } else {
            if (skor >= 1.5) {
                rekomendasi = "Tugas ini memiliki prioritas menengah";
            } else {
                rekomendasi = "Tugas ini relatif ringan, namun jangan tunda terlalu lama";
            }
        }
        
        return rekomendasi;
    }
    
    public static double hitungPrioritas(int kesulitan, int hari) {
        double hasil;

        if (hari == 0) {
            hasil = kesulitan;
        } else {
            hasil = kesulitan * 1.0 / hari;
        }
        
        return hasil;
    }
    
    public static int tambahTugas(int jumlahTugas, String[] deskripsi, String[] kodeMK, String[] matkul, String[] dosen, String[] deadline, String[] keterangan, String[] status, int[] kesulitan, int[] hari, double[] prioritas) {
        int hasil;

        hasil = jumlahTugas;
        if (jumlahTugas >= 10) {
            System.out.println("Daftar Tugas Penuh!");
        } else {
            deskripsi[jumlahTugas] = input.nextLine();
            kodeMK[jumlahTugas] = input.nextLine();
            matkul[jumlahTugas] = input.nextLine();
            dosen[jumlahTugas] = input.nextLine();
            deadline[jumlahTugas] = input.nextLine();
            keterangan[jumlahTugas] = input.nextLine();
            kesulitan[jumlahTugas] = Integer.parseInt(input.nextLine());
            hari[jumlahTugas] = Integer.parseInt(input.nextLine());
            status[jumlahTugas] = input.nextLine();
            prioritas[jumlahTugas] = hitungPrioritas(kesulitan[jumlahTugas], hari[jumlahTugas]);
            hasil = jumlahTugas + 1;
        }
        
        return hasil;
    }
    
    public static void tampilkanTugas(int jumlahTugas, String[] deskripsi, String[] kodeMK, String[] matkul, String[] dosen, String[] deadline, String[] keterangan, int[] kesulitan, int[] hari, String[] status, double[] prioritas) {
        int i, j;
        String tempString;
        int tempInt;
        double tempReal;
        String pesanRekomendasi;

        for (i = 0; i <= jumlahTugas - 2; i++) {
            for (j = 0; j <= jumlahTugas - i - 2; j++) {
                if (prioritas[j] < prioritas[j + 1]) {
                    tempReal = prioritas[j];
                    prioritas[j] = prioritas[j + 1];
                    prioritas[j + 1] = tempReal;
                    tempString = deskripsi[j];
                    deskripsi[j] = deskripsi[j + 1];
                    deskripsi[j + 1] = tempString;
                    tempString = kodeMK[j];
                    kodeMK[j] = kodeMK[j + 1];
                    kodeMK[j + 1] = tempString;
                    tempString = matkul[j];
                    matkul[j] = matkul[j + 1];
                    matkul[j + 1] = tempString;
                    tempString = dosen[j];
                    dosen[j] = dosen[j + 1];
                    dosen[j + 1] = tempString;
                    tempString = deadline[j];
                    deadline[j] = deadline[j + 1];
                    deadline[j + 1] = tempString;
                    tempString = keterangan[j];
                    keterangan[j] = keterangan[j + 1];
                    keterangan[j + 1] = tempString;
                    tempString = status[j];
                    status[j] = status[j + 1];
                    status[j + 1] = tempString;
                    tempInt = kesulitan[j];
                    kesulitan[j] = kesulitan[j + 1];
                    kesulitan[j + 1] = tempInt;
                    tempInt = hari[j];
                    hari[j] = hari[j + 1];
                    hari[j + 1] = tempInt;
                }
            }
        }
        for (i = 0; i <= jumlahTugas - 1; i++) {
            System.out.println("Prioritas: " + toFixed(prioritas[i],2));
            if (status[i].equals("Selesai")) {
                System.out.println(deskripsi[i] + "|" + kodeMK[i] + "|" + matkul[i] + "|" + dosen[i] + "|" + keterangan[i] + "|" + status[i]);
            } else {
                pesanRekomendasi = dapatkanRekomendasi(prioritas[i]);
                System.out.println(deskripsi[i] + "|" + kodeMK[i] + "|" + matkul[i] + "|" + dosen[i] + "|" + deadline[i] + "|" + keterangan[i] + "|" + status[i] + "|" + pesanRekomendasi);
            }
        }
    }
    
    public static void updateStatus(int jumlahTugas, String[] kodeMK, String[] status) {
        String kodeTarget, statusBaru;
        int i;

        kodeTarget = input.nextLine();
        statusBaru = input.nextLine();
        for (i = 0; i <= jumlahTugas - 1; i++) {
            if (kodeMK[i].equals(kodeTarget)) {
                status[i] = statusBaru;
            }
        }
    }
    
    private static String toFixed(double value, int digits) {
        return String.format("%." + digits + "f", value);
    }
}
