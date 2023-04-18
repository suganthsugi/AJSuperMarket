public abstract class Offer {
    int id;
    Offer(int id) {
        this.id = id;
    }

    public abstract Order processOffer(Order order);
}
