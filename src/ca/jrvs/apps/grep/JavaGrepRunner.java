package ca.jrvs.apps.grep;

import java.io.File;
import java.util.List;

public class JavaGrepRunner {
    public static void main(String[] args) {
        if (args.length != 3)
            throw new IllegalArgumentException(
                    "USAGE: regex rootPath outFile");

        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        try {
//            javaGrepImp.process();
            List<String> files = javaGrepImp.readLines(new File("/home/milad/code/jarvis/bash_sql/regex/q3"));
            files.stream().forEach(System.out::println);
            /*List<File> files = javaGrepImp.listFiles(javaGrepImp.getRootPath());
            files.stream().forEach(System.out::println);*/
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
