package projedata_app;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {

	public static void main(String[] args) {
		
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		// 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.

		List<Funcionario> funcionarios = new ArrayList<>();

		funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
		funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
		funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
		funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
		funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
		funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
		funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
		funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
		funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
		funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

		
		System.out.println("Lista de todos os Funcionários com formatação de Data de Nascimento e Salário:\n");
		for (Funcionario funcionario : funcionarios) {
			System.out.println(funcionario.getNome() + " -> " + funcionario.getDataNascimento().format(dateTimeFormatter) + " -> "
							+ decimalFormat.format(funcionario.getSalario()) + " -> " + funcionario.getFuncao());
		}
		System.out.println("\n\n");
		
		
		// 3.2 – Remover o funcionário “João” da lista.
		
		for (int i = 0; i < funcionarios.size(); i++) {
			Funcionario funcionario = funcionarios.get(i);
			if (funcionario.getNome().equals("João")) {
				funcionarios.remove(i);
				break;
			}
		}

		// 3.3 – Imprimir todos os funcionários com formatação		

		System.out.println("Funcionários com formatação de Data de Nascimento e Salário(sem o João):\n");
		for (Funcionario funcionario : funcionarios) {
			System.out.println(
					funcionario.getNome() + " -> " + funcionario.getDataNascimento().format(dateTimeFormatter) + " -> "
							+ decimalFormat.format(funcionario.getSalario()) + " -> " + funcionario.getFuncao());
		}

		// 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
		
		for (Funcionario funcionario : funcionarios) {
			BigDecimal salarioAtual = funcionario.getSalario();
			BigDecimal aumento = salarioAtual.multiply(new BigDecimal("0.10"));
			BigDecimal novoSalario = salarioAtual.add(aumento);
			funcionario.setSalario(novoSalario.setScale(2, RoundingMode.HALF_UP));
		}
		
		System.out.println("\nLista atualizada com o respectivo aumento de 10%:\n");
		for (Funcionario funcionario : funcionarios) {
			System.out.println(funcionario.getNome() + " -> " + funcionario.getDataNascimento().format(dateTimeFormatter) + " -> "
							+ decimalFormat.format(funcionario.getSalario()) + " -> " + funcionario.getFuncao());
		}
		System.out.println("\n");

		// 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.

		Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
				.collect(Collectors.groupingBy(Funcionario::getFuncao));

		// 3.6 – Imprimir os funcionários, agrupados por função.
		
		System.out.println("\nFuncionários agrupados por função:");
		for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
			String funcao = entry.getKey();
			List<Funcionario> lista = entry.getValue();

			System.out.println("\nFunção: " + funcao);
			for (Funcionario funcionario : lista) {
				System.out.println(funcionario.getNome());
			}
		}

		// 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
		
		System.out.println("\nAniversariantes dos meses 10 e 12:\n");
		for (Funcionario funcionario : funcionarios) {
			int mes = funcionario.getDataNascimento().getMonthValue();
			if (mes == 10 || mes == 12) {
				System.out.println(funcionario.getNome() + " -> " + funcionario.getDataNascimento().format(dateTimeFormatter));
			}
		}

		// 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
		
		Funcionario maisVelho = funcionarios.get(0);
		LocalDate hoje = LocalDate.now();

		for (Funcionario funcionario : funcionarios) {
			LocalDate dataNascimento = funcionario.getDataNascimento();
			if (dataNascimento.isBefore(maisVelho.getDataNascimento())) {
				maisVelho = funcionario;
			}
		}

		int idade = hoje.getYear() - maisVelho.getDataNascimento().getYear();
		System.out.println("\nO Funcionário mais velho é o(a): " + maisVelho.getNome() + " -> Idade: " + idade);

		// 3.10 – Imprimir a lista de funcionários por ordem alfabética.
		
		System.out.println("\nFuncionários ordenados alfabeticamente:\n");
		List<Funcionario> funcionariosOrdenados = new ArrayList<>(funcionarios);
		funcionariosOrdenados.sort((f1, f2) -> f1.getNome().compareTo(f2.getNome()));

		for (Funcionario funcionario : funcionariosOrdenados) {
			System.out.println(funcionario.getNome());
		}

		// 3.11 – Imprimir o total dos salários dos funcionários.
		
		BigDecimal totalSalarios = BigDecimal.ZERO;

		for (Funcionario funcionario : funcionarios) {
			totalSalarios = totalSalarios.add(funcionario.getSalario());
		}

		System.out.println("\nTotal dos salários: " + totalSalarios);

		// 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
		
		BigDecimal salarioMinimo = new BigDecimal("1212.00");

		System.out.println("\nQuantidade de salários mínimos que cada funcionário recebe:\n");
		for (Funcionario funcionario : funcionarios) {
			BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
			System.out.println(funcionario.getNome() + ": " + salariosMinimos + " salários mínimos");
		}
	}

}
