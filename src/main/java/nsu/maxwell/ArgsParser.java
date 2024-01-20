package nsu.maxwell;

public class ArgsParser {
    static FileFilterConfig getConfig(String[] args) {
        FileFilterConfig config = new FileFilterConfig();

        for (int i = 0; i < args.length; ++i) {
            String arg = args[i];

            switch (arg) {
                case "-f" -> config.addFlag(Flag.F);
                case "-s" -> config.addFlag(Flag.S);
                case "-a" -> config.addFlag(Flag.A);

                case "-o" -> {
                    if (i == args.length - 1) {
                        System.err.println("Expected path of file, but got eof");
                        break;
                    }

                    config.setPath(args[++i]);
                    config.addFlag(Flag.O);
                }

                case "-p" -> {
                    if (i == args.length - 1) {
                        System.err.println("Expected prefix of file, but got eof");
                        break;
                    }

                    config.setPrefix(args[++i]);
                    config.addFlag(Flag.P);
                }

                default -> config.addSrcFile(arg);
            }
        }
        return config;
    }
}
