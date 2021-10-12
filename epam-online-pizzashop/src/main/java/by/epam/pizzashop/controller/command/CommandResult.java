package by.epam.pizzashop.controller.command;

/**
 * The type Command result.
 */
public class CommandResult {
    private final RoutingType routingType;
    private final String page;

    /**
     * Instantiates a new Command result.
     *
     * @param page        the page
     * @param routingType the routing type
     */
    public CommandResult(String page, RoutingType routingType) {
        this.routingType = routingType;
        this.page = page;
    }


    /**
     * Gets routing type.
     *
     * @return the routing type
     */
    public RoutingType getRoutingType() {
        return routingType;
    }

    /**
     * Gets page.
     *
     * @return the page
     */
    public String getPage() {
        return page;
    }

    /**
     * The enum Routing type.
     */
    public enum RoutingType {
        /**
         * Forward routing type.
         */
        FORWARD,
        /**
         * Redirect routing type.
         */
        REDIRECT
    }
}
