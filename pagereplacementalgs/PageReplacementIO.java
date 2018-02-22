package pagereplacementalgs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaioMoraes on 10/05/2017.
 * GitHub: MoraesCaio
 * Email: caiomoraes@msn.com
 */
public class PageReplacementIO
{
    private String entryFileName;
    private String outputFileName;
    private List<String> lines;
    private int pagesAvailable;
    private List<Integer> referenceSequence;
    private List<PageReplacementAlgorithm> pageRepAlgs;

    public String getEntryFileName()
    {
        return entryFileName;
    }

    public String getOutputFileName()
    {
        return outputFileName;
    }

    public PageReplacementIO(String entryFileName, String outputFileName)
    {
        this.entryFileName = entryFileName;
        this.outputFileName = outputFileName;
        lines = new ArrayList<>();
        pagesAvailable = 0;
        referenceSequence = new ArrayList<>();
        pageRepAlgs = new ArrayList<>();
    }

    private void read() throws IOException
    {
        //Checks if input file exists
        if (Files.notExists(Paths.get(entryFileName)))
        {
            throw new IOException("File doesn't exist: " + entryFileName);
        }

        //Reading file
        lines = Files.readAllLines(Paths.get(entryFileName));

        //Checking if input has the maximum number of pages and at least one page
        if (lines.size() < 2)
        {
            throw new IOException("Invalid entry file.");
        }

        //Maximum pages
        pagesAvailable = Integer.parseInt(lines.get(0));

        //Creating list of pages
        for (int i = 1; i < lines.size(); i++)
        {
            referenceSequence.add(Integer.parseInt(lines.get(i)));
        }
    }

    private void write() throws IOException
    {
        if (Files.notExists(Paths.get(outputFileName)))
        {
            Files.createFile(Paths.get(outputFileName));
        }

        //Clearing output file
        Files.write(Paths.get(outputFileName), "".getBytes());

        //Printing and writing results
        for (PageReplacementAlgorithm alg : pageRepAlgs)
        {
            System.out.println(alg);
            Files.write(Paths.get(outputFileName), (alg.toString()+"\n").getBytes(), StandardOpenOption.APPEND);
        }
    }

    public void run(boolean runFIFO, boolean runOTM, boolean runLRU) throws IOException
    {
        //Reading and Parsing
        read();

        //Choosing algorithms to run
        if (runFIFO) { pageRepAlgs.add(new FIFO(pagesAvailable, referenceSequence)); }
        if (runOTM)  { pageRepAlgs.add(new OTM(pagesAvailable, referenceSequence));  }
        if (runLRU)  { pageRepAlgs.add(new LRU(pagesAvailable, referenceSequence));  }

        //Running algorithms
        for (PageReplacementAlgorithm alg : pageRepAlgs)
        {
            System.out.println("--------------------------------------------------------");
            alg.run();
        }

        //Writing output
        write();
    }
}
