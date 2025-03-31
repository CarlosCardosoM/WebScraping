package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String urlBase = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";
        String[] anexosDesejados = {"Anexo I", "Anexo II"};
        String arquivoZipSaida = "anexos_ans.zip";
        String dirTemp = "temp_anexos";

        try {
            // Gerenciador de downloads
            GerenciadorDownloads gerenciador = new GerenciadorDownloads(urlBase);

            // Baixar anexos
            List<String> arquivosBaixados = gerenciador.baixarAnexos(anexosDesejados, dirTemp);

            // Compactar em ZIP
            if (!arquivosBaixados.isEmpty()) {
                CompactadorZIP.compactar(arquivosBaixados, arquivoZipSaida);
                System.out.println("\nArquivo ZIP criado com sucesso: " + arquivoZipSaida);
            }

            // Limpar temporários
            GerenciadorArquivos.limparDiretorio(dirTemp);

        } catch (Exception e) {
            System.err.println("Erro durante o processo:");
            e.printStackTrace();
        }
    }
}