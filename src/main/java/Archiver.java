import Service.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Archiver {
    public static void main(String[] args) {
        ArchiveFactory factory = new ArchiveFactory();
////        Получил путь к начальной директории
        File directory = new File(PropCache.getInstance().getProperty("catalog"));
//        System.out.println(directory.getAbsolutePath());
//
//        Отсортировал файлы в директории
        HashMap<String, List<File>> dirList = new DirectorySortService().dirSort(directory);

//        Создаю новую директорию
        TimeService timeService = new TimeService();
        File destination = new File(directory + timeService.getTime());
        System.out.println("DESTINATION " + destination);
        boolean a = destination.mkdirs();

//        Пишу в архив, используя паттерн "Фабрика"
        ArchiveService zip = factory.getArchiveType(ArchiveType.ZIP);
        ArchiveService copyServ = factory.getArchiveType(ArchiveType.COPY);
        dirList.forEach((k,v) -> {
            if (!"nz".equals(k)){
                zip.compress(v, destination + "\\" + k);
            }
        });
        List<File> copied = dirList.get("nz");
        copyServ.compress(copied, destination.getPath());

    }
}
