import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import java.io.*;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SomeClass {



    public static void main(String[] args) {

        File directory = new File(getPath());
        HashMap<String, List<String>> directories = dirSort(directory);
        String time = null;
        try {
            time = getTime();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File zipDir = new File(directory + time);
        zipDir.mkdirs();
        directories.forEach((k,v) -> zipCompress(v, zipDir + "\\" + k));
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

    public static HashMap<String, List<String>> dirSort(File file){
        File catalog = new File(getPath());
        String s[] = catalog.list();
        List<String> am = new ArrayList<>();
        List<String> nz = new ArrayList<>();
        List<String> digit = new ArrayList<>();
        HashMap<String,List<String>> dir = new HashMap<>();

        for (int i = 0; i < s.length; i++){
            if (s[i].matches(".*\\d{4,}.*")){
                digit.add(file.getPath() + "\\" + s[i]);
            }
            else if (s[i].matches("[a-mA-M].*")){
                am.add(file.getPath() + "\\" + s[i]);
            }
            else if (s[i].matches("[n-zN-Z].*")){
                nz.add(file.getPath() + "\\" + s[i]);
            }
        }
        dir.put("am.zip", am);
        dir.put("nz.zip", nz);
        dir.put("digit.zip", digit);
        return dir;
    }

    public static String getTime () throws IOException {
        String TIME_SERVER = "pool.ntp.org";
//        www.pool.ntp.org поддерживает round-robin списки публичных Stratum-2 NTP серверов.
//        Таким образом обеспечивается балансировка нагрузки, и они практически всегда доступны.
        NTPUDPClient timeClient = new NTPUDPClient();
        InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);
        TimeInfo timeInfo = timeClient.getTime(inetAddress);
        long returnTime = timeInfo.getReturnTime();
        Date time = new Date(returnTime);
        String pattern = "yyyy-MM-dd HH-24-mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(time);
    }

    public static void zipCompress (List<String> list, String filename){
        try (FileOutputStream fos = new FileOutputStream(filename);ZipOutputStream zipOut = new ZipOutputStream(fos)){
            for (String fname : list){
                File fileToZip = new File(fname);
                FileInputStream fis = new FileInputStream(fileToZip);
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);

                byte[]bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0){
                    zipOut.write(bytes, 0, length);
                }
                fis.close();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
