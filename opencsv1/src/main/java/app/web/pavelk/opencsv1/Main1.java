package app.web.pavelk.opencsv1;

import com.opencsv.*;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {

    }
}

class Reader4 {
    public static void main(String[] args) throws IOException, CsvException {
        Path path = Paths.get("file4.csv");
        Reader reader = Files.newBufferedReader(path);
        List<String[]> strings = new CSVReader(reader).readAll();
        strings.forEach(f -> {
            System.out.print(f[0]);
            System.out.print(f[1]);
            System.out.print(f[2]);
            System.out.print(f[3]);
            System.out.println(f[4]);

            System.out.println(Long.valueOf(f[2]));
            System.out.println(LocalDate.parse(f[3]));
            System.out.println(Double.valueOf(f[4]));

        });
    }
}


class Reader1 {
    public static void main(String[] args) throws IOException, CsvException {
        Path path = Paths.get("file4.csv");
        Reader reader = Files.newBufferedReader(path);
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> list = new ArrayList<>();
        list = csvReader.readAll();
        reader.close();
        csvReader.close();
        list.forEach(f -> {
            System.out.println(f[0]);
            System.out.println(f[1]);
            System.out.println(f[2]);
            System.out.println(f[3]);
        });
    }
}

class Reader2 {
    public static void main(String[] args) throws IOException, CsvException {

        Path path = Paths.get("file1.csv");
        Reader reader = Files.newBufferedReader(path);

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withQuoteChar('\'')
                .withIgnoreQuotations(true)
                .build();

        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withSkipLines(0)
                .withCSVParser(parser)
                .build();

        List<String[]> strings = csvReader.readAll();

        reader.close();
        csvReader.close();
        strings.forEach(f -> {
            System.out.print(f[0]);
            System.out.print(f[1]);
            System.out.println(f[2]);
        });
    }
}


class DataDto {
    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "s1")
    String s1;
    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "s2")
    String s2;
    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "l1")
    Long l1;
    @CsvBindByPosition(position = 3)
    @CsvBindByName(column = "localDate1")
    LocalDate localDate1;
    @CsvBindByPosition(position = 4)
    @CsvBindByName(column = "d1")
    Double d1;

    public DataDto(String s1, String s2, Long l1, LocalDate localDate1, Double d1) {
        this.s1 = s1;
        this.s2 = s2;
        this.l1 = l1;
        this.localDate1 = localDate1;
        this.d1 = d1;
    }

}


//из дто
class Writer4 {
    public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

        List<DataDto> l1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            l1.add(new DataDto("s1", "s2" + i, 23l, LocalDate.now(), 243.0));
        }
        Path path = Paths.get("file4.csv");
        CSVWriter writer = new CSVWriter(new FileWriter(path.toString()));
        StatefulBeanToCsv<DataDto> beanToCsv = new StatefulBeanToCsvBuilder<DataDto>(writer).build();


        beanToCsv.write(l1);
        writer.close();

    }
}


//содать в памяти
class Writer3 {
    public static void main(String[] args) throws IOException {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        OutputStreamWriter streamWriter = new OutputStreamWriter(stream);
        CSVWriter writer = new CSVWriter(streamWriter);

        List<String[]> l1 = new ArrayList(50);
        for (int i = 0; i < 50; i++) {
            l1.add(new String[]{"wer", "e" + i, "t" + (i * 2)});
        }
        writer.writeAll(l1);
        streamWriter.flush();
        stream.toByteArray();

    }
}

//свои настройки
class Writer2 {
    public static void main(String[] args) throws IOException {

        Path path = Paths.get("file2.csv");
        Writer writer = Files.newBufferedWriter(path);

        ICSVWriter build = new CSVWriterBuilder(writer)
                .withSeparator('\t')
                .withQuoteChar('\'')
                .build();

        List<String[]> l1 = new ArrayList(50);
        for (int i = 0; i < 50; i++) {
            l1.add(new String[]{"qqq", "www" + i, "eee" + (i * 2)});
        }
        build.writeAll(l1);
        build.close();
    }
}


class Writer1 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("file1.csv");
        CSVWriter writer = new CSVWriter(new FileWriter(path.toString()));

        List<String[]> l1 = new ArrayList(50);
        for (int i = 0; i < 50; i++) {
            l1.add(new String[]{"wer", "e" + i, "t" + (i * 2)});
        }

        for (String[] array : l1) {
            writer.writeNext(array);
        }
        writer.close();
    }
}
