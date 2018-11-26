package project;

import java.util.*;

public class Constants {

    public static List<String> fileEndings = Arrays.asList("java", "py", "js", "css", "html", "jsx", "md");
    public static Map<String, String> fileEndingsMapping = createMap();

    private static Map<String, String> createMap()
    {
        Map<String,String> map = new HashMap<>();
        map.put("java", "Java");
        map.put("py", "Python");
        map.put("js", "JavaScript");
        map.put("jsx", "JavaScript");
        map.put("css", "CSS");
        map.put("html", "HTML");
        map.put("md", "Markdown");
        return map;
    }
}
