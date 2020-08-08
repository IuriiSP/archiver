import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SomeClass {



    public static void main(String[] args) throws IOException {

        // чтение параметров имени каталога из ресурсов
        // получение времени от сервера
        // 1й метод создание каталога с учетом эксепшенов
        // 2й метод сортировка и разбиение (коллекции)
        // 3й запись в архив

        File directory = new File(getPath());
        List<List<String>> dirList = rootSplit(directory);
        System.out.println(getTime());
        dirList.forEach(x -> System.out.println(x));

    }

    public static String getPath(){
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "config.properties";
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String directoryName = props.getProperty("catalog");
        return directoryName;
    }

    public static List<List<String>> rootSplit (File file){
        File catalog = new File(getPath());
        String s[] = catalog.list();
        List<String> am = new ArrayList<>();
        List<String> nz = new ArrayList<>();
        List<String> numbers = new ArrayList<>();
        List<List<String>> dir = new ArrayList<>();

        for (int i = 0; i < s.length; i++){
            if (s[i].matches(".*\\d{4,}.*")){
                numbers.add(s[i]);
            }
            else if (s[i].matches("[a-mA-M].*")){
                am.add(s[i]);
            }
            else if (s[i].matches("[n-zN-Z].*")){
                nz.add(s[i]);
            }
        }
        dir.add(am);
        dir.add(nz);
        dir.add(numbers);

        return dir;
    }

    public static String getTime () throws IOException {
        String TIME_SERVER = "pool.ntp.org";
        NTPUDPClient timeClient = new NTPUDPClient();
        InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);
        TimeInfo timeInfo = timeClient.getTime(inetAddress);
        long returnTime = timeInfo.getReturnTime();
        Date time = new Date(returnTime);
        String pattern = "yyyy-MM-dd HH-24-mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(time);
    }

}
