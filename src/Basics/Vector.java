package Basics;

import java.util.Objects;

public class Vector<T> {

    private T x, y;

    public Vector(T x, T y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector<?> vector = (Vector<?>) o;
        return Objects.equals(x, vector.x) && Objects.equals(y, vector.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


    public T x() {
        return x;
    }

    public T y() {
        return y;
    }

    public void setX(T x) {
        this.x = x;
    }

    public void setY(T y) {
        this.y = y;
    }
}
