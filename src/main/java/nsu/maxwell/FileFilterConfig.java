package nsu.maxwell;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class FileFilterConfig {
    private String prefix = "";
    private String path = "";
    Set<Flag> flags = new HashSet<>();
    Set<String> srcFiles = new HashSet<>();

    public boolean addFlag(Flag flag) {
        return flags.add(flag);
    }

    public boolean addSrcFile(String file) {
        return srcFiles.add(file);
    }
}
