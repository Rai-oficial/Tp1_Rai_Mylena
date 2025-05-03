import java.time.LocalDateTime;

public abstract class Veiculo {
    protected String placa;
    protected LocalDateTime horaEntrada;

    public Veiculo(String placa, LocalDateTime horaEntrada) {
        this.placa = placa;
        this.horaEntrada = horaEntrada;
    }

    public String getPlaca() {
        return placa;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public abstract double getTarifaPorHora();
    public abstract double getMultaPorHora();
    public abstract int getTempoMaximoHoras();
    public abstract String getTipo();
}
