package io.github.mjaroslav.sharedjava.reflect;

@SuppressWarnings("FieldMayBeFinal")
public class TreeDummy {
    private int root = -10;

    public static class TreeChildDummy extends TreeDummy {
        private boolean child = true;

        public static class TreeGrandChildDummy extends TreeChildDummy {
            private double grandChild = 5D;
        }
    }
}
