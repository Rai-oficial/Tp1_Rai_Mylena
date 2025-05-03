import java.time.LocalDateTime;

public class Moto extends Veiculo {
    public Moto(String placa, LocalDateTime horaEntrada) {
        super(placa, horaEntrada);
    }

    @Override
    public double getTarifaPorHora() {
        return 3.0;
    }

    @Override
    public double getMultaPorHora() {
        return 3.0;
    }

    @Override
    public int getTempoMaximoHoras() {
        return 24;
    }

    @Override
    public String getTipo() {
        return "Moto";
    }
}
