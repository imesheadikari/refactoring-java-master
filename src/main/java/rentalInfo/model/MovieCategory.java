package main.java.rentalInfo.model;

/**
 * Category of {@link Movie}.
 */
public enum MovieCategory {
    NEW {
        @Override
        public double getRental(final int period) {
            return period * 3;
        }
    },
    CHILDREN {
        @Override
        public double getRental(final int period) {
            if (period > 3) {
                return ((period - 3) * 1.5) + 1.5;
            } else {
                return 1.5;
            }
        }
    },
    REGULAR{
        @Override
        public double getRental(final int period) {
            if (period > 2) {
                return ((period - 2) * 1.5) + 2;
            } else {
                return 2;
            }
        }
    };

    public double getRental(final int period) {
        return 0;
    }
}
