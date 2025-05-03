import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Estacionamento {
    private final Map<String, Veiculo> veiculosEstacionados = new HashMap<>();
    private final int maxCarros = 50;
    private final int maxMotos = 30;
    private final int maxCaminhoes = 10;
    private double totalArrecadado = 0.0;

    public boolean registrarEntrada(String tipo, String placa, LocalDateTime horaEntrada) {
        if (veiculosEstacionados.containsKey(placa)) return false;

        Veiculo veiculo;
        switch (tipo.toLowerCase()) {
            case "carro":
                if (getQuantidade("Carro") >= maxCarros) return false;
                veiculo = new Carro(placa, horaEntrada);
                break;
            case "moto":
                if (getQuantidade("Moto") >= maxMotos) return false;
                veiculo = new Moto(placa, horaEntrada);
                break;
            case "caminhao":
                if (getQuantidade("Caminhao") >= maxCaminhoes) return false;
                veiculo = new Caminhao(placa, horaEntrada);
                break;
            default:
                return false;
        }

        veiculosEstacionados.put(placa, veiculo);
        return true;
    }

    public double registrarSaida(String placa, LocalDateTime horaSaida) {
        Veiculo veiculo = veiculosEstacionados.remove(placa);
        if (veiculo == null) return -1;

        long minutos = Duration.between(veiculo.getHoraEntrada(), horaSaida).toMinutes();
        double horas = Math.ceil(minutos / 60.0);
        double tarifa = horas * veiculo.getTarifaPorHora();

        double multa = 0.0;
        if (horas > veiculo.getTempoMaximoHoras()) {
            double horasExcedidas = horas - veiculo.getTempoMaximoHoras();
            multa = horasExcedidas * veiculo.getMultaPorHora();
        }

        double total = tarifa + multa;
        totalArrecadado += total;

        System.out.printf("Veículo: %s | Placa: %s | Entrada: %s | Saída: %s | Tarifa: R$ %.2f | Multa: R$ %.2f | Total: R$ %.2f%n",
                veiculo.getTipo(), veiculo.getPlaca(), veiculo.getHoraEntrada(), horaSaida, tarifa, multa, total);

        return total;
    }

    public void exibirVagasDisponiveis() {
        System.out.printf("Carros: %d/%d%n", maxCarros - getQuantidade("Carro"), maxCarros);
        System.out.printf("Motos: %d/%d%n", maxMotos - getQuantidade("Moto"), maxMotos);
        System.out.printf("Caminhões: %d/%d%n", maxCaminhoes - getQuantidade("Caminhao"), maxCaminhoes);
    }

    public void listarVeiculosEstacionados() {
        veiculosEstacionados.values().forEach(v -> {
            System.out.printf("%s | Placa: %s | Entrada: %s%n", v.getTipo(), v.getPlaca(), v.getHoraEntrada());
        });
    }

    public void gerarRelatorioTarifas() {
        System.out.printf("Total arrecadado até agora: R$ %.2f%n", totalArrecadado);
    }

    private long getQuantidade(String tipo) {
        return veiculosEstacionados.values().stream().filter(v -> v.getTipo().equalsIgnoreCase(tipo)).count();
    }
}

