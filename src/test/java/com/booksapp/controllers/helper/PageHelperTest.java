package com.booksapp.controllers.helper;

import org.junit.jupiter.api.Test;

import com.booksapp.controllers.helpers.PageHelper;

public class PageHelperTest {

    @Test
    void getDisplayPagesTest_表示ページ数が最大ページ数より少ない() {
        PageHelper.getDisplayedPages(0, 5, 3);
    }
    
}
