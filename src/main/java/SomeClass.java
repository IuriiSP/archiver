import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class SomeClass {
    public static void main(String[] args) {
        String dirname = "D:\\Java\\someDir";

        File f1 = new File(dirname);
        System.out.println(f1.isDirectory());
//        FilenameFilter a_m = new DirSplit("[a-m]*");
        String[] s = f1.list();
        List<String> am = new ArrayList<>();
        List<String> nz = new ArrayList<>();
        List<String> numbers = new ArrayList<>();
        List<String> other = new ArrayList<>();

        for (int i = 0; i < s.length; i++){
            if (s[i].matches(".*\\d{4,}.*"))numbers.add(s[i]);
            else if (s[i].matches("[a-mA-M].*")){
                am.add(s[i]);
            }
            else if (s[i].matches("[n-zN-Z].*")){
                nz.add(s[i]);
            }
        }
        System.out.println("\n a-n:");
        am.forEach(x -> System.out.println(x));
        System.out.println("\n n-z:");
        nz.forEach(x -> System.out.println(x));
        System.out.println("\n numbers:");
        numbers.forEach(x -> System.out.println(x));
        System.out.println("\n another:");
        other.forEach(x -> System.out.println(x));
    }

}
