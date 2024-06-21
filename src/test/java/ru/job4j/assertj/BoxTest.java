package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .startsWithIgnoringCase("s")
                .endsWithIgnoringCase("e");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 15);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron")
                .startsWithIgnoringCase("t")
                .endsWithIgnoringCase("n");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 21);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube")
                .startsWithIgnoringCase("c")
                .endsWithIgnoringCase("e");
    }

    @Test
    void whenNumberOfVerticesIsZero() {
        Box box = new Box(0, 2);
        int countOfVertices = box.getNumberOfVertices();
        assertThat(countOfVertices).isEqualTo(0)
                .isLessThan(1);
    }

    @Test
    void whenNumberOfVerticesIsFour() {
        Box box = new Box(4, 2);
        int countOfVertices = box.getNumberOfVertices();
        assertThat(countOfVertices).isEqualTo(4)
                .isGreaterThan(3)
                .isLessThan(5);
    }

    @Test
    void whenNumberOfVerticesIsEight() {
        Box box = new Box(8, 2);
        int countOfVertices = box.getNumberOfVertices();
        assertThat(countOfVertices).isEqualTo(8)
                .isGreaterThan(7)
                .isLessThan(9);
    }

    @Test
    void whenExist() {
        Box box = new Box(8, 2);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }

    @Test
    void whenNotExist() {
        Box box = new Box(7, 3);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }

    @Test
    void whenSphereArea() {
        Box box = new Box(0, 10);
        double area = box.getArea();
        assertThat(area).isCloseTo(1256.63d, withPrecision(0.01d))
                .isPositive();
    }

    @Test
    void whenTetrahedronArea() {
        Box box = new Box(4, 12);
        double area = box.getArea();
        assertThat(area).isCloseTo(249.41d, withPrecision(0.01d))
                .isPositive();
    }

    @Test
    void whenCubeArea() {
        Box box = new Box(8, 15);
        double area = box.getArea();
        assertThat(area).isCloseTo(1350.0d, withPrecision(0.01d));
    }
}