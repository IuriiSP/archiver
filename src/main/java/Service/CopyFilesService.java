package Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class CopyFilesService implements ArchiveService{
    @Override
    public void compress(List<File> list, String filename) {

        System.out.println(filename + " filename!");
        File catalog = new File(filename);
        list.forEach(file -> {
            Path source = file.toPath();
            Path destination = Path.of(catalog + "\\" + file.getName());
            try{
                System.out.println(source);
                System.out.println(destination);
                Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}
