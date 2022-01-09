package app.web.pavelk.shell1;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.stream.Collectors;

class MainWSL {
    public static void main(String[] args) throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println(os);

        String absolutePath = new File("shell1/p2.png").toURI().getPath().toLowerCase(Locale.ROOT)
                .replace(":", "");

        String command2 = """
                wsl tesseract %s outfile2
                """.formatted("/mnt" + absolutePath);

        Process process2 = Runtime.getRuntime().exec(command2);
        System.out.println(process2.pid());
        System.out.println(process2.inputReader().lines().collect(Collectors.joining()));
    }
}
