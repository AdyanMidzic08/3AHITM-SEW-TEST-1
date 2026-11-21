package htl.HashedZoo;

public class AnimalNode {
    private Animal value;
    private AnimalNode next;

    public AnimalNode(Animal value) {
        this.value = value;
    }

    public Animal getValue() {
        return value;
    }

    public AnimalNode getNext() {
        return next;
    }

    public void setNext(AnimalNode next) {
        this.next = next;
    }
}
