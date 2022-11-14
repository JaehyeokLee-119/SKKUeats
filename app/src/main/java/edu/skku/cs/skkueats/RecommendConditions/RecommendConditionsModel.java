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
    private RecommendConditionsContract.contactView view;
    private RecommendQueryCondition recommendQueryCondition;

    public RecommendConditionsModel(RecommendConditionsContract.contactView view, RecommendQueryCondition recommendQueryCondition) {
        this.view = view;
        this.recommendQueryCondition = recommendQueryCondition;
    }

}
