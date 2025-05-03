import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static final Estacionamento estacionamento = new Estacionamento();
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Registrar Entrada");
            System.out.println("2. Registrar Saída");
            System.out.println("3. Consultar Vagas");
            System.out.println("4. Veículos Estacionados");
            System.out.println("5. Relatório de Tarifas");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> registrarEntrada();
                case 2 -> registrarSaida();
                case 3 -> estacionamento.exibirVagasDisponiveis();
                case 4 -> estacionamento.listarVeiculosEstacionados();
                case 5 -> estacionamento.gerarRelatorioTarifas();
                case 6 -> {
                    System.out.println("Encerrando...");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void registrarEntrada() {
        System.out.print("Tipo (Carro/Moto/Caminhao): ");
        String tipo = scanner.nextLine();
        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        System.out.print("Hora de Entrada (yyyy-MM-dd HH:mm): ");
        String entradaStr = scanner.nextLine();
        LocalDateTime entrada = LocalDateTime.parse(entradaStr, formatter);

        if (estacionamento.registrarEntrada(tipo, placa, entrada)) {
            System.out.println("Entrada registrada com sucesso!");
        } else {
            System.out.println("Erro: tipo inválido, placa duplicada ou sem vagas disponíveis.");
        }
    }

    private static void registrarSaida() {
        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        System.out.print("Hora de Saída (yyyy-MM-dd HH:mm): ");
        String saidaStr = scanner.nextLine();
        LocalDateTime saida = LocalDateTime.parse(saidaStr, formatter);
        double total = estacionamento.registrarSaida(placa, saida);
        if (total == -1) {
            System.out.println("Erro: veículo não encontrado.");
        }
    }
}
