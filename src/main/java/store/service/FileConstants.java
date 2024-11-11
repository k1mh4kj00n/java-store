package store.service;

public class FileConstants {
    public static final int HEADER_LINE = 1;
    public static final String DELIMITER = ",";
    public static final int START_PRODUCT_CODE = 1000;
    public static final int START_PROMOTION_CODE = 0;

    public static final int READ_NAME = 0;
    public static final int READ_PRODUCT_PRICE = 1;
    public static final int READ_PRODUCT_QUANTITY = 2;
    public static final int READ_PRODUCT_PROMOTION = 3;

    public static final int READ_PROMOTION_BUY = 1;
    public static final int READ_PROMOTION_GET = 2;
    public static final int READ_PROMOTION_START_DATE = 3;
    public static final int READ_PROMOTION_END_DATE = 4;

    private FileConstants() {}
}
