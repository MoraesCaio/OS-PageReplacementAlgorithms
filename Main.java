import pagereplacementalgs.PageReplacementIO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by CaioMoraes on 05/05/2017.
 * GitHub: MoraesCaio
 * Email: caiomoraes@msn.com
 */
public class Main
{
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        List<PageReplacementIO> pageRepIO = new ArrayList<>();

        do
        {
            /*INPUT*/
            System.out.println("Please inform the name of the entry file.");
            String entryFileName = s.nextLine();
            System.out.println("Please inform the name of the output file.");
            String outputFileName = s.nextLine();

            pageRepIO.add(new PageReplacementIO(entryFileName, outputFileName));

            //Prompts about another input
            System.out.println("Do you want to add another input file and output file? (y/n)");
            String addAnotherInput = s.nextLine();
            if (addAnotherInput.isEmpty() || addAnotherInput.contains("n")){
                break;
            }

        }while (true);

        /*Running*/
        for (PageReplacementIO p : pageRepIO)
        {
            try
            {
                System.out.println("\n\nRunning " + p.getEntryFileName() + " and writing on " + p.getOutputFileName() + ":");
                p.run(true, true, true);
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }

    }
}
