package edu.skku.cs.skkueats.SearchActivity;

import java.util.ArrayList;

public interface SearchActivityContract {
    interface contactView {
        void showSearchResult(SearchResult searchResult);
        void initView();
    }

    interface contactModel {
        void fetchSearchResults(String queryContent, int queryType);
        void pushSearchResultsToViewer(ArrayList<SearchResult> reviewArrays);
        void searchResultReset();
    }
}
