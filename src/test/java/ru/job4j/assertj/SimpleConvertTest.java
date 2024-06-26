package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<Integer> input = List.of(1, 2, 2, 3, 4, 5, 6, 7, 8, 9);
        assertThat(input).isNotNull()
                .contains(1, 9, 7)
                .containsAnyOf(124, 234, 1, 0)
                .allSatisfy(e -> {
                    assertThat(e).isPositive();
                    assertThat(e).isGreaterThan(0);
                })
                .first().isEqualTo(1);
    }

    @Test
    void checkToSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> input = Set.of("привет", "пока");
        assertThat(input).isNotNull()
                .filteredOn(e -> e.contains("ет"))
                .containsAnyOf("здравствуйте", "привет")
                .allMatch(e -> e.length() > 5);
    }

    @Test
    void checkToMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<Integer, Integer> input = Map.of(11, 1, 22, 2);
        assertThat(input).hasSize(2)
                .containsEntry(11, 1)
                .doesNotContainEntry(1, 11)
                .containsValue(2);
    }
}