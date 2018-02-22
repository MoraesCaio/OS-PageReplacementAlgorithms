package pagereplacementalgs;

import java.util.List;

/**
 * Created by CaioMoraes on 05/05/2017.
 * GitHub: MoraesCaio
 * Email: caiomoraes@msn.com
 */
public class FIFO extends PageReplacementAlgorithm
{
    public FIFO(int pagesAvailable, List<Integer> referenceSequence)
    {
        super(pagesAvailable, referenceSequence);
    }

    @Override
    public void run()
    {
        int nextPageToReplace = 0;

        for (Integer reference : referenceSequence)
        {
            if (!containsPage(reference))
            {
                pageFaults++;
                referencedPages[nextPageToReplace] = reference;
                nextPageToReplace = (nextPageToReplace + 1) % pagesAvailable;
                printPageFault(reference); //Considering index of the current reference
            }
            else
            {
                System.out.println("Referencing page number "+reference+": OK.");
            }
        }
        System.out.println();
    }

}
