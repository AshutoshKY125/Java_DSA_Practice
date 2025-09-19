class FoodRatings {

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        
    }
    
    public void changeRating(String food, int newRating) {

        for( int i = 0 ; i < foods.length ; i++){
            if( foods[i].equals(food)){
                ratings[i] = newRating;
                break;
            }
        }  
    }
    
    public String highestRated(String cuisine) {
        List<String> l = new ArrayList<>();
        int maxscore = 0;
        for( int i = 0 ; i< cuisines.length ; i++){
            if( cuisines[i].equals(cuisine)){
                int score = ratings[i];
                if( maxscore < score){
                    l.clear();
                    l.add(foods[i]);
                }else if( maxscore == score){
                    l.add(foods[i]);
                }
            }
        }

        Collections.sort(l);
        return l.get(0);
    }
}


