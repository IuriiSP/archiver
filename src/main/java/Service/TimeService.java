package Service;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeService {
    public String getTime() {
        String TIME_SERVER = "pool.ntp.org";
//        www.pool.ntp.org поддерживает round-robin списки публичных Stratum-2 NTP серверов.
//        Таким образом обеспечивается балансировка нагрузки, и они практически всегда доступны.
        NTPUDPClient timeClient = new NTPUDPClient();
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByName(TIME_SERVER);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        TimeInfo timeInfo = null;
        try {
            timeInfo = timeClient.getTime(inetAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long returnTime = 0;
        if (timeInfo != null) {
            returnTime = timeInfo.getReturnTime();
        }
        Date time = new Date(returnTime);
        String pattern = "yyyy-MM-dd HH-24-mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(time);
    }
}
