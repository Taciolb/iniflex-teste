package iniflex.util;

import java.util.List;

public class TableUtils {

    private TableUtils() {
    }

    public static void imprimir(String[] cabecalho, List<String[]> linhas, boolean[] alinharDireita) {
        int[] larguras = new int[cabecalho.length];
        for (int i = 0; i < cabecalho.length; i++) {
            larguras[i] = cabecalho[i].length();
        }
        for (String[] linha : linhas) {
            for (int i = 0; i < linha.length; i++) {
                larguras[i] = Math.max(larguras[i], linha[i].length());
            }
        }

        String separador = montarSeparador(larguras);
        System.out.println(separador);
        System.out.println(montarLinha(cabecalho, larguras, new boolean[cabecalho.length]));
        System.out.println(separador);
        for (String[] linha : linhas) {
            System.out.println(montarLinha(linha, larguras, alinharDireita));
        }
        System.out.println(separador);
    }

    private static String montarSeparador(int[] larguras) {
        StringBuilder sb = new StringBuilder("+");
        for (int largura : larguras) {
            sb.append("-".repeat(largura + 2)).append("+");
        }
        return sb.toString();

    }

    private static String montarLinha(String[] valores, int[] larguras, boolean[] alinharDireita) {
        StringBuilder sb = new StringBuilder("|");
        for (int i = 0; i < valores.length; i++) {
            String formato = alinharDireita[i] ? "%" + larguras[i] + "s" : "%-" + larguras[i] + "s";
            sb.append(" ").append(String.format(formato, valores[i])).append(" |");
        }
        return sb.toString();
    }
}
