package app.web.pavelk.shell1;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

class MainW {
    public static void main(String[] args) throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println(os);

        String command1 = "powershell.exe  $PSVersionTable.PSVersion";
        Process process1 = Runtime.getRuntime().exec(command1);
        System.out.println(process1.pid());

        BufferedReader bufferedReader1 = process1.inputReader();
        String c1 = bufferedReader1.lines().collect(Collectors.joining());
        System.out.println(c1);

        //language=PS1
        String command2 = """
                powershell.exe Write-Output 'start shell';
                Add-Type -AssemblyName System.speech;
                $speak = New-Object System.Speech.Synthesis.SpeechSynthesizer;
                $speak.Speak('This is os %s and shell version %s.');
                Write-Output 'finish shell';
                """.formatted(os, c1);

        Process process2 = Runtime.getRuntime().exec(command2);
        System.out.println(process2.pid());
        System.out.println(process2.inputReader().lines().collect(Collectors.joining()));
    }
}
