package edu.skku.cs.skkueats.RecommendConditions;

import java.util.ArrayList;

class RecommendQueryCondition {
    /*
    추천 조건들을 설정한 것
    1. 대분류
    2. 소분류
    3. 가격
    4. 최소평점
    5. 위치
     */
    public String bigCategory;      // 대분류 조건
    public String smallCategory;    // 소분류 조건
    public int price;               // 가격 조건
    public double minGrade;         // 최소 평점 조건
    public String location;         // 가게 위치 조건
    public int maxNum;              // 추천받을 식사메뉴의 최대 개수

    public RecommendQueryCondition(String bigCategory, String smallCategory,
                                   int price, double minGrade, String location, int maxNum) {
        this.bigCategory = bigCategory;
        this.smallCategory = smallCategory;
        this.price = price;
        this.minGrade = minGrade;
        this.location = location;
        this.maxNum = maxNum;
    }
}
public class RecommendConditionsModel implements RecommendConditionsContract.contactModel {
    private ArrayList<RecommendConditionItem> recommendsArray;
    private RecommendConditionsContract.contactView view;
    private RecommendQueryCondition recommendQueryCondition;

    public RecommendConditionsModel(RecommendConditionsContract.contactView view, RecommendQueryCondition recommendQueryCondition) {
        this.view = view;
        recommendsArray = new ArrayList<>();
        this.recommendQueryCondition = recommendQueryCondition;
        fetchRecommends();
    }

    @Override
    public void fetchRecommends() {
        // 추천 쿼리는 RecommendQueryCondition 형태로 주어짐
        // 이걸로 백엔드에 쿼리를 보내서 [가게, 메뉴, 가격, 위치]를 '개수'만큼 받아온다

//        recommendsArray.add(new RecommendConditionItem("미가라멘", "돈가스김치나베", 8000, "후문쪽"));
//        recommendsArray.add(new RecommendConditionItem("미가라멘", "돈가스김치나베", 6000, "후문쪽"));
//        recommendsArray.add(new RecommendConditionItem("본찌 돈가스", "메밀정식", 7000, "후문쪽"));
//        recommendsArray.add(new RecommendConditionItem("미가라멘", "돈가스김치나베", 6000, "후문쪽"));


        // 메뉴 정보 Response가 오면 아래 메소드를 실행시켜서 review를 view에 표시
        pushRecommendsToViewer(recommendsArray);
    }

    /*
    DB에 가게 이름으로 쿼리 보내서
    가게, 가게의 위치, 메뉴들, 저장된 리뷰들 모두 가져오기
     */

    @Override
    public void pushRecommendsToViewer(ArrayList<RecommendConditionItem> recommendsArrays) {
        RecommendConditionItem menuRecommends;
        for(int i = 0; i < recommendsArrays.size(); i++) {
            menuRecommends = new RecommendConditionItem(recommendsArrays.get(i));
            view.showRecommend(menuRecommends);
        }
    }
}
