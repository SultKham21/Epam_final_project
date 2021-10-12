package by.epam.pizzashop.controller.command;

import by.epam.pizzashop.controller.command.impl.*;
import by.epam.pizzashop.controller.command.impl.admin.*;
import by.epam.pizzashop.controller.command.impl.customer.SendingOrderCommand;
import by.epam.pizzashop.controller.command.impl.customer.*;
import by.epam.pizzashop.controller.command.impl.manager.*;

import java.util.EnumMap;

/**
 * The type Command provider.
 */
public class CommandProvider {
    private static CommandProvider instance = new CommandProvider();

    private final EnumMap<CommandType, Command> commands;

    private CommandProvider() {
        commands = new EnumMap<>(CommandType.class);
        commands.put(CommandType.LOGIN, new LoginCommand());
        commands.put(CommandType.REGISTRATION_PAGE, new GoToRegistrationPageCommand());
        commands.put(CommandType.VERIFICATION_CUSTOMER_PAGE, new GoToVerificationCustomerPageCommand());
        commands.put(CommandType.REGISTRATION, new RegistrationCommand());
        commands.put(CommandType.LOGIN_PAGE, new GoToLoginPageCommand());
        commands.put(CommandType.VERIFICATION_CUSTOMER, new VerificationCustomerCommand());
        commands.put(CommandType.ALL_MANAGERS, new AllManagersCommand());
        commands.put(CommandType.MAIN_ADMIN, new GoToMainAdminPageCommand());
        commands.put(CommandType.LOGOUT, new LogoutCommand());
        commands.put(CommandType.VERIFICATION_MANAGER, new VerificationManagerCommand());
        commands.put(CommandType.INACTIVATION_MANAGER, new InactivationManagerCommand());
        commands.put(CommandType.INACTIVE_MANAGERS_PAGE, new GoToInactiveManagersPageCommand());
        commands.put(CommandType.ACTIVATION_MANAGER, new ActivationManagerCommand());
        commands.put(CommandType.ALL_PHARMACIES, new AllRestaurantsCommand());
        commands.put(CommandType.ADDITION_RESTAURANT, new AdditionRestaurantCommand());
        commands.put(CommandType.UPDATING_MANAGER_LOGIN_PAGE, new GoToManagerLoginPageCommand());
        commands.put(CommandType.UPDATING_MANAGER_FIRST_NAME_PAGE, new GoToUpdatingManagerFirstNamePageCommand());
        commands.put(CommandType.UPDATING_MANAGER_LOGIN, new UpdatingManagerLoginCommand());
        commands.put(CommandType.UPDATING_MANAGER_FIRST_NAME, new UpdatingManagerFirstNameCommand());
        commands.put(CommandType.UPDATING_MANAGER_LAST_NAME_PAGE, new GoToUpdatingManagerLastNamePageCommand());
        commands.put(CommandType.UPDATING_MANAGER_LAST_NAME, new UpdatingManagerLastNameCommand());
        commands.put(CommandType.UPDATING_MANAGER_EMAIL, new UpdatingManagerEmailCommand());
        commands.put(CommandType.UPDATING_MANAGER_EMAIL_PAGE, new GoToUpdatingManagerEmailPageCommand());
        commands.put(CommandType.UPDATING_MANAGER_TELEPHONE_PAGE, new GoToUpdatingManagerTelephonePageCommand());
        commands.put(CommandType.UPDATING_MANAGER_TELEPHONE, new UpdatingManagerTelephoneCommand());
        commands.put(CommandType.CHANGE_LANGUAGE, new ChangeLanguageCommand());
        commands.put(CommandType.MAIN_CUSTOMER, new GoToCustomerMainPageCommand());
        commands.put(CommandType.MAIN_MANAGER, new GoToManagerMainPageCommand());
        commands.put(CommandType.ALL_PRODUCTS, new AllProductsPageCommand());
        commands.put(CommandType.ADDITION_PRODUCT, new AdditionProductCommand());
        commands.put(CommandType.ADDITION_PICTURE_PAGE, new GoToAdditionPicturePageCommand());
        commands.put(CommandType.SEE_PRODUCT, new SeeProductCommand());
        commands.put(CommandType.SEARCH_PRODUCTS_BY_NAME_PAGE, new GoToSearchProductsByNamePageCommand());
        commands.put(CommandType.SEARCH_PRODUCTS_BY_NAME, new SearchProductsByNameCommand());
        commands.put(CommandType.ABOUT_PRODUCT, new GoToAboutProductPageCommand());
        commands.put(CommandType.SEARCH_PHARMACIES_BY_CITY, new SearchPharmaciesByCityCommand());
        commands.put(CommandType.ADDITION_PRODUCT_TO_ORDER, new AdditionProductToOrderCommand());
        commands.put(CommandType.BASKET_PAGE, new GoToBasketPageCommand());
        commands.put(CommandType.UPDATING_PRODUCT_QUANTITY, new UpdatingProductQuantityCommand());
        commands.put(CommandType.CHOOSE_RESTAURANT, new GoToRestaurantForCustomerPageCommand());
        commands.put(CommandType.ORDER, new GoToOrderPageCommand());
        commands.put(CommandType.UPDATING_RESTAURANT_NUMBER_PAGE, new GoToUpdatingRestaurantNumberPageCommand());
        commands.put(CommandType.UPDATING_RESTAURANT_CITY_PAGE, new GoToUpdatingRestaurantCityPageCommand());
        commands.put(CommandType.UPDATING_RESTAURANT_STREET_PAGE, new GoToUpdatingRestaurantStreetPageCommand());
        commands.put(CommandType.UPDATING_RESTAURANT_HOUSE_PAGE, new GoToUpdatingRestaurantHousePageCommand());
        commands.put(CommandType.UPDATING_RESTAURANT_BLOCK_PAGE, new GoToUpdatingRestaurantBlockPageCommand());
        commands.put(CommandType.UPDATING_RESTAURANT_NUMBER, new UpdatingRestaurantNumberCommand());
        commands.put(CommandType.UPDATING_RESTAURANT_CITY, new UpdatingRestaurantCityCommand());
        commands.put(CommandType.UPDATING_RESTAURANT_STREET, new UpdatingRestaurantStreetCommand());
        commands.put(CommandType.UPDATING_RESTAURANT_HOUSE, new UpdatingRestaurantHouseCommand());
        commands.put(CommandType.UPDATING_RESTAURANT_BLOCK, new UpdatingRestaurantBlockCommand());
        commands.put(CommandType.SEND_ORDER, new SendingOrderCommand());
        commands.put(CommandType.UPDATING_PRODUCT_NAME_PAGE, new GoToUpdatingProductNamePageCommand());
        commands.put(CommandType.UPDATING_PRODUCT_DOSE_PAGE, new GoToUpdatingProductDosePageCommand());
        commands.put(CommandType.UPDATING_PRODUCT_GROUP_PAGE, new GoToUpdatingProductGroupPageCommand());
        commands.put(CommandType.UPDATING_PRODUCT_PRICE_PAGE, new GoToUpdatingProductPricePageCommand());
        commands.put(CommandType.UPDATING_PRODUCT_INSTRUCTION_PAGE, new GoToUpdatingProductInstructionPageCommand());
        commands.put(CommandType.UPDATING_PRODUCT_NAME, new UpdatingProductNameCommand());
        commands.put(CommandType.UPDATING_PRODUCT_DOSE, new UpdatingProductDoseCommand());
        commands.put(CommandType.UPDATING_PRODUCT_GROUP, new UpdatingProductGroupCommand());
        commands.put(CommandType.UPDATING_PRODUCT_PRICE, new UpdatingProductPriceCommand());
        commands.put(CommandType.UPDATING_PRODUCT_INSTRUCTION, new UpdatingProductInstructionCommand());
        commands.put(CommandType.ALL_PROCESSING_ORDERS, new AllProcessingOrdersForPharmacyCommand());
        commands.put(CommandType.BASKET_FOR_ORDER, new BasketForOrderCommand());
        commands.put(CommandType.UPDATING_ORDER_STATUS_TO_PREPARED, new UpdatingOrderStatusToPreparedCommand());
        commands.put(CommandType.UPDATING_ORDER_STATUS_TO_DELETED, new UpdatingOrderStatusToDeletedCommand());
        commands.put(CommandType.HOW_TO_DO_ORDER_PAGE, new GoToHowToDoOrderPageCommand());
        commands.put(CommandType.ABOUT_US_PAGE, new GoToAboutUsPageCommand());
        commands.put(CommandType.QUESTIONS_PAGE, new GoToQuestionsPageCommand());
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static CommandProvider getInstance() {
        return instance;
    }

    /**
     * Gets command.
     *
     * @param commandString the command string
     * @return the command
     */
    public Command getCommand(String commandString) {
        if (commandString.isEmpty()) {
            return commands.get(CommandType.DEFAULT);
        }
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = CommandType.DEFAULT;
        }
        return commands.get(commandType);
    }
}
