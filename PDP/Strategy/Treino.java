public interface Treino {
    void treinar();
}

public class TreinoGerente implements Treino {
    @Override
    public void treinar() {
        System.out.println("Treinamento específico para Gerente.");
    }
}

public class TreinoSupervisor implements Treino {
    @Override
    public void treinar() {
        System.out.println("Treinamento específico para Supervisor.");
    }
}

public class Funcionario {
    private Treino treino;

    public void setTreino(Treino treino) {
        this.treino = treino;
    }

    public void aplicarTreinamento() {
            treino.treinar();
    }

    public static void main(String[] args) {
        Funcionario funcionario = new Funcionario();

        // Gerente
        Treino treinoGerente = new TreinoGerente();
        funcionario.setTreino(treinoGerente);
        funcionario.aplicarTreinamento();

        // Supervisor
        Treino treinoSupervisor = new TreinoSupervisor();
        funcionario.setTreino(treinoSupervisor);
        funcionario.aplicarTreinamento();
    }
}
