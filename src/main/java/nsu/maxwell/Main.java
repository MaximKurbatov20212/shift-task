package nsu.maxwell;

import java.util.Arrays;

public class Main {
    public static void main(String[] args)  {

        FileFilterConfig config = ArgsParser.getConfig(args[0].split(" "));

        FileFilter fileFilter = new FileFilter(config);
        fileFilter.filter(config.getSrcFiles().stream().toList());
    }
}