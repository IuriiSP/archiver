package Service;

import java.io.File;
import java.util.List;

public interface ArchiveService {
    void compress(List<File> list, String filename);
}
