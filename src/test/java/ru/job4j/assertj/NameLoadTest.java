package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");

    }

    @Test
    void whenValuesContainsElements() {
        NameLoad nameLoad = new NameLoad();
        nameLoad.parse("key=value");
        Map<String, String> result = nameLoad.getMap();
        assertThat(result).containsEntry("key", "value");

    }

    @Test
    void whenArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("array is empty");

    }

    @Test
    void whenNameDoesNotContainSymbol() {
        NameLoad nameLoad = new NameLoad();
        String parameter = "keyvalue";
        assertThatThrownBy(() -> nameLoad.parse(parameter))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain the symbol")
                .hasMessageContaining(parameter);
    }

    @Test
    void whenNameDoesNotContainKey() {
        NameLoad nameLoad = new NameLoad();
        String parameter = "=value";
        assertThatThrownBy(() -> nameLoad.parse(parameter))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a key")
                .hasMessageContaining(parameter);
    }

    @Test
    void whenNameDoesNotContainValue() {
        NameLoad nameLoad = new NameLoad();
        String parameter = "key=";
        assertThatThrownBy(() -> nameLoad.parse(parameter))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a value")
                .hasMessageContaining(parameter);
    }
}