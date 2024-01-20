package nsu.maxwell;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public class MyFileWriter implements AutoCloseable {
    private final static String floatTypeFilePath = "floats.txt";
    private final static String intTypeFilePath = "integers.txt";
    private final static String stringTypeFilePath = "strings.txt";

    private FileWriter intTypeFileWriter;
    private FileWriter floatTypeFileWriter;
    private FileWriter stringTypeFileWriter;

    private String prefix = "";
    private String path = "";

    private final boolean isAppend;
    public MyFileWriter(String prefix, String path, boolean isAppend)  {
        this.prefix = prefix;
        this.path = path;
        this.isAppend = isAppend;
    }

    public void write(BigInteger value) throws IOException {

        if (intTypeFileWriter == null) {
            File file = new File((!Objects.equals(path, "") ? (path + '/') : path) + prefix + intTypeFilePath);
            intTypeFileWriter = new FileWriter(file, isAppend);
        }

        intTypeFileWriter.write(value.toString() + '\n');
    }

    public void write(String value) throws IOException {
        if (stringTypeFileWriter == null) {
            File file = new File((!Objects.equals(path, "") ? (path + '/') : path) + prefix + stringTypeFilePath);

            stringTypeFileWriter = new FileWriter(file, isAppend);
        }

        stringTypeFileWriter.write(value + '\n');
    }

    public void write(BigDecimal value) throws IOException{
        if (floatTypeFileWriter == null) {
            File file = new File((!Objects.equals(path, "") ? (path + '/') : path) + prefix + floatTypeFilePath);
            floatTypeFileWriter = new FileWriter(file, isAppend);
        }

        floatTypeFileWriter.write(value.toString() + '\n');
    }

    public void close() {
        try {
            if (intTypeFileWriter != null) {
                intTypeFileWriter.close();
            }

        } catch (IOException e) {
            System.err.println("Can't close file: " + e.getMessage());
        }

        try {
            if (floatTypeFileWriter != null) {
                floatTypeFileWriter.close();
            }
        } catch (IOException e) {
            System.err.println("Can't close file: " + e.getMessage());
        }

        try {
            if (stringTypeFileWriter != null) {
                stringTypeFileWriter.close();
            }
        } catch (IOException e) {
            System.err.println("Can't close file: " + e.getMessage());
        }
    }
}
