package edu.skku.cs.skkueats.SearchActivity;

import java.util.ArrayList;

public interface SearchActivityContract {
    interface contactView {
        void showRecommend(SearchResult searchResult);
        void initView();
    }

    interface contactModel {
        void fetchSearchResults();
        void pushSearchResultsToViewer(ArrayList<SearchResult> reviewArrays);
    }
}
