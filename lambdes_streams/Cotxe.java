package mp3_uf5_2425.lambdes_streams;

public class Cotxe {
    private String marca;
    private String model;

    public Cotxe(String m, String mod) {
        marca = m;
        model = mod;
    }

    public String getMarca() {
        return marca;
    }

    @Override
    public String toString() {
        return "Cotxe{" +
                "marca='" + marca + '\'' +
                '}';
    }
}