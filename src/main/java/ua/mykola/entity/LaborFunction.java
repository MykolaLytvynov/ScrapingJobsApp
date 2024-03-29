package ua.mykola.entity;

/**
 * Labor function taken from https://jobs.techstars.com/jobs
 */
public enum LaborFunction {

    ACCOUNTING_AND_FINANCE("Accounting & Finance"),
    ADMINISTRATION("Administration"),
    DATA_SCIENCE("Data Science"),
    CUSTOMER_SERVICE("Customer Service"),
    DESIGN("Design"),
    IT("IT"),
    LEGAL("Legal"),
    MARKETING_AND_COMMUNICATIONS("Marketing & Communications"),
    OPERATIONS("Operations"),
    OTHER_ENGINEERING("Other Engineering"),
    PEOPLE_AND_HR("People & HR"),
    PRODUCT("Product"),
    QUALITY_ASSURANCE("Quality Assurance"),
    SALES_AND_BUSINESS_DEVELOPMENT("Sales & Business Development"),
    SOFTWARE_ENGINEERING("Software Engineering");

    private String value;

    LaborFunction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
