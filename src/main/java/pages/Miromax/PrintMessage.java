package pages.Miromax;

public class PrintMessage {
    public static void showRowsInfo(int rows){
        System.out.println("Всего в зале: " + rows + " рядов");
    }

    public static void showSeatsInfo(int row, int allSeats, int takenSeats){
        System.out.println("Ряд " + (row + 1) + ": всего мест: " + allSeats + ", из них занято " + takenSeats);
    }
    public static void showTwoCentralFreeSeatsPrice(String middle, String second){
        System.out.println("Цена за первый билет - " + middle + " грн");
        System.out.println("Цена за второй билет - " + second + " грн");
    }

    public static void allCentralSeatsAreTaken(){
        System.out.println("Все центральные места заняты");
    }

    public static void getMovieInfo(String movieTitle, String ageLimit, String location, String beInCinemaAt, String movieDuration){
        String MOVIE = "Фильм: ";
        String MIN_AGE = "\nМин возраст: ";
        String CINEMA = "Кинотеарт : ";
        String NEED_TO_BE_IM_CINEMA_AT = "В кинотеатре нужно быть в ";
        String MOVIE_DURATION = "Длительность фильма ";
        System.out.println(MOVIE + movieTitle + MIN_AGE + ageLimit);
        System.out.println(CINEMA + location);
        System.out.println(NEED_TO_BE_IM_CINEMA_AT + movieDuration);
        System.out.println(MOVIE_DURATION + beInCinemaAt);
    }
}
