package spring.telegrambot.weatherBot.data.district;


public enum DistrictEnum {
    // 1. Адмиралтейский
    // 2. Василеостровский
    // 3. Выборгский
    // 4. Калининский
    // 5. Кировский
    // 6. Колпинский
    // 7. Красногвардейский
    // 8. Красносельский
    // 9. Кронштадтский
    // 10. Курортный
    // 11. Московский
    // 12. Невский
    // 13. Петроградский
    // 14. Петродворцовый
    // 15. Приморский
    // 16. Пушкинский
    // 17. Фрунзенский
    // 18. Центральный.
    ADMIRALTEISKII (1, "Адмиралтейский","в Адмиралтейском",
            new Coordinates(59.91563620060743,30.297633736839693)),
    VASILEOSTROVSKII(2, "Василеостровский", "в Василеостровском",
            new Coordinates(59.94389615169415,30.223476021995936)),
    VIBORGSKII(3, "Выборгский", "в Выборгском",
            new Coordinates(60.083461865686715,30.224849313011557)),
    KALININSKII(4, "Калининский","в Калининском",
            new Coordinates(60.010658936727154,30.389644234886568)),
    KIROVSKII(5, "Кировский","в Кировском",
            new Coordinates(59.850023026058224,30.274469051709726)),
    KOLPINSKII(6, "Колпинский", "в Колпинском",
            new Coordinates(59.75424119282505,30.609437293908638)),
    KRASNOGVARDEISKII(7, "Красногвардейский" , "в Красногвардейском",
            new Coordinates(59.97626199815711,30.4638019497303)),
    KRASNOSELSKII(8, "Красносельский", "в Красносельском",
            new Coordinates(59.85416834082863,30.16185918842848)),
    KRONSHTADTSKII(9, "Кронштадтский", "в Кронштадтском",
            new Coordinates(60.0051391591721,29.742636220388366)),
    KURORTNII(10, "Курортный" ,"в Курортном",
            new Coordinates(60.20087387878063,29.699414843237292)),
    MOSKOVSKII(11,"Московский","в Московском",
            new Coordinates(59.853132060710436,30.319101009717542)),
    NEVSKII(12,"Невский","в Невском",
            new Coordinates(59.903709166647715,30.447195619430396)),
    PETROGRADSKII(13,"Петроградский","в Петроградском",
            new Coordinates(59.96783630510365,30.282348823034166)),
    PETRODDVOR(14,"Петродворцовый","в Петродворцовом",
            new Coordinates( 59.873571436827035,29.913245922643245)),
    PRIMORSKII(15,"Приморский","в Приморском",
            new Coordinates(60.026469483527464,30.178157418480307)),
    PUSHKINSKII(16,"Пушкинский","в Пушкинском",
            new Coordinates(59.7285931571603,30.43778937401814)),
    FRUNZENSKII(17,"Фрунзенский","во Фрунзенском",
            new Coordinates(59.867485280680434,30.40138099535739)),
    CENTRALNII(18, "Центральный","в Центральном",
            new Coordinates(59.93746280057958,30.37178337843117));





    private final int id;
    private final String name;
    private final String namePrepositional;
    private final Coordinates coordinates;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNamePrepositional() {
        return namePrepositional;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    DistrictEnum(int id, String name, String namePrepositional, Coordinates coordinates) {
        this.id = id;
        this.name = name;
        this.namePrepositional = namePrepositional;
        this.coordinates = coordinates;
    }
}
