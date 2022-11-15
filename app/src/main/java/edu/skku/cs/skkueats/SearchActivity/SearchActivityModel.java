package edu.skku.cs.skkueats.SearchActivity;

import java.util.ArrayList;

public class SearchActivityModel implements SearchActivityContract.contactModel {
    private ArrayList<SearchResult> searchResultsArray;
    private SearchActivityContract.contactView view;

    public SearchActivityModel(SearchActivityContract.contactView view) {
        this.view = view;
        searchResultsArray = new ArrayList<>();
    }

    public void searchResultReset() {
        // 초기화가 잘 안됨
        searchResultsArray.clear();
        pushSearchResultsToViewer(searchResultsArray);
    }

    @Override
    public void fetchSearchResults(String queryContent, int queryType) {
        /*
        DB에 가게 이름/메뉴 이름으로 쿼리 보내서
        가게, 가게의 위치, 메뉴들, 저장된 리뷰들 모두 가져오기
            > 리뷰 평점 평균내기
         */


        if (queryContent.isEmpty()) {
            searchResultReset();
        } else {
            searchResultsArray.add(new SearchResult("미가", "돈가스김치나베", 2.5, "후문쪽"));
            searchResultsArray.add(new SearchResult("모수밀면", "물밀면, 비빔밀면, 찐만두", 4.7, "길건너"));
            searchResultsArray.add(new SearchResult("본찌 돈가스", "우동정식, 치즈돈가스덮밥", 4.2, "후문쪽"));
            searchResultsArray.add(new SearchResult("본찌 돈가스", "우동정식, 치즈돈가스덮밥", 4.2, "후문쪽"));
            searchResultsArray.add(new SearchResult("본찌 돈가스", "우동정식, 치즈돈가스덮밥", 4.2, "후문쪽"));

        }

        pushSearchResultsToViewer(searchResultsArray);

    }

    @Override
    public void pushSearchResultsToViewer(ArrayList<SearchResult> searchResultsArray) {
        SearchResult searchResult;
        for(int i = 0; i < searchResultsArray.size(); i++) {
            searchResult = new SearchResult(searchResultsArray.get(i));
            view.showSearchResult(searchResult);
        }

    }
}
