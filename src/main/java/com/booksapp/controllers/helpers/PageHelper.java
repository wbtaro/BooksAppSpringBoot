package com.booksapp.controllers.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PageHelper {
    public static List<Integer> getDisplayedPages (int displayedPage, int maxDisplayedPages, int totalPages) {
        ArrayList<Integer> displayedPages = new ArrayList<Integer>();
        
        displayedPages.add(displayedPage);
        for(int i = displayedPage-1; i>=0 && i >= displayedPage - maxDisplayedPages / 2; i--) {
            displayedPages.add(i);
        }
        
        for(int i = displayedPage + 1; i < totalPages && displayedPages.size() < Math.min(maxDisplayedPages, totalPages); i++) {
            displayedPages.add(i);
        }
        
        Collections.sort(displayedPages);

        return displayedPages;
    }
}
