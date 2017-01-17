import java.util.Iterator;
import java.util.Scanner;

public class Main {

    static class TestCase {
        private final int wellHeight;
        private final int dailyClimb;
        private final int nightlySlide;
        private final int fatigueFactor;

        TestCase(int wellHeight, int dailyClimb, int nightlySlide, int fatigueFactor) {

            this.wellHeight = wellHeight;
            this.dailyClimb = dailyClimb;
            this.nightlySlide = nightlySlide;
            this.fatigueFactor = fatigueFactor;
        }
    }

    static class TestCaseIterator implements Iterator<TestCase> {

        Integer wellHeight = null;
        Scanner scanner = new Scanner(System.in);

        @Override
        public boolean hasNext() {
            if (wellHeight == null) {
                wellHeight = scanner.nextInt();
            }
            return wellHeight != 0;
        }

        @Override
        public TestCase next() {
            TestCase testCase;
            if (hasNext()) {
                testCase = new TestCase(wellHeight, scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
                wellHeight = null;
            } else {
                throw new IndexOutOfBoundsException();
            }
            return testCase;
        }
    }

    static class Result {
        String type;
        int day;
        Result(String type, int day) {
            this.type = type;
            this.day = day;
        }
    }

    private static Result getOutcome(int wellHeight, int initialDailyClimb, int nightlySlide, int fatigueFactor) {
        double currentHeight = 0;
        int day = 0;
        Result result;
        int fatigueFactorDistance = fatigueFactor * initialDailyClimb;
        double dailyClimb = initialDailyClimb;
        while (true) {
            day++;
            currentHeight += dailyClimb;
            boolean gotToTop = currentHeight > wellHeight;
            if (gotToTop) {
                result = new Result("success", day);
                break;
            }
            currentHeight -= nightlySlide;
            boolean slidToBottom = currentHeight < 0;
            if (slidToBottom) {
                result = new Result("failure", day);
                break;
            }
            dailyClimb = Math.max(0, dailyClimb - (fatigueFactorDistance / 100D));
        }
        return result;
    }

    static public void main(String[] args) {
        TestCaseIterator iterator = new TestCaseIterator();
        while (iterator.hasNext()) {
            TestCase testCase = iterator.next();
            Result result = getOutcome(testCase.wellHeight, testCase.dailyClimb,
                    testCase.nightlySlide, testCase.fatigueFactor);
            System.out.println(String.format("%s on day %d", result.type, result.day));
        }
    }

}
