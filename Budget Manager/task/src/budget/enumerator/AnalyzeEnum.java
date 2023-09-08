package budget.enumerator;

public enum AnalyzeEnum {
    SORT_ALL_PURCHASES("1"),
    SORT_BY_TYPE("2"),
    SORT_CERTAIN_TYPE("3"),
    BACK("4");

    private final String analyzeNumber;

    AnalyzeEnum(String analyzeNumber) {
        this.analyzeNumber = analyzeNumber;
    }

    public String getAnalyzeNumber() {
        return analyzeNumber;
    }

    public static AnalyzeEnum fromAnalyzeNumber(String analyzeNumber) {
        for (AnalyzeEnum analyze : AnalyzeEnum.values()) {
            if (analyze.getAnalyzeNumber().equals(analyzeNumber)) {
                return analyze;
            }
        }
        throw new IllegalArgumentException("Invalid analyze number: " + analyzeNumber);
    }
}
