class BuyXMore extends Offer {
    int min_quantity, discount_percent;
    BuyXMore(int id, int min_quantity, int discount_percent) {
        super(id);
        this.min_quantity = min_quantity;
        this.discount_percent = discount_percent;
    }


    @Override
    public Order processOffer(Order order) {
        return null;
    }
}