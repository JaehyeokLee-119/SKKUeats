package edu.skku.cs.skkueats.SearchActivity;

import java.util.ArrayList;

public class SearchActivityModel implements SearchActivityContract.contactModel {
    private ArrayList<SearchResult> searchResultsArray;
    private SearchActivityContract.contactView view;

    public SearchActivityModel(SearchActivityContract.contactView view) {
        this.view = view;
        searchResultsArray = new ArrayList<>();
        fetchSearchResults();
    }

    /*
    DB에 가게 이름으로 쿼리 보내서
    가게, 가게의 위치, 메뉴들, 저장된 리뷰들 모두 가져오기
     */
    @Override
    public void fetchSearchResults() {
        searchResultsArray.add(new SearchResult("미가", "돈가스김치나베", 6000, "후문쪽"));

        pushSearchResultsToViewer(searchResultsArray);

    }

    @Override
    public void pushSearchResultsToViewer(ArrayList<SearchResult> searchResultsArray) {
        SearchResult searchResult;
        for(int i = 0; i < searchResultsArray.size(); i++) {
            searchResult = new SearchResult(searchResultsArray.get(i));
            view.showRecommend(searchResult);
        }

    }
}
