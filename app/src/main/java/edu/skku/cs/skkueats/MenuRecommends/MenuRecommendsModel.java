package edu.skku.cs.skkueats.MenuRecommends;

import com.naver.maps.geometry.LatLng;

import java.util.ArrayList;

public class MenuRecommendsModel implements MenuRecommendsContract.contactModel {
    private ArrayList<MenuRecommends> recommendsArray;
    private MenuRecommendsContract.contactView view;

    public MenuRecommendsModel(MenuRecommendsContract.contactView view) {
        this.view = view;
        recommendsArray = new ArrayList<>();
        fetchRecommends();
    }

    @Override
    public void fetchRecommends() {
        recommendsArray.add(new MenuRecommends("미가", "돈가스김치나베", 8000, "후문쪽"));
        recommendsArray.add(new MenuRecommends("미가", "돈가스김치나베", 6000, "후문쪽"));
        recommendsArray.add(new MenuRecommends("본찌 돈가스", "메밀정식", 7000, "후문쪽"));
        recommendsArray.add(new MenuRecommends("미가", "돈가스김치나베", 6000, "후문쪽"));


        // 메뉴 정보 Response가 오면 아래 메소드를 실행시켜서 review를 view에 표시
        pushRecommendsToViewer(recommendsArray);
    }

    /*
    DB에 가게 이름으로 쿼리 보내서
    가게, 가게의 위치, 메뉴들, 저장된 리뷰들 모두 가져오기
     */

    @Override
    public void pushRecommendsToViewer(ArrayList<MenuRecommends> recommendsArrays) {
        MenuRecommends menuRecommends;
        for(int i = 0; i < recommendsArrays.size(); i++) {
            menuRecommends = new MenuRecommends(recommendsArrays.get(i));
            view.showRecommend(menuRecommends);
        }
    }
}
