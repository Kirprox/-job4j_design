package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {

        this.root = new Node<>(root);
    }

    public boolean isBinary() {
        return findByPredicate(node -> node.children.size() > 2).isEmpty();
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        if (findBy(child).isEmpty()) {
            Optional<Node<E>> optionalNode = findBy(parent);
            if (optionalNode.isPresent()) {
                optionalNode.get().children.add(new Node<>(child));
                result = true;
            }
        }
        return result;
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(this.root);
        while (!queue.isEmpty()) {
            Node<E> current = queue.poll();
            if (condition.test(current)) {
                result = Optional.of(current);
                break;
            }
            queue.addAll(current.children);
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(node -> node.value.equals(value));
    }
}