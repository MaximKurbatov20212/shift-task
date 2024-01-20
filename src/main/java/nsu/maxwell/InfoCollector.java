package nsu.maxwell;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class InfoCollector {

    static abstract class Stat {
        int count;

        public final String getReduceStat() {
            return String.format("count: %d \n", count);
        }

        public abstract String getFullStat();
    }

    static class IntegerStat extends Stat {
        BigInteger maxNumber = new BigInteger("0");
        BigInteger minNumber = new BigInteger("0");
        BigInteger sum = new BigInteger("0");
        BigDecimal average = new BigDecimal(0);

        void add(BigInteger value) {
            if (count == 0) {
                minNumber = value;
                maxNumber = value;
            }

            else if (value.compareTo(maxNumber) > 0) {
                maxNumber = value;
            }
            else if (value.compareTo(minNumber) < 0) {
                minNumber = value;
            }

            count++;
            sum = sum.add(value);
            average = new BigDecimal(sum.divide(BigInteger.valueOf(count)));
        }

        @Override
        public String getFullStat() {
            return String.format("""
                    Integer Type Statistic:
                    Count: %d
                    Max: %d
                    Min: %d
                    Sum: %d
                    Average: %2f
                    """,
                    count, maxNumber, minNumber, sum, average);
        }
    }

    static class DoubleStat extends Stat {
        BigDecimal maxNumber = new BigDecimal(0);
        BigDecimal minNumber = new BigDecimal(0);
        BigDecimal sum = new BigDecimal(0);
        BigDecimal average = new BigDecimal(0);

        void add(BigDecimal value) {
            if (count == 0) {
                minNumber = value;
                maxNumber = value;
            }

            if (value.compareTo(maxNumber) > 0) {
                maxNumber = value;
            }
            else if (value.compareTo(minNumber) < 0) {
                minNumber = value;
            }
            count++;
            sum = sum.add(value);
            average = sum.divide(BigDecimal.valueOf(count), RoundingMode.CEILING);
        }

        @Override
        public String getFullStat() {
            return String.format("""
                    Double Type Statistic:
                    Count: %d
                    Max: %2f
                    Min: %2f
                    Sum: %2f
                    Average: %2f
                    """,
                    count, maxNumber, minNumber, sum, average);
        }

    }

    static class StringStat extends Stat {
        int maxLen;
        int minLen;

        void add(String value) {
            if (value.length() > maxLen) {
                maxLen = value.length();

                if (minLen == 0) {
                    minLen = maxLen;
                }

            }
            else if (value.length() < minLen) {
                minLen = value.length();
            }

            count++;
        }

        @Override
        public String getFullStat() {
            return String.format("""
                    String Type Statistic:
                    Count: %d
                    MaxLen: %d
                    MinLen: %d
                    """,
                    count, maxLen, minLen);
        }
    }

    IntegerStat integerStat = new IntegerStat();
    DoubleStat doubleStat = new DoubleStat();
    StringStat stringStat = new StringStat();

    void add(BigInteger value) {
        integerStat.add(value);
    }

    void add(BigDecimal value) {
        doubleStat.add(value);
    }

    void add(String value) {
        stringStat.add(value);
    }

    String getReduceStat() {
        return "Reduce stats: \n----------------\n" +
                "Integer " + integerStat.getReduceStat() +
                "Float " + doubleStat.getReduceStat() +
                "String " + stringStat.getReduceStat() +
                "--------------------\n";
    }

    String getFullStat() {
        return "Full stats: \n----------------\n" +
               integerStat.getFullStat()  +
               doubleStat.getFullStat() +
               stringStat.getFullStat() +
                "--------------------\n";
    }
}
