package com.rishabh.ShopAnalyst.constants;

public class Constants {

    public static final String DB_NAME = "ShopAnalystsDB";
    public static final String COLL_NAME = "ShopAnalyst";

    public final static String JSON_FILE_LOCATION = "";

    public final static String  ID = "pnum";
    public final static String  DOB = "dob";
    public final static String  CAPTION = "caption";
    public final static String  ETHNICITY = "ethnicity";
    public final static String  WEIGHT = "weight";
    public final static String  HEIGHT = "height";
    public final static String  isVEG = "is_veg";
    public final static String  isDRINK = "drink";

    public final static int GRAMS_TO_KGS = 1000;

    public final static int PAGE_SIZE = 10;

    public static int getEthinicityCode(final String ethn) {
        switch(ethn){
            case "Asian" : return 0;
            case "Indian" : return 1;
            case "African Americans" : return 2;
            case "Asian Americans" : return 3;
            case "European" : return 4;
            case "British" : return 5;
            case "Jewish" : return 6;
            case "Latino" : return 7;
            case "Native American" : return 8;
            case "Arabic" : return 9;
        }
        return -1;
    }
}
