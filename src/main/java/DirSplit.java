import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class DirSplit implements FilenameFilter {
    private Pattern pattern;

    public DirSplit(String regex) {
        pattern = Pattern.compile(regex);
    }

    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}
