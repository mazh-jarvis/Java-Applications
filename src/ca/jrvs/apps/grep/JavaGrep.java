package ca.jrvs.apps.grep;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface JavaGrep {
    /**
     * Top level search flow
     * @throws IOException
     */
    void process() throws IOException;

    /**
     * Traverse a given directory and return all files
     * @param rootDir input directory
     * @return files under the rootDir
     */
    List<File> listFiles(String rootDir);

    /**
     * Read a file and return all lines
     * (Explain FileReader, BufferedReader, and character encoding)
     * @param inputFile file to be read
     * @return lines
     * @throws IllegalArgumentException if given input is not a file
     */
    List<String> readLines(File inputFile) throws
            IllegalArgumentException, FileNotFoundException;

    /**
     *check if a line contain the regex pattern
     * @param line input string
     * @return true if there is a match
     */
    boolean containsPattern(String line);

    /**
     * Write lines to a file
     * Use FileOutputStream, OutputStreamWriter, BufferedWriter
     * @param lines matched lines
     * @throws IOException if write failed
     */
    void writeToFile(List<String> lines) throws IOException;

    String getRootPath();

    void setRootPath(String rootPath);

    String getRegex();

    void setRegex(String regex);

    String getOutFile();

    void setOutFile(String outFile);
}
