package lesson_2.seminar_2.Annotation;

public class Annotations {
    public static void main(String[] args) throws NoSuchFieldException {

        MyClass myClass = new MyClass();
        System.out.println(myClass.getPositive());
        System.out.println(myClass.getNegative());

        RandomIntegerProcessor.processObject(myClass);

        System.out.println(myClass.getPositive());
        System.out.println(myClass.getNegative());

//        Field negativeField = MyClass.class.getDeclaredField("negative");
//        RandomInteger annotation = negativeField.getAnnotation(RandomInteger.class);
//
//        System.out.println(annotation.minValue());
//        System.out.println(annotation.maxValue());
    }

    static class MyClass {
        @RandomInteger(minValue = -15, maxValue = -1)
        private int negative;

        @RandomInteger(minValue = 1, maxValue = 20)
        private int positive;

        public int getNegative() {
            return negative;
        }

        public int getPositive() {
            return positive;
        }
    }
}
