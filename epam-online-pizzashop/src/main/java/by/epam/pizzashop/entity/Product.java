package by.epam.pizzashop.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * The type Product.
 */
public class Product {
    private long productId;
    private String name;
    private String dose;
    private String group;
    /**
     * The Price.
     */
    public BigDecimal price;
    private String pathToPicture;
    private String instruction;

    /**
     * Instantiates a new Product.
     */
    public Product() {
    }

    /**
     * Instantiates a new Product.
     *
     * @param productId     the product id
     * @param name          the name
     * @param dose          the dose
     * @param group         the group
     * @param price         the price
     * @param pathToPicture the path to picture
     * @param instruction   the instruction
     */
    public Product(long productId, String name,
                   String dose,  String group, BigDecimal price,
                   String pathToPicture, String instruction) {
        this.productId = productId;
        this.name = name;
        this.dose = dose;
        this.group = group;
        this.price = price;
        this.pathToPicture = pathToPicture;
        this.instruction = instruction;
    }

    /**
     * Gets product id.
     *
     * @return the product id
     */
    public long getProductId() {
        return productId;
    }

    /**
     * Sets product id.
     *
     * @param productId the product id
     */
    public void setProductId(long productId) {
        this.productId = productId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets dose.
     *
     * @return the dose
     */
    public String getDose() {
        return dose;
    }

    /**
     * Sets dose.
     *
     * @param dose the dose
     */
    public void setDose(String dose) {
        this.dose = dose;
    }

    /**
     * Gets group.
     *
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * Sets group.
     *
     * @param group the group
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets path to picture.
     *
     * @return the path to picture
     */
    public String getPathToPicture() {
        return pathToPicture;
    }

    /**
     * Sets path to picture.
     *
     * @param pathToPicture the path to picture
     */
    public void setPathToPicture(String pathToPicture) {
        this.pathToPicture = pathToPicture;
    }

    /**
     * Gets instruction.
     *
     * @return the instruction
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * Sets instruction.
     *
     * @param instruction the instruction
     */
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId
                && Objects.equals(name, product.name)
                && Objects.equals(dose, product.dose)
                && Objects.equals(group, product.group)
                && Objects.equals(price, product.price)
                && Objects.equals(pathToPicture, product.pathToPicture)
                && Objects.equals(instruction, product.instruction);
    }

    @Override
    public int hashCode() {
        int result = (int) (productId ^ (productId >>> 32));
        result *= 31 + (name != null ? name.hashCode() : 0);
        result *= 31 + (dose != null ? dose.hashCode() : 0);
        result *= 31 + (group != null ? group.hashCode() : 0);
        result *= 31 + (price != null ? price.hashCode() : 0);
        result *= 31 + (pathToPicture != null ? pathToPicture.hashCode() : 0);
        result *= 31 + (instruction != null ? instruction.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Product{");
        builder.append("productId=").append(productId);
        builder.append(", name=").append(name);
        builder.append(", dose=").append(dose);
        builder.append(", group=").append(group);
        builder.append(", price=").append(price);
        builder.append(", picture=").append(pathToPicture);
        builder.append(", instruction=").append(instruction);
        builder.append("}");
        return builder.toString();
    }

    /**
     * The type Builder.
     */
    public static class Builder {
        private Product newProduct;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            newProduct = new Product();
        }

        /**
         * Sets product id.
         *
         * @param productId the product id
         * @return the product id
         */
        public Product.Builder setProductId(long productId) {
            newProduct.productId = productId;
            return this;
        }

        /**
         * Sets name.
         *
         * @param name the name
         * @return the name
         */
        public Product.Builder setName(String name) {
            newProduct.name = name;
            return this;
        }


        /**
         * Sets dose.
         *
         * @param dose the dose
         * @return the dose
         */
        public Product.Builder setDose(String dose) {
            newProduct.dose = dose;
            return this;
        }


        /**
         * Sets group.
         *
         * @param group the group
         * @return the group
         */
        public Product.Builder setGroup(String group) {
            newProduct.group = group;
            return this;
        }


        /**
         * Sets price.
         *
         * @param price the price
         * @return the price
         */
        public Product.Builder setPrice(BigDecimal price) {
            newProduct.price = price;
            return this;
        }


        /**
         * Sets picture.
         *
         * @param pathToPicture the path to picture
         * @return the picture
         */
        public Product.Builder setPicture(String pathToPicture) {
            newProduct.pathToPicture = pathToPicture;
            return this;
        }

        /**
         * Sets instruction.
         *
         * @param instruction the instruction
         * @return the instruction
         */
        public Product.Builder setInstruction(String instruction) {
            newProduct.instruction = instruction;
            return this;
        }

        /**
         * Build product.
         *
         * @return the product
         */
        public Product build() {
            return newProduct;
        }
    }
}
