package pagereplacementalgs;

import java.util.List;

/**
 * Created by CaioMoraes on 05/05/2017.
 * GitHub: MoraesCaio
 * Email: caiomoraes@msn.com
 */
//OTM stands for "ótimo", portuguese for optimal.
public class OTM extends PageReplacementAlgorithm
{
    public OTM(int pagesAvailable, List<Integer> referenceSequence)
    {
        super(pagesAvailable, referenceSequence);
    }


    /**
     * Similar to LRU's version. The only difference remains on the line of k's loop.
     * Replaces the page that will take longer to reappear.
     */
    @Override
    public void run()
    {
        //Iterating through the input
        for (int i = 0; i < referenceSequence.size(); i++)
        {
            //Page fault
            if (!containsPage(referenceSequence.get(i)))
            {
                pageFaults++;

                //Calculating the index of the page for replacement
                int maxSteps = -1;
                int pageToReplace = 0;

                //For each page on the table,
                //  we'll be counting how many steps it will take for the page to reappear.
                for (int j = 0; j < referencedPages.length; j++)
                {
                    int pageSteps = 0;
                    for (int k = i; k < referenceSequence.size(); k++)
                    {
                        if (referencedPages[j] == referenceSequence.get(k))
                        {
                            break;
                        }
                        pageSteps++;
                    }

                    //New maximum
                    if (pageSteps > maxSteps){
                        maxSteps = pageSteps;
                        pageToReplace = j;
                    }
                }

                //Replacing
                referencedPages[pageToReplace] = referenceSequence.get(i);
                printPageFault(referenceSequence.get(i));
            }
            else
            {
                System.out.println("Referencing page number "+referenceSequence.get(i)+": OK.");
            }
        }
        System.out.println();
    }
}
