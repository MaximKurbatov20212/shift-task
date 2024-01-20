package nsu.maxwell;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class FileFilter {
    private final static String intRegex = "[+-]?[0-9]+";
    private final static String floatRegex = "[+-]?[0-9]+([.][0-9]+)?([eE][+-]?[0-9]+)?";
    private final InfoCollector infoCollector = new InfoCollector();
    private MyFileWriter writer;
    private final FileFilterConfig config;

    public FileFilter(FileFilterConfig config) {
        this.config = config;
    }

    public void filter(List<String> files) {
        try (MyFileWriter writer = new MyFileWriter(config.getPrefix(), config.getPath(), config.getFlags().contains(Flag.A))) {

            for (String file : files) {
                try ( BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                    filterData(bufferedReader, writer);
                } catch (IOException e) {
                    System.err.println("No such file: " + file);
                }
            }

            if (config.getFlags().contains(Flag.F)) {
                System.out.println(infoCollector.getFullStat());
            }
            if (config.getFlags().contains(Flag.S)) {
                System.out.println(infoCollector.getReduceStat());
            }
        }
    }

    private void filterData(BufferedReader bufferedReader, MyFileWriter writer) {
        try {
            while (true) {
                String line = bufferedReader.readLine();

                if (line == null) {
                    return;
                }

                if (line.matches(intRegex)) {
                    BigInteger value = new BigInteger(line);
                    writer.write(value);
                    infoCollector.add(value);
                }

                else if (line.matches(floatRegex)) {
                    BigDecimal value = new BigDecimal(line);
                    writer.write(value);
                    infoCollector.add(value);
                }

                else {
                    writer.write(line);
                    infoCollector.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Can't write to file. " + e.getMessage());
        }
    }
}
