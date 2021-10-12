package by.epam.pizzashop.dto;

import java.util.Objects;

/**
 * The type Restaurant dto.
 */
public class RestaurantDto {
    private long restaurantId;
    private int number;
    private String city;
    private String street;
    private String house;
    private String block;

    /**
     * Instantiates a new Restaurant dto.
     */
    public RestaurantDto() {
    }

    /**
     * Instantiates a new Restaurant dto.
     *
     * @param restaurantId the restaurant id
     * @param number       the number
     * @param city         the city
     * @param street       the street
     * @param house        the house
     * @param block        the block
     */
    public RestaurantDto(long restaurantId, int number, String city, String street, String house, String block) {
        this.restaurantId = restaurantId;
        this.number = number;
        this.city = city;
        this.street = street;
        this.house = house;
        this.block = block;
    }

    /**
     * Gets restaurant id.
     *
     * @return the restaurant id
     */
    public long getRestaurantId() {
        return restaurantId;
    }

    /**
     * Sets restaurant id.
     *
     * @param restaurantId the restaurant id
     */
    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    /**
     * Gets number.
     *
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets number.
     *
     * @param number the number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets street.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets street.
     *
     * @param street the street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets house.
     *
     * @return the house
     */
    public String getHouse() {
        return house;
    }

    /**
     * Sets house.
     *
     * @param house the house
     */
    public void setHouse(String house) {
        this.house = house;
    }

    /**
     * Gets block.
     *
     * @return the block
     */
    public String getBlock() {
        return block;
    }

    /**
     * Sets block.
     *
     * @param block the block
     */
    public void setBlock(String block) {
        this.block = block;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantDto restaurant = (RestaurantDto) o;
        return restaurantId == restaurant.restaurantId && number == restaurant.number && Objects.equals(city, restaurant.city)
                && Objects.equals(street, restaurant.street) && Objects.equals(house, restaurant.house)
                && Objects.equals(block, restaurant.block);
    }

    @Override
    public int hashCode() {
        int result = (int) (restaurantId ^ (restaurantId >>> 32));
        result *= number;
        result *= 31 + (city != null ? city.hashCode() : 0);
        result *= 31 + (street != null ? street.hashCode() : 0);
        result *= 31 + (house != null ? house.hashCode() : 0);
        result *= 31 + (block != null ? block.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Restaurant{");
        builder.append("restaurantId=").append(restaurantId);
        builder.append(", number=").append(number);
        builder.append(", city=").append(city);
        builder.append(", street=").append(street);
        builder.append(", house=").append(house);
        builder.append(", block=").append(block);
        builder.append('}');
        return builder.toString();
    }

    /**
     * The type Builder.
     */
    public static class Builder {
        private RestaurantDto newRestaurant;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            newRestaurant = new RestaurantDto();
        }

        /**
         * Sets restaurant id.
         *
         * @param restaurantId the restaurant id
         * @return the restaurant id
         */
        public RestaurantDto.Builder setRestaurantId(long restaurantId) {
            newRestaurant.restaurantId = restaurantId;
            return this;
        }

        /**
         * Sets number.
         *
         * @param number the number
         * @return the number
         */
        public RestaurantDto.Builder setNumber(int number) {
            newRestaurant.number = number;
            return this;
        }

        /**
         * Sets city.
         *
         * @param city the city
         * @return the city
         */
        public RestaurantDto.Builder setCity(String city) {
            newRestaurant.city = city;
            return this;
        }


        /**
         * Sets street.
         *
         * @param street the street
         * @return the street
         */
        public RestaurantDto.Builder setStreet(String street) {
            newRestaurant.street = street;
            return this;
        }

        /**
         * Sets house.
         *
         * @param house the house
         * @return the house
         */
        public RestaurantDto.Builder setHouse(String house) {
            newRestaurant.house = house;
            return this;
        }

        /**
         * Sets block.
         *
         * @param block the block
         * @return the block
         */
        public RestaurantDto.Builder setBlock(String block) {
            newRestaurant.block = block;
            return this;
        }

        /**
         * Build restaurant dto.
         *
         * @return the restaurant dto
         */
        public RestaurantDto build() {
            return newRestaurant;
        }
    }
}
