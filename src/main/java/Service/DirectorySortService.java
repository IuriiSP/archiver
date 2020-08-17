package Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DirectorySortService {
    public HashMap<String, List<File>> dirSort(File catalog){
//        PathService pathService = new PathService();
//        catalog = new File(pathService.getPath());
        File[] files = catalog.listFiles();
        List<File> am = new ArrayList<>();
        List<File> nz = new ArrayList<>();
        List<File> digit = new ArrayList<>();
        HashMap<String,List<File>> dir = new HashMap<>();

        for (int i = 0; i < files.length; i++){
            if (files[i].getName().matches(".*\\d{4,}.*")){
                digit.add(files[i]);
            }
            else if (files[i].getName().matches("[a-mA-M].*")){
                am.add(files[i]);
            }
            else if (files[i].getName().matches("[n-zN-Z].*")){
                nz.add(files[i]);
            }
        }
        dir.put("am.zip", am);
        dir.put("nz", nz);
        dir.put("digit.zip", digit);
        return dir;
    }
}
