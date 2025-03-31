package org.example;

import java.io.File;

public class GerenciadorArquivos {
    public static void criarDiretorio(String caminho) {
        File dir = new File(caminho);
        if (!dir.exists() && dir.mkdir()) {
            System.out.println("Diretório criado: " + caminho);
        }
    }

    public static void limparDiretorio(String caminho) {
        File diretorio = new File(caminho);
        if (diretorio.exists()) {
            deletarDiretorio(diretorio);
            System.out.println("Arquivos temporários removidos");
        }
    }

    private static void deletarDiretorio(File diretorio) {
        File[] arquivos = diretorio.listFiles();
        if (arquivos != null) {
            for (File arquivo : arquivos) {
                if (arquivo.isDirectory()) {
                    deletarDiretorio(arquivo);
                } else {
                    arquivo.delete();
                }
            }
        }
        diretorio.delete();
    }
}