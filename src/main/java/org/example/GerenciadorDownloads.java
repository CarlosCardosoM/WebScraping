package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDownloads {
    private final String urlBase;

    public GerenciadorDownloads(String urlBase) {
        this.urlBase = urlBase;
    }

    public List<String> baixarAnexos(String[] anexosDesejados, String dirTemp) throws IOException {
        GerenciadorArquivos.criarDiretorio(dirTemp);
        List<String> urlsAnexos = encontrarUrlsAnexos(anexosDesejados);
        List<String> arquivosBaixados = new ArrayList<>();

        for (int i = 0; i < urlsAnexos.size(); i++) {
            String nomeArquivo = dirTemp + "/Anexo_" + (i + 1) + ".pdf";
            baixarArquivo(urlsAnexos.get(i), nomeArquivo);
            arquivosBaixados.add(nomeArquivo);
            System.out.println("Baixado com sucesso: " + nomeArquivo);
        }

        return arquivosBaixados;
    }

    private List<String> encontrarUrlsAnexos(String[] anexosDesejados) throws IOException {
        System.out.println("Conectando ao site...");
        Document doc = Jsoup.connect(urlBase).get();
        Elements links = doc.select("a[href$=.pdf]");
        List<String> urlsAnexos = new ArrayList<>();

        for (String anexo : anexosDesejados) {
            boolean encontrado = false;
            for (Element link : links) {
                if (link.text().contains(anexo)) {
                    urlsAnexos.add(link.attr("abs:href"));
                    System.out.println("Encontrado: " + anexo + " - " + link.attr("abs:href"));
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                System.out.println("Aviso: " + anexo + " não encontrado na página");
            }
        }
        return urlsAnexos;
    }

    private void baixarArquivo(String urlArquivo, String caminhoLocal) throws IOException {
        System.out.println("Baixando: " + urlArquivo);
        try (InputStream in = new URL(urlArquivo).openStream()) {
            Files.copy(in, Paths.get(caminhoLocal));
        }
    }
}