package nsu.maxwell;

public class Main {
    public static void main(String[] args)  {

        FileFilterConfig config = ArgsParser.getConfig(args);

        FileFilter fileFilter = new FileFilter(config);
        fileFilter.filter(config.getSrcFiles().stream().toList());
    }
}