import java.time.LocalDateTime;

public class Carro extends Veiculo {
    public Carro(String placa, LocalDateTime horaEntrada) {
        super(placa, horaEntrada);
    }

    @Override
    public double getTarifaPorHora() {
        return 5.0;
    }

    @Override
    public double getMultaPorHora() {
        return 5.0;
    }

    @Override
    public int getTempoMaximoHoras() {
        return 12;
    }

    @Override
    public String getTipo() {
        return "Carro";
    }
}
