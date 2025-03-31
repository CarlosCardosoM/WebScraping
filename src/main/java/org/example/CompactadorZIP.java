package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompactadorZIP {
    public static void compactar(List<String> arquivos, String arquivoZip) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(arquivoZip);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            for (String arquivo : arquivos) {
                adicionarAoZip(arquivo, zos);
            }
        }
    }

    private static void adicionarAoZip(String arquivo, ZipOutputStream zos) throws IOException {
        File file = new File(arquivo);
        try (FileInputStream fis = new FileInputStream(file)) {
            ZipEntry ze = new ZipEntry(file.getName());
            zos.putNextEntry(ze);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
            zos.closeEntry();
        }
    }
}
