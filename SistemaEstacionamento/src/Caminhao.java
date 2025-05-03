import java.time.LocalDateTime;

public class Caminhao extends Veiculo {
    public Caminhao(String placa, LocalDateTime horaEntrada) {
        super(placa, horaEntrada);
    }

    @Override
    public double getTarifaPorHora() {
        return 10.0;
    }

    @Override
    public double getMultaPorHora() {
        return 10.0;
    }

    @Override
    public int getTempoMaximoHoras() {
        return 48;
    }

    @Override
    public String getTipo() {
        return "Caminhao";
    }
}
