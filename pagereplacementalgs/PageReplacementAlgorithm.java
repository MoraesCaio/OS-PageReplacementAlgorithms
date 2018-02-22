package pagereplacementalgs;

import java.util.Arrays;
import java.util.List;

/**
 * Created by CaioMoraes on 05/05/2017.
 * GitHub: MoraesCaio
 * Email: caiomoraes@msn.com
 */
public abstract class PageReplacementAlgorithm
{
    /*Variables*/
    private String algorithmName;
    int pagesAvailable; //Number of pages available on the page table.
    int pageFaults; //Counter of page faults
    List<Integer> referenceSequence; //Sequence in which pages will be referenced.
    int[] referencedPages; //Pages currently on the page table


    /**
     * Running method of the algorithm. It changes accordingly the page table, whilst counting the page faults.
     * Every page fault is printed.
     */
    public abstract void run();


    /**
     *Constructor
     * @param pagesAvailable - Number of pages available on the page table.
     * @param referenceSequence - Sequence in which pages will be referenced.
     */
    PageReplacementAlgorithm(int pagesAvailable, List<Integer> referenceSequence)
    {
        this.pagesAvailable = pagesAvailable;
        this.referencedPages = new int[pagesAvailable]; //Pages currently on the page table
        Arrays.fill(this.referencedPages, -1);
        this.referenceSequence = referenceSequence;
        this.algorithmName = getClass().getSimpleName();
    }


    /**
     * Checks if the page is already on the page table.
     * @param pageNumber - number of the page being checked.
     * @return true, if the page is already on the memory; false, otherwise.
     */
    boolean containsPage(int pageNumber)
    {
        for (int referencedPage : referencedPages)
        {
            if (referencedPage == pageNumber)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Method for logging the process.
     * @param currentPage - index of the page on the entry file's list
     */
    void printPageFault(int currentPage)
    {
        System.out.println("["+algorithmName+"] Page Fault while trying to reference page number " + currentPage);
        for (int i = 0; i < referencedPages.length; i++)
        {
            System.out.println("page["+i+"] = "+referencedPages[i]);
        }
        System.out.println();
    }


    @Override
    public String toString()
    {
        return algorithmName + " " + pageFaults;
    }
}
