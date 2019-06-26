package ca.jrvs.apps.grep;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JavaGrepImp implements JavaGrep {

    private String regex,
            rootPath;
    private List<String> matchedLines;
    private List<File> files;

    @Override
    public void process() throws IOException {
        matchedLines = new ArrayList<>();
        for(File file: listFiles(getRootPath())) {
            for (String line: readLines(file))
                if (containsPattern(line))
                    matchedLines.add(line);
        }
    }

    @Override
    public List<File> listFiles(String rootDir) {
        if(files == null)
            files = new ArrayList<>();
        File file = new File(rootDir);
        for(File currentFile: file.listFiles()) {
            if(currentFile.isFile())
                files.add(currentFile);
            else
                listFiles(currentFile.getPath());
        }
        return files;
    }

    @Override
    public List<String> readLines(File inputFile)
            throws IllegalArgumentException, FileNotFoundException {
        List<String> lines = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(inputFile));
        in.lines().forEach(lines::add);
        return lines;
    }

    @Override
    public boolean containsPattern(String line) {

        return line.matches(this.regex);
    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {

    }

    @Override
    public String getRootPath() {
        return rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String getOutFile() {
        return null;
    }

    @Override
    public void setOutFile(String outFile) {

    }
}
