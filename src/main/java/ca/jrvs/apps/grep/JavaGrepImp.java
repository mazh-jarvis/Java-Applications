package ca.jrvs.apps.grep;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JavaGrepImp implements JavaGrep {

    private String regex,
            rootPath,
            outFile;

    @Override
    public void process() throws IOException {
        List<String> matchedLines = new ArrayList<>();
        for(File file: listFiles(getRootPath())) {
            for (String line: readLines(file))
                if (containsPattern(line))
                    matchedLines.add(line);
        }
        writeToFile(matchedLines);
    }

    @Override
    public List<File> listFiles(String rootDir) {
        return Arrays.stream(new File(rootDir).listFiles()).filter(file -> file.isFile()).collect(Collectors.toList());
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
        Writer out =  new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(this.outFile)));
//        lines.stream().forEach(out::append);
        for(String line: lines)
            out.append(line)
                    .append("\n");
        out.close();
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
        return outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }
}
